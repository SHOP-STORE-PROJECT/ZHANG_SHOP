package shop.yunifang.com.yunifang.bean;

import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/12.
 */

public class CateGoryBean {
    public DataBean data;
    public class DataBean{
        public List<CategoryBean>category;
       public List<GoodsBriefBean>goodsBrief;
    }
    public class CategoryBean{

    }
    public class GoodsBriefBean{
        public String efficacy;
        public String goods_img;
        public String goods_name;
        public String market_price;
        public String shop_price;
        public String watermarkUrl;
    }
}
