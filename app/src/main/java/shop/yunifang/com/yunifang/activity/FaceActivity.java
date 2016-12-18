package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.adapter.FaceBaseAdapter;
import shop.yunifang.com.yunifang.bean.CateGoryBean;
import shop.yunifang.com.yunifang.bean.DetailBean;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */

public class FaceActivity extends Activity implements View.OnClickListener, ViewsInterface {
    private ViewPager facePager;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ArrayList<CateGoryBean.CateBean> category;
    private Context context;
    private List<DetailBean.DetailData> detailDatas;
    private MyPent pent;
    private FaceBaseAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            DetailBean bean = (DetailBean) msg.obj;
            List<DetailBean.DetailData> list = bean.data;
            detailDatas.remove(detailDatas);
            detailDatas = list;
            adapter.setData(detailDatas);
           adapter.notifyDataSetChanged();
            adapter.setOnClickListener(new FaceBaseAdapter.OnClickListener() {
                @Override
                public void returnData(String tag) {
                    Intent intent = new Intent(context,BuyActivity.class);
                    intent.putExtra("key",tag);
                    startActivity(intent);
                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_mo);
        context = this;
        pent = new MyPent();
        pent.setFace(this);
        //数据初始化
        initViews();
    }
    private void initViews() {

        detailDatas = new ArrayList<>();
        Intent intent = getIntent();
        category = (ArrayList<CateGoryBean.CateBean>) intent.getSerializableExtra("key");
        //网络数据请求
        MyPent.myPent(context, Api.getCategory(category.get(1).children.get(6).cateId));
        textView1 = (TextView) findViewById(R.id.zs_mm);
        textView1.setOnClickListener(this);
        textView1.setTextColor(Color.RED);
        textView2 = (TextView) findViewById(R.id.sm_mm);
        textView2.setOnClickListener(this);
        textView3 = (TextView) findViewById(R.id.nj_mm);
        textView3.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.face_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaceActivity.this.finish();
            }
        });
        facePager = (ViewPager) findViewById(R.id.face_mm_viewpager);
        facePager.setAdapter(new FaceAdaper());
        //ViewPager監聽事件
        facePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                textView1.setTextColor(Color.BLACK);
                textView2.setTextColor(Color.BLACK);
                textView3.setTextColor(Color.BLACK);
                switch (position) {
                    case 0:
                        //网络数据请求
                        MyPent.myPent(context, Api.getCategory(category.get(1).children.get(6).cateId));
                        textView1.setTextColor(Color.RED);
                        break;
                    case 1:
                        //网络数据请求
                        MyPent.myPent(context, Api.getCategory(category.get(1).children.get(7).cateId));
                        textView2.setTextColor(Color.RED);
                        break;
                    case 2:
                        //网络数据请求
                        MyPent.myPent(context, Api.getCategory(category.get(1).children.get(8).cateId));
                        textView3.setTextColor(Color.RED);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zs_mm:
                //网络数据请求
                facePager.setCurrentItem(0);
                break;
            case R.id.sm_mm:
                facePager.setCurrentItem(1);

                break;
            case R.id.nj_mm:

                facePager.setCurrentItem(2);

                break;
        }
        adapter = new FaceBaseAdapter(detailDatas, context);
        adapter.setData(detailDatas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void successGet(String response) {
        DetailBean bean = new Gson().fromJson(response, DetailBean.class);
        Message msg = Message.obtain();
        msg.obj = bean;
        handler.sendMessage(msg);
    }

    @Override
    public void failedGet(String errCode) {

    }

    class FaceAdaper extends PagerAdapter {


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.cate_footer_layout, null);
            GridView gridView = (GridView) view.findViewById(R.id.footer_grid_item_cate);

            adapter = new FaceBaseAdapter(detailDatas, context);
            adapter.setData(detailDatas);
            adapter.notifyDataSetChanged();
            gridView.setAdapter(adapter);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
