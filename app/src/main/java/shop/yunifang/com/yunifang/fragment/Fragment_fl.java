package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.FaceActivity;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.adapter.CateAdapter;
import shop.yunifang.com.yunifang.bean.CateGoryBean;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_fl extends Fragment implements ViewsInterface,View.OnClickListener{

    private PullToRefreshListView mPullRefreshListView;
    private MyPent pent;
    private Context context;
    private ListView actualListView;
    private GridView mView;
    private CateAdapter adapter;
    private ArrayList<CateGoryBean.CateBean> myBeen;
    private TextView fz_text1;
    private TextView fz_text2;
    private TextView fz_text3;
    private TextView fz_text4;
    private TextView fz_text5;
    private TextView fz_text6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        pent = new MyPent();
        pent.setFl(this);
        MyPent.myPent(context, Api.category);
        View view = inflater.inflate(R.layout.fragment_fl, null);
        mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.pull_refresh_fl);
        //刷新界面
        refreshFragment();
        return view;
    }
    private void refreshFragment() {
        // Set a listener to be invoked when the list should be refreshed.
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                // Do work to refresh the list here.
                new Fragment_fl.GetDataTask().execute();
            }
        });

        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "End of List!", Toast.LENGTH_SHORT).show();
            }
        });

        actualListView = mPullRefreshListView.getRefreshableView();

        // Need to use the Actual ListView when registering for Context Menu
        registerForContextMenu(actualListView);
        /**
         * Add Sound Event Listener
         */
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(getActivity());
        mPullRefreshListView.setOnPullEventListener(soundListener);
//        actualListView.addFooterView(View);
        View view = View.inflate(context,R.layout.cate_header_layout,null);
         viewOnClick(view);
//// TODO: 2016/12/12 头部点击事件 
        View view1 = View.inflate(context,R.layout.cate_footer_layout,null);
        mView = (GridView) view1.findViewById(R.id.footer_grid_item_cate);
        //添加addHeaderView
        actualListView.addHeaderView(view);
        //添加addFooterView
        actualListView.addFooterView(view1);
        actualListView.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_expandable_list_item_1));
//        actualListView.setAdapter(mAdapter);
    }
//界面属性初始化
    private void viewOnClick(View view) {
        ImageView cate_item_image1 = (ImageView) view.findViewById(R.id.cate_item_image1);
        cate_item_image1.setOnClickListener(this); ImageView small_image1 = (ImageView) view.findViewById(R.id.small_image1);
        small_image1.setOnClickListener(this); ImageView small_image2 = (ImageView) view.findViewById(R.id.small_image2);
        small_image2.setOnClickListener(this); ImageView small_image3 = (ImageView) view.findViewById(R.id.small_image3);
        small_image3.setOnClickListener(this); ImageView small_image4 = (ImageView) view.findViewById(R.id.small_image4);
        small_image4.setOnClickListener(this); ImageView small_image5 = (ImageView) view.findViewById(R.id.small_image5);
        small_image5.setOnClickListener(this); ImageView gx_image1 = (ImageView) view.findViewById(R.id.gx_image1);
        gx_image1.setOnClickListener(this); ImageView gx_image2 = (ImageView) view.findViewById(R.id.gx_image2);
        gx_image2.setOnClickListener(this); ImageView gx_image3 = (ImageView) view.findViewById(R.id.gx_image3);
        gx_image3.setOnClickListener(this); ImageView gx_image4 = (ImageView) view.findViewById(R.id.gx_image4);
        gx_image4.setOnClickListener(this); ImageView gx_image5 = (ImageView) view.findViewById(R.id.gx_image5);
        gx_image5.setOnClickListener(this);

        fz_text1 = (TextView) view.findViewById(R.id.fz_text1);
        fz_text1.setTextColor(Color.WHITE);
        fz_text1.setOnClickListener(this);

        fz_text2 = (TextView) view.findViewById(R.id.fz_text2);
        fz_text2.setOnClickListener(this);


        fz_text3 = (TextView) view.findViewById(R.id.fz_text3);
        fz_text3.setOnClickListener(this);


        fz_text4 = (TextView) view.findViewById(R.id.fz_text4);
        fz_text4.setOnClickListener(this);

        fz_text5 = (TextView) view.findViewById(R.id.fz_text5);
        fz_text5.setOnClickListener(this);

        fz_text6 = (TextView) view.findViewById(R.id.fz_text6);
        fz_text6.setOnClickListener(this);


        fz_text2.setTextColor(Color.WHITE);
        fz_text3.setTextColor(Color.WHITE);
        fz_text4.setTextColor(Color.WHITE);
        fz_text5.setTextColor(Color.WHITE);
        fz_text6.setTextColor(Color.WHITE);
    }
    //解析网络数据添加显示
    @Override
    public void successGet(String response) {
        myBeen = new ArrayList<CateGoryBean.CateBean>();
        CateGoryBean bean = new Gson().fromJson(response,CateGoryBean.class);
        List<CateGoryBean.CateBean> goryBeen = bean.data.category;
//        Log.e("CateGoryBean===",""+goryBeen);

        myBeen.addAll(goryBeen);
        List<CateGoryBean.GoodsBriefBean> briefBeen = bean.data.goodsBrief;
        adapter = new CateAdapter(context);
        adapter.setData(briefBeen);
        mView.setAdapter(adapter);
    }
    @Override
    public void failedGet(String errCode) {

    }
//// TODO: 2016/12/13 界面点击事件
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.cate_item_image1:
                Intent intent = new Intent(context, FaceActivity.class);
//                Log.e("myBeen===",""+myBeen);
                intent.putExtra("key",myBeen);
                startActivity(intent);
                break;
            case R.id.small_image1:
                break;
            case R.id.small_image2:
                break;
            case R.id.small_image3:
                break;
            case R.id.small_image4:
                break;
            case R.id.small_image5:
                break;
            case R.id.gx_image1:
                break;
            case R.id.gx_image2:
                break;
            case R.id.gx_image3:
                break;
            case R.id.gx_image4:
                break;
            case R.id.gx_image5:
                break;
            case R.id.fz_text1:fz_text1.setTextColor(Color.RED);
                break;
            case R.id.fz_text2:fz_text2.setTextColor(Color.RED);
                break;
            case R.id.fz_text3:fz_text3.setTextColor(Color.RED);
                break;
            case R.id.fz_text4:fz_text4.setTextColor(Color.RED);
                break;
            case R.id.fz_text5:fz_text5.setTextColor(Color.RED);
                break;
            case R.id.fz_text6:fz_text6.setTextColor(Color.RED);
                break;
        }


    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
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
}
