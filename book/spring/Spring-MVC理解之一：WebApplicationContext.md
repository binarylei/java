# Spring-MVC理解之一：应用上下文WebApplicationContext

### 一、先说ServletContext

　　javaee标准规定了，servlet容器需要在应用项目启动时，给应用项目初始化一个ServletContext作为公共环境容器存放公共信息。ServletContext中的信息都是由容器提供的。

举例：

通过自定义contextListener获取web.xml中配置的参数:

1. 容器启动时，找到配置文件中的context-param作为键值对放到ServletContext中

2. 然后找到listener，容器调用它的contextInitialized(ServletContextEvent event)方法，执行其中的操作

例如：在web.xml中配置

```xml
<context-param>
   <param-name>key</param-name>
   <param-value>value123</param-value>
</context-param>
<listener> 
   <listener-class>com.brolanda.contextlistener.listener.ContextListenerTest</listener-class>
</listener>
```

配置好之后，在该类中获取对应的参数信息

```java
package com.binarylei.contextlistener.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListenerTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("*************init ContextListener*************");
        ServletContext servletContext = event.getServletContext();
        System.out.println("key: " + servletContext.getInitParameter("key"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("*************destroy ContextListener*************");
    }
}
```

执行流程：web.xml在<context-param></context-param>标签中声明应用范围内的初始化参数

1. 启动一个WEB项目的时候,容器(如:Tomcat)会去读它的配置文件web.xml.读两个节点: <listener></listener> 和 <context-param></context-param>。

2. 紧接着，容器创建一个ServletContext(上下文)。在该应用内全局共享。

3. 容器将<context-param></context-param>转化为键值对，并交给ServletContext。

4. 容器创建<listener></listener>中的类实例，即创建监听。该监听器必须实现自ServletContextListener接口。

5. 在监听中会有contextInitialized(ServletContextEvent event)初始化方法

        ServletContext servletContext = event.getServletContext();
        "context-param的值" = ServletContext.getInitParameter("context-param的键");
            
6. 得到这个context-param的值之后,你就可以做一些操作了。注意：这个时候你的WEB项目还没有完全启动完成，这个动作会比所有的Servlet都要早。换句话说，这个时候，你对<context-param>中的键值做的操作，将在你的WEB项目完全启动之前被执行。

### 二、spring上下文容器配置

spring为我们提供了实现ServletContextListener接口的上下文初始化监听器： `org.springframework.web.context.ContextLoaderListener`

```xml
<context-param>
    <!--参数名称必须为contextConfigLocation-->
    <param-name>contextConfigLocation</param-name>
    <!--默认读取/WEB-INF/下的applicationContext.xml文件-->
    <param-value>/WEB-INF/applicationContext.xml,/WEB-INF/action-servlet.xml,/WEB-INF/jason-servlet.xml</param-value>
</context-param>
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

### 三、spring上下文容器配置后，初始化了什么？

既然，ServletContext是由Servlet容器初始化的，那spring的ContextLoaderListener又做了什么初始化呢？

1. servlet容器启动，为应用创建一个“全局上下文环境”：ServletContext。

2. 容器调用web.xml中配置的contextLoaderListener，初始化WebApplicationContext上下文环境（即IOC容器），加载context-param指定的配置文件信息到IOC容器中。WebApplicationContext在ServletContext中以键值对的形式保存

3. 容器初始化web.xml中配置的servlet，为其初始化自己的上下文信息servletContext，并加载其设置的配置信息到该上下文中。将WebApplicationContext设置为它的父容器。

4. 此后的所有servlet的初始化都按照3步中方式创建，初始化自己的上下文环境，将WebApplicationContext设置为自己的父上下文环境。

[ServletContext容器](http://images.cnitblog.com/blog/698747/201502/011528042224276.png)

对于作用范围而言，在DispatcherServlet中可以引用由ContextLoaderListener所创建的ApplicationContext中的内容，而反过来不行。

当 Spring 在执行 ApplicationContext 的 getBean 时，如果在自己 context 中找不到对应的 bean，则会在父 ApplicationContext  中去找。这也解释了为什么我们可以在 DispatcherServlet 中获取到由 ContextLoaderListener 对应的 ApplicationContext 中的 bean。

### 四、spring配置

既然知道了spring的启动流程，那么web容器初始化webApplicationContext时作为公共的上下文环境，只需要将service、dao等的配置信息在这里加载，而servlet自己的上下文环境信息不需要加载。故，在applicationContext.xml中将@Controller注释的组件排除在外，而在dispatcherServlet加载的配置文件中将@Controller注释的组件加载进来，方便dispatcherServlet进行控制和查找。故，配置如下：
 
applicationContext.mxl中：
```xml
<context:component-scan base-package="com.linkage.edumanage">
    <context:exclude-filter expression="org.springframework.stereotype.Controller"    type="annotation" /> 
</context:component-scan>
```

spring-mvc.xml中：
```xml
<context:component-scan base-package="com.brolanda.cloud"   use-default-filters="false"> 
    <context:include-filter expression="org.springframework.stereotype.Controller"    type="annotation" /> 
</context:component-scan>
 ```
