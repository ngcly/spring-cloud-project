# spring-cloud-config 配置中心服务

Spring Cloud Config 有它的一套访问规则，我们通过这套规则在浏览器上直接访问就可以。
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
1. {application} 就是应用名称，对应到配置文件上来，就是配置文件的名称部分，例如我上面创建的配置文件。 
2. {profile} 就是配置文件的版本，我们的项目有开发版本、测试环境版本、生产环境版本，对应到配置文件上来就是以 application-{profile}.yml 加以区分，例如application-dev.yml、application-test.yml、application-prod.yml。 
3. {label} 表示 git 分支，默认是 master 分支，如果项目是以分支做区分也是可以的，那就可以通过不同的 label 来控制访问不同的配置文件了。
  
根据上面规则，我们可以通过以下地址查看配置文件内容:
```
http://localhost:8001/application-config/dev
http://localhost:8001/application-config-dev.yml
http://localhost:8001/application-gateway/dev
```