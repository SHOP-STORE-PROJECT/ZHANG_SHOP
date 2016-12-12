package shop.yunifang.com.yunifang.prent;

import android.content.Context;

import com.android.volley.VolleyError;

import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.fragment.Fragment_fl;
import shop.yunifang.com.yunifang.fragment.Fragment_sy;
import shop.yunifang.com.yunifang.modle.CallBack;
import shop.yunifang.com.yunifang.modle.HttpUtils;

/**
 * Created by ZhangFanfan on 2016/12/6.
 */

public class MyPent {
    //对获得的数据进行操作
    private static ViewsInterface mAnInterface;
    private static int first;
    //对httputils进行初始化操作
    public static void myPent(Context context,String url){
        HttpUtils.netRequest(context).volleyRequest(url,new CallBack() {
            @Override
            public void httpSuccess(String response) {
//                Log.e("TAG","                  ------"+response);
//                SubBean bean = new Gson().fromJson(response,SubBean.class);
//                String msg = bean.msg;
//                if(first==0||first ==1){
//                    Log.e("MyPent==",response+"--------MyPent");


                    mAnInterface.successGet(response);

//                }
            }
            @Override
            public void httpFailed(VolleyError error) {
                mAnInterface.failedGet("数据返回失败");
            }
        });
    }
    public void setSy(Fragment_sy mAnInterface) {
        this.mAnInterface = mAnInterface;
        first = 1;
    }

    public void setFl(Fragment_fl mAnInterface) {
        this.mAnInterface = mAnInterface;
        first = 2;
    }

    public void setMain(ViewsInterface mAnInterface) {
        this.mAnInterface = mAnInterface;
        first = 0;
    }
}
