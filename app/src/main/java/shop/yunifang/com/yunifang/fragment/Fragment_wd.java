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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.LoginMainActivity;
import shop.yunifang.com.yunifang.activity.SettingActivity;
import shop.yunifang.com.yunifang.activity.TheorderActivity;
import shop.yunifang.com.yunifang.adapter.ListMyAdapter;
import shop.yunifang.com.yunifang.bean.UserListBean;


/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_wd extends Fragment {
    private ListView flv;
    private List<UserListBean> list = new ArrayList<UserListBean>();
    private TextView login;
    private ImageView user_setting;
    private UserListBean da;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wd, null);
       //初始化控件
        flv=(ListView)view.findViewById(R.id.fragment_lv);
        login=(TextView)view.findViewById(R.id.tv_login);
        //设置的控件
        user_setting=(ImageView)view.findViewById(R.id.user_setting);
        //设置的监听
        user_setting.setOnClickListener(settingoncli);
        //登录的点击事件
        login.setOnClickListener(onlist);
        //listview去分割线--------
        flv.setDividerHeight(0);
        flv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                da = list.get(i);
                if(da.name.equals("我的订单")){
                    Intent intent=new Intent(getActivity(), TheorderActivity.class);
                    startActivity(intent);
                }


            }
        });

        //初始化数据
        LoadData();
        //我的界面listview适配器
        ListMyAdapter adapter=new ListMyAdapter(list,getActivity());
        flv.setAdapter(adapter);

        return view;

    }
    //登录的点击事件
   View.OnClickListener onlist=new View.OnClickListener() {
       @Override
        public void onClick(View view) {
            //跳转至登录页面
            Intent intent=new Intent(getActivity(),LoginMainActivity.class);
           startActivity(intent);
        }
    };


    View.OnClickListener settingoncli=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    };



    //list添加数据
    private void LoadData(){

        UserListBean user1=new UserListBean("我的订单",R.mipmap.my_order_icon);
        UserListBean user2=new UserListBean("邀请有礼",R.mipmap.my_invite_gift_icon);
        UserListBean user3=new UserListBean("刷脸测尺寸",R.mipmap.my_vip_icon);
        UserListBean user4=new UserListBean("我的现金券",R.mipmap.my_coupon_icon);
        UserListBean user5=new UserListBean("我的抽奖单",R.mipmap.my_lottery_icon);
        UserListBean user6=new UserListBean("我收藏的商品",R.mipmap.my_collection_icon);
        UserListBean user7=new UserListBean("联系客服",R.mipmap.personal_center_contact_service_icon);

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);


    }
}
