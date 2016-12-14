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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.adapter.FaceBaseAdapter;
import shop.yunifang.com.yunifang.bean.CateGoryBean;
import shop.yunifang.com.yunifang.bean.DetailBean;
import shop.yunifang.com.yunifang.modle.Api;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */

public class FaceActivity extends Activity implements View.OnClickListener{
    private ViewPager facePager;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ArrayList<CateGoryBean.CateBean> category;
    private Context context;
    private List<DetailBean.DetailData> detailDatas;

private Handler  handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
     DetailBean bean = (DetailBean) msg.obj;
        List<DetailBean.DetailData>list = bean.data;

            detailDatas=list;

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_mo);
        context = this;
//        MyPent.myPent(context, );
        //数据初始化
        initViews();
        String  target = Api.getCategory(""+9);
        netRequest(target);
    }
    private void netRequest(final String target)  {
//        datas.removeAll(datas);
            new Thread(){
                private String json;
                private int code;
                @Override
                public void run() {
                    URL url = null;
                    try {
                        url = new URL(target);
                        //要访问的URL地址
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        code = conn.getResponseCode();
                        if(code == 200){
                            InputStream stream = conn.getInputStream();
                            json = streamToString(stream);
                            DetailBean bean = new Gson().fromJson(json,DetailBean.class);
//                            datas .addAll(bean.data);
                            Message msg = Message.obtain();
                            msg.obj = bean;
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
    }
    private String streamToString(InputStream stream) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] b = new byte[1024];
        try {
            while((len = stream.read(b))!=-1){
                bos.write(b,0,len);
            } Log.e("ByteArrayOutputStream",bos.toString());

            return bos.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void initViews() {
        detailDatas = new ArrayList<>();
        Intent intent = getIntent();
        category = (ArrayList<CateGoryBean.CateBean>)intent.getSerializableExtra("key");
//        Log.e("category  == ",category.get(1).children.get(6).cateId+"          ------------");
        textView1 = (TextView) findViewById(R.id.zs_mm);
        textView1.setOnClickListener(this);
        textView1.setTextColor(Color.RED);
        textView2 = (TextView) findViewById(R.id.sm_mm);
        textView2.setOnClickListener(this);
        textView3 = (TextView) findViewById(R.id.nj_mm);
        textView3.setOnClickListener(this);
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
             switch(position){
                 case 0:
                     //网络数据请求
                     String  target1 = Api.getCategory(category.get(1).children.get(6).cateId);
                     netRequest(target1);
                     textView1.setTextColor(Color.RED);
                     break;
                 case 1:
                     //网络数据请求
                     String  target2 = Api.getCategory(category.get(1).children.get(7).cateId);
                     netRequest(target2);
                     textView2.setTextColor(Color.RED);
                     break;
                 case 2:
                     //网络数据请求
                     String  target3 = Api.getCategory(category.get(1).children.get(8).cateId);
                     netRequest(target3);
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
        switch(v.getId()){
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
    }
    class FaceAdaper extends PagerAdapter{

        private FaceBaseAdapter adapter;

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
            View view = View.inflate(context,R.layout.cate_footer_layout,null);
            GridView gridView = (GridView) view.findViewById(R.id.footer_grid_item_cate);
                adapter = new FaceBaseAdapter(detailDatas,context);
                gridView.setAdapter(adapter);
            container.addView(view);
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
