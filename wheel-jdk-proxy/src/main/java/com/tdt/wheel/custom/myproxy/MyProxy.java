package com.tdt.wheel.custom.myproxy;

import org.springframework.util.FileCopyUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * description: MyProxy
 *
 * @date: 2020年11月12日 18:09
 * @author: qinrenchuan
 */
public class MyProxy {
    private static final String rt = "\r";

    public static Object newProxyInstance(
            MyClassLoader loader,
            Class<?> interfaces,
            MyInvocationHandler h) throws IllegalArgumentException {
        if (h == null) {
            throw new NullPointerException();
        }

        // 根据接口构造代理类： $Proxy0
        Method[] methods = interfaces.getMethods();
        StringBuffer proxyClassBuf = new StringBuffer();
        proxyClassBuf.append("package ")
                .append(loader.getProxyClassPackage()).append(";").append(rt)
                .append("import java.lang.reflect.Method;").append(rt)
                .append("import ").append(MyInvocationHandler.class.getName()).append(";").append(rt)
                .append("public class $MyProxy0 implements ").append(interfaces.getName()).append("{").append(rt)
                .append("MyInvocationHandler h;").append(rt)
                .append("public $MyProxy0(MyInvocationHandler h){").append(rt).append("this.h = h;}").append(rt)
                .append(getMethodString(methods, interfaces))
                .append("}");

        // 写入java文件 并进行编译
        String filename = loader.getDir() + File.separator + "$MyProxy0.java";
        File myProxyFile = new File(filename);
        try {
            compile(proxyClassBuf, myProxyFile);

            // 利用自定义的ClassLoader加载
            Class $myProxy0 = loader.findClass("$MyProxy0");
            // $MyProxy0初始化
            Constructor constructor = $myProxy0.getConstructor(MyInvocationHandler.class);
            Object o = constructor.newInstance(h);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void compile(StringBuffer proxyClassBuf, File myProxyFile) throws IOException {
        FileCopyUtils.copy(proxyClassBuf.toString().getBytes(), myProxyFile);

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

        Iterable javaFileObjects = standardFileManager.getJavaFileObjects(myProxyFile);

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);

        task.call();

        standardFileManager.close();
    }

    private static String getMethodString(Method[] methods, Class interfaces) {
        StringBuffer methodBuff = new StringBuffer();

        for (Method method : methods) {
            methodBuff.append("public void ").append(method.getName())
                    .append("()").append(" throws Exception{")
                    .append("Method method1 = ").append(interfaces.getName())
                    .append(".class.getMethod(\"").append(method.getName())
                    .append("\", new Class[]{});")
                    .append("this.h.invoke(this,method1,null);}").append(rt);
        }
        return methodBuff.toString();
    }
    
    
}
