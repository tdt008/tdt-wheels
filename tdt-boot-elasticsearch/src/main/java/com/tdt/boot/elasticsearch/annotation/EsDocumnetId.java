package com.tdt.boot.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * @description: 索引文档id注解
 * @date: 2022/1/5 14:43
 * @author: renchuangege
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsDocumnetId {
}
