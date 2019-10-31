# Spring-Cloud-Project 微服务项目


> 相关内容还在摸索中...

## 核心依赖

Spring Cloud Alibaba。

| 依赖 | 版本 |
|:--|:--|
| Spring Boot | 2.1.9.RELEASE |
| Spring Cloud | Greenwich.RS3 |

## 模块概述

| 模块名称 | 端口号 | 功能 |
|:--|:--|:--|
| spring-cloud-gateway | 8002 | [网关服务](./spring-cloud-gateway/README.md) |
| spring-cloud-user | 8006 | [用户服务](./spring-cloud-user/README.md) |
| spring-cloud-other | 8007 | [其他服务](./spring-cloud-other/README.md) |
| spring-cloud-common | 无 | [公共模块](./spring-cloud-common/README.md) |
| spring-cloud-vue | 8080 | [前端页面](./spring-cloud-vue/README.md) |

### 一、开发环境
* JDK 11  
* gradle 5.4.1  
* IntelliJ IDEA 2019.2  
### 二、软件工具  
* MySql 8.0.13  
* Redis 5.0.2
* RabbitMQ 3.7.9  
* Elasticsearch 7.3.0
* Logstash 7.3.0
* Kibana 7.3.0  
### 三、组件说明  
* 注册中心：Eureka  
* 配置中心：spring cloud config -> native / git
* 消息总线：spring cloud bus
* 负载均衡：feign / ribbon
* 熔断保护：hystrix
* 路由网关：gateway
* 服务监控：spring boot admin
* 集群监控：turbine
* 链路追踪：zipkin
* 数据源监控：druid
* ORM 框架：JPA / Hibernate
* 安全认证：spring security oauth2
* 在线文档：swagger2
* 分布式锁：spring-integration-redis
* 消息队列：RabbitMQ
* 日志处理：ELK (Elasticsearch、Logstash、Kibana)
* 分布式事务：TX-LCN  
### 四、项目结构  
```
spring-cloud-project -- 根目录
├── spring-cloud-admin -- 服务监控
├── spring-cloud-common -- 通用工具公共模块
├── spring-cloud-config -- 配置中心服务
├── spring-cloud-eureka -- 注册发现服务
├── spring-cloud-gateway -- 路由网关服务
├── spring-cloud-other -- 其他 示例服务
├── spring-cloud-turbine -- 调用实时监控服务
├── spring-cloud-txlcn -- 分布式事务服务
├── spring-cloud-user -- 用户认证授权服务
├── spring-cloud-vue -- vue 页面端
├── spring-cloud-zipkin -- 链路追踪服务
├── spring-cloud-zuul -- zuul网关 (已放弃)
```
### 五、项目运行步骤  
1. git 下载当前项目，完成后从 IDEA中打开
2. 使用 gradle 工具进行项目依赖构建
3. 修改配置中心的 application-config-dev.yml 配置文件中相关数据源信息
4. 启动 MySql、RabbitMQ、Redis 三个必备服务 和 三个非必需的 ELK(Elasticsearch、Logstash、Kibana) 服务
5. 创建数据库并执行sql脚本。若要启动 vue 页面端 请首先安装好 node.js
6. 上述完成后即可开始启动项目服务  
#### 项目服务启动顺序  
1. spring-cloud-eureka （必须）
2. spring-cloud-config （必须）
3. spring-cloud-admin （可选）
4. spring-cloud-gateway （可选）
5. spring-cloud-zipkin （可选）
6. spring-cloud-turbine （可选）
7. spring-cloud-txlcn (可选)
8. spring-cloud-user （可选）
9. spring-cloud-other （可选）  
##### spring-cloud-vue 页面端的启动  
1. 请先点开idea的 Terminal 调出执行命令窗口
2. 依次执行以下命令：  
``` 
 cd spring-cloud-vue  
 npm install
 npm run dev
```
### 六、效果预览
![首页](/images/index.png)  
![注册中心](/images/eureka.png)  
![监控中心](/images/admin.png)  
![熔断监控](/images/turbine.png)  
![链路追踪](/images/zipkin.png)  
![API文档](/images/swagger.png)
***
