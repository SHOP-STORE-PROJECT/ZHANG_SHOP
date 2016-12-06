package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangFanfan on 2016/12/6.
 */

public class TimeActivity extends Activity {

    private Context mContext;
    private ImageView mImageView;
    private TextView mTextView;
    //开启handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//        super.handleMessage(msg);
            int count = msg.what;
            //计时器实现跳转
            if (count > 0) {
//                mTextView.setText("跳转 " + count);
                count--;
                handler.sendEmptyMessageDelayed(count, 1500);
            } else {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_layout);
        //属性初始化
        initViews();
        handler.sendEmptyMessage(3);
    }

    private void initViews() {
        mContext = this;
        mImageView = (ImageView) findViewById(R.id.time_image);
        mTextView = (TextView) findViewById(R.id.time_tv);
        //点击按钮实现跳转
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();

            }
        });
    }
}
