package shop.yunifang.com.yunifang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

public class MainActivity extends AppCompatActivity implements ViewsInterface{

    private MyPent myPent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
//TODO数据库操作
    private void init() {
        myPent = new MyPent(this);
        MyPent.myPent(this, Api.xiaohua);
    }

    @Override
    public void successGet(String datas) {

    }

    @Override
    public void failedGet(String errCode) {

    }
}
