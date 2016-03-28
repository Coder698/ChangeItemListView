##ChangeItemListView
自定义RecyclerViewview的item条目滚动动画

通过监听recyclerView的滑动, 实时改变item的大小,实现在最上并可见的item条目放大效果,并且的recyclerView滑动的时候有动画过度

因为recyclerView是使用viewHodler复用条目的,所以当recyclerView滑动过快的时候,会出现viewHodler复用出错的情况,所以手动设置了recyclerView的滑动速度减半,来修补bug

可以添加recyclerView滑动时各种过度动画,比如透明度,模糊效果,文字大小等等...

ps: 代码只提供思路

![image](https://github.com/Zhaoss/ChangeItemListView/blob/master/image/1.jpg?raw=true)
![image](https://github.com/Zhaoss/ChangeItemListView/blob/master/image/2.jpg?raw=true)
