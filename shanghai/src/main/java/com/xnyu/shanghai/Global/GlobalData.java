package com.xnyu.shanghai.Global;

import android.renderscript.Type;

/**
 * Created by Administrator on 2016/2/16 0016.
 */
public class GlobalData {
    public static final String SERVER_URL = "http://www.ixnyu.com/zhsh";
    public static final String CATEGORIES = SERVER_URL + "/categories.json";
    public static final String PHOTOS = SERVER_URL + "/photos/photos_1.json";
    public static final String TX_KEY = "8eabfe86e5e64dab513a99abcfe758fd";
    public static final String TX_SERVER = "http://api.huceo.com/";
    //key	string	是	urlParam	API密钥（请在个人中心获取）	用户自己的key
    //num	int	    是	urlParam	指定返回数量，最大50	           10
    //rand	int	    否	urlParam	参数值为1则随机获取	           0
    //word	string	否	urlParam	检索关键词	                   上海
    //page	int	    否	urlParam	翻页，每页输出数量跟随num参数	   1

    public static final String TX_SOCIAL = "http://api.huceo.com/social/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_GUONEI="http://api.huceo.com/guonei/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_WORLD = "http://api.huceo.com/world/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_TIYU ="http://api.huceo.com/tiyu/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_YULE ="http://api.huceo.com/huabian/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_MEI ="http://api.huceo.com/meinv/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_KEJI ="http://api.huceo.com/keji/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_QIWEN ="http://api.huceo.com/qiwen/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String TX_JIAN ="http://api.huceo.com/health/?key=8eabfe86e5e64dab513a99abcfe758fd&num=15";
    public static final String[] TX_TITLE = {"社会","国内","国际","美女","奇闻","健康","体育","科技","娱乐"};

    public static String replaceURL(String url) {
        return url.replace("http://10.0.2.2:8080/zhbj", GlobalData.SERVER_URL);
    }

}
