# Spring-Cloud-Project 微服务项目


> 相关内容还在摸索中...

## 核心依赖

Spring Cloud Alibaba

| 依赖 | 版本 |
|:--|:--|
| Spring Boot | 2.2.6.RELEASE |
| Spring Cloud | Hoxton.SR3 |  
| Spring-Cloud-Alibaba | 2.2.1.RELEASE |

## 模块概述

| 模块名称 | 端口号 | 功能 |
|:--|:--|:--|
| spring-cloud-user | 8001 | [用户服务](./spring-cloud-user/README.md) |
| spring-cloud-other | 8002 | [其他服务](./spring-cloud-other/README.md) |
| spring-cloud-authorize | 8003 | [授权服务](./spring-cloud-authorize/README.md) |
| spring-cloud-gateway | 8004 | [网关服务](./spring-cloud-gateway/README.md) |
| spring-cloud-admin | 8005 | [监控服务](./spring-cloud-admin/README.md) |
| spring-cloud-common | 无 | [公共模块](./spring-cloud-common/README.md) |
| spring-cloud-vue | 8080 | [前端页面](./spring-cloud-vue/README.md) |

### 一、开发环境
* JDK 13  
* gradle 5.4.1  
* IntelliJ IDEA 2020.1  
### 二、软件工具  
* MySql 8.0.13  
* Redis 5.0.2
* RocketMQ 4.7.0 
* Elasticsearch 7.3.0
* Logstash 7.3.0
* Kibana 7.3.0  
### 三、组件说明  
* 注册中心/配置中心：Nacos
* RPC调用：Dubbo
* 熔断保护：Sentinel
* 路由网关：gateway
* 数据源监控：druid
* ORM 框架：JPA / Hibernate
* 安全认证：spring security oauth2
* 在线文档：swagger2
* 分布式锁：spring-integration-redis
* 消息队列：RocketMQ
* 日志处理：ELK (Elasticsearch、Logstash、Kibana)
* 分布式事务：Seata 
### 四、项目结构  
```
spring-cloud-project -- 根目录
├── spring-cloud-admin -- 服务监控
├── spring-cloud-common -- 通用工具公共模块
├── spring-cloud-gateway -- 路由网关服务
├── spring-cloud-other -- 其他 示例服务
├── spring-cloud-user -- 用户认证授权服务
├── spring-cloud-vue -- vue 页面端
```
### 五、项目运行步骤  
1. git 下载当前项目，完成后从 IDEA中打开
2. 使用 maven 工具进行项目依赖构建
3. 启动 MySql、RocketMQ、Redis 三个必备服务 和 三个非必需的 ELK(Elasticsearch、Logstash、Kibana) 服务
4. 创建数据库并执行sql脚本。若要启动 vue 页面端 请首先安装好 node.js
5. 上述完成后即可开始启动项目服务  

nacos服务启动(win系统)：  
```
cd other/nacos/bin  
startup.cmd
```  
Sentinel控制台启动：  
```
cd other
java -Dserver.port=8088 -Dcsp.sentinel.dashboard.server=localhost:8088 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```
设置RocketMQ环境变量 ROCKETMQ_HOME：rocketmq/bin  
RocketMQ启动(win平台)：  
`cd other/rocketmq/bin`  
启动 Name Server：  
`mqnamesrv.cmd`  
启动 Broker：  
`mqbroker.cmd -n localhost:9876`

#### 项目服务启动顺序  
1. spring-cloud-gateway
2. spring-cloud-user
3. spring-cloud-other
4. spring-cloud-authorize
5. spring-cloud-admin （可选）
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
![监控中心](/images/admin.png)  
![API文档](/images/swagger.png)
***
