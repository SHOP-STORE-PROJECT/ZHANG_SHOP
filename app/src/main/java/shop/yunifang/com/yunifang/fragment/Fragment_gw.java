package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.LoginMainActivity;
import shop.yunifang.com.yunifang.adapter.PullToGwAdapter;
import shop.yunifang.com.yunifang.bean.PullToGwBean;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_gw extends Fragment{
    private PullToRefreshListView gwlist1;
    private List<PullToGwBean>pulllist=new ArrayList<>();

    @Nullable    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_gw,null);

//   购物车页面未登录的情况下提示登录页面点击返回挑战到首页
        gwbacklogin();
        LoadData();
        gwlist1=(PullToRefreshListView)view.findViewById(R.id.pull_refresh_gw1);
        //支持下拉刷新
        gwlist1.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        PullToGwAdapter gwadapter=new PullToGwAdapter(pulllist,getActivity());
        gwlist1.setAdapter(gwadapter);

        return view;
    }

    private void gwbacklogin() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("itcast", Context.MODE_PRIVATE);
        boolean code = sharedPreferences.getBoolean("name", false);
        if(code!=true){
            Intent intent=new Intent(getActivity(),LoginMainActivity.class);
            intent.putExtra("name","gw");
            startActivity(intent);


        }

    }

    public void LoadData(){
        PullToGwBean pullToGwBean1=new PullToGwBean(R.mipmap.shopping_trolley_empty);
        pulllist.add(pullToGwBean1);
    }

}
