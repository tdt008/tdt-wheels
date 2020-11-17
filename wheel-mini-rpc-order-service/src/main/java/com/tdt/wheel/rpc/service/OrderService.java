package com.tdt.wheel.order;

import com.tdt.wheel.rpc.service.IProductService;
import com.tdt.wheel.rpc.service.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * description: OrderService
 *
 * @date: 2020年11月17日 19:54
 * @author: qinrenchuan
 */
public class OrderService {
    public static void main(String[] args) {
        IProductService productService = (IProductService) rpc(IProductService.class);
        Product product = productService.getById(100);
        System.out.println(product);
    }

    public static Object rpc(final Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("127.0.0.1", 8888);

                        String apiClassName = clazz.getName();
                        String methodName = method.getName();
                        Class<?>[] parameterTypes = method.getParameterTypes();

                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(apiClassName);
                        objectOutputStream.writeUTF(methodName);
                        objectOutputStream.writeObject(parameterTypes);
                        objectOutputStream.writeObject(args);

                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Object o = objectInputStream.readObject();

                        objectInputStream.close();
                        objectOutputStream.close();
                        socket.close();

                        return o;
                    }
                });
    }
}
