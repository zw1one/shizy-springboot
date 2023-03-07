#### servlet过滤器 springweb拦截器
```
https://cloud.tencent.com/developer/article/1858587
```

过滤器
- servlet自带
- 无法获取spring bean
- 使用回调返回 故可以一个方法计算请求时间

拦截器
- spring自带 aop原理
- 可以获取spring bean
- 使用反射 故拦截前后需分两个方法，计算请求时间需额外setAttribute

执行顺序
```
过滤器(前) -> 拦截器(前) -> AOP(前) -> Controller -> AOP(后)-> 拦截器(后) -> 过滤器(后)
```

项目案例
```
过滤器-打印请求信息
  - 同一个方法易计算时间
  - 过滤器最先执行 时间更准

过滤器-POST参数输入流get重新写入
  - POST参数输入流get之后，流会关闭，导致后面读取不到，这里添加过滤器重新写入

拦截器-参数校验
  - 用户鉴权
  - 安全校验，请求ip白名单
  - 安全校验，冗余参数校验
  - 字符编码。deprecated。已在yml文件中指定utf8
  - 这里不处理sql注入，sql注入由#号的PreparedStatement处理。$号的表处理不对外暴露。也不处理XSS攻击，前端会处理。
```
---




