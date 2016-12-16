package shop.yunifang.com.yunifang.adapter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class UserBean { public List<CateData> data;
    public class CateData{
        @SerializedName("id")
        public String dbId;
        public String efficacy;
        public String goods_img;
        public String goods_name;
        public String market_price;
        public String shop_price;
    }
}
