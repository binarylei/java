# log4j

> log4j 配置文件有三个主要的组件：Logger，Appender和Layout，分别为日志类型，日志输出目的地，日志输出格式。


### log4j.properties

```properties
# Root logger option
log4j.rootLogger=INFO, stdout, file
 
# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
 
# Redirect log messages to a log file
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.File=../logs/app.log
log4j.appender.file.MaxFileSize=30MB
log4j.appender.file.MaxBackupIndex=30
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

log4j.logger.org.springframework=info
log4j.logger.org.springframework.web=debug
log4j.logger.com.binarylei=debug
```
 
1. 配置根Logger，其语法为： `log4j.rootLogger = [ level ] , appenderName1, appenderName2, …  `
        
    * `level` 优先级分别为 fatal、error、warn、info、debug 5个级别。通过定义的级别,你可以控制程序中的日志输出。

    * `appenderName` 就是指定日志信息输出到哪个地方。您可以同时指定多个输出目的地。

2. 配置日志信息输出目的地，其语法为：
 
```properties
log4j.appender.file=org.apache.log4j.RollingFileAppender
```

3. 配置日志信息的格式，其语法为：

```properties
log4j.appender.file.layout = org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```
        
日志信息格式中几个符号所代表的含义：

符号 | 说明
--- | ---
-X号 | X信息输出时左对齐； 
%p | 输出日志信息优先级，即DEBUG，INFO，WARN，ERROR，FATAL, 
%d | 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，输出类似：2002年10月18日 22：10：28，921 
%r | 输出自应用启动到输出该log信息耗费的毫秒数 
%c | 输出日志信息所属的类目，通常就是所在类的全名 
%t | 输出产生该日志事件的线程名 
%l | 输出日志事件的发生位置，相当于%C.%M(%F:%L)的组合,包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10) 
%x | 输出和当前线程相关联的NDC(嵌套诊断环境),尤其用到像java servlets这样的多客户多线程的应用中 
%% | 输出一个"%"字符 
%F | 输出日志消息产生时所在的文件名称 
%L | 输出代码中的行号 
%m | 输出代码中指定的消息,产生的日志具体信息 
%n | 输出一个回车换行符，Windows平台为"/r/n"，Unix平台为"/n"输出日志信息换行 
%20c | 指定输出category的名称，最小的宽度是20，如果category的名称小于20的话，默认的情况下右对齐
%-20c | 指定输出category的名称，最小的宽度是20，如果category的名称小于20的话，"-"号指定左对齐
%.30c | 指定输出category的名称，最大的宽度是30，如果category的名称大于30的话，就会将左边多出的字符截掉，但小于30的话也不会有空格
%20.30c | 如果category的名称小于20就补空格，并且右对齐，如果其名称长于30字符，就从左边交远销出的字符截掉

4. 配置日志级别控制，其语法为：

```properties
#首先所有的第三方jar都不太想看到输出日志，设定所有log输出给stdout, file的等级为error
log4j.rootLogger=error, stdout, file

#其次自己的包下日志等级为debug，直接覆盖了rootLogger对com.binarylei包的作用
log4j.logger.com.binarylei=debug

#最后在控制台输出所有日志，而在文件中，只写入warn级别以上的日志
log4j.appender.stdout.Threshold=debug
log4j.appender.file.Threshold=warn
```

### 输出到回滚文件 

```properties
log4j.appender.ROLLING_FILE=org.apache.log4j.RollingFileAppender    //文件大小到达指定尺寸的时候产生一个新的文件
log4j.appender.ROLLING_FILE.Threshold=ERROR
log4j.appender.ROLLING_FILE.File=rolling.log    //文件位置,也可以用变量${java.home}、rolling.log
log4j.appender.ROLLING_FILE.Append=true         //true:添加 false:覆盖
log4j.appender.ROLLING_FILE.MaxFileSize=10KB    //文件最大尺寸
log4j.appender.ROLLING_FILE.MaxBackupIndex=1    //备份数
log4j.appender.ROLLING_FILE.layout=org.apache.log4j.PatternLayout
log4j.appender.ROLLING_FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```
### 输出到控制台

```properties
log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.Threshold=DEBUG
log4j.appender.CONSOLE.Target=System.out
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
log4j.appender.CONSOLE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
#log4j.appender.CONSOLE.layout.ConversionPattern=[start]%d{DATE}[DATE]%n%p[PRIORITY]%n%x[NDC]%n%t[thread] n%c[CATEGORY]%n%m[MESSAGE]%n%n
```

### 输出到文件

```properties
log4j.appender.FILE=org.apache.log4j.FileAppender
log4j.appender.FILE.File=file.log
log4j.appender.FILE.Append=false
log4j.appender.FILE.layout=org.apache.log4j.PatternLayout
log4j.appender.FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```

### 发送日志给邮件

```properties
log4j.appender.MAIL=org.apache.log4j.net.SMTPAppender
log4j.appender.MAIL.Threshold=FATAL
log4j.appender.MAIL.BufferSize=10
log4j.appender.MAIL.From=web@www.wuset.com
log4j.appender.MAIL.SMTPHost=www.wusetu.com
log4j.appender.MAIL.Subject=Log4J Message
log4j.appender.MAIL.To=web@www.wusetu.com
log4j.appender.MAIL.layout=org.apache.log4j.PatternLayout
log4j.appender.MAIL.layout.ConversionPattern=[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n
```

### 输出到数据库

```properties
log4j.appender.DATABASE=org.apache.log4j.jdbc.JDBCAppender
log4j.appender.DATABASE.URL=jdbc:mysql://localhost:3306/test
log4j.appender.DATABASE.driver=com.mysql.jdbc.Driver
log4j.appender.DATABASE.user=root
log4j.appender.DATABASE.password=root
log4j.appender.DATABASE.sql=INSERT INTO LOG4J (Message) VALUES ('[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n')
log4j.appender.DATABASE.layout=org.apache.log4j.PatternLayout
log4j.appender.DATABASE.layout.ConversionPattern=[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n
```

**参考：** 

* [log4j的基本配置参数](http://blog.csdn.net/fengyifei11228/article/details/6070006)

* [log4j输出日志级别控制](http://blog.csdn.net/zmx729618/article/details/51260803)
