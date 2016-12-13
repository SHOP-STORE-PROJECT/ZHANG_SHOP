package shop.yunifang.com.yunifang.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.adapter.SettingMyAdapter;
import shop.yunifang.com.yunifang.bean.UserListBean;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";
    private List<UserListBean> settinglist=new ArrayList<>();
    private ListView settinglv;
    private Button backLogin;
    private ImageView img_backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initview();
        //判断是否登录
        codelogin();

        settingData();



    }

    private void codelogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("itcast", Context.MODE_PRIVATE);
        boolean code = sharedPreferences.getBoolean("name", false);
        Log.d(TAG,code+"========================================");
        if(code!=true){
            backLogin.setVisibility(View.GONE);
        }else {
            backLogin.setVisibility(View.VISIBLE);
            backLogin.setOnClickListener(backLoginoncli);
        }


    }
        //退出登录点击事件
    View.OnClickListener backLoginoncli=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("itcast", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("name", false);
                editor.commit();
                finish();
            }
        };
    private void initview() {

        settinglv=(ListView)findViewById(R.id.settinglv);
        backLogin = (Button)findViewById(R.id.backLogin);
        img_backLogin =(ImageView)findViewById(R.id.img_backLogin);

        SettingMyAdapter settingMyAdapter=new SettingMyAdapter(settinglist, SettingActivity.this);
        settinglv.setAdapter(settingMyAdapter);

        img_backLogin.setOnClickListener(backloginoncli);

    }

    View.OnClickListener backloginoncli=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    private void settingData() {

        UserListBean userListBean1=new UserListBean("购物须知",R.mipmap.next_icon);
        UserListBean userListBean2=new UserListBean("意见反馈",R.mipmap.next_icon);
        UserListBean userListBean3=new UserListBean("清除缓存",R.mipmap.next_icon);
        UserListBean userListBean4=new UserListBean("关于我们",3);
        UserListBean userListBean5=new UserListBean("拨打电话",2);
        UserListBean userListBean6=new UserListBean("检查更新",1);

        settinglist.add(userListBean1);
        settinglist.add(userListBean2);
        settinglist.add(userListBean3);
        settinglist.add(userListBean4);
        settinglist.add(userListBean5);
        settinglist.add(userListBean6);


    }


}
