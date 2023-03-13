# reids

redis命令与基础数据类型
https://www.runoob.com/redis/redis-tutorial.html

#### spring-boot-starter-data-redis 

```
RedisAutoConfiguration.class
- 按先后顺序加载 LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class
- 默认引入lettuce客户端
- 没有引入jedis客户端(需要的话可以排除lettuce，然后引入jedis，不排除就会先加载到lettuce)

RedisProperties.class
其中有redis的连接配置默认
private int database = 0;
private String host = "localhost";
private int port = 6379;
```
https://juejin.cn/post/7076244567569203208

#### 序列化
```
RedisTemplate默认序列化方式
- JdkSerializationRedisSerializer 源码能看到
    ObjectOutputStream和ObjectInputStream 对象内容会乱码

需改为json字符串序列化
- Jackson2JsonRedisSerializer
    见
```

什么是序列化以及序列化id？
todo

#### redis原理及面试题
todo

#### redis项目使用场景
redis分布式锁
    自旋锁
    错误锁
    todo

redis缓存登录
    redis切面注解 自定义

todo

#### redis命令

```
查询所有当前库key
keys *

删除当前库所有key(刷新库)
flushdb
```