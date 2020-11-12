package com.tdt.wheel.custom.myproxy;

import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * description: MyClassLoader
 *
 * @date: 2020年11月12日 18:01
 * @author: qinrenchuan
 */
public class MyClassLoader extends ClassLoader {

    private File dir;

    private String proxyClassPackage;

    public File getDir() {
        return dir;
    }

    public String getProxyClassPackage() {
        return proxyClassPackage;
    }

    public MyClassLoader(String dirPath, String proxyClassPackage) {
        this.dir = new File(dirPath);
        this.proxyClassPackage = proxyClassPackage;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (dir != null) {
            File classFile = new File(dir, name + ".java");

            if (classFile.exists()) {
                try {
                    // 生成class的二进制字符流
                    byte[] classBytes = FileCopyUtils.copyToByteArray(classFile);

                    return defineClass(proxyClassPackage + "." + name,
                            classBytes,
                            0,
                            classBytes.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 如果上述自定义的类没有加载到，走默认的加载方式
        return super.findClass(name);
    }
}
