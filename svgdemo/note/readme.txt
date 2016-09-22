ref
[a] http://blog.csdn.net/eclipsexys/article/details/51838119

ImageView 使用 Vector
兼容有3点： 1 build.gradle
           2 appcompatActivity
           3 imageView.src 改为 imageview.srcCompat

Button 使用 Vector
1 只能通过 Selector。
2 Activity
  static {
      AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }


[b] https://github.com/xuyisheng/VectorDemo
这个是一个开源库， 可以学习一下。