package shop.yunifang.com.yunifang.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import shop.yunifang.com.yunifang.application.MyApp;

/**
 * Created by ZhangFanfan on 2016/12/10.
 */

public class Utils {
    //工具类imageloader
    public static void showImage(String url, ImageView view){
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(url,view, MyApp.getDisplayOptions());
    }
}
