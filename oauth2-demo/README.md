## oauth2
四种角色
- 用户
- 第三方软件
- 授权服务器
- 受保护资源

用户拥有受保护资源，用户访问第三方软件时，第三方软件向授权服务器请求授权
授权服务器会重定向到一个授权页面让用户确认
用户确认后授权服务器颁发令牌给第三方软件
第三方软件使用令牌访问受保护资源
用户就可以使用第三方软件了

简单来说，oauth2就是一套授权的流程


### 授权码和访问令牌

### 刷新令牌