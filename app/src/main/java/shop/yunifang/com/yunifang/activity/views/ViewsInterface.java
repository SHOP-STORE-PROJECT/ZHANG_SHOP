package shop.yunifang.com.yunifang.activity.views;

import shop.yunifang.com.yunifang.bean.SubBean;

/**
 * Created by ZhangFanfan on 2016/12/6.
 */

public interface ViewsInterface {
    public void successGet(SubBean datas);
    public void failedGet(String errCode);
}
