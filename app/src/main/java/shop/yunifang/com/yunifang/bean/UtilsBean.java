package shop.yunifang.com.yunifang.bean;

/**
 * Created by ZhangFanfan on 2016/12/15.
 */

public class UtilsBean {
    private String name;
    private String title;
    private String market_price;
    private String shop_price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    @Override
    public String toString() {
        return "UtilsBean{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", market_price='" + market_price + '\'' +
                ", shop_price='" + shop_price + '\'' +
                '}';
    }
}
