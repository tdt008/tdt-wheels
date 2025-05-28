package com.tdt.springmybatis;

import com.tdt.springmybatis.entity.User;
import com.tdt.springmybatis.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mybatis.xml")
public class SpringMybatisTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private UserMapper userMapper;

    @Before
    public void printBeanInfo() {
        ListableBeanFactory listableBeanFactory = applicationContext;
        String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        System.out.println("\n--------------☆ bean name ☆-------------");
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

        System.out.println("\n-----------☆ bean class info ☆------------");
        System.out.println("UserMapper Class: " + userMapper.getClass());
        System.out.println("\n-------xxxx--------xxxx-------xxx--------\n");
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        User user = userMapper.getById(id);
        System.out.println(user);
    }
}
