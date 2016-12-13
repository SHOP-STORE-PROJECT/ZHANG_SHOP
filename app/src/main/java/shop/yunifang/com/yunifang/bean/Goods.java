package shop.yunifang.com.yunifang.bean;

/**
 * Created by ZhangFanfan on 2016/12/15.
 */

public class Goods {
    private String name;
    private String imagePath;
    private String price;
    private String currentPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price='" + price + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                '}';
    }
}
