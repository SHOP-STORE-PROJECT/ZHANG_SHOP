package shop.yunifang.com.yunifang.bean;

import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */
//物品分类界面
public class DetailBean {
    public List<DetailData>data;

    public  class DetailData{
     public  String efficacy;
     public  String goods_img;
     public  String goods_name;
//     public  String id;
     public  String market_price;
     public  String shop_price;
     public  String watermarkUrl;
    }
}
