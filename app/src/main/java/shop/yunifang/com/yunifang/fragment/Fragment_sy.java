package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.WebActivity;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.adapter.GridAdapter;
import shop.yunifang.com.yunifang.adapter.MyFirstAdapter;
import shop.yunifang.com.yunifang.bean.SubBean;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;
import shop.yunifang.com.yunifang.utils.Utils;
import shop.yunifang.com.yunifang.view.ScalePageTransformer;
import shop.yunifang.com.yunifang.view.ZoomViewPager;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_sy extends Fragment implements ViewsInterface,View.OnClickListener {
    private Context context;
    private View view;
    private PullToRefreshListView mPullRefreshListView;
    private MyPent pent;
    private MyFirstAdapter adapter;
    private ListView mListView1;
    private GridView gridView;
    private View view2;
    private    List<SubBean.SubjectBean> subjectBeen;
    private Handler pagerhHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int current = pager.getCurrentItem();
            if(current != -1){
                current++;
                pager.setCurrentItem(current);
                pagerhHandler.sendEmptyMessageDelayed(current,2000);
            }
        }
    };
    private ViewPager pager;
    private View view1;
    private List<SubBean.Ad1Bean> ad1Been;
    private List<SubBean.ActivityInfoListBean> activityInfoListBeen;
    private ArrayList<SubBean.Ad5Bean> myBeen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //数据初始化
        initViews();
        //刷新界面
        refreshFragment();
        return view;
    }
    private void initViews() {
        context = getActivity();
        pent = new MyPent();
        pent.setSy(this);
        MyPent.myPent(context, Api.homeUrl);
        view = View.inflate(context, R.layout.fragment_sy, null);
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_sy);
        mListView1 = mPullRefreshListView.getRefreshableView();
        // Need to use the Actual ListView when registering for Context Menu
        registerForContextMenu(mListView1);
    }
    private void refreshFragment() {
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //刷新完成
                new Fragment_sy.GetDataTask().execute();

            }
        });
        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "End of List!", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * Add Sound Event Listener
         */
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(getActivity());
        mPullRefreshListView.setOnPullEventListener(soundListener);
        view1 = View.inflate(context, R.layout.first_viewpager, null);

        pager = (ViewPager) view1.findViewById(R.id.first_pager);

        ZoomViewPager pager1 = (ZoomViewPager) view1.findViewById(R.id.youhui_viewpager);
        pager1.setPageTransformer(true, new ScalePageTransformer());
        pager1.setAdapter(new MyYouhuiAdapter());
        MyViewPagerAdapter   pagerAdapter = new MyViewPagerAdapter();
        pager.setAdapter(pagerAdapter);
        //添加一个脚部
        view2 = View.inflate(context, R.layout.footer_view_layout, null);
        gridView = (GridView) view2.findViewById(R.id.footer_grid_item);
        mListView1.addFooterView(view2);
        mListView1.addHeaderView(view1);
        //使用Handler消息机制实现无限轮播
        pagerhHandler.sendEmptyMessage(0);
//        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //// TODO: 2016/12/13 专题页面跳转
////                Intent intent = new Intent(context, ZhuanActivity.class);
////                intent.putExtra("subject",subjectBeen.get(position));
////                startActivity(intent);
//                Toast.makeText(context,"专题页面跳转  ====="+position,Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    //解析网络数据添加显示
    @Override
    public void successGet(String response) {

        SubBean datas = new Gson().fromJson(response, SubBean.class);
        //首页头Viewpager数据
        ad1Been = datas.data.ad1;
        //热门条目点击事件
        List<SubBean.Ad5Bean>ad5Been = datas.data.ad5;
        showView1(ad5Been);
        //activityInfoList
        activityInfoListBeen = datas.data.activityInfo.activityInfoList;

        List<SubBean.DefaultGoodsListBean> defaultGoodsListBeen = datas.data.defaultGoodsList;
        GridAdapter gridAdapter = new GridAdapter(context);
        gridAdapter.setData(defaultGoodsListBeen);
        gridView.setAdapter(gridAdapter);
        List<SubBean.SubjectBean> subjectsBeen = datas.data.subjects;
        Message msg = Message.obtain();
        msg.obj = subjectsBeen;
        handler.sendMessage(msg);
//        mAdapter.notifyDataSetChanged();

//刷新完成
//        mPullRefreshListView.onRefreshComplete();
    }

    private void showView1(List<SubBean.Ad5Bean> ad5Been) {
        myBeen = new ArrayList<>();
        myBeen.addAll(ad5Been);
        ImageView year_happy1 = (ImageView) view1.findViewById(R.id.year_happy1);
        Utils.showImage(ad5Been.get(0).image,year_happy1);
        year_happy1.setOnClickListener(this);
        ImageView year_happy2 = (ImageView) view1.findViewById(R.id.year_happy2);
        Utils.showImage(ad5Been.get(1).image,year_happy2);
        year_happy2.setOnClickListener(this);
        ImageView year_happy3 = (ImageView) view1.findViewById(R.id.year_happy3);
        Utils.showImage(ad5Been.get(2).image,year_happy3);
        year_happy3.setOnClickListener(this);
        ImageView year_happy4 = (ImageView) view1.findViewById(R.id.year_happy4);
        Utils.showImage(ad5Been.get(3).image,year_happy4);
        year_happy4.setOnClickListener(this);
        ImageView year_happy5 = (ImageView) view1.findViewById(R.id.year_happy5);
        Utils.showImage(ad5Been.get(4).image,year_happy5);
        year_happy5.setOnClickListener(this);
        ImageView year_happy6 = (ImageView) view1.findViewById(R.id.year_happy6);
        Utils.showImage(ad5Been.get(5).image,year_happy6);
        year_happy6.setOnClickListener(this);
        ImageView year_happy7 = (ImageView) view1.findViewById(R.id.year_happy7);
        Utils.showImage(ad5Been.get(6).image,year_happy7);
        year_happy7.setOnClickListener(this);
        ImageView year_happy8 = (ImageView) view1.findViewById(R.id.year_happy8);
        Utils.showImage(ad5Been.get(7).image,year_happy8);
        year_happy8.setOnClickListener(this);
    }
    @Override
    public void failedGet(String errCode) {
        Log.e("首页", "首页数据请求失败");
    }

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            subjectBeen = new ArrayList<>();
            List<SubBean.SubjectBean> bean = (List<SubBean.SubjectBean>) msg.obj;
            subjectBeen.addAll(bean);
            adapter = new MyFirstAdapter(context);
            adapter.setData(bean);
            mListView1.setAdapter(adapter);
        }
    };
//八张图片点击事件
    @Override
    public void onClick(View v) {
        Intent intent  = new Intent(context,WebActivity.class);
        switch(v.getId()){
            case R.id.year_happy8:
                intent.putExtra("year",7);
                break;
            case R.id.year_happy1:
                intent.putExtra("year",0);
                break;
            case R.id.year_happy2:
                intent.putExtra("year",1);
                break;
            case R.id.year_happy3:
                intent.putExtra("year",2);
                break;
            case R.id.year_happy4:
                intent.putExtra("year",3);
                break;
            case R.id.year_happy5:
                intent.putExtra("year",4);
                break;
            case R.id.year_happy6:
                intent.putExtra("year",5);
                break;
            case R.id.year_happy7:
                intent.putExtra("year",6);
                break;
        }
        intent.putExtra("ad5bean",myBeen);
        startActivity(intent);
    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            Utils.showImage(ad1Been.get(position%ad1Been.size()).image,imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("header",ad1Been.get(position%ad1Been.size()).ad_type_dynamic_data);
                    startActivity(intent);
                }
            });
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

    public class MyYouhuiAdapter extends PagerAdapter {
//        int[] sDrawables = {R.drawable.youhui_pagera, R.drawable.youhui_pagerb, R.drawable.youhui_pagerc,
//                R.drawable.youhui_pagerd, R.drawable.youhui_pagere};

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
//            imageView.setImageResource(sDrawables[position%sDrawables.length]);
            // Now just add ImageView to ViewPager and return it
            Utils.showImage(activityInfoListBeen.get(position%activityInfoListBeen.size()).activityImg,imageView);
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
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return null;
        }
        @Override
        protected void onPostExecute(String[] result) {

            adapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
//    //给脚部添加数据
//    private void footerAddDatas(List<SubBean.DefaultGoodsListBean> defaultGoodsListBeen) {
////        initFooterViews(defaultGoodsListBeen);
//    }

//    private void initFooterViews(List<SubBean.DefaultGoodsListBean> defaultGoodsListBeen) {
//        ImageView imageView1 = (ImageView) view2.findViewById(R.id.footer_image1);
//        Utils.showImage(defaultGoodsListBeen.get(0).goods_img, imageView1);
//        ImageView imageView2 = (ImageView) view2.findViewById(R.id.footer_image2);
//        Utils.showImage(defaultGoodsListBeen.get(1).goods_img, imageView2);
//        ImageView imageView3 = (ImageView) view2.findViewById(R.id.footer_image3);
//        Utils.showImage(defaultGoodsListBeen.get(2).goods_img, imageView3);
//        ImageView imageView4 = (ImageView) view2.findViewById(R.id.footer_image4);
//        Utils.showImage(defaultGoodsListBeen.get(3).goods_img, imageView4);
//        ImageView imageView5 = (ImageView) view2.findViewById(R.id.footer_image5);
//        Utils.showImage(defaultGoodsListBeen.get(4).goods_img, imageView5);
//        ImageView imageView6 = (ImageView) view2.findViewById(R.id.footer_image6);
//        Utils.showImage(defaultGoodsListBeen.get(5).goods_img, imageView6);
//
//        TextView footer_text1_effic = (TextView) view2.findViewById(R.id.footer_text1_effic);
//        footer_text1_effic.setText(defaultGoodsListBeen.get(0).efficacy);
//        TextView footer_text1_name = (TextView) view2.findViewById(R.id.footer_text1_name);
//        footer_text1_name.setText(defaultGoodsListBeen.get(0).goods_name);
//        TextView footer_text1_shop = (TextView) view2.findViewById(R.id.footer_text1_shop);
//        footer_text1_shop.setText(defaultGoodsListBeen.get(0).shop_price);
//        TextView footer_text1_mark = (TextView) view2.findViewById(R.id.footer_text1_mark);
//        footer_text1_mark.setText(defaultGoodsListBeen.get(0).market_price+"");
//
//        TextView footer_text2_effic = (TextView) view2.findViewById(R.id.footer_text2_effic);
//        footer_text2_effic.setText(defaultGoodsListBeen.get(1).efficacy);
//        TextView footer_text2_name = (TextView) view2.findViewById(R.id.footer_text2_name);
//        footer_text2_name.setText(defaultGoodsListBeen.get(1).goods_name);
//        TextView footer_text2_shop = (TextView) view2.findViewById(R.id.footer_text2_shop);
//        footer_text2_shop.setText(defaultGoodsListBeen.get(1).shop_price+"");
//        TextView footer_text2_mark = (TextView) view2.findViewById(R.id.footer_text2_mark);
//        footer_text2_mark.setText(defaultGoodsListBeen.get(0).market_price+"");
//
//        TextView footer_text3_effic = (TextView) view2.findViewById(R.id.footer_text3_effic);
//        footer_text3_effic.setText(defaultGoodsListBeen.get(2).efficacy);
//        TextView footer_text3_name = (TextView) view2.findViewById(R.id.footer_text3_name);
//        footer_text3_name.setText(defaultGoodsListBeen.get(2).goods_name);
//        TextView footer_text3_shop = (TextView) view2.findViewById(R.id.footer_text3_shop);
//        footer_text3_shop.setText(defaultGoodsListBeen.get(2).shop_price+"");
//        TextView footer_text3_mark = (TextView) view2.findViewById(R.id.footer_text3_mark);
//        footer_text3_mark.setText(defaultGoodsListBeen.get(0).market_price+"");
//
//        TextView footer_text4_effic = (TextView) view2.findViewById(R.id.footer_text4_effic);
//        footer_text4_effic.setText(defaultGoodsListBeen.get(3).efficacy);
//        TextView footer_text4_name = (TextView) view2.findViewById(R.id.footer_text4_name);
//        footer_text4_name.setText(defaultGoodsListBeen.get(3).goods_name);
//        TextView footer_text4_shop = (TextView) view2.findViewById(R.id.footer_text4_shop);
//        footer_text4_shop.setText(defaultGoodsListBeen.get(3).shop_price+"");
//        TextView footer_text4_mark = (TextView) view2.findViewById(R.id.footer_text4_mark);
//        footer_text4_mark.setText(defaultGoodsListBeen.get(0).market_price+"");
//
//        TextView footer_text5_effic = (TextView) view2.findViewById(R.id.footer_text5_effic);
//        footer_text5_effic.setText(defaultGoodsListBeen.get(4).efficacy);
//        TextView footer_text5_name = (TextView) view2.findViewById(R.id.footer_text5_name);
//        footer_text5_name.setText(defaultGoodsListBeen.get(4).goods_name);
//        TextView footer_text5_shop = (TextView) view2.findViewById(R.id.footer_text5_shop);
//        footer_text5_shop.setText(defaultGoodsListBeen.get(4).shop_price+"");
//        TextView footer_text5_mark = (TextView) view2.findViewById(R.id.footer_text5_mark);
//        footer_text5_mark.setText(defaultGoodsListBeen.get(0).market_price+"");
//
//        TextView footer_text6_effic = (TextView) view2.findViewById(R.id.footer_text6_effic);
//        footer_text6_effic.setText(defaultGoodsListBeen.get(5).efficacy);
//        TextView footer_text6_name = (TextView) view2.findViewById(R.id.footer_text6_name);
//        footer_text6_name.setText(defaultGoodsListBeen.get(5).goods_name);
//        TextView footer_text6_shop = (TextView) view2.findViewById(R.id.footer_text6_shop);
//        footer_text6_shop.setText(defaultGoodsListBeen.get(5).shop_price+"");
//        TextView footer_text6_mark = (TextView) view2.findViewById(R.id.footer_text6_mark);
//        footer_text6_mark.setText(defaultGoodsListBeen.get(0).market_price+"");
//    }
}
