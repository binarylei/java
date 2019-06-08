# logback 

<https://logback.qos.ch/manual/index.html>

* 核心API日志对象(ch.qos.logback.classic.Logger)
* 日志级别(ch.qos.logback.classic.Level)
* 日志管理上下文(ch.qos.logback.classic.Loggercontext)
* 日志附加器(ch.qos.logback.core.appender)
* 口志过滤器(ch.qos.logback.core.filter.Filter)
* 日志格式布局(ch.qos.logback.core.Layout)
* 日志事件(ch.qos.logback.classic.spi.Loggingevent)
* 日志配置器(ch.qos.logback.classic.spi.Loggingevent)
* 日志诊断上下文(org.s1f4.MC)

## 统一日志框架

日志框架无论 Log4j 还是 Logback，虽然它们功能完备，但是各自 API 相互独立，并且各自为政。当应用系統在团队协作开发时，由于工程人员可能有所偏好，因此，可能导致一套系统可能同时出现多套日志框架。其次，最流行的口志框架基本上基于实现类编程，而非接口编程。因此，暴露一些无关紧要的细节给用户，这种耦合性是没有必要的。

诸如此类的原因，开源社区提供统一日志的 API 框架，最为流行的是

* `Apache commons-logging` 适配 1og4j 和 Java Logging
* `slf4j` 适配 log4j、 Java Iogging 和 logback