# Shopping

# 运行环境
  JDK版本：jdk 8 <br/>
  MAVEN版本：3.6.3 <br/>
  MySQL版本：5.7.33 <br/>
  Redis版本：5.0.12 <br/>
  SpringBoot版本：2.2.2 <br/>
  SpringCloud版本：Hoxton.SR1 <br/>
  SpringCloudAlibaba版本：2.1.0 <br/>
  
# 说明
  以80开头的是各个功能模块，9001是认证授权服务，以91开头的是网关 <br/>
  以nacos（1.2.1）作为服务注册与发现，配置中心，需要修改配置文件：application.properties，将数据源切换为MySQL，再创建nacos_config数据库，执行nacos_config.sql脚本 <br/>
  以sentinel（1.7.2）作为服务熔断与限流，直接执行java -jar的命令运行sentinel的jar包即可 <br/>
  以seata（0.9.0）作为分布式事务，将seata注册进nacos，需要修改配置文件file.conf，registry.conf，再创建seata数据库，执行seata.sql脚本 <br/>

# 启动项目
  依次启动nacos，sentinel，seata，再启动以91开头的网关服务，再启动9001认证授权服务，最后再启动需要的微服务模块 <br/>
  基于OAuth2.0协议，以token令牌的方式实现认证授权，先请求网关，由网关将请求转发到各个微服务并携带token，每个微服务从请求中获取token，再将用户信息填充到SpringSecurity上下文中 <br/>
  
  申请令牌的地址为：localhost:9101/authentication-authorization/oauth/token?client_id=p1&client_secret=secret&grant_type=password&username=root&password=123456，根据需求可以修改携带的参数，请求携带的参数必须与oauth_client_details表中内容匹配 <br/>
  验证令牌的地址为：localhost:9101/authentication-authorization/oauth/check_token，在请求体中携带申请到的令牌进行访问 <br/>
