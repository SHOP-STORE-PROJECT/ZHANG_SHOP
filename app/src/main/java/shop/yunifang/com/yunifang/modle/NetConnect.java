package shop.yunifang.com.yunifang.modle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ZhangFanfan on 2016/12/8.
 */
//网络请求判断
public class NetConnect {
   public static boolean isNetworkConnected(Context context) {
            if (context != null) {
                    ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                           .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                   if (mNetworkInfo != null) {
                       return mNetworkInfo.isAvailable();
                   }
                }
           return false;
       }
}
