package shop.yunifang.com.yunifang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;


import java.util.List;

import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangDongXu on 2016/12/16.
 */

public class PullToGwAdapter extends BaseAdapter{

    private List<shop.yunifang.com.yunifang.bean.PullToGwBean> pulllist;
    private Context context;

    public PullToGwAdapter(List<shop.yunifang.com.yunifang.bean.PullToGwBean> pulllist, Context context) {
        this.pulllist = pulllist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pulllist.size();
    }

    @Override
    public Object getItem(int i) {
        return pulllist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            vh=new ViewHolder();
            view = View.inflate(context, R.layout.gw_item ,null);
            vh.gwimg=(ImageView) view.findViewById(R.id.gw_img);
            vh.but=(Button)view.findViewById(R.id.gyg_img);
            view.setTag(vh);
        }else{
            vh= (ViewHolder)view.getTag();
        }

            vh.gwimg.setImageResource(pulllist.get(i).gw_img);
            vh.but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity main = (MainActivity) context;
                    Intent intent=new Intent(context, MainActivity.class);
                    main.startActivity(intent);
                }
            });
        return view;
    }
//sss
    class ViewHolder{
        ImageView gwimg;
        Button but;
    }
}
//ss