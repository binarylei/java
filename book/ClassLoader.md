# ClassLoader

> Java中的所有类，必须被装载到JVM中才能运行，这个装载工作是由JVM中的类装载器完成的，类装载器所做的工作实质是把类文件从硬盘读取到内存中。

## 一、JVM如何加载class文件

![JVM加载Class过程]()

JVM加载Class包括3个阶段：类加载，链接，初始化

1. 类加载

JVM通过类的全限定名（包命+类名）找到类的.class文件。然后把这个.class文件加载进来，这个过程需要通过ClassLoader来实现。ClassLoader包括Boostrap ClassLoader，Extendsion ClassLoader和System ClassLoader。

2. 链接：

当一个class文件被成功加载后，接下来就要做链接了。链接就是要把二进制的.class文件转换成可以被jvm执行的Class对象的过程。这个过程又分为：检验、准备、解析。

* 检验：就是检查class的结构是否正确，是否符合Java虚拟机的语义要求。

* 准备：给类的静态变量分配存储空间。

* 解析：将类中对另一个类或接口的符号引号转化成全限定名引用，将对他们的方法、字段的符号引用转化成直接引用。

3. 初始化：对静态变量，静态代码块执行初始化工作，这就是我们平时理解的对静态变量赋值。

至此，一个类才加载完成，可以调用类的类变量了（静态变量）和对类进行实例化了。

### 类装载方式

1. 隐式加载：不通过代码调用 ClassLoader 来加载需要的类，而是通过 JVM 来自动加载需要的类到的方式。eg: 当我们在继承或者引用某个类时，JVM 在解析当前这个类时发现引用的类不在内存中，那么就会自动将这些类加载到内存中。

2. 显式加载：代码中调用 ClassLoader 类加载这个类的方式。eg: 调用 `this.getClass().getClassLoader().loadClass()` 或 `Class.forName("name")`

## 二、java类装载器

* **Bootstrap ClassLoader：** 称为启动类加载器，是Java类加载层次中最顶层的类加载器，**负责加载JDK中的核心类库，并调用Launcher初始化ExtClassLoader和AppClassLoader**，如：rt.jar、resources.jar、charsets.jar等，可通过 `System.getProperty("sun.boot.class.path")` 这个系统属性所得知的。
    
* **Extention ClassLoader：** 称为扩展类加载器，**负责加载Java的扩展类库**，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar。可通过 `System.getProperty("sun.boot.class.path")` 这个系统属性所得知的。还可以加载-D java.ext.dirs选项指定的目录。 

* **Appclass ClassLoader：** 称为系统类加载器(SystemAppClass)，负责加载应用程序classpath目录下的所有jar和class文件。可通过 `System.getProperty("java.class.path")` 这个系统属性所得知的。

![ClassLoader的体系架构](http://hi.csdn.net/attachment/201202/25/0_13301699801ybH.gif)

为了更好的理解，我们可以查看源码。 

看 **sun.misc.Launcher** ,它是一个java虚拟机的入口应用。

```java
public class Launcher {
    private static Launcher launcher = new Launcher();
    private static String bootClassPath = System.getProperty("sun.boot.class.path");

    public static Launcher getLauncher() {
        return launcher;
    }

    private ClassLoader loader;//AppClassLoader

    public Launcher() {
        // Create the extension class loader
        ClassLoader extcl;
        try {
            extcl = ExtClassLoader.getExtClassLoader();//ExtClassLoader无父加载类
        } catch (IOException e) {
            throw new InternalError("Could not create extension class loader", e);
        }

        // Now create the class loader to use to launch the application
        try {
            loader = AppClassLoader.getAppClassLoader(extcl);//AppClassLoader父加载类为ExtClassLoader
        } catch (IOException e) {
            throw new InternalError("Could not create application class loader", e);
        }

        //设置AppClassLoader为线程上下文类加载器，这个文章后面部分讲解
        Thread.currentThread().setContextClassLoader(loader);
    }

    /**
     * Returns the class loader used to launch the main application.
     */
    public ClassLoader getClassLoader() {
        return loader;
    }
    
    /**
     * The class loader used for loading installed extensions.
     */
    static class ExtClassLoader extends URLClassLoader {}

    /**
     * The class loader used for loading from java.class.path.
     * runs in a restricted security context.
     */
    static class AppClassLoader extends URLClassLoader {}
}
```

源码有精简，我们可以得到相关的信息。
 
1. Launcher初始化了ExtClassLoader和AppClassLoader。 

2. Launcher中并没有看见BootstrapClassLoader，但通过System.getProperty("sun.boot.class.path")得到了字符串bootClassPath,这个应该就是BootstrapClassLoader加载的jar包路径。

我们可以先代码测试一下sun.boot.class.path是什么内容。

    System.out.println(System.getProperty("sun.boot.class.path"));

得到的结果是：

```
C:\Program Files\Java\jre1.8.0_91\lib\resources.jar;
C:\Program Files\Java\jre1.8.0_91\lib\rt.jar;
C:\Program Files\Java\jre1.8.0_91\lib\sunrsasign.jar;
C:\Program Files\Java\jre1.8.0_91\lib\jsse.jar;
C:\Program Files\Java\jre1.8.0_91\lib\jce.jar;
C:\Program Files\Java\jre1.8.0_91\lib\charsets.jar;
C:\Program Files\Java\jre1.8.0_91\lib\jfr.jar;
C:\Program Files\Java\jre1.8.0_91\classes
```

可以看到，这些全是JRE目录下的jar包或者是class文件。

**sun.misc.Launcher.ExtClassLoader源码**

```java
/**
 * ExtClassLoader加载扩展包System.getProperty("java.ext.dirs")
 */
static class ExtClassLoader extends URLClassLoader {

    /**
     * create an ExtClassLoader. The ExtClassLoader is created
     * within a context that limits which files it can read
     */
    public static ExtClassLoader getExtClassLoader() throws IOException
    {
        final File[] dirs = getExtDirs();

        try {
            // Prior implementations of this doPrivileged() block supplied
            // aa synthesized ACC via a call to the private method
            // ExtClassLoader.getContext().
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<ExtClassLoader>() {
                    public ExtClassLoader run() throws IOException {
                        int len = dirs.length;
                        for (int i = 0; i < len; i++) {
                            MetaIndex.registerDirectory(dirs[i]);
                        }
                        return new ExtClassLoader(dirs);
                    }
                });
        } catch (java.security.PrivilegedActionException e) {
            throw (IOException) e.getException();
        }
    }

    private static File[] getExtDirs() {
        String s = System.getProperty("java.ext.dirs");
        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }
        return dirs;
    }
}
```

我们先前的内容有说过，可以指定-D java.ext.dirs参数来添加和改变ExtClassLoader的加载路径。这里我们通过可以编写测试代码。

    System.out.println(System.getProperty("java.ext.dirs"));

结果如下：

    C:\Program Files\Java\jre1.8.0_91\lib\ext;C:\Windows\Sun\Java\lib\ext

**sun.misc.Launcher.AppClassLoader源码**

```java
/**
 * The class loader used for loading from java.class.path.
 * runs in a restricted security context.
 */
static class AppClassLoader extends URLClassLoader {

    public static ClassLoader getAppClassLoader(final ClassLoader extcl)
        throws IOException
    {
        final String s = System.getProperty("java.class.path");
        final File[] path = (s == null) ? new File[0] : getClassPath(s);


        return AccessController.doPrivileged(
            new PrivilegedAction<AppClassLoader>() {
                public AppClassLoader run() {
                URL[] urls =
                    (s == null) ? new URL[0] : pathToURLs(path);
                return new AppClassLoader(urls, extcl);
            }
        });
    }
}
```
可以看到AppClassLoader加载的就是java.class.path下的路径。我们同样打印它的值。

    System.out.println(System.getProperty("java.class.path"));

结果：

    D:\workspace\ClassLoaderDemo\bin

这个路径其实就是当前java工程目录bin，里面存放的是编译生成的class文件。

## 二、ClassLoader加载类的原理

ClassLoader使用的是 **双亲委托模型** 来搜索类的，每个ClassLoader实例都有一个父类加载器的引用（不是继承的关系，是一个包含的关系），虚拟机内置的类加载器（Bootstrap ClassLoader）本身没有父类加载器，但可以用作其它ClassLoader实例的的父类加载器。当一个ClassLoader实例需要加载某个类时，它会试图亲自搜索某个类之前，先把这个任务委托给它的父类加载器，这个过程是由上至下依次检查的，首先由最顶层的类加载器Bootstrap ClassLoader试图加载，如果没加载到，则把任务转交给Extension ClassLoader试图加载，如果也没加载到，则转交给App ClassLoader 进行加载，如果它也没有加载得到的话，则返回给委托的发起者，由它到指定的文件系统或网络等URL中加载该类。如果它们都没有加载到这个类时，则抛出ClassNotFoundException异常。否则将这个找到的类生成一个类的定义，并将它加载到内存当中，最后返回这个类在内存中的Class实例对象。

![ClassLoader双亲委托加载规则](http://hi.csdn.net/attachment/201202/25/0_1330186866b93o.gif)

**java.lang.ClassLoader源码**

```java
public abstract class ClassLoader {
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            //第一步：检测是否已经加载
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        //第二步：父加载器不为空则调用父加载器的loadClass
                        c = parent.loadClass(name, false);
                    } else {
                        //第三步：父加载器为空则调用Bootstrap Classloader
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    //第四步：父加载器没有找到，则调用findclass
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                //调用resolveClass()
                resolveClass(c);
            }
            return c;
        }
    }
}
```

### 一个经典的实例说明

我们看看下面的代码：

```java
package java.lang;  
  
public class String {  
    public static void main(String[] args){  
          
    }
}  
```

大家发现什么不同了吗？对了，我们写了一个与JDK中String一模一样的类，连包java.lang都一样，唯一不同的是我们自定义的String类有一个main函数。我们来运行一下：

     java.lang.NoSuchMethodError: main
     Exception in thread "main"
                     
这是为什么? 我们的String类不是明明有main方法吗？
 
其实联系我们上面讲到的双亲委托模型，我们就能解释这个问题了。

1. 运行这段代码，JVM会首先创建一个自定义类加载器，不妨叫做AppClassLoader，并把这个加载器链接到委托链中：AppClassLoader -> ExtClassLoader -> BootstrapLoader。

2. 然后AppClassLoader会将加载java.lang.String的请求委托给ExtClassLoader，而 ExtClassLoader 又会委托给最后的启动类加载器BootstrapLoader。

3. 启动类加载器BootstrapLoader只能加载JAVA_HOME\jre\lib中的class类(即J2SE API)，问题是标准API中确实有一个java.lang.String(注意，这个类和我们自定义的类是完全两个类)。BootstrapLoader以为找到了这个类，毫不犹豫的加载了j2se api中的java.lang.String。

4. 最后出现上面的加载错误(注意不是异常，是错误，JVM退出)，因为API中的String类是没有main方法的。

## 四、自定义ClassLsoader

**既然JVM已经提供了默认的类加载器，为什么还要定义自已的类加载器呢？**

因为Java中提供的默认ClassLoader，只加载指定目录下的jar和class，如果我们想加载其它位置的类或jar时，比如：我要加载网络上的一个class文件，通过动态加载到内存之后，要调用这个类中的方法实现我的业务逻辑。在这样的情况下，默认的ClassLoader就不能满足我们的需求了，所以需要定义自己的ClassLoader。

**定义自已的类加载器分为两步：**

1. 继承java.lang.ClassLoader

2. 重写父类的findClass方法

我们编写自定义DiskClassLoader的代码。

```java
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DiskClassLoader extends ClassLoader {

    private String mLibPath;

    public DiskClassLoader(String path) {
        mLibPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);

        File file = new File(mLibPath,fileName);

        try {
            FileInputStream is = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();

            return defineClass(name,data,0,data.length);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){ 
            return name+".class";
        }else{
            return name.substring(index)+"class";
        }
    }
}
```

测试 我们写编写一个测试用的类文件，Test.java

```java
package com.frank.test;

public class Test {

    public void say(){
        System.out.println("Say Hello");
    }

}
```

```java
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    public static void main(String[] args) {
        //创建自定义classloader对象。
        DiskClassLoader diskLoader = new DiskClassLoader("D:\\lib");
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.frank.test.Test");

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException 
                        | NoSuchMethodException
                        | SecurityException | 
                        IllegalArgumentException | 
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
```

**参考：**

1. 【深入分析Java Web技术内幕-许令波】-第6章(深入分析ClassLoader工作机制)-158

2. [深入分析Java ClassLoader原理](http://blog.csdn.net/xyang81/article/details/7292380)

3. [一看你就懂，超详细java中的ClassLoader详解](http://blog.csdn.net/briblue/article/details/54973413)

