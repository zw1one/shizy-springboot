# springboot starter 原理及手动实现

#### 自动装配原理
1 maven依赖 spring-boot-autoconfigure  
2 可按以下路径找到spring.factories加载配类的路径
```
@SpringBootApplication
|
@EnableAutoConfiguration
|
@Import(AutoConfigurationImportSelector.class)
|
selectImports()
|
getAutoConfigurationEntry()
|
getCandidateConfigurations()
|
SpringFactoriesLoader.loadFactoryNames()
|
loadSpringFactories()
|
classLoader.getResources(FACTORIES_RESOURCE_LOCATION);
|
FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
```

https://juejin.cn/post/6844903882133340168

#### 自动装配手动实现

1 建一个配置生效后需要注入的bean Class
```
public class TenantInterceptor implements Interceptor {...}
```

2 建一个Configuration类
```java
@Configuration
// 配置中存在config.tenant.enable=true 则该config生效
@ConditionalOnProperty(name = "config.tenant.enable", havingValue = "true")
//从该类加载配置文件
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfiguration {

    //配置生效后，需要注入的bean
    @Bean
    @ConditionalOnMissingBean(TenantInterceptor.class)//该bean不存在时注入
    public TenantInterceptor tenantInterceptor(TenantProperties tenantProperties) {
        return new TenantInterceptor();
    }
}
```

3 在META-INF/spring.factories 文件中 添加
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.config.autoconfig.ConfigDemoAutoConfiguration,\
 \ com.example.config.autoconfig.TenantAutoConfiguration
```

4 构建该config工程，在业务工程中依赖该工程
- maven构建命令
```
mvn clean install/deploy -Dmaven.test.skip=true -Dmaven.javadoc.skip=true 
```
- 业务工程依赖config工程
```
<!-- demo-starter-->
<dependency>
    <groupId>com.example</groupId>
    <artifactId>config</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
- 业务工程配置文件添加该配置
```
### config test
config:
  tenant:
    enable: true
```

5 启动业务工程时，即可注入TenantAutoConfiguration的Bean
- 示例1 这里注入的是自行编写的工具类，类似JdbcTemplate
```
@Autowired
private ConfigHelloTemplet configHelloTemplet;
```
- 示例2 这里注入的是TenantInterceptor.class，实现了mybatis的插件，注入后可拦截update sql添加tenant_id字段

```
TenantInterceptor.class
```

#### 自动装配案例

spring-boot-starter-web

spring-boot-starter-jdbc

spring-boot-starter-data-redis

spring-boot-starter-test

手动实现拦截器starter tanentId、UpdateTime、isDelete
通过配置开启

手动实现工具类



#### 约定大于配置
官方文档的默认配置(缺省)
https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#appendix.application-properties

spring-boot-starter就是一种约定大于配置的实现。
xxx-starter都按照starter的约定来编写使用，内置默认配置，使开发者做到开箱即用，个性配置。
https://juejin.cn/post/7024854083223683085






