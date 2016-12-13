package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.adapter.ZhuanAdapter;
import shop.yunifang.com.yunifang.bean.SubBean;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */

public class ZhuanActivity extends Activity {

    private TextView title;
    private SubBean.SubjectBean subjectBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuan_ti_layout);
        //获取页面传的值
        dataGet();
        //控件初始化
    initViews();
    }

    private void dataGet() {
        Intent intent = getIntent();
        subjectBean = (SubBean.SubjectBean) intent.getSerializableExtra("key");

    }

    private void initViews() {
        ImageView backImage = (ImageView) findViewById(R.id.back_zhuan_image);
        title = (TextView) findViewById(R.id.zhuan_text);
        GridView mGridView = (GridView) findViewById(R.id.zhuan_grid);
        title.setText(subjectBean.title+"\n\n"+subjectBean.detail);
        mGridView.setAdapter(new ZhuanAdapter(subjectBean.goodsList,this));
    }
}
