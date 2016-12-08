package shop.yunifang.com.yunifang;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import shop.yunifang.com.yunifang.fragment.Fragment_fl;
import shop.yunifang.com.yunifang.fragment.Fragment_gw;
import shop.yunifang.com.yunifang.fragment.Fragment_sy;
import shop.yunifang.com.yunifang.fragment.Fragment_wd;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

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
    private FragmentTransaction transaction1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //android.support.v4.app.Fragment导航页
        yuNiFangFragment();
    }
    private void yuNiFangFragment() {
        //获取fragment
        fragment = getSupportFragmentManager();
//        FragmentTransaction transaction = fragment.beginTransaction();
        //获取四个页面fragment对象
        fragment.beginTransaction().replace(R.id.fragment,new Fragment_sy()).commit();
        //添加
//        transaction.add(R.id.fragment, fragment_sy);
//        transaction.add(R.id.fragment, fragment_fl);
//        transaction.add(R.id.fragment, fragment_gw);
//        transaction.add(R.id.fragment, fragment_wd);
        //设置隐藏与现实
//        transaction.hide(fragment_wd);
//        transaction.hide(fragment_fl);
//        transaction.hide(fragment_gw);
//        transaction.show(fragment_sy);
//        //提交事务
//        transaction.commit();

    }
    private void init() {
        //android.app.Fragment

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
    public void onClick(View v) {
        transaction1 = fragment.beginTransaction();
        switch (v.getId()) {
            case R.id.rb01:
                transaction1.replace(R.id.fragment,new Fragment_sy());
//                MyPent.myPent(this, Api.homeUrl);
//                transaction1.hide(fragment_fl);
//                transaction1.hide(fragment_gw);
//                transaction1.hide(fragment_wd);
//                transaction1.show(fragment_sy);
                break;
            case R.id.rb02:
                transaction1.replace(R.id.fragment,new Fragment_fl());
//                transaction1.hide(fragment_sy);
//                transaction1.hide(fragment_gw);
//                transaction1.hide(fragment_wd);
//                transaction1.show(fragment_fl);
                break;
            case R.id.rb03:
                transaction1.replace(R.id.fragment,new Fragment_gw());
//                transaction1.hide(fragment_sy);
//                transaction1.hide(fragment_fl);
//                transaction1.hide(fragment_wd);
//                transaction1.show(fragment_gw);
                break;
            case R.id.rb04:
                transaction1.replace(R.id.fragment,new Fragment_wd());
//                transaction1.hide(fragment_sy);
//                transaction1.hide(fragment_fl);
//                transaction1.hide(fragment_gw);
//                transaction1.show(fragment_wd);
                break;
        }
        transaction1.commit();
    }
}
