# Spring-AOP

## 2.1 什么是 AOP 的技术

* 在软件业，AOP 为 Aspect Oriented Programming 的缩写，即，面向切面编程。

* AOP 由 AOP 联盟的组织提出的，制定了一套规范，Spring 将 AOP 思想引入到框架中，必须遵守 AOP 联盟的规范。

* AOP 通过预编译方式和运行期**动态代理**实现程序功能的统一维护的一种技术。

* AOP 可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性。
    
总结：AOP 采用横向抽取机制，取代了传统纵向继承体系重复性代码，可用于权限校验、日志记录、性能监控、事务控制等，在不修改源代码的前提下，对程序进行增强。

## 2.2 AOP 底层实现

Spring的AOP的底层用到两种代理机制：

* JDK的动态代理：针对实现了**有接口**的类产生代理

* Cglib的动态代理	：针对**没有接口**的类产生代理。应用的是底层的字节码增强的技术，生成当前类的子类对象

### 2.2.1 JDK的动态代理

实现对有接口的 UserDaoImpl 类实现动态代理：

```java
public interface UserDao {
    public void save();
    public void update();
}

public class UserDaoImpl implements UserDao{
    public void save() {
        System.out.println("save");
    }
    public void update() {
        System.out.println("update");
    }
}
```

编写代理类：

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyJDKProxyUtils {

    public static UserDao getProxy (final UserDao obj) {
        UserDao proxy = (UserDao) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("save ... start");
                }

                Object ret = method.invoke(obj, args);

                if ("update".equals(method.getName())) {
                    System.out.println("update ... over");
                }
                return ret;
            }
        });
        return proxy;
    }
}
```

测试：

```java
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        UserDao proxy = MyJDKProxyUtils.getProxy(userDao);
        proxy.save();
        proxy.update();
    }
}
```

执行结果，对原有方法进行了增强：

```
save ... start
save
update
update ... over
```

### 2.2.2 Cglib的动态代理	

`spring-core` 中集成了 cglib 包，实现对没有接口的 UserDaoImpl 类实现动态代理：

```java
public class UserDaoImpl implements UserDao{
    public void save() {
        System.out.println("save");
    }
    public void update() {
        System.out.println("update");
    }
}
```

编写代理类：

```java
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class MyCglibProxyUtils {

    public static UserDaoImpl getProxy (UserDaoImpl userDao) {
        Enhancer enhancer = new Enhancer();
        //1. 设置父类
        enhancer.setSuperclass(userDao.getClass());

        //2. 设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("save ... start");
                }

                if ("update".equals(method.getName())) {
                    System.out.println("update ... start");
                }
                Object ret = methodProxy.invokeSuper(obj, args);
                return ret;
            }
        });

        //3. 获取代理对象
        UserDaoImpl userDaoImpl = (UserDaoImpl) enhancer.create();
        return userDaoImpl;
    }
}
```

