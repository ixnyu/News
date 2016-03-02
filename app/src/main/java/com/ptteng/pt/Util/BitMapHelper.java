package com.ptteng.pt.Util;


import android.widget.ImageView;

import com.ptteng.pt.R;

import org.xutils.image.ImageOptions;

/**
 * Created by xnyu on 2016/1/21.
 */
public class BitMapHelper {

    public static ImageOptions options = new ImageOptions.Builder()
            // 是否忽略GIF格式的图片
            .setIgnoreGif(false)
                    // 图片缩放模式
//            .setImageScaleType(ImageView.ScaleType.class)
                    // 下载中显示的图片
            .setLoadingDrawableId(R.mipmap.ic_launcher)
                    // 下载失败显示的图片
            .setFailureDrawableId(R.mipmap.ic_launcher)
                    // 得到ImageOptions对象
            .build();




















    // 设置加载图片的参数
//    ImageOptions options = new ImageOptions.Builder()
//            // 是否忽略GIF格式的图片
//            .setIgnoreGif(false)
//                    // 图片缩放模式
//            .setImageScaleType(ScaleType.CENTER_CROP)
//                    // 下载中显示的图片
//            .setLoadingDrawableId(R.drawable.ic_launcher)
//                    // 下载失败显示的图片
//            .setFailureDrawableId(R.drawable.ic_launcher)
//                    // 得到ImageOptions对象
//            .build();
//    // 加载图片
//    x.image().bind(imgv, imagUrl, options, new CommonCallback<Drawable>() {
//        @Override
//        public void onSuccess(Drawable arg0) {
//            LogUtil.e("下载成功");
//        }
//
//        @Override
//        public void onFinished() {
//            LogUtil.e("下载完成");
//        }
//
//        @Override
//        public void onError(Throwable arg0, boolean arg1) {
//
//            LogUtil.e("下载出错，" + arg0.getMessage());
//        }
//
//        @Override
//        public void onCancelled(CancelledException arg0) {
//            LogUtil.e("下载取消");
//        }
//    });
    // 加载本地图片
    // x.image().bind(imgv, "assets://test.gif", options);
    // x.image().bind(iv_big_img, new
    // File("/sdcard/test.gif").toURI().toString(), imageOptions);
    // x.image().bind(iv_big_img, "/sdcard/test.gif", imageOptions);
    // x.image().bind(iv_big_img, "file:///sdcard/test.gif", imageOptions);
    // x.image().bind(iv_big_img, "file:/sdcard/test.gif", imageOptions);
}
