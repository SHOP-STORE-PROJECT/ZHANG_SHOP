package shop.yunifang.com.yunifang.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shop.yunifang.com.yunifang.R;

public class TheorderActivity extends AppCompatActivity {

    private ViewPager ordervp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theorder);

        initView();

    }

    private void initView() {
    ordervp=(ViewPager)findViewById(R.id.order_vp);

    }
}
