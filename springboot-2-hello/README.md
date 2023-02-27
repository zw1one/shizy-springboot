# springboot hello

空的springboot工程

swagger地址：http://127.0.0.1:8080/swagger-ui/index.html

knife4j地址：http://127.0.0.1:8080/doc.html

### 打包部署

* Maven打包命令
```
mvn clean package -Dmaven.test.skip=true -Dmaven.javadoc.skip=true 
```
* jar包运行命令
```
nohup java -server -Xmx1024m -Xms256m -XX:+UseParallelGC -XX:ParallelGCThreads=20  -jar hello-0.0.1-SNAPSHOT.jar >system.log 2>&1 &
```
* start.sh
```sh
app_name="hello-0.0.1-SNAPSHOT.jar"

rm -rf system.log*
ps -ef | grep java | grep $app_name | grep -v grep | awk '{print $2}' |xargs kill -9

nohup java -server -Xmx1024m -Xms256m -XX:+UseParallelGC -XX:ParallelGCThreads=20  -jar $app_name >system.log.$(date +%Y%m%d%H%H%M%S) 2>&1 &
```