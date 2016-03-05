# News
##可以学到:
>* 自定义基类Activity
>* 使用Webview时避免内存泄露
>* OKhttp封装框架的用法
>* 用Gson解析网络数据
>* 用Picasso加载缓存图片
>* 使用ShareSDK集成新浪微博,QQ,微信等第三方平台登录和分享
>* 自定义ListView实现下路刷新和下拉加载
>* 用RecyclerView实现瀑布流
>* 集成极光推送

##使用到的库
	dependencies {
	    compile 'com.android.support:appcompat-v7:23.1.1'
	    compile 'com.jeremyfeinstein.slidingmenu:library:1.3@aar' //侧滑
	    compile 'com.orhanobut:logger:1.11' //漂亮的log工具
	    compile 'com.zhy:okhttputils:2.2.0'	//okhttp包装类
	    compile 'com.google.code.gson:gson:2.6.1' //json数据解析
	    compile 'org.simple:androideventbus:1.0.5.1' //事件总线
	    compile 'com.gxz.pagerslidingtabstrip:library:1.2' //ViewPager头部指示器
	    compile 'com.nineoldandroids:library:2.4.0'
	    compile 'com.squareup.picasso:picasso:2.5.2' //图片加载库
	    compile project(':onekeyshare') //ShareSDK第三方分享和登录
	    compile 'com.android.support:recyclerview-v7:23.1.1'
	}
