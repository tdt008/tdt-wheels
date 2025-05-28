package com.tdt.boot.elasticsearch.manager;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.annotation.EsDocumnetId;
import com.tdt.boot.elasticsearch.annotation.EsIndex;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CloseIndexRequest;
import org.elasticsearch.client.indices.CloseIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: document
 * @date: 2021/12/23 11:04
 * @author: renchuangege
 */
@Slf4j
public class DocumentManager <E> {

    @Autowired
    protected RestHighLevelClient restHighLevelClient;
    /**
     * 索引名称
     */
    private String indexName = getIndexName();
    /**
     * 实体类型class
     */
    private Class<E> entityClazz = getEntityClazz();

    /**
     * 插入
     * @param documentId
     * @param documentMap
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/28 10:01
     */
    public boolean addDocument(String documentId, Map<String, Object> documentMap) {
        try {
            IndexRequest indexRequest = new IndexRequest(getIndexName());
            indexRequest.source(documentMap).id(documentId);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("add document resp:{}", JSONObject.toJSONString(indexResponse));
            DocWriteResponse.Result result = indexResponse.getResult();
            log.info("result op:{}, lowercase:{}", result.getOp(), result.getLowercase());
            if (result.getOp() == DocWriteResponse.Result.CREATED.getOp()) {
                return true;
            }
            return false;
        } catch (IOException ioe) {
            log.error("add document error， indexName:{}, data:{}", getIndexName(), documentMap, ioe);
            throw new RuntimeException("add " + getIndexName() + " document  error");
        }
    }

    /**
     * 插入
     * @param e
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/27 17:35
     */
    public boolean addDocument(E e) {
        try {
            String documentId = getEsDocumentId(e);
            IndexRequest indexRequest = new IndexRequest(getIndexName());
            indexRequest.source(JSONObject.toJSONString(e), XContentType.JSON);
            if (!StringUtils.isEmpty(documentId)) {
                indexRequest.id(documentId);
            }
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("add document resp:{}", JSONObject.toJSONString(indexResponse));
            DocWriteResponse.Result result = indexResponse.getResult();
            log.info("result op:{}, lowercase:{}", result.getOp(), result.getLowercase());
            if (result.getOp() == DocWriteResponse.Result.CREATED.getOp()) {
                return true;
            }
            return false;
        } catch (Exception ioe) {
            log.error("add document error， indexName:{}, data:{}", getIndexName(), JSONObject.toJSONString(e), ioe);
            throw new RuntimeException("add " + getIndexName() + " document  error");
        }
    }

    /**
     * 批量插入
     * @param documents
     * @return: int
     * @author: renchuangege
     * @date: 2021/12/28 18:31
     */
    public <E> int addDocuments(List<E> documents) {
        try {
            String indexName = getIndexName();
            BulkRequest bulkRequest = new BulkRequest();
            IndexRequest indexRequest;
            for (E e : documents) {
                indexRequest = new IndexRequest(indexName);
                // id
                Field[] declaredFields = e.getClass().getDeclaredFields();
                for(Field f : declaredFields){
                    f.setAccessible(true);
                    if (f.getName().equals("id")) {
                        indexRequest.id(f.get(e).toString());
                        break;
                    }
                }
                indexRequest.source(JSONObject.toJSONString(e), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }

            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("add document bulk resp:{}", JSONObject.toJSONString(bulkResponse));
            BulkItemResponse[] items = bulkResponse.getItems();
            int i = 0;
            for (BulkItemResponse bulkItemResponse : items) {
                if (bulkItemResponse.status().getStatus() == RestStatus.CREATED.getStatus()) {
                    i++;
                }
            }
            return i;
        } catch (Exception ioe) {
            log.error("add document bulk error， indexName:{}, data:{}",
                    indexName, JSONObject.toJSONString(documents), ioe);
            throw new RuntimeException("add " + indexName + " document  error");
        }
    }

    /**
     * 根据id查询
     * @param documentId
     * @author: renchuangege
     * @date: 2021/12/27 17:35
     */
    public E getById(String documentId) {
        try {
            GetRequest getRequest = new GetRequest(indexName, documentId);
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            log.info("get document resp:{}", JSONObject.toJSONString(getResponse));
            if (getResponse.isExists()) {
                return JSONObject.parseObject(getResponse.getSourceAsString(), getEntityClazz());
            }
            return null;
        } catch (IOException ioe) {
            log.error("get document error， indexName:{}, id:{}", getIndexName(), documentId, ioe);
            throw new RuntimeException("get " + getIndexName() + " document  error");
        }
    }

    /**
     * 查询
     * @param searchSourceBuilder
     * @return: java.util.List<E>
     * @author: renchuangege
     * @date: 2022/1/6 18:12
     */
    public List<E> list(SearchSourceBuilder searchSourceBuilder) {
       try {
           SearchRequest searchRequest = new SearchRequest(getIndexName());
           searchRequest.source(searchSourceBuilder);
           SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
           log.info("list document resp:{}", JSONObject.toJSONString(searchResponse));

           if (searchResponse == null || searchResponse.getHits() == null || searchResponse.getHits().getHits() == null) {
               return new ArrayList<>();
           }

           List<E> result = new ArrayList<>(searchResponse.getHits().getHits().length);
           for (SearchHit searchHit : searchResponse.getHits().getHits()) {
               result.add(JSONObject.parseObject(searchHit.getSourceAsString(), getEntityClazz()));
           }
           return result;
       } catch (IOException ioe) {
           log.error("list document error， indexName:{}", getIndexName(), ioe);
           throw new RuntimeException("list " + getIndexName() + " document  error");
       }
    }

    /**
     * delete
     * @param documentId
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/28 14:40
     */
    public boolean deleteById(String documentId) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(getIndexName(), documentId);
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("delete document resp:{}", JSONObject.toJSONString(deleteResponse));
            if (deleteResponse.getResult().getOp() == DocWriteResponse.Result.DELETED.getOp()) {
                return true;
            }
            return false;
        } catch (IOException ioe) {
            log.error("get document error， indexName:{}, id:{}", getIndexName(), documentId, ioe);
            throw new RuntimeException("get " + indexName + " document  error");
        }
    }


    /**
     * 根据对象中的注解获取ID的字段值
     * @param obj
     * @return
     */
    protected String getEsDocumentId(E obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field f : fields){
            f.setAccessible(true);
            EsDocumnetId esDocumnetId = f.getAnnotation(EsDocumnetId.class);
            if(esDocumnetId != null){
                Object value = f.get(obj);
                if(value != null) {
                    return value.toString();
                }
            }
        }
        throw new IllegalArgumentException(getIndexName() + "没有配置文档id");
    }


    /**
     * 判断索引是否存在
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/23 11:04
     */
    public boolean exists() {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(getIndexName());
            return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException ioe) {
            log.error("exists index error， indexName:{}", getIndexName(), ioe);
            throw new RuntimeException("exists " + getIndexName() + " error");
        }
    }

    /**
     * 删除索引
     * @author: renchuangege
     * @date: 2021/12/21 11:06
     */
    public boolean deleteIndex() {
        try {
            if (!exists()) {
                throw new RuntimeException(getIndexName() + " not exists");
            }
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(getIndexName());
            AcknowledgedResponse acknowledgedResponse =
                    restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            log.info("deleteIndx result:{}", JSONObject.toJSONString(acknowledgedResponse));
            return acknowledgedResponse.isAcknowledged();
        } catch (IOException ioe) {
            log.error("deleteIndex error， indexName:{}", getIndexName(), ioe);
            throw new RuntimeException("delete " + getIndexName() + " index error");
        }
    }

    /**
     * 打开索引-索引打开以后就能正常读写
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/22 19:34
     */
    public boolean openIndex() {
        try {
            if (!exists()) {
                throw new RuntimeException(getIndexName() + " not exists");
            }
            OpenIndexRequest openIndexRequest = new OpenIndexRequest(getIndexName());
            OpenIndexResponse openIndexResponse = restHighLevelClient.indices().open(openIndexRequest, RequestOptions.DEFAULT);
            log.info("openIndx result:{}", JSONObject.toJSONString(openIndexResponse));
            return openIndexResponse.isAcknowledged();
        } catch (Exception ioe) {
            log.error("openIndex error， indexName:{}", getIndexName(), ioe);
            throw new RuntimeException("open " + getIndexName() + " index error");
        }
    }

    /**
     * 关闭索引-索引关闭以后不能进行读写，只能等打开索引以后才能正常读写
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/22 19:34
     */
    public boolean closeIndex() {
        try {
            if (!exists()) {
                throw new RuntimeException(getIndexName() + " not exists");
            }
            CloseIndexRequest CloseIndexRequest = new CloseIndexRequest(indexName);
            CloseIndexResponse colosexIndexResponse = restHighLevelClient.indices().close(CloseIndexRequest, RequestOptions.DEFAULT);
            log.info("closeIndex result:{}", JSONObject.toJSONString(colosexIndexResponse));
            return colosexIndexResponse.isAcknowledged();
        } catch (Exception ioe) {
            log.error("closeIndex error， indexName:{}", indexName, ioe);
            throw new RuntimeException("close " + indexName + " index error");
        }
    }


    /**
     * 查询索引名称
     * @return: java.lang.String
     * @author: renchuangege
     * @date: 2022/1/5 15:02
     */
    protected String getIndexName() {
        if (indexName != null && indexName.length() > 0) {
            return indexName;
        }
        synchronized (this) {
            Type genericSuperType = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperType;
            Class<? extends Type> entityClass = (Class<? extends Type>) parameterizedType.getActualTypeArguments()[0];
            EsIndex entityClassAnnotation = entityClass.getAnnotation(EsIndex.class);
            if (entityClassAnnotation == null) {
                throw new IllegalArgumentException(entityClass.getName() + "没有配置es索引名称");
            }
            indexName = entityClassAnnotation.indexName();
        }
        return indexName;
    }

    /**
     * 查询索引Clazz
     * @return: java.lang.Class<E>
     * @author: renchuangege
     * @date: 2022/1/5 15:16
     */
    protected Class<E> getEntityClazz() {
        if (entityClazz != null) {
            return entityClazz;
        }

        synchronized (this) {
            ResolvableType resolvableType = ResolvableType.forClass(this.getClass());
            entityClazz = (Class) resolvableType.as(DocumentManager.class).getGeneric(new int[]{0}).getType();

            /*Type genericSuperType = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperType;
            entityClass = (Class<? extends Type>) parameterizedType.getActualTypeArguments()[0];*/
        }
        return entityClazz;
    }
}
