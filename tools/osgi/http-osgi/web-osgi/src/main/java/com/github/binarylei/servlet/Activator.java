package com.github.binarylei.servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;

import javax.servlet.Servlet;
import java.util.Dictionary;
import java.util.Hashtable;

public class Activator implements BundleActivator {

	private HttpService http = null;
	private ServiceRegistration<Servlet> serviceRegistration;
	
	public void start(BundleContext context) throws Exception {

		ServiceReference<HttpService> ref = context.getServiceReference(HttpService.class);
		if(ref == null) {
			System.out.println("http service is null");
		} else {
			http = context.getService(ref);
			
			HttpContext httpContext = http.createDefaultHttpContext();
			
			//注册servlet
			http.registerServlet("/home", new HomeServlet(), null, httpContext);
			
			//注册静态资源
			http.registerResources("/static", "web", httpContext);
		}
		
		//通过发布服务的方式，注册servlet
		Dictionary<String, String> properties = new Hashtable<>();
		properties.put("alias", "/home2");
		serviceRegistration = context.registerService(Servlet.class, new HomeServlet(), properties);
	}

	public void stop(BundleContext context) throws Exception {
		if(http != null) {
			http.unregister("/home");
			http.unregister("/static");
		}
		serviceRegistration.unregister();
	}
}
