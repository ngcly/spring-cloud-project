# spring-cloud-txlcn 分布式事务服务
TX_LCN bug还是挺多的。  
第一：如果用springboot devtools 会导致数据源报错。  
第二：和zipkin有冲突，在加上oauth2依赖后，会报
```
Field existingConcurrencyStrategy in org.springframework.cloud.netflix.hystrix.security.HystrixSecurityAutoConfiguration required a single bean, but 2 were found:
	- tracingHystrixConcurrencyStrategy: defined in URL [jar:file:/C:/Users/chenning/.gradle/caches/modules-2/files-2.1/com.codingapi.txlcn/txlcn-tracing/5.0.2.RELEASE/e4d1a9fc988d78dd98c9a616270272829b37871b/txlcn-tracing-5.0.2.RELEASE.jar!/com/codingapi/txlcn/tracing/http/TracingHystrixConcurrencyStrategy.class]
	- sleuthHystrixConcurrencyStrategy: defined by method 'sleuthHystrixConcurrencyStrategy' in class path resource [org/springframework/cloud/sleuth/instrument/hystrix/SleuthHystrixAutoConfiguration.class]
```
第三:TM端对于yml的优先级非常低，用bootstrap.yml都没用，只能用properties  
以上还只是在整合时发现的一些问题，利用测试理论，当前发现的bug越多说明隐藏的bug更多，而且github上面上次更新是半年前，跟没人维护的一样。  
所以这个自己玩玩就好，上生产就需要慎重考虑了。