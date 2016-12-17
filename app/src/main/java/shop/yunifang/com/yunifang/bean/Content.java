package shop.yunifang.com.yunifang.bean;

/**
 * Created by ZhangFanfan on 2016/12/17.
 */

public class Content {
    private String content;

    public Content(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "     " + content;
    }
}
