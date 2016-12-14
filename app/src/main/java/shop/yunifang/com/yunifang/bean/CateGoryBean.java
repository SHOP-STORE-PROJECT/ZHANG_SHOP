package shop.yunifang.com.yunifang.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/12.
 */

public class CateGoryBean implements Serializable{
    public DataBean data;
    public class DataBean implements Serializable{
        public List<CateBean>category;
       public List<GoodsBriefBean>goodsBrief;
    }
    public class CateBean implements Serializable{
        public String cat_name;
        public List<ChildBean> children;

    }
    public class GoodsBriefBean implements Serializable{
        public String efficacy;
        public String goods_img;
        public String goods_name;
        public String market_price;
        public String shop_price;
        public String watermarkUrl;
    }
    public class ChildBean implements Serializable{
        @SerializedName("id")
        public String cateId;
        public String cat_name;
    }
}
