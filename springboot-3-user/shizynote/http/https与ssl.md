### https SSL/TLS 使用RSA+AES  
> https://segmentfault.com/a/1190000021559557

ssl 3.0之后 迭代为TLSv1.1、TLSv1.2、TLSv1.2

```plantUML
@startuml

title:https TLS 握手过程

participant 客户端 as client
participant 服务器 as server
participant CA认证机构 as ca

server -> server
note right
服务器预先生成一对RSA非对称秘钥
- 私钥 由服务器保留
- 公钥 公开，任意客户端可以获取
end note

client -> server : client hello
note left
客户端发起握手请求：
- 客户端所支持的 TLS 版本和密码组合
- "client random"随机字符串
end note

server -> client : server hello
note right
对客户端进行回应：
- 服务器选择的密码组合
- "server  random"随机字符串
- CA证书: 证书链、证书有效期、证书状态、证书签名
end note

client -> ca : 验证证书
note left
验证失败场景
1、证书链不是由受信任的CA机构颁发的
    证书链：由DigiCert、GeoTrust、GlobalSign颁发跟证书，然后阿里云层层外包
2、证书过期
3、访问的网站域名与证书绑定的域名不一致
4、证书签名不一致 信息摘要算法(sha1、sha256、md5)
end note

client -> client : 证书验证通过，生成premaster secret

client -> server : 公钥加密premaster secret发送给服务器

client -> client : 本地生成共享对称密钥AES
note left
共享对称密钥，由下列项生成
- client random
- server random
- premaster secret
- 服务器指定算法
end note

client -> server : finished

server -> server : 本地生成共享对称密钥AES
note right
共享对称密钥，由下列项生成
- client random
- server random
- premaster secret
- 服务器指定算法
end note

server -> client : finished

client -> server : 握手完成，使用AES进行加密通信

@enduml
```

在TLS场景中，公钥仅做加密，私钥仅做解密。

#### nginx配置

```
1 下载ca证书到nginx服务器
2 从ca证书中生成私钥+公钥 (一个ca证书仅有一对)
3 在nginx中配置
    ca证书路径
    公钥私钥路径
    session_id 会话缓存方式、时间(保存对称秘钥)
```

---

### 供票项目请求过程


```plantUML
@startuml

title:供票-票交所对接加密方式

== 供票请求票交所 ==

participant 供票 as sb
participant 票交所 as pjs

sb -> pjs : 请求公钥
note right: 票交所给出请求公钥接口
pjs -> pjs : SM2生成公钥+私钥
note right
- 将公钥、私钥根据合作方编码保存
- 每次调用该接口会新生成非对称秘钥，弃用旧秘钥
    一个合作方仅保持一个生效秘钥
end note

pjs -> sb: 返回公钥
note left : 将公钥保存在数据库
sb -> sb: SM4生成对称秘钥
note left
使用对称秘钥是因为非对称秘钥加/解密效率低
end note

sb -> pjs : 调用业务接口
note left
在请求参数中，带上加密后的对称秘钥
数据使用该对称秘钥加密
end note

pjs -> pjs : 解密数据
note right
1 获取合作方之前生成的私钥
2 用私钥解密该对称秘钥
3 用对称秘钥解密数据
end note

== 票交所请求供票 == 

sb <- pjs : 请求公钥
note left
供票给出请求公钥接口
    公钥+私钥已预先生成好放在配置文件中
    是唯一一份，没有实时更换需求
end note

sb -> pjs : 返回公钥
note right: 其他同上 

@enduml
```
```
项目中，使用nginx服务器安装ca证书，并配置加密方式。
国密证书+sm2+sm4+sm3
国际证书+ras+aes+sha256
```

```
国密
https://www.sofastack.tech/blog/the-secret-of-anolisos/
sm2 -> RSA 非对称加密
sm3 -> MD5 信息摘要
sm4 -> AES 对称加密
```

```plantUML
@startuml

title:供票-票交所对接通信方式

participant "供票-前端" as sbfront
participant "供票-业务系统" as sbbiz
participant "供票-前置机mq" as qzjmq
participant "供票-前置机" as qzj
participant 票交所mq as pjs

sbfront -> sbbiz : 发起http背书请求
sbbiz -> sbbiz : 校验前置机、票交所状态
sbbiz -> qzjmq : 推送背书请求至mq
sbbiz -> sbfront : 前端页面返回：操作成功
qzj -> qzjmq: 消费背书请求
qzj -> qzj: sm2公钥1加密(需私钥1解密)
qzj -> pjs: 推送

pjs -> pjs: 票交所处理(确认收到请求)
pjs -> qzj: 业务确认报文
qzj -> qzj: sm2私钥2解密(需公钥2解密)
qzj -> qzjmq: 推送业务确认报文
sbbiz -> qzjmq : 消费业务确认报文
sbbiz -> sbbiz : 更改数据状态：已确认
sbfront -> sbbiz : 前端可查询业务状态

pjs -> pjs: 票交所处理(请求处理的响应)
pjs -> qzj: 业务响应报文
qzj -> qzjmq: 推送业务响应报文
sbbiz -> qzjmq : 消费业务响应报文
sbbiz -> sbbiz : 更改数据状态：已响应-成功/失败
sbfront -> sbbiz : 前端可查询业务状态

@enduml
```
```
项目中，使用nginx服务器安装ca证书，并配置加密方式。
国密证书+sm2+sm4+sm3
国际证书+ras+aes+sha256
```

