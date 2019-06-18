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
| spring-cloud-zuul | 8004 | 布式微服务网关 |
| spring-cloud-auth | 8005 | 授权服务 |
| spring-cloud-eureka | 8000 | 分布式微服务注册中心 |
| spring-cloud-admin | 8001 | spring boot admin 服务监控中心 |
| spring-cloud-config | 8002 | 分布式微服务配置中心 |
| spring-cloud-zipkin | 8003 | 分布式微服务链路追踪 |