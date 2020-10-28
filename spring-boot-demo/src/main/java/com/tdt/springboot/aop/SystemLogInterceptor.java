package com.tdt.springboot.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * description: SystemLogInterceptor
 *
 * @date: 2020年10月28日 14:25
 */
public class SystemLogInterceptor implements MethodInterceptor, Serializable {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        String clazzName = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        System.out.println("======[" + clazzName + "#" + methodName + " method begin execute]======");

        Arrays.stream(methodInvocation.getArguments()).forEach(argument -> {
            System.out.println("======[execute method argument：" + argument + "]======");
        });

        StopWatch st = new StopWatch("SystemLogInterceptor");
        st.start();
        Object result = methodInvocation.proceed();
        st.stop();
        System.out.println(st.prettyPrint());

        return result;
    }
}
