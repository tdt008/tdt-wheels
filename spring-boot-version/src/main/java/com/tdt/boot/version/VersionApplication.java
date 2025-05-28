package com.tdt.boot.version;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: TODO
 * @date: 2022/2/18 10:18
 * @author: renchuangege
 */
@SpringBootApplication
@ComponentScan(nameGenerator = VersionApplication.UniqueNameGenerator.class)
public class VersionApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersionApplication.class, args);
    }

    public static class UniqueNameGenerator extends AnnotationBeanNameGenerator {

        @Override
        public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
            //全限定类名
            String beanName = definition.getBeanClassName();
            return beanName;
        }
    }
}
