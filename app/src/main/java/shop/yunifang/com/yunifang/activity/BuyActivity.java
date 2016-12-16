package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.bean.Goods;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;
import shop.yunifang.com.yunifang.utils.Utils;

/**
 * Created by ZhangFanfan on 2016/12/14.
 */
//http://m.yunifang.com/yunifang/mobile/goods/detail?random=6716&encode=b02382bd9e457e06e09b68a6a4f26eb4&id=14
public class  BuyActivity extends Activity implements ViewsInterface{
    private ImageView buyBack;
    private MyPent pent;
    private ViewPager mPager;
    private List<Goods.GoodsGallery> goodsActivities;
    private WebView webView1;
    private List<Goods.GoodsActivity> activities;
    private WebView webView2;
    private WebView webView3;
    private TextView price_goods;
    private TextView desc_goods;
    private ListView buy_list;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_layout);
        context = this;
         pent = new MyPent();
        pent.setBuy(this);
        getGoodsId();
        intiViews();
    }
    private void getGoodsId() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("key");
        MyPent.myPent(this,Api.goodsId(id));
    }
    private void intiViews() {
        price_goods = (TextView) findViewById(R.id.price_goods);
        desc_goods = (TextView) findViewById(R.id.desc_goods);

        mPager = (ViewPager) findViewById(R.id.buy_viewpager);
        buyBack = (ImageView) findViewById(R.id.buy_back);
        buy_list = (ListView) findViewById(R.id.buy_list);
        // 点击返回商品界面
        buyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//      startActivity(new Intent(BuyActivity.this,ZhuanActivity.class));
             BuyActivity.this.finish();
            }
        });
    }
    @Override
    public void successGet(String response) {
        Goods goods = new Gson().fromJson(response,Goods.class);
        Message msg = Message.obtain();
        msg.obj = goods;
        handler.sendMessage(msg);
    }
    @Override
    public void failedGet(String errCode) {

    }

private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        Goods goods = (Goods) msg.obj;
        goodsActivities = goods.data.goods.gallery;
        activities = goods.data.activity;
        mPager.setAdapter(new BuyViewPagerAdapter());

        //物品信息加载

        desc_goods.setText(goods.data.goods.goods_name+"\n"+goods.data.goods.efficacy);
        price_goods.setText(goods.data.goods.shop_price+"\t"+goods.data.goods.market_price);

        //list集合数据加载
buy_list.setAdapter(new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,goods.data.activity));
    }
};
    public  class BuyViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            Utils.showImage(goodsActivities.get(position%goodsActivities.size()).normal_url,imageView);

            // Now just add ImageView to ViewPager and return it
            container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
