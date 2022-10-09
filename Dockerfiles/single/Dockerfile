#使用java8
FROM openjdk:8u151-jdk-slim

#作者信息
LABEL author="Benjamin Cheung <ben@gzand-c.com>"

#默认64M运行内存
ENV JAVA_OPT -Xmx64

ENV SPRING_OPT ""

ARG PROJECT_NAME
ENV PROJECT_NAME ${PROJECT_NAME}
ARG JAR_PATH
ENV JAR_PATH ${JAR_PATH}
ENV EXEC_PATH /jar
ENV CMD_PROJECT_PATH ${EXEC_PATH}/${PROJECT_NAME}.jar

RUN mkdir ${EXEC_PATH}
RUN chmod 755 ${EXEC_PATH}

COPY ${JAR_PATH}${PROJECT_NAME}.jar ${EXEC_PATH}

#校正时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#运行指定jar
CMD java ${JAVA_OPT} "-jar" ${SPRING_OPT} ${CMD_PROJECT_PATH}

## 使用IDEA Docker插件时 ##
# 0 注意Dockerfile中的jar文件命名为项目pom输出FinalName参数
# 1 Bind mounts指向target目录
# 2 build-arg中需要设定PROJECT_NAME参数为FinalName
# 3 按boot的bootstrap.yml指定Bind ports
# 3.1 若随机端口Bind ports为All
# 4 jvm参数在env参数中指定JAVA_OPT加入所有的参数
# 5 spring配置参数在env参数中指定SPRING_OPT加入所有的参数
