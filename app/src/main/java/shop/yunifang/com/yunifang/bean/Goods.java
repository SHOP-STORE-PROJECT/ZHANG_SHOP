package shop.yunifang.com.yunifang.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ZhangFanfan on 2016/12/15.
 */

public class Goods {
    public GoodsData data;
    public class GoodsData{
        public List<GoodsActivity>activity;
        public List<GoodsComments>comments;
        public GoodsDes goods;
    }
    @SuppressLint("ParcelCreator")
    public class GoodsActivity implements Parcelable{
     public String description;
     public String title;

        @Override
        public String toString() {
            return "   " + title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
    @SuppressLint("ParcelCreator")
    public class GoodsComments implements Parcelable{
        public GoodsUser user;
        public String content;

        @Override
        public String toString() {
            return "\t" + content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
    public class GoodsDes{
        public String goods_name;
        public String efficacy;
        public float market_price;
        public float shop_price;
        public List<GoodsAttributes>attributes;
        public List<GoodsGallery>gallery;
    }
    @SuppressLint("ParcelCreator")
    public class GoodsAttributes implements Parcelable {
        public String attr_name;
        public String attr_value;

        @Override
        public String toString() {
            return  attr_name + "\t" + attr_value;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    } public class GoodsGallery{
        public String normal_url;
    }
public  class GoodsUser{
    public String icon;
    public String nick_name;
}
}
