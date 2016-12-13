package shop.yunifang.com.yunifang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.SubBean;
import shop.yunifang.com.yunifang.utils.Utils;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */

public class ZhuanAdapter extends BaseAdapter {

    private List<SubBean.GoodsBean>dataBean;
    private Context context;
    public ZhuanAdapter(List<SubBean.GoodsBean> dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ZhViewHolder holder = null;
        if(convertView ==null){
            holder = new ZhViewHolder();
            convertView = View.inflate(context, R.layout.footer_item,null);
            initViews(convertView,holder);
        }else{
            holder = (ZhViewHolder) convertView.getTag();
        }
        showImageAndText(holder,position);
        return convertView;
    }

    private void showImageAndText(ZhViewHolder holder, int position) {
        Utils.showImage(dataBean.get(position).goods_img,holder.imageView1);
//        Utils.showImage(dataBean.get(position).watermarkUrl,holder.imageView2);
        holder.text1.setText(dataBean.get(position).goods_name);
        holder.text2.setText(dataBean.get(position).goods_name);
        holder.text3.setText(dataBean.get(position).shop_price+"");
        holder.text4.setText(dataBean.get(position).market_price+"");
        holder.text4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        Log.e("GirdAdapter==========",dataBean.get(position).shop_price);
        holder.text4.setText(dataBean.get(position).market_price+"");
    }
    private void initViews(View convertView, ZhViewHolder holder) {
        holder.imageView1 = (ImageView) convertView.findViewById(R.id.footer_image1);
//        holder.imageView2 = (ImageView) convertView.findViewById(R.id.footer_image2);
        holder.text1 = (TextView) convertView.findViewById(R.id.footer_text1);
        holder.text2 = (TextView) convertView.findViewById(R.id.footer_text2);
        holder.text3 = (TextView) convertView.findViewById(R.id.footer_text3);
        holder.text4 = (TextView) convertView.findViewById(R.id.footer_text4);
        convertView.setTag(holder);
    }
    //自定义Viewholder类
    public static class ZhViewHolder{
        ImageView imageView1,imageView2;
        TextView text1,text2,text3,text4;
    }
}
