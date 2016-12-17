package shop.yunifang.com.yunifang.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.bean.Goods;
import shop.yunifang.com.yunifang.fragment.CanShuFragment;
import shop.yunifang.com.yunifang.fragment.JiaFragment;
import shop.yunifang.com.yunifang.fragment.ProFragment;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;
import shop.yunifang.com.yunifang.utils.Utils;

/**
 * Created by ZhangFanfan on 2016/12/14.
 */
//http://m.yunifang.com/yunifang/mobile/goods/detail?random=6716&encode=b02382bd9e457e06e09b68a6a4f26eb4&id=14
public class  BuyActivity extends FragmentActivity implements ViewsInterface{
    private ImageView buyBack;
    private MyPent pent;
    private ViewPager mPager;
    private List<Goods.GoodsGallery> goodsActivities;
    private List<Goods.GoodsActivity> activities;
    private TextView price_goods;
    private TextView desc_goods;
    private ListView buy_list;
    private Context context;
    private FrameLayout buy_frame;
    private FragmentManager manager;
    private ArrayList<Goods.GoodsComments>goodsCommentses;
    private ArrayList<Goods.GoodsAttributes>goodsAttributes;
    private ArrayList<Goods.GoodsActivity>goodActivities;
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
        goodsCommentses = new ArrayList<>();
        goodsAttributes = new ArrayList<>();
        goodActivities = new ArrayList<>();
        buyBack = (ImageView) findViewById(R.id.buy_back);
        buy_list = (ListView) findViewById(R.id.buy_list);

        manager = getFragmentManager();
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
        final Goods goods = (Goods) msg.obj;
        goodsCommentses.addAll(goods.data.comments);
        goodsAttributes.addAll(goods.data.goods.attributes);
        goodActivities.addAll(goods.data.activity);
        goodsActivities = goods.data.goods.gallery;
        activities = goods.data.activity;

        //物品信息加载
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_desc, ProFragment.newGetInstance(goodActivities));
        transaction.commit();


        //list集合数据加载
        //添加头部和脚部数据
        View headerView = View.inflate(context,R.layout.desc_header_layout,null);
        price_goods = (TextView)headerView.findViewById(R.id.price_goods);
        desc_goods = (TextView)headerView.findViewById(R.id.desc_goods);
        mPager = (ViewPager)headerView. findViewById(R.id.buy_viewpager);
        desc_goods.setText(goods.data.goods.goods_name+"\n"+goods.data.goods.efficacy);
        //文字处理
        SpannableString ss = new SpannableString("¥ :"+"\t"+goods.data.goods.shop_price+"\t\t"+goods.data.goods.market_price);
        String  shop = "¥ :"+"\t"+goods.data.goods.shop_price;
        ss.setSpan(new ForegroundColorSpan(Color.RED), 0,shop.length() ,
                //setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StrikethroughSpan(), shop.length(), ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(2.0f), 0,shop.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍
        price_goods.setText(ss);


        buy_list.addHeaderView(headerView);
        View footerView = View.inflate(context,R.layout.desc_footer_layout,null);
        buy_frame = (FrameLayout)footerView.findViewById(R.id.frame_desc);
        final TextView tv1 = (TextView) footerView.findViewById(R.id.tv_des1);
        final TextView tv2 = (TextView)footerView. findViewById(R.id.tv_des2);
        final TextView tv3 = (TextView)footerView. findViewById(R.id.tv_des3);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.RED);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame_desc, ProFragment.newGetInstance(goodActivities));
                transaction.commit();

            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setTextColor(Color.RED);
                tv1.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame_desc, CanShuFragment.canInsTance(goodsAttributes));
                transaction.commit();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 添加评论数
                tv3.setText("评论("+goods.data.comments.get(0).content.length()+")");
                tv3.setTextColor(Color.RED);
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame_desc, JiaFragment.jiaInsTance(goodsCommentses));
                transaction.commit();
            }
        });
        buy_list.addFooterView(footerView);
        buy_list.setAdapter(new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,goods.data.activity));
        //加载viewpager数据
        mPager.setAdapter(new BuyViewPagerAdapter());
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
//            Log.e("instantiateItem===",goodsActivities.get(position%goodsActivities.size()).normal_url);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
