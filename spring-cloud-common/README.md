# spring-cloud-common 公共模块  
由于common包并不是可执行的启动类，所以不可以在build.gradle中配置  
apply plugin: 'org.springframework.boot'  
由此根目录的build.gradle中所有子项目配置里不可加这个配置，只能麻烦点在每个服务里单独加这个