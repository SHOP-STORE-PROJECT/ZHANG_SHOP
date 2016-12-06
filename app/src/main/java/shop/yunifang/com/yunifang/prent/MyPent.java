package shop.yunifang.com.yunifang.prent;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.modle.CallBack;
import shop.yunifang.com.yunifang.modle.HttpUtils;

/**
 * Created by ZhangFanfan on 2016/12/6.
 */

public class MyPent {
    //对获得的数据进行操作
    private ViewsInterface mAnInterface;
    private HttpUtils mUtils;
    public MyPent(MainActivity mAnInterface) {
        this.mAnInterface = mAnInterface;
    }
    //对httputils进行初始化操作
    public static void myPent(Context context,String url){
        HttpUtils.netRequest(context).volleyRequest(url,new CallBack() {
            @Override
            public void httpSuccess(String response) {
                Log.e("TAG","                  ------"+response);
            }

            @Override
            public void httpFailed(VolleyError error) {

            }
        });
    }
}
