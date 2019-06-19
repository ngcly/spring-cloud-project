# spring-cloud-zipkin 链路追踪服务
zipkinVersion = '2.12.9' 是个大坑，目前和cloud整合会报：  
Factory method 'armeriaServer' threw exception; nested exception is java.lang.NullPointerException  
百度谷歌找了N多，没有看到相关的解决方案  
然后用2.12.8倒是可以正常启动，但是没啥用啊，访问404，根本就没有页面。  
然后在官网看到关于最新版的服务端说明，是直接去下它的jar 然后运行就可以访问链路追踪的页面了。  
整了许久看来新版服务端是放弃了与cloud的整合，我也只好放弃这个服务，还是用官网推荐的方式去了。  
当前服务只能作废了，就当爬坑的一次记录，旧版应该可以成功，但没啥意义，毕竟要跟潮流走不能守旧。