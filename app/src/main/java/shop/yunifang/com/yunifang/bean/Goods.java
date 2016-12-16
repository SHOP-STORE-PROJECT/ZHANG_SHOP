package shop.yunifang.com.yunifang.bean;

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
    public class GoodsActivity{
     public String description;
     public String title;

        @Override
        public String toString() {
            return "   " + title;
        }
    }
    public class GoodsComments{
        public GoodsUser user;
        public String content;
    }
    public class GoodsDes{
        public String goods_name;
        public String efficacy;
        public float market_price;
        public float shop_price;
        public List<GoodsAttributes>attributes;
        public List<GoodsGallery>gallery;
    }
    public class GoodsAttributes implements Parcelable {
        public String attr_name;
        public String attr_value;

        protected GoodsAttributes(Parcel in) {
            attr_name = in.readString();
            attr_value = in.readString();
        }
        public  final Creator<GoodsAttributes> CREATOR = new Creator<GoodsAttributes>() {
            @Override
            public GoodsAttributes createFromParcel(Parcel in) {
                return new GoodsAttributes(in);
            }

            @Override
            public GoodsAttributes[] newArray(int size) {
                return new GoodsAttributes[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(attr_name);
            dest.writeString(attr_value);
        }
    } public class GoodsGallery{
        public String normal_url;
    }
public  class GoodsUser{
    public String icon;
    public String nick_name;
}
}
