# Maven系列（一）plugin

## maven-compiler-plugin

使用 `mvn compile` 命令，出现错误: 编码GBK的不可映射字符而不能编译。这是因为代码或注释中存在中文引起的，一般在ide中会自动处理编译时的字符集，就不会碰到这个错误。这个错误是在生成代码后，其中自动加上了中文注释，手动删除中文注释处理这个问题太麻烦。这个错误是在命令行执行编译命令才出现的，需要设置编译的字符集，设置方式是：

```xml
<plugin> 
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId> 
    <configuration> 
        <source>1.7</source> 
        <target>1.7</target> 
        <encoding>UTF-8</encoding> 
    </configuration> 
</plugin> 
```

其中：

* `encoding` 如果不设置的话会用本地操作系统的编码来编译文件

## maven-resources-plugin

```xml
<plugin> 
    <groupId>org.apache.maven.plugins</groupId> 
    <artifactId>maven-resources-plugin</artifactId> 
    <version>2.3</version> 
    <configuration> 
        <encoding>UTF-8</encoding> 
    </configuration> 
</plugin>
```

其中：

* `encoding` 设置资源文件的编码方式

## maven-dependency-plugin

拷贝依赖包 mvn dependency:copy-dependencies，默认会拷到项目的 target\dependency 目录，想要复制到自定义的目录比如target/lib目录下，需要在pom.xml文件中添加设置覆盖默认设置：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <configuration>
        <outputDirectory>${project.build.directory}/lib</outputDirectory>
        <excludeTransitive>false</excludeTransitive>
        <stripVersion>true</stripVersion>
    </configuration>
</plugin>
```

其中：

* `outputDirectory` ${project.build.directory}是maven变量，表示target目录。如果不写的话，将在根目录下创建 target\dependency 目录；
* `excludeTransitive` 表示是否不包含间接依赖的包；
* `stripVersion` 表示复制的jar文件去掉版本信息。

如果需要在其他过程，比如package中加入copy-dependencies，需要在该plugin标签中这样设置：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <outputDirectory>${project.build.directory}/lib</outputDirectory>
                <excludeTransitive>false</excludeTransitive>
                <stripVersion>true</stripVersion>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## maven-jar-plugin

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <mainClass>com.github.binarylei.network.netty.helloworld.Server</mainClass>
                <!-- 打包时 MANIFEST.MF文件不记录的时间戳版本 -->
                <useUniqueVersions>false</useUniqueVersions>
                <addClasspath>true</addClasspath>
                <classpathPrefix>lib/</classpathPrefix>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

## maven-javadoc-plugin

通过 maven-javadoc-plugin 生产文档包插件

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>2.10.4</version>
    <configuration>  
        <aggregate>true</aggregate>  
    </configuration> 
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## maven-source-plugin

通过 maven-source-plugin 生产源码包插件

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-source-plugin</artifactId>
    <version>2.4</version>
    <configuration>  
        <attach>true</attach>  
    </configuration> 
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>jar-no-fork</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## tomcat7-maven-plugin

tomcat7:run 本地运行项目

```xml
 <plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <!--此端口表示本地项目运行的端口-->
        <port>9999</port>
        <!--利用tomcat manager部署-->
        <url>http://192.168.0.11:8080/manager/text/</url>
        <server>tomcat-deploy</server>
        <!--以下用户名和密码需要在tomca-user.xml文件中配置如下内容
        <role rolename="tomcat"/>  
        <role rolename="manager"/>      
        <role rolename="manager-script"/>  
        <role rolename="manager-status"/>  
        <role rolename="manager-jmx"/>      
        <role rolename="manager-gui"/>  
        <role rolename="admin"/>  
        <role rolename="admin-gui"/>   
        <user username="tomcat" password="tomcat" roles="admin,admin-gui,manager,manager-gui,manager-script,manager-status,manager-jmx"/>  
        第一次部署用tomcat7:deploy命令，前提是tomcat必须先启动，然后tomcat webapps目录下manager目录还在
        tomcat7:redeploy重新部署
        tomcat7:undeploy删除部署
        -->
        <username>tomcat</username>
        <password>tomcat</password>
        <!--项目部署访问路径-->
        <path>/${project.name}</path>
    </configuration>
</plugin>
```

## jetty-maven-plugin

```xml
 <plugin>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-maven-plugin</artifactId>
    <version>9.3.8.v20160314</version>
    <configuration>
        <webAppConfig>
            <!-- 指定 root context 在这里指定为${project.artifactId} 即 jetty， 那么访问时就用http://localized:8080/jetty 
                访问， 如果指定梶为test 就用http://localized:8080/test访问，更多信息，请查看jetty 插件官方文档 -->
            <contextPath>/${project.name}</contextPath>
            <defaultsDescriptor>src/test/resources/webdefault.xml</defaultsDescriptor>
        </webAppConfig>
        <stopPort>9966</stopPort>
        <stopKey>stop</stopKey>
        <stopWait>10</stopWait>
        <scanIntervalSeconds>10</scanIntervalSeconds>
        <!-- 指定额外需要监控变化的文件或文件夹，主要用于热部署中的识别文件更新 -->
        <scanTargetPatterns>
            <scanTargetPattern>
                <directory>src</directory>
                <includes>
                    <!-- <include>*.java</include> <include>*.properties</include> -->
                    <include>*.html</include>
                    <include>*.js</include>
                    <include>*.css</include>
                </includes>
                <!-- <excludes> <exclude>**/*.xml</exclude> <exclude>**/myspecial.properties</exclude> 
                    </excludes> -->
            </scanTargetPattern>
        </scanTargetPatterns>
        <!-- 指定监控的扫描时间间隔，0为关闭jetty自身的热部署，主要是为了使用jrebel -->
        <!-- <scanIntervalSeconds>0</scanIntervalSeconds> -->
        <!-- 指定web页面的文件夹 -->
        <webAppSourceDirectory>${project.name}/src/main/webapp</webAppSourceDirectory>
    </configuration>
</plugin>
```
