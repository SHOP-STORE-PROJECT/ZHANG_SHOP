package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.adapter.CateAdapter;
import shop.yunifang.com.yunifang.bean.CateGoryBean;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_fl extends Fragment implements ViewsInterface{

    private PullToRefreshListView mPullRefreshListView;
    private MyPent pent;
    private Context context;
    private ListView actualListView;
    private GridView mView;
    private CateAdapter adapter;

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
    //解析网络数据添加显示
    @Override
    public void successGet(String response) {
        CateGoryBean bean = new Gson().fromJson(response,CateGoryBean.class);
        List<CateGoryBean.GoodsBriefBean>briefBeen = bean.data.goodsBrief;
        adapter = new CateAdapter(context);
        adapter.setData(briefBeen);
        mView.setAdapter(adapter);
    }
    @Override
    public void failedGet(String errCode) {

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
