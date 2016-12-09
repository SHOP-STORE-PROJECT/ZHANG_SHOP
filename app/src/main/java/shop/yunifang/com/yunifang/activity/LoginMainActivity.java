package shop.yunifang.com.yunifang.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import shop.yunifang.com.yunifang.R;

public class LoginMainActivity extends AppCompatActivity {

    private ImageView img_zh;
    private ImageView zh1;
    private ImageView zh3;
    private ImageView sj2;
    private ImageView sj4;
    private EditText ed_name;
    private EditText ed_parss;
    private EditText ed_yz;
    private ImageView img_yzm;
    private TextView wjparss;
    private ImageView img_back;
    private TextView login_dsf;
    private String TAG = "LoginMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        //初始化控件
            initView();


    }

    private void initView() {

        //登录输入框
        ed_name =(EditText)findViewById(R.id.ed_name);
        ed_parss=(EditText)findViewById(R.id.ed_parss);
        ed_yz   =(EditText)findViewById(R.id.ed_yz);
        //登录控件
       zh1 =(ImageView) findViewById(R.id.img_zh1);
       zh3 =(ImageView) findViewById(R.id.img_zh3);
       sj2 =(ImageView) findViewById(R.id.img_sj2);
       sj4 =(ImageView) findViewById(R.id.img_sj4);
       img_yzm=(ImageView) findViewById(R.id.img_yzm);
        //忘记密码控件
        wjparss=(TextView)findViewById(R.id.tv_wj_parss);
        //返回控件
        img_back=(ImageView)findViewById(R.id.img_back);
        //第三方登录控件
        login_dsf=(TextView)findViewById(R.id.img_login_dsf);

        //第三方登录监听点击弹出popwind
        login_dsf.setOnClickListener(dsfonclist);



        //登录切换
        //账号登录监听
        zh3.setOnClickListener(zhonlist);
        zh1.setOnClickListener(zhonlist);
        //手机登录监听
        sj4.setOnClickListener(sjonlist);
        sj2.setOnClickListener(sjonlist);
        //返回监听

        img_back.setOnClickListener(backonclist);

    }

        //账号登录监听
        View.OnClickListener  zhonlist=new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //布局-------------------------------
                ed_parss.setVisibility(View.VISIBLE);
                img_yzm.setVisibility(View.GONE);
                ed_yz.setVisibility(View.GONE);
                sj2.setVisibility(View.VISIBLE);
                sj4.setVisibility(View.GONE);
                zh1.setVisibility(View.VISIBLE);
                zh3.setVisibility(View.GONE);
                wjparss.setVisibility(View.VISIBLE);
                //--------------------------------
            }
        };
        //手机登录监听
        View.OnClickListener sjonlist=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //布局-------------------------------
                wjparss.setVisibility(View.GONE);
                ed_parss.setVisibility(View.GONE);
                img_yzm.setVisibility(View.VISIBLE);
                ed_yz.setVisibility(View.VISIBLE);
                zh3.setVisibility(View.VISIBLE);
                zh1.setVisibility(View.GONE);
                sj4.setVisibility(View.VISIBLE);
                sj2.setVisibility(View.GONE);
                //--------------------------------
            }
        };

    //返回监听
    View.OnClickListener backonclist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    //第三方登录监听
    View.OnClickListener dsfonclist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            View view1= View.inflate(LoginMainActivity.this, R.layout.popw, null);

            Log.d(TAG,"--------------------------------------");

            // 创建 popuWindow对象 视图文件宽高是否能得到焦点
            PopupWindow popuwind = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            // 点击其他区域进行关闭popuWindow对话框
            popuwind.setOutsideTouchable(true);
            popuwind.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
//				popuwind.showAsDropDown(ks_text);
            popuwind.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    };






}
