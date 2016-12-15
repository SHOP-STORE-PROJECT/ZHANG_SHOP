package shop.yunifang.com.yunifang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import shop.yunifang.com.yunifang.R;

public class TheorderActivity extends AppCompatActivity {

    private ViewPager ordervp;

//<<<<<<< HEAD
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_theorder);
//
//        initView();
//
//    }
//
//    private void initView() {
//    ordervp=(ViewPager)findViewById(R.id.order_vp);
//
//    }
//=======
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theorder);

        initView();
    }

    private void initView() {
        ordervp=(ViewPager)findViewById(R.id.order_vp);

    }
//>>>>>>> 5c66b98def03c3b38c2e43cde0a2bd0599ab5635
}
