package shop.yunifang.com.yunifang.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/8.
 */

public class SubBean implements Serializable{
    public int code;
    public String msg;
    public SubDataBean data;
    public class SubDataBean implements Serializable{
        public  List<Ad1Bean>ad1;
        public List<Ad5Bean>ad5;
        public List<SubjectBean> subjects;
        public List<DefaultGoodsListBean>defaultGoodsList;
        public ActivityInfoBean activityInfo;
    }

    public class SubjectBean implements Serializable{

        public String detail;
        public String image;
        public String title;
        public List<GoodsBean> goodsList;
    }

    public class GoodsBean implements Serializable{
        @SerializedName("id")
        public String gId;
        public String goods_img;
        public String goods_name;
        public String watermarkUrl;
        public float market_price;
        public float shop_price;
    }
public class DefaultGoodsListBean implements Serializable{
    @SerializedName("id")
    public String dbId;
    public String efficacy;
    public String goods_img;
    public String goods_name;
    public String market_price;
    public String shop_price;
    public String watermarkUrl;
}
    public class Ad5Bean implements Serializable{
        public  String ad_type_dynamic_data;
        public  String image;
        public  String title;
    }
    public class Ad1Bean implements Serializable{
      public String ad_type_dynamic_data;
     public String image;
    }
    public class ActivityInfoBean implements Serializable{
        public List<ActivityInfoListBean>activityInfoList;
    }
    public class ActivityInfoListBean implements Serializable{
        public String activityImg;
    }
}
