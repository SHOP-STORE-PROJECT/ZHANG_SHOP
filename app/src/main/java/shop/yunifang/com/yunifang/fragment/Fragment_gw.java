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


import com.handmark.pulltorefresh.library.PullToRefreshListView;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.LoginMainActivity;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_gw extends Fragment{
    private PullToRefreshListView gwlist;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gw, null);

//   购物车页面未登录的情况下提示登录页面点击返回挑战到首页
        gwbacklogin();

        gwlist=(PullToRefreshListView)view.findViewById(R.id.pull_refresh_gw);



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
}
