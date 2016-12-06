package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.R;

import static android.view.View.inflate;

/**
 * Created by ZhangFanfan on 2016/12/6.
 * 导航页
 */

public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private List<View> mList;
    private Context mContext;
    private SharedPreferences spf;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        spf = getSharedPreferences("config", MODE_PRIVATE);
        flag = spf.getBoolean("key", true);
        if (flag) {
            SharedPreferences.Editor editor = spf.edit();
            editor.putBoolean("key", false).commit();
            setContentView(R.layout.guide_layout);
            //视图初始化
            initViews();
            //添加图片
            addViews();
        } else {
            startActivity(new Intent(mContext, IntoActivity.class));
            finish();
        }
    }

    private void addViews() {
        mList.add(inflate(mContext, R.layout.guide_item, null));
        mList.add(inflate(mContext, R.layout.guide_item1, null));
        mList.add(inflate(mContext, R.layout.guide_item2, null));
        mList.add(inflate(mContext, R.layout.guide_item3, null));
        View mView = View.inflate(mContext, R.layout.guide_item4, null);
        ImageView mImageView = (ImageView) mView.findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        });
        mList.add(mView);
        mViewPager.setAdapter(new MyPagerAdapter());
    }

    private void initViews() {

        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mList = new ArrayList<>();
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(mList.get(position));
        }
    }
}
