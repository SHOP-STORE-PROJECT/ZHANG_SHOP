package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangFanfan on 2016/12/14.
 */

public class  BuyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_layout);
        intiViews();
    }

    private void intiViews() {
        ImageView buyBack = (ImageView) findViewById(R.id.buy_back);
        //点击返回商品界面
        buyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyActivity.this,MainActivity.class));
            }
        });
    }

}
