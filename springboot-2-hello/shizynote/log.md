# springboot - 日志管理log4j2

## 日志框架

- springboot默认日志：slf4j + logback
  默认情况下，Spring Boot会用Logback来记录日志，并用INFO级别输出到控制台。

- 工程推荐使用日志：slf4j + log4j2

### 日志框架介绍

日志框架出现的历史顺序为 ： log4j → JUL → JCL → SLF4J → logback → log4j2

- xxx
- xxx
- xxx
- xxx

- log4j2
  在全异步输出日志时表现优秀，官方测试报告https://logging.apache.org/log4j/log4j-2.3/manual/async.html
  而Async Log基于LMAX Disruptor库，实现了一个高性能的异步记录器。


Async Append




### ELK

生产ELK如何实现
ELK为何不上生产














-----------------

做系统迁移/重构，存量数据如何处理？