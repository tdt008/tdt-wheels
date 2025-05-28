package com.tdt.boot.version.config.version;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @description: TODO
 * @date: 2022/2/18 10:25
 * @author: renchuangege
 */
public class ApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return createCondition(method.getClass());
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return createCondition(handlerType);
    }

    private static RequestCondition<?> createCondition(Class<?> clazz) {
        RequestMapping requestMappingAnnotation = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
        if (requestMappingAnnotation == null) {
            return null;
        }

        StringBuilder mappingUrlBuilder = new StringBuilder();
        if (requestMappingAnnotation.value().length > 0) {
            mappingUrlBuilder.append(requestMappingAnnotation.value()[0]);
        }

        String mappingUrl = mappingUrlBuilder.toString();
        if (!mappingUrl.contains("{version}")) {
            return null;
        }

        ApiVersion apiVersionAnnotation = AnnotationUtils.findAnnotation(clazz, ApiVersion.class);
        if (apiVersionAnnotation == null) {
            return new ApiVersionCondition(1);
        }
        return new ApiVersionCondition(apiVersionAnnotation.value());
    }
}
