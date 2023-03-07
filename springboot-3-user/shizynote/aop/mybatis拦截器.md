
#### mybatis 拦截器 (不是aop 但场景类似)

mybatis Interceptor 
- 自己写的
  - 打印sql日志及处理时间，仅用于debug，统计收集用pinpoint
  - 拦截sql用于设置通用字段 update_time,update_id
- mybatis plus
  - 分页
  - 数据权限 租户
  - 处理逻辑字段：逻辑删除、data_version
  - sql检查：是否全表操作 (druid也有)

```
mybatis Interceptor 拦截器使用
https://cloud.tencent.com/developer/article/1858587
https://developer.aliyun.com/article/1043956
https://juejin.cn/post/7027633293684113421

拦截器顺序
https://juejin.cn/post/6926849748481605646
https://springboot.io/t/topic/2330
```

Signature 指定切点类型
```
Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
StatementHandler (prepare, parameterize, batch, update, query)
ParameterHandler (getParameterObject, setParameters)
ResultSetHandler (handleResultSets, handleOutputParameters)

从上到下，也是切点类型的执行顺序
若切点相同，则按照Bean加载顺序，从后往前调用
  原理：按bean加载顺序添加到pluginList，遍历list创建代理。即最后创建的在最外层。即责任链
  创建代理的原理类似aop的原理，都是proxy(aop也可以是cglib)
```

mybatis plus 插件问题
```
不传递执行链
```

---

typeHanlder
- Java类到数据库的类型转换(字符、日期)
- 用户数据(卡号、姓名)脱敏查询
- 用户数据(卡号、姓名)加密落库 解密读取
```
typeHanlder使用问题：
    需在resultMap中指定typeHandler
    mybatis plus提供注解在PO类字段上，但需测试service与wrapper的各种sql写法是否生效
https://www.lin2j.tech/archives/typehandler-does-not-work
https://github.com/baomidou/mybatis-plus/issues/794
```











