# 工具数据平台

## 模块说明

[tools-dashboard] 为项目名同时也为各个模块的前缀, 同时使用Maven的Root项目POM来统一各个项目构建. 

各个项目名称说明: 

1) [aggregate] 整合项目,位于根目录上

2) [discovery] 注册中心, 单节点模式部署

3) [config/config-properties] 配置中心/配置文件仓库

4) [authorization] 安全验证模块, 简单token模式, header传递校验登录后使用api

5) [api-gateway] 网关模块, 控制校验输入规则

6) [web-facade] 面向前端的聚合逻辑模块, 聚合了baidu/aatool/myspace(aplus)主要逻辑适用在web输出内容/导出内容

7) [baidu/aatool/myspace] 分别接入百度统计/活动助手/A+画册数据逻辑, 后俩者部分SQL逻辑与原应用类似,且俩模块是直连DB(通过代理AzureDB)

***以上模块中,discovery / api 是固定端口, 其他采用随机端口(Docker化后亦是)***



## 部署启动说明

### Profile
项目配置profile主要分prod及local, 默认启动prod. aatool模块提供local用于本地DB参数.

* 若使用Redisson的项目(aatool/baidu),是代码内指定redisson配置文件,可手动改变读取规则

### Maven Build
Root项目中添加mvn自定义命令

    package dockerfile:push -pl org.codeworks.web:tools-dashboard-discovery,org.codeworks.web:tools-dashboard-config,org.codeworks.web:tools-dashboard-authorization,org.codeworks.web:tools-dashboard-aatool,org.codeworks.web:tools-dashboard-api-gateway,org.codeworks.web:tools-dashboard-baidu,org.codeworks.web:tools-dashboard-myspace,org.codeworks.web:tools-dashboard-web-facade

其中-pl 指定某些项目执行之前的mvn命令 , 项目规格是{groupId}:{artifactId}, 逗号分隔. 

### Docker
项目兼容Docker容器化部署, 通过Single Dockerfile不同细化参数进行控制服务细节.详细参数可看Dockerfile.
同时bootstrap.yml亦兼容实例启动.

在项目使用上可使用Single模式或Compose模式启动

##### Single Docker properties
项目使用docker network及自定义网络互联, 其bridge命名为"td-web",创建命令如下:

``# docker network create td-web``

参数要点(Dockerfile以下简称DF):

    1. 创建上使用build-arg传递项目Jar包名称, DF中已指定容器内的目录结构, 若IDEA中需要把target指向为挂载盘, 若实际项目可忽略-v的参数需要DF与jar相同目录下才可运行. 
    2. 除业务模块使用64/128, 其余需要注意JVM启动内容, 避免占用过高(单机部署下)
    3. 必须要指明容器名称及网络别名, 别名在相同模块下相同可用于负载均衡
    4. myspace(aplus)使用纤程库, jar命名及启动参数略有不同

#### 各个项目构建及运行参数

discovery

    docker build --build-arg PROJECT_NAME=tools-dashboard-discovery -t tools-dashboard-discovery-image . \
    && \
    docker run -p 9001:9001 \ 
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-discovery/target/:/jar \ 
    --env JAVA_OPT=-Xms64m -Xmx128m \
    --name tools-dashboard-discovery \
    --network td-web --network-alias td-discovery tools-dashboard-discovery-image  

api

    docker build --build-arg PROJECT_NAME=tools-dashboard-api-gateway -t tools-dashboard-api-gateway-image . \
    && \
    docker run -p 9002:9002 \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-api-gateway/target:/jar \
    --env JAVA_OPT=-Xms64m -Xmx128m \
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery -Ddiscovery.port=9001 \
    --name tools-dashboard-api-gateway \
    --network td-web --network-alias td-api-gateway tools-dashboard-api-gateway-image 

config

    docker build --build-arg PROJECT_NAME=tools-dashboard-config -t tools-dashboard-config-image . \ 
    && \
    docker run -P \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-config/target/:/jar \
    --env JAVA_OPT=-Xms64m -Xmx128m \
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery -Ddiscovery.port=9001 \
    --name tools-dashboard-config \
    --network td-web --network-alias td-config tools-dashboard-config-image 

auth

    docker build --build-arg PROJECT_NAME=tools-dashboard-authorization -t tools-dashboard-authorization-image . \
    && \
    docker run -P \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-authorization/target:/jar \
    --env JAVA_OPT=-Xms64m -Xmx128m \
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery -Ddiscovery.port=9001 -Dspring.redis.host=redis \
    --name tools-dashboard-authorization \
    --network td-web --network-alias td-authorization tools-dashboard-authorization-image 

web-facade

    docker build --build-arg PROJECT_NAME=tools-dashboard-web-facade -t tools-dashboard-web-facade-image . \
    && \
    docker run -P \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-web-facade/target:/jar \
    --env JAVA_OPT=-Xms64m -Xmx128m 
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery -Ddiscovery.port=9001 \
    --name tools-dashboard-web-facade \
    --network td-web --network-alias tdweb-facade tools-dashboard-web-facade-image

baidu

    docker build --build-arg PROJECT_NAME=tools-dashboard-baidu -t tools-dashboard-baidu-image . \
    && \
    docker run -P \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-baidu/target:/jar \
    --env JAVA_OPT=-Xms64m -Xmx128m \
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery -Ddiscovery.port=9001 -Ddocker.enable=true \
    --name tools-dashboard-baidu \
    --network td-web --network-alias td-baid tools-dashboard-baidu-image 

aatool

    docker build --build-arg PROJECT_NAME=tools-dashboard-aatool -t tools-dashboard-aatool-image . \
    && \
    docker run -P \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-aatool/target:/jar \
    --env JAVA_OPT=-Xms64m -Xmx512m \
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery -Ddiscovery.port=9001 -Ddocker.enable=true -Dspring.profiles.active=local \
    --name tools-dashboard-aatool \
    --network td-web --network-alias td-aatool tools-dashboard-aatool-image 

aplus

    docker build --build-arg PROJECT_NAME=tools-dashboard-myspace-cap -t tools-dashboard-myspace-image . \
    && \
    docker run -P \
    -v /Users/benjaminkc/Documents/IDEASpace/tools-dashboard/tools-dashboard-myspace/target:/jar \
    --env JAVA_OPT=-Xms64m -Xmx256m \
    --env SPRING_OPT=-Ddiscovery.dns=td-discovery \
    -Ddiscovery.port=9001 -Dspring.redis.host=redis -Dco.paralleluniverse.fibers.detectRunawayFibers=false \
    --name tools-dashboard-myspace \
    --network td-web --network-alias td-aplus tools-dashboard-myspace-image 

