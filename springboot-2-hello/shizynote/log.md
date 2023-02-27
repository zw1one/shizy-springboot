# springboot - 日志管理log4j2

## 日志框架

- springboot默认日志：slf4j + logback
  默认情况下，Spring Boot会用Logback来记录日志，并用INFO级别输出到控制台。

- 工程推荐使用日志：slf4j + log4j2

### 日志框架介绍
```
常用日志框架（Log4j，Slf4j，Logback）之间到底有啥区别 
https://cloud.tencent.com/developer/article/1442406  

Spring Boot 日志框架
https://www.cnblogs.com/niumoo/p/10398595.html
```
日志框架出现的历史顺序为 ： log4j → JUL → JCL → SLF4J → logback → log4j2

- JUL   
  java自带的日志框架
- log4j  
  旧日志框架。创始人捐赠给apache
- **logback**   
  springboot默认  
  log4j创始人新作
- **slf4j**  
  Simple Logging Facade for Java （java日志外观模式）  
  log4j创始人同logback一起制作 代码调用相同api，slf4j做不同实现
- **log4j2**  
  apache在log4j基础上的新版本 (即log4j 2.x)
  在全异步输出日志时表现优秀  
  Async Log基于LMAX Disruptor库，实现了一个高性能的异步记录器。


### log4j2 配置
```
Log4j2配置参考
xml格式配置：
https://www.cnblogs.com/keeya/p/10101547.html
https://juejin.cn/post/6870656918567567367

yml格式配置(使用较少，反而难以cv)：
https://juejin.cn/post/7123954724419665950

Log4j2中的同步/异步配置
https://www.cnblogs.com/yeyang/p/7944906.html
https://juejin.cn/post/7124259612173271053

log4j2官方文档
https://logging.apache.org/log4j/log4j-2.3/manual/async.html  

logback配置
http://c.biancheng.net/spring_boot/log-config.html
```

log4j2同步异步配置

- log4j2 同步
```
<Loggers>
  <Logger name="org.apache" level="INFO"/>
</Loggers>
```
- log4j2 异步/同步混合(单独线程来写日志)
```
<Loggers>
  <AsyncLogger name="org.apache" level="INFO"/>
</Loggers>
```
- log4j2 全异步(类mq)
1. Log4j-2.9之后，需disruptor-3.3.4.jar包或更高； Log4j-2.9之前，需disruptor-3.0.0.jar或更高
2. springboot项目 - 添加配置文件 log4j2.component.properties
```
log4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector

# 其他异步配置项：见官方文档
```

#### log4j2异步思考

- 需要xx量级的写日志并发 才会出现性能问题？ todo
- 会额外引入异步锁问题，带来异步成本 todo
- 与其异步日志的写入，不如直接异步该业务线程+同步日志，技术成本低


### ELK

生产ELK如何实现
ELK为何不上生产

### log4j漏洞












-----------------

做系统迁移/重构，存量数据如何处理？