package shop.yunifang.com.yunifang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.utils.Utils;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class CateDataAdapter extends BaseAdapter {
    private List<UserBean.CateData> list = new ArrayList<>();
    private Context context;
    private CateAdapter.onClickCate clickCate;

    public void setOnCateAdapter(CateAdapter.onClickCate clickCate) {
        this.clickCate = clickCate;
    }

    public CateDataAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CateDataViewHolder holder = null;
        if(convertView ==null){
            holder = new CateDataViewHolder();
            convertView = View.inflate(context, R.layout.cate_adapter_layout,null);
            initViews(convertView,holder);
        }else{
            holder = (CateDataViewHolder) convertView.getTag();
        }
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCate.myCate(list.get(position).dbId);
            }
        });
        showImageAndText(holder,position);
        return convertView;
    }

    private void showImageAndText(CateDataViewHolder holder, int position) {
        Utils.showImage(list.get(position).goods_img,holder.imageView1);
//        Utils.showImage(briefBeen.get(position).watermarkUrl,holder.imageView2);
        holder.text1.setText(list.get(position).efficacy);
        holder.text2.setText(list.get(position).goods_name);
        holder.text3.setText(list.get(position).shop_price+"");
        holder.text4.setText(list.get(position).market_price+"");
        holder.text4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        Log.e("GirdAdapter==========",dataBean.get(position).shop_price);
        holder.text4.setText(list.get(position).market_price+"");
    }
    private void initViews(View convertView, CateDataViewHolder holder) {
        holder.imageView1 = (ImageView) convertView.findViewById(R.id.footer_image_cate1);
//        holder.imageView2 = (ImageView) convertView.findViewById(R.id.footer_image_cate2);
        holder.text1 = (TextView) convertView.findViewById(R.id.footer_text_cate1);
        holder.text2 = (TextView) convertView.findViewById(R.id.footer_text_cate2);
        holder.text3 = (TextView) convertView.findViewById(R.id.footer_text_cate3);
        holder.text4 = (TextView) convertView.findViewById(R.id.footer_text_cate4);
        convertView.setTag(holder);
    }
    //添加网络数据实时刷新
    public void setData(List<UserBean.CateData> data) {
//        Log.e("CateViewHolder",""+data.get(0).market_price+"=================");
        list.addAll(data);
        notifyDataSetChanged();
    }
    //自定义Viewholder类
    public static class CateDataViewHolder{
        ImageView imageView1;
        TextView text1,text2,text3,text4;
    }
    public  interface  onClickCate{
        void myCate(String position);
    }
}
