# java-iotre-deleteDestination

## 用途：

从某一条规则中移除一个目的地。

## 使用方法：

* 第一步：在`App.java`中填写AK/SK和要移除的destination的UUID。
* 第二步：编译执行`App.java`。

**Destination的UUID 可以通过 [java-iotre-getRules](../java-iotre-getRules) 来查询。**

## 注意事项：

需要在项目中导入`bce-java-sdk`（**版本不能低于 0.10.12**），可从[百度云 开发者资源](https://cloud.baidu.com/doc/Developer/index.html)页面下载。