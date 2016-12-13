package shop.yunifang.com.yunifang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.UserListBean;

/**
 * Created by ZhangDongXu on 2016/12/7.
 */
public class SettingMyAdapter extends BaseAdapter{

    private List<UserListBean> list;
    private Context context;

    public SettingMyAdapter(List<UserListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view==null){
            vh=new ViewHolder();
            view=View.inflate(context, R.layout.settingitem,null);
            vh.listimg=(ImageView)view.findViewById(R.id.settingimg);
            vh.listtv=(TextView)view.findViewById(R.id.settingtv);
            vh.listtv2=(TextView)view.findViewById(R.id.settingtv2);
            vh.listimg2=(ImageView)view.findViewById(R.id.settingimg2);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
            if(list.get(i).img==1) {

                vh.listimg.setVisibility(View.GONE);
                vh.listtv2.setVisibility(View.VISIBLE);
                vh.listtv2.setText("400-688-0900");
                vh.listtv2.setTextColor(Color.RED);
                vh.listtv.setText(list.get(i).name);
            }else if(list.get(i).img==2){
                vh.listimg.setVisibility(View.GONE);
                vh.listtv2.setVisibility(View.VISIBLE);
                vh.listtv2.setTextColor(Color.BLACK);
                vh.listtv2.setText("已是最新版本");
                vh.listtv.setText(list.get(i).name);
            }else if (list.get(i).img==3){
                vh.listimg2.setVisibility(View.VISIBLE);
                vh.listimg.setVisibility(View.VISIBLE);
                vh.listtv2.setVisibility(View.GONE);
                vh.listtv.setText(list.get(i).name);
                vh.listimg2.setBackgroundResource(R.mipmap.next_icon);
                vh.listimg.setBackgroundResource(R.mipmap.about_us_code);

            }

            else{
                vh.listimg.setVisibility(View.VISIBLE);
                vh.listtv2.setVisibility(View.GONE);
                vh.listtv.setText(list.get(i).name);
                vh.listimg.setBackgroundResource(list.get(i).img);
            }
        return view;
    }

    class ViewHolder{
        ImageView listimg;
        TextView listtv2;
        TextView  listtv;
        ImageView listimg2;
    }
}
