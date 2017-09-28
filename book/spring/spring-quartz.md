# spring-quartz定时任务

### 基于 xml 的定时器任务

1. spring-quartz.xml 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
       http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd">

    <bean id="timedTask" class="com.herolei.task.TimedTask"/>
    <!--5秒钟-->
    <task:scheduled-tasks>
        <task:scheduled ref="timedTask" method="Task5s" cron="*/5 * * * * ?" />
    </task:scheduled-tasks>

    <!--5分钟-->
    <task:scheduled-tasks>
        <task:scheduled ref="timedTask" method="Task5m" cron="0 0/5 * * * ?" />
    </task:scheduled-tasks>

</beans>
```

2. 基于 xml 的定时器任务 com.herolei.task.TimedTask

```java
package com.herolei.task;

public class TimedTask {

    public void Task5s() {
        System.out.println("5秒钟任务");
    }

    public void Task5m() {
        System.out.println("5分钟任务-------------------");
    }
}
```

### 基于注解的定时器任务

1. spring-quartz2.xml 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
       http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--开启注解驱动-->
    <task:annotation-driven/>
    <context:component-scan base-package="com.herolei.task"/>

</beans>
```

3. 基于注解的定时器任务 com.herolei.task.TimedTask2

```java
package com.herolei.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
 * 基于注解的定时器 
 */  
@Component
public class TimedTask2 {

    //每隔4秒执行一次
    @Scheduled(cron="*/4 * * * * ?")
    public void Task5s() {
        System.out.println("4秒钟任务+++++++++++++++++++");
    }

    //每隔8秒执行一次
    @Scheduled(cron="*/8 * * * * ?")
    public void Task5m() {
        System.out.println("8秒钟任务-------------------");
    }

    //心跳更新。启动时执行一次，之后每隔2秒执行一次
    @Scheduled(fixedRate = 1000*2)
    public void Task2s() {
        System.out.println("心跳更新。启动时执行一次，之后每隔2秒执行一次");
    }
}
```

测试 

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-quartz2.xml"})
public class TaskTest {

    @Test
    public void test1() {
        while (true) {

        }
    }
}
```

或

```java
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
  
  
public class Main {  
    public static void main(String[] args) {  
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-quartz2.xml");
    }  
}  
```
