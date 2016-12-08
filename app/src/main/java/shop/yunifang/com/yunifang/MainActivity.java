package shop.yunifang.com.yunifang;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.fragment.Fragment_fl;
import shop.yunifang.com.yunifang.fragment.Fragment_gw;
import shop.yunifang.com.yunifang.fragment.Fragment_sy;
import shop.yunifang.com.yunifang.fragment.Fragment_wd;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

public class MainActivity extends FragmentActivity implements ViewsInterface,View.OnClickListener{

    private MyPent myPent;
    private FragmentManager fragment;
    private Fragment_sy fragment_sy;
    private Fragment_fl fragment_fl;
    private Fragment_gw fragment_gw;
    private Fragment_wd fragment_wd;
    private RadioButton rb01;
    private RadioButton rb02;
    private RadioButton rb03;
    private RadioButton rb04;

    static final int MENU_MANUAL_REFRESH = 0;
    static final int MENU_DISABLE_SCROLL = 1;
    static final int MENU_SET_MODE = 2;
    static final int MENU_DEMO = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //android.support.v4.app.Fragment导航页
        yuNiFangFragment();
        Log.d("TAG============","QEWEQWWQEQEWEQWWWWWWWWWWWWWWWWWWDSDDSFRR");
    }


    private void yuNiFangFragment() {
        //获取fragment
        fragment = getSupportFragmentManager();
        FragmentTransaction transaction = fragment.beginTransaction();
        //获取四个页面fragment对象
        fragment_sy = new Fragment_sy();
        fragment_fl = new Fragment_fl();
        fragment_gw = new Fragment_gw();
        fragment_wd = new Fragment_wd();
        //添加
        transaction.add(R.id.fragment, fragment_sy);
        transaction.add(R.id.fragment, fragment_fl);
        transaction.add(R.id.fragment, fragment_gw);
        transaction.add(R.id.fragment, fragment_wd);
        //设置隐藏与现实
        transaction.hide(fragment_wd);
        transaction.hide(fragment_fl);
        transaction.hide(fragment_gw);
        transaction.show(fragment_sy);
        //提交事务
        transaction.commit();

    }
    //TODO数据库操作
    private void init() {
        //android.app.Fragment
        myPent = new MyPent(this);
        MyPent.myPent(this, Api.xiaohua);
        rb01=(RadioButton)findViewById(R.id.rb01);
        rb02=(RadioButton)findViewById(R.id.rb02);
        rb03=(RadioButton)findViewById(R.id.rb03);
        rb04=(RadioButton)findViewById(R.id.rb04);

        rb01.setOnClickListener(this);
        rb02.setOnClickListener(this);
        rb03.setOnClickListener(this);
        rb04.setOnClickListener(this);
    }
    @Override
    public void successGet(String datas) {

    }

    @Override
    public void failedGet(String errCode) {

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragment.beginTransaction();
        switch (v.getId()) {
            case R.id.rb01:
                transaction.hide(fragment_fl);
                transaction.hide(fragment_gw);
                transaction.hide(fragment_wd);
                transaction.show(fragment_sy);
                break;
            case R.id.rb02:
                transaction.hide(fragment_sy);
                transaction.hide(fragment_gw);
                transaction.hide(fragment_wd);
                transaction.show(fragment_fl);
                break;
            case R.id.rb03:
                transaction.hide(fragment_sy);
                transaction.hide(fragment_fl);
                transaction.hide(fragment_wd);
                transaction.show(fragment_gw);
                break;
            case R.id.rb04:
                transaction.hide(fragment_sy);
                transaction.hide(fragment_fl);
                transaction.hide(fragment_gw);
                transaction.show(fragment_wd);
                break;
        }
        transaction.commit();
    }
}
