# springboot docker mvn构建  

见：http://www.ityouknow.com/springboot/2018/03/19/spring-boot-docker.html


---

### 使用springboot自带 maven docker插件

pom.xml配置
```
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image>
            <!--配置镜像名称-->
            <name>192.168.3.101:5000/mall-tiny/${project.name}:${project.version}</name>
            <!--镜像打包完成后自动推送到镜像仓库-->
            <publish>true</publish>
        </image>
        <docker>
            <!--Docker远程管理地址-->
            <host>http://192.168.3.101:2375</host>
            <!--不使用TLS访问-->
            <tlsVerify>false</tlsVerify>
            <!--Docker推送镜像仓库配置-->
            <publishRegistry>
                <!--推送镜像仓库用户名-->
                <username>test</username>
                <!--推送镜像仓库密码-->
                <password>test</password>
                <!--推送镜像仓库地址-->
                <url>http://192.168.3.101:5000</url>
            </publishRegistry>
        </docker>
    </configuration>
</plugin>
```

build命令
```
mvn spring-boot:build-image
```



### 使用spotify maven docker插件

pom.xml配置
```
			<!-- Docker maven plugin -->
			<plugin>
				<groupId>com.spotify</groupId>
				<artifactId>docker-maven-plugin</artifactId>
				<version>1.0.0</version>
				<configuration>
					<imageName>springboot-demo/${project.artifactId}</imageName>
<!--					<dockerDirectory>src/main/docker</dockerDirectory>-->
					<resources>
						<resource>
							<targetPath>/</targetPath>
							<directory>${project.build.directory}</directory>
							<include>${project.build.finalName}.jar</include>
						</resource>
					</resources>
				</configuration>
			</plugin>
			<!-- Docker maven plugin -->
```

build命令
```
mvn clean package -Dmaven.test.skip=true -Dmaven.javadoc.skip=true docker:build
```


