package com.tdt.wheel.myspringmvc.servlet;

import static sun.awt.image.PixelConverter.Argb.instance;

import com.tdt.wheel.myspringmvc.annotation.Controller;
import com.tdt.wheel.myspringmvc.annotation.Qualifier;
import com.tdt.wheel.myspringmvc.annotation.Repository;
import com.tdt.wheel.myspringmvc.annotation.RequestMapping;
import com.tdt.wheel.myspringmvc.annotation.Service;
import com.tdt.wheel.myspringmvc.controller.UserController;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: DispatcherServlet
 * @since 2020/05/17 12:24
 */
@WebServlet(name = "dispatcherServlet",
		urlPatterns = "/*", loadOnStartup = 1,
		initParams = {@WebInitParam(name = "base-package", value = "com.tdt.wheel.myspringmvc")})
public class DispatcherServlet extends HttpServlet {
	/** 扫描的基包 */
	private String basePackage = "";
	/** 基包下面所有的带包路径权限类名 */
	private List<String> packageNames = new ArrayList<String>();
	/** 注解实例化。 注解上的名称：实例化对象 */
	private Map<String, Object> instanceMap = new HashMap<String, Object>();
	/** 带包路径的全类名： 注解上的名称 */
	private Map<String, String> nameMap = new HashMap<String, String>();
	/** URL地址和方法映射关系。 springmvc就是方法调用链 */
	private Map<String, Method> urlMethodMap = new HashMap<String, Method>();
	/** method和全类名映射关系  主要是为了通过method找到该方法的对象利用反射 */
	private Map<Method, String> methodPackageMap = new HashMap<Method, String>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		basePackage = config.getInitParameter("base-package");

		try {
			// 1.扫描基包得到全部的带包路径的全类名
			scanBasePackage(basePackage);
			// 2.把带有@Controller，@Service,@Repository的类实例化放入Map中，key为注解上的名称
			instance(packageNames);
			// 3.spring ioc注入
			springIoc();
			// 4. 完成URL地址与方法的映射关系
			handlerUrlMethodMap();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	private void handlerUrlMethodMap() throws ClassNotFoundException {
		if (packageNames.size() < 1) {
			return;
		}

		for (String str : packageNames) {
			Class<?> c = Class.forName(str);
			if (c.isAnnotationPresent(Controller.class)) {
				Method[] methods = c.getMethods();
				String baseUrl = "";
				if (c.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping requestMapping = c.getAnnotation(RequestMapping.class);
					baseUrl += requestMapping.value();
				}

				for (Method method : methods) {
					if (method.isAnnotationPresent(RequestMapping.class)) {
						RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

						urlMethodMap.put(baseUrl + requestMapping.value(), method);
						methodPackageMap.put(method, str);

					}
				}
			}
		}
	}

	private void springIoc() throws IllegalAccessException {
		for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {

			Field[] fields = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Qualifier.class)) {
					String name = field.getAnnotation(Qualifier.class).value();
					field.setAccessible(true);
					field.set(entry.getValue(), instanceMap.get(name));
				}
			}
		}
	}

	private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		if (packageNames.size() < 1) {
			return;
		}

		for (String str : packageNames) {
			Class<?> c = Class.forName(str);

			if (c.isAnnotationPresent(Controller.class)) {
				Controller controller = c.getAnnotation(Controller.class);
				String controllerName = controller.value();

				instanceMap.put(controllerName, c.newInstance());
				nameMap.put(str, controllerName);
				System.out.println("controller: " + str + ", value : " + controller.value());
			} else if (c.isAnnotationPresent(Service.class)) {
				Service service = c.getAnnotation(Service.class);
				String serviceName = service.value();

				instanceMap.put(serviceName, c.newInstance());
				nameMap.put(str, serviceName);
				System.out.println("service: " + str + ", value : " + service.value());
			}  else if (c.isAnnotationPresent(Repository.class)) {
				Repository repository = c.getAnnotation(Repository.class);
				String repositoryName = repository.value();

				instanceMap.put(repositoryName, c.newInstance());
				nameMap.put(str, repositoryName);
				System.out.println("repository: " + str + ", value : " + repository.value());
			}

		}
	}


	private void scanBasePackage(String basePackage) {
		// 注意： 为了得到基包下面URL路径需要对basePackage做转换，将.转换为/
		URL url = this.getClass().getClassLoader().getResource(
				basePackage.replaceAll("\\.", "/"));
		File basePackageFile = new File(url.getPath());
		System.out.println("scan:" + basePackageFile);

		File[] files = basePackageFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				scanBasePackage(basePackage + "." + file.getName());
			} else if (file.isFile()) {
				// 类似这种:com.tdt.wheel.myspringmvc.service.impl.UserServiceImpl.class去掉class
				packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);
			}
		}
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = uri.replaceAll(contextPath, "");

		// 通过path找到Method
		Method method = urlMethodMap.get(path);
		if (method != null) {
			// 通过Method拿到controller对象， 准备反射执行
			String packageName = methodPackageMap.get(method);
			String controllerName = nameMap.get(packageName);

			UserController userController = (UserController) instanceMap.get(controllerName);
			try {
				method.setAccessible(true);
				method.invoke(userController);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
