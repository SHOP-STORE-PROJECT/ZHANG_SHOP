package shop.yunifang.com.yunifang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.DetailBean;
import shop.yunifang.com.yunifang.utils.Utils;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */

public class FaceBaseAdapter extends BaseAdapter {

    private List<DetailBean.DetailData>datas;
    private Context context;

    public FaceBaseAdapter(List<DetailBean.DetailData> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FaceViewHolder holder = null;
        if(convertView ==null){
            holder = new FaceViewHolder();
            convertView = View.inflate(context, R.layout.footer_item,null);
            initViews(convertView,holder);
        }else{
            holder = (FaceViewHolder) convertView.getTag();
        }
        showImageAndText(holder,position);
        return convertView;
    }

    private void showImageAndText(FaceViewHolder holder, int position) {
        Log.e("showImageAndText======",datas.get(position).efficacy);
        Utils.showImage(datas.get(position).goods_img,holder.imageView1);
//        Utils.showImage(datas.get(position).watermarkUrl,holder.imageView2);
        holder.text1.setText(datas.get(position).efficacy);
        holder.text2.setText(datas.get(position).goods_name);
        holder.text3.setText(datas.get(position).shop_price+"");
        holder.text4.setText(datas.get(position).market_price+"");
        holder.text4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        Log.e("GirdAdapter==========",dataBean.get(position).shop_price);
        holder.text4.setText(datas.get(position).market_price+"");
    }
    private void initViews(View convertView,FaceViewHolder holder) {
        holder.imageView1 = (ImageView) convertView.findViewById(R.id.footer_image1);
//        holder.imageView2 = (ImageView) convertView.findViewById(R.id.footer_image2);
        holder.text1 = (TextView) convertView.findViewById(R.id.footer_text1);
        holder.text2 = (TextView) convertView.findViewById(R.id.footer_text2);
        holder.text3 = (TextView) convertView.findViewById(R.id.footer_text3);
        holder.text4 = (TextView) convertView.findViewById(R.id.footer_text4);
        convertView.setTag(holder);
    }


//    //添加网络数据实时刷新
//    public void setData(List<DetailBean.DetailData> data) {
//        datas = new ArrayList<>();
//        datas.addAll(data);
//        notifyDataSetChanged();
//    }
    //自定义Viewholder类
    public static class FaceViewHolder{
        ImageView imageView1,imageView2;
        TextView text1,text2,text3,text4;
    }
}
