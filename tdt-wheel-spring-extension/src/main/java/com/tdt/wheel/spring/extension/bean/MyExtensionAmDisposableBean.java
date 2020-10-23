package com.tdt.wheel.spring.extension.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * description:
 *      这个扩展点也只有一个方法：destroy()，其触发时机为当此对象销毁时，会自动执行这个方法。
 *      比如说运行applicationContext.registerShutdownHook时，就会触发这个方法。
 *
 * @date: 2020年10月23日 20:09
 * @author: qinrenchuan
 */
@Component
public class MyExtensionAmDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("[MyExtensionAmDisposableBean#destroy]");
    }
}
