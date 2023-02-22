# webflux 响应式编程 (后端服务之间调用)

```plantUML
@startuml
== webflux响应式编程 ==

后端a -> 后端b: api调用请求
后端a --> 后端a: 继续执行代码
后端b --> 后端b: 处理请求
后端b -> 后端a: 响应数据
@enduml
```

```plantUML
@startuml
== 阻塞式编程-模拟响应式编程 ==

后端a -> 后端b: api调用请求
后端b --> 后端a: 返回响应 (仅表示请求成功/失败，没有处理结果)
后端a --> 后端a: 继续执行代码
后端b --> 后端b: 处理请求
"后端a(定时任务)" --> 后端b: 定时任务轮询
后端b -> "后端a(定时任务)": 响应数据
@enduml
```


- webflux介绍
https://segmentfault.com/a/1190000042295586

重点：
JDBC或者一些阻塞式的API不适用于webflux项目