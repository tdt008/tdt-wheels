package com.tdt.boot.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * @description: 索引
 * @date: 2022/1/5 14:43
 * @author: renchuangege
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsIndex {
    /**
     * 索引名称
     */
    String indexName() default "";
}
