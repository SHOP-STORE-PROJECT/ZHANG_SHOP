package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.adapter.MyFirstAdapter;
import shop.yunifang.com.yunifang.bean.SubBean;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_sy extends Fragment implements ViewsInterface {
    private Context context;
    private View view;
    private PullToRefreshListView mPullRefreshListView;
    private MyPent pent;
    private MyFirstAdapter adapter;
    private ListView mListView1;

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
        View view1 = View.inflate(context,R.layout.first_viewpager,null);
        ViewPager pager = (ViewPager) view1.findViewById(R.id.first_pager);
        ViewPager pager1 = (ViewPager) view1.findViewById(R.id.youhui_viewpager);
        pager1.setAdapter(new MyYouhuiAdapter());
        pager.setAdapter(new MyViewPagerAdapter());
        mListView1.addHeaderView(view1);
    }
    @Override
    public void successGet(SubBean datas) {
        List<SubBean.SubjectBean>subjectsBeen = datas.data.subjects;
        Message msg = Message.obtain();
        msg.obj = subjectsBeen;
            handler.sendMessage(msg);
//        mAdapter.notifyDataSetChanged();

//刷新完成
//        mPullRefreshListView.onRefreshComplete();
    }
    @Override
    public void failedGet(String errCode) {
        Log.e("首页","首页数据请求失败");
    }

    protected Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           List<SubBean.SubjectBean> bean = (List<SubBean.SubjectBean>) msg.obj;
            adapter = new MyFirstAdapter(context);
            adapter.setData(bean);
            mListView1.setAdapter(adapter);
        }
    };
    class MyViewPagerAdapter extends PagerAdapter{
         int[] sDrawables = { R.drawable.headview_pager1, R.drawable.headview_pager2, R.drawable.headview_pager3,
                R.drawable.headview_pager4, R.drawable.headview_pager5, R.drawable.headview_pager6, R.drawable.headview_pager7,R.drawable.headview_pager8};

        @Override
        public int getCount() {
            return sDrawables.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(sDrawables[position]);

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
public  class MyYouhuiAdapter extends  PagerAdapter{
    int[] sDrawables = { R.drawable.youhui_pagera, R.drawable.youhui_pagerb, R.drawable.youhui_pagerc,
            R.drawable.youhui_pagerd, R.drawable.youhui_pagere};

    @Override
    public int getCount() {
        return sDrawables.length;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(sDrawables[position]);

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
