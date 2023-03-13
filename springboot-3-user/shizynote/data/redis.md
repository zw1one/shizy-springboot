# reids

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

#### redis原理及面试题

#### redis项目使用场景
redis分布式锁
    自旋锁
    错误锁

redis缓存登录
    redis切面注解 自定义

#### redis序列化