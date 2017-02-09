# 链接
- 微信公众平台：https://mp.weixin.qq.com
- 开发者工具下载：https://mp.weixin.qq.com/wiki/10/e5f772f4521da17fa0d7304f68b97d7e.html
- 企业号帮助文档：http://qydev.weixin.qq.com/wiki/index.php?title=首页
- 企业号调试工具：http://qydev.weixin.qq.com/debug
- 企业号返回码：http://qydev.weixin.qq.com/wiki/index.php?title=全局返回码说明
- 订阅号与服务号开发者文档：http://mp.weixin.qq.com/wiki/home/index.html
- 订阅号与服务号返回码：http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html

# 微信企业号
## 关于myappid
注意要跟cropId和appid区分开来，默认情况下，cropId和appid是等同的，都是微信给每个企业号定的唯一标识。
而myappid为本程序自定义标识，同样用于区分不同的企业号，也是唯一的，在实际项目中，一般是会有多个企业号同时投入使用。

## 回调模式
回调模式需使用com.qq.weixin.mp.aes包，还需下载JCE无限制权限策略文件，详情见“zxy.web.controller.weixinqyh.CallbackController”相关说明
或“http://qydev.weixin.qq.com/wiki/index.php?title=加解密库下载与返回码”。

## 客户端跳转报错“redirect_uri错误”
填写一下可信域名即可。
