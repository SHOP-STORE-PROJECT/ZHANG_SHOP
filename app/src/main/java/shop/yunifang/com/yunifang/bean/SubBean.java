package shop.yunifang.com.yunifang.bean;

import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/8.
 */

public class SubBean {
    public int code;
    public String msg;

    public SubDataBean data;

    public class SubDataBean {
        public List<SubjectBean> subjects;
    }

    public class SubjectBean {
        public String detail;
        public String image;
        public String title;
        public List<GoodsBean> goodsList;
    }

    public class GoodsBean {
        public String goods_img;
        public String goods_name;
        public String watermarkUrl;
        public float market_price;
        public float shop_price;
    }


}
