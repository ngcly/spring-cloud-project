# spring-cloud-zipkin 链路追踪服务
zipkinVersion = '2.12.9' 是个大坑，目前和cloud整合会报各种错误
百度谷歌找了N多，没有看到相关的解决方案  
然后用2.12.8倒是可以正常启动，但是没啥用啊，访问404，根本就没有页面。  
然后在官网看到关于最新版的服务端说明，是直接去下它的jar 然后运行就可以访问链路追踪的页面了。  
整了许久看来新版服务端是放弃了与cloud的整合，我也只好放弃这个服务，还是用官网推荐的方式去了。  
当前服务只能作废了，就当爬坑的一次记录，旧版2.12.3可以成功，同时elasticsearchs版本必须低于6.0 但没啥意义，毕竟要跟潮流走不能守旧。    
###最新版请前往官网下载相应的jar包  
启动方式：`java -jar zipkin.jar --RABBIT_URI=amqp://guest:guest@localhost:5672 --STORAGE_TYPE=elasticsearch --ES_HOSTS=http://localhost:9200 --ES_HTTP_LOGGING=BASIC`  
说明：  
`--RABBIT_URI=amqp://guest:guest@localhost:5672` 指定用 RabbitMQ 做数据传输  
`--STORAGE_TYPE=elasticsearch --ES_HOSTS=http//:localhost:9200 --ES_HTTP_LOGGING=BASIC` 指定用 Eelasticsearch 做数据传输
 *** 
##Zipkin是什么  
Zipkin分布式跟踪系统；它可以帮助收集时间数据，解决在microservice架构下的延迟问题；它管理这些数据的收集和查找；Zipkin的设计是基于谷歌的Google Dapper论文。
每个应用程序向Zipkin报告定时数据，Zipkin UI呈现了一个依赖图表来展示多少跟踪请求经过了每个应用程序；如果想解决延迟问题，可以过滤或者排序所有的跟踪请求，并且可以查看每个跟踪请求占总跟踪时间的百分比。

##为什么使用Zipkin  
随着业务越来越复杂，系统也随之进行各种拆分，特别是随着微服务架构和容器技术的兴起，看似简单的一个应用，后台可能有几十个甚至几百个服务在支撑；一个前端的请求可能需要多次的服务调用最后才能完成；当请求变慢或者不可用时，我们无法得知是哪个后台服务引起的，这时就需要解决如何快速定位服务故障点，Zipkin分布式跟踪系统就能很好的解决这样的问题。