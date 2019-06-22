# Spring-Cloud-Project 分布式微服务项目


> 相关内容还在摸索中...

## 核心依赖

SpringCloud 分布式微服务基础架构采用的是当前官网最新的版本。

| 依赖 | 版本 |
|:--|:--|
| Spring Boot | 2.1.5.RELEASE |
| Spring Cloud | Greenwich.RS2 |

## 模块概述

| 模块名称 | 端口号 | 功能 |
|:--|:--|:--|
| spring-cloud-eureka | 8000 | 注册中心 |
| spring-cloud-config | 8001 | 配置中心 |
| spring-cloud-zuul | 8002 | 网关服务 |
| spring-cloud-admin | 8003 | 监控中心 |
| spring-cloud-zipkin | 8004 | 链路追踪 |
| spring-cloud-turbine | 8005 | 聚合监控 |
| spring-cloud-common | 无 | 公共模块 |
| spring-cloud-auth | 8006 | 授权服务 |
| spring-cloud-user | 8007 | 用户服务 |
