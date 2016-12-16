package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.adapter.CateAdapter;
import shop.yunifang.com.yunifang.adapter.CateDataAdapter;
import shop.yunifang.com.yunifang.adapter.UserBean;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class CateActivity extends Activity implements ViewsInterface{
    private Context context;
    private MyPent pent;
    private TextView cate_text;
    private ImageView imageView;
private Handler handler = new Handler(){

    private CateDataAdapter adapter;
    private List<UserBean.CateData> cateDatas;

    @Override
    public void handleMessage(Message msg) {
        cateDatas = (List<UserBean.CateData>) msg.obj;
        adapter = new CateDataAdapter(context);
        adapter.setData(cateDatas);
        gridView.setAdapter(adapter);
        adapter.setOnCateAdapter(new CateAdapter.onClickCate() {
            @Override
            public void myCate(String position) {
                Intent intent = new Intent(context, BuyActivity.class);
                intent.putExtra("key",position+"");
                startActivity(intent);
            }
        });
    }
};
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cate_layout);
        context = this;
        pent = new MyPent();
        pent.setCate(this);
        initViews();
    }
    private void initViews() {
        //数据请求
        String pwd = getIntent().getStringExtra("key");
        MyPent.myPent(context, Api.getCategory(pwd));
        cate_text = (TextView) findViewById(R.id.what_face);
        imageView = (ImageView) findViewById(R.id.cate__back);
        gridView = (GridView) findViewById(R.id.cate_grid);
        //设置界面
        cate_text.setText(pwd);
        //页面返回
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CateActivity.this.finish();
            }
        });
    }
//数据获取
    @Override
    public void successGet(String response) {
        UserBean bean = new Gson().fromJson(response,UserBean.class);
        List<UserBean.CateData>list = bean.data;
        Message msg = Message.obtain();
        msg.obj = list;
        handler.sendMessage(msg);
    }

    @Override
    public void failedGet(String errCode) {

    }
}
