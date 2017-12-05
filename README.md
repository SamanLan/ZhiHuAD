# ZhiHuAD
仿知乎广告

今天看了一波鸿洋大神的推送，原文链接：https://mp.weixin.qq.com/s/BHbmtfUzg3XTaGrGcX5-bg
写得思路很清晰明了。
不过有两个小小的缺陷。。。
1. 效果方向刚好反了。（评论上有人也提到了，不过我觉得这种方向也很好看）
2. 这种写法没能支持长图，图片只能看到rv的高度的内容，超过就没有了。

于是又上网搜了一波，果然又有人已经实现了，（原文链接：https://mp.weixin.qq.com/s/VZnDPLzMDD-c7nV0KIWPnQ）而且还支持各种图片加载库加载，很棒。

两种写法的主要差异之处在于前者是继承imageview，后者是继承view。
于是乎自己在鸿洋大神的基础上又改造了一波。实现

1. 两种方向都支持了
2. 支持长图

下面看一下代码
##### 获取rv中广告item滑动距离
![获取rv中广告item滑动距离](http://upload-images.jianshu.io/upload_images/1787089-0c14ed3612809f0d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 计算比例
![计算比例](http://upload-images.jianshu.io/upload_images/1787089-f2267730c6b4efba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
主要是根据``广告view距离rv顶部的距离``以及``(rv高度 - 广告view高度)``来计算百分比。控制方向只是一个简单减法而已。
之所以要所有计算都需要``-广告view高度``是因为原来知乎的效果是广告item为第一项或者最后一项的时候，图片已经是显示最上或者最下了，这样从第一项滑到最后一项就可以看完整个广告的图片了。
这样就完成了``两种方向都支持了``，``支持长图``这两个需求了

#### 接下来是效果图时候了
![正向.gif](http://upload-images.jianshu.io/upload_images/1787089-39d6695656381549.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![反向.gif](http://upload-images.jianshu.io/upload_images/1787089-54ff3d9a0d5aa304.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其实谷歌日历里面早就有这个效果了
![谷歌日历.gif](http://upload-images.jianshu.io/upload_images/1787089-44f3832027014b2a.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)






