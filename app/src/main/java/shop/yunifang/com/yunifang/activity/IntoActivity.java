package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangFanfan on 2016/12/6.
 * 每次进入界面都会显示界面
 */

public class IntoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.into_layout);
        AlphaAnimation mAnimation = new AlphaAnimation(1, 1);
        mAnimation.setDuration(3000);
        ImageView view = (ImageView) findViewById(R.id.into_image);
        view.startAnimation(mAnimation);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画定时实现跳转
                startActivity(new Intent(IntoActivity.this, TimeActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
