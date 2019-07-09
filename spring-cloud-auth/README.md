# spring-cloud-auth 认证授权服务  
获取access_token请求（/oauth/token）
请求所需参数：client_id、client_secret、scope、grant_type、username、password  
``http://localhost:8006/oauth/token?client_id=cloud_client&client_secret=secret&scope=all&grant_type=password&username=admin&password=123456``  
  
检查token是否有效请求（/oauth/check_token）  
请求所需参数：token  
``http://localhost:8006/oauth/check_token?token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx``  
  
刷新token请求（/oauth/token）  
####请求所需参数：grant_type、refresh_token、client_id、client_secret  
``http://localhost:8006/oauth/token?grant_type=refresh_token&refresh_token=fbde81ee-f419-42b1-1234-9191f1f95be9&client_id=cloud_client&client_secret=secret``  

获取当前用户权限信息  
``http://localhost:8006/user/principal?access_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx``  

销毁token  
``http://localhost:8006/user/revoke?access_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx``