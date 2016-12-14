package shop.yunifang.com.yunifang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.BuyActivity;
import shop.yunifang.com.yunifang.activity.ZhuanActivity;
import shop.yunifang.com.yunifang.bean.SubBean;
import shop.yunifang.com.yunifang.utils.Utils;

/**
 * Created by ZhangFanfan on 2016/12/8.
 */
//sfagfdgfd
public class MyFirstAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private int mPosition;
    private List<SubBean.SubjectBean> subjectBean = new ArrayList<>();

    public MyFirstAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return subjectBean.size();
    }

    @Override
    public Object getItem(int position) {
        return subjectBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mPosition = position;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.pulltofreash_item,null);
            initViews(holder,convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        showDatas(holder,position);
        return convertView;
    }

    private void showDatas(ViewHolder holder,int position) {
        Utils.showImage(subjectBean.get(position).image, holder.imageView);
        Utils.showImage(subjectBean.get(position).goodsList.get(0).goods_img, holder.imageView1);
        Utils.showImage(subjectBean.get(position).goodsList.get(1).goods_img, holder.imageView2);
        Utils.showImage(subjectBean.get(position).goodsList.get(2).goods_img, holder.imageView3);
        Utils.showImage(subjectBean.get(position).goodsList.get(3).goods_img, holder.imageView4);
        Utils.showImage(subjectBean.get(position).goodsList.get(4).goods_img, holder.imageView5);
        Utils.showImage(subjectBean.get(position).goodsList.get(5).goods_img, holder.imageView6);

//        Utils.showImage(subjectBean.get(position).goodsList.get(position).watermarkUrl,holder.hotImgae1);
//        Utils.showImage(subjectBean.get(position).goodsList.get(position).watermarkUrl,holder.hotImgae2);
//        Utils.showImage(subjectBean.get(position).goodsList.get(position).watermarkUrl,holder.hotImgae3);
//        Utils.showImage(subjectBean.get(position).goodsList.get(position).watermarkUrl,holder.hotImgae4);
//        Utils.showImage(subjectBean.get(position).goodsList.get(position).watermarkUrl,holder.hotImgae5);
//        Utils.showImage(subjectBean.get(position).goodsList.get(position).watermarkUrl,holder.hotImgae6);

        if(subjectBean.get(position).goodsList.get(position).goods_name.length()>19){
            holder.nameText1.setText(subjectBean.get(position).goodsList.get(position).goods_name.substring(0,19)+"...");
            holder.nameText2.setText(subjectBean.get(position).goodsList.get(position).goods_name.substring(0,19)+"...");
            holder.nameText3.setText(subjectBean.get(position).goodsList.get(position).goods_name.substring(0,19)+"...");
            holder.nameText4.setText(subjectBean.get(position).goodsList.get(position).goods_name.substring(0,19)+"...");
            holder.nameText5.setText(subjectBean.get(position).goodsList.get(position).goods_name.substring(0,19)+"...");
            holder.nameText6.setText(subjectBean.get(position).goodsList.get(position).goods_name.substring(0,19)+"...");
        }else {
            holder.nameText1.setText(subjectBean.get(position).goodsList.get(position).goods_name);
            holder.nameText2.setText(subjectBean.get(position).goodsList.get(position).goods_name);
            holder.nameText3.setText(subjectBean.get(position).goodsList.get(position).goods_name);
            holder.nameText4.setText(subjectBean.get(position).goodsList.get(position).goods_name);
            holder.nameText5.setText(subjectBean.get(position).goodsList.get(position).goods_name);
            holder.nameText6.setText(subjectBean.get(position).goodsList.get(position).goods_name);
        }
        holder.priceText1.setText(""+subjectBean.get(position).goodsList.get(position).shop_price);
        holder.priceText2.setText(""+subjectBean.get(position).goodsList.get(position).shop_price);
        holder.priceText3.setText(""+subjectBean.get(position).goodsList.get(position).shop_price);
        holder.priceText4.setText(""+subjectBean.get(position).goodsList.get(position).shop_price);
        holder.priceText5.setText(""+subjectBean.get(position).goodsList.get(position).shop_price);
        holder.priceText6.setText(""+subjectBean.get(position).goodsList.get(position).shop_price);
//    textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
        holder.currentPrice1.setText(""+subjectBean.get(position).goodsList.get(position).market_price);
        holder.currentPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.currentPrice2.setText(""+subjectBean.get(position).goodsList.get(position).market_price);
        holder.currentPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.currentPrice3.setText(""+subjectBean.get(position).goodsList.get(position).market_price);
        holder.currentPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.currentPrice4.setText(""+subjectBean.get(position).goodsList.get(position).market_price);
        holder.currentPrice4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.currentPrice5.setText(""+subjectBean.get(position).goodsList.get(position).market_price);
        holder.currentPrice5.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.currentPrice6.setText(""+subjectBean.get(position).goodsList.get(position).market_price);
        holder.currentPrice6.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
    private void initViews(ViewHolder holder, View convertView) {
        holder.imageView = (ImageView)convertView.findViewById(R.id.pullto_item_image);
        holder.imageView.setOnClickListener(this);
        holder.imageView1 = (ImageView)convertView.findViewById(R.id.puul_image1);
        holder.imageView1.setOnClickListener(this);
        holder.imageView2 = (ImageView)convertView.findViewById(R.id.puul_image2);
        holder.imageView2.setOnClickListener(this);
        holder.imageView3 = (ImageView)convertView.findViewById(R.id.puul_image3);
        holder.imageView3.setOnClickListener(this);
        holder.imageView4 = (ImageView)convertView.findViewById(R.id.puul_image4);
        holder.imageView4.setOnClickListener(this);
        holder.imageView5 = (ImageView)convertView.findViewById(R.id.puul_image5);
        holder.imageView5.setOnClickListener(this);
        holder.imageView6 = (ImageView)convertView.findViewById(R.id.puul_image6);
        holder.imageView6.setOnClickListener(this);
        holder.imageView7 = (ImageView)convertView.findViewById(R.id.puul_image7);
        holder.imageView7.setOnClickListener(this);

//        holder.hotImgae1 = (ImageView)convertView.findViewById(R.id.hot_image1);
//        holder.hotImgae2 = (ImageView)convertView.findViewById(R.id.hot_image2);
//        holder.hotImgae3 = (ImageView)convertView.findViewById(R.id.hot_image3);
//        holder.hotImgae4 = (ImageView)convertView.findViewById(R.id.hot_image4);
//        holder.hotImgae5 = (ImageView)convertView.findViewById(R.id.hot_image5);
//        holder.hotImgae6 = (ImageView)convertView.findViewById(R.id.hot_image6);

        holder.nameText1 = (TextView) convertView.findViewById(R.id.name_text1);
        holder.nameText2 = (TextView) convertView.findViewById(R.id.name_text2);
        holder.nameText3 = (TextView) convertView.findViewById(R.id.name_text3);
        holder.nameText4 = (TextView) convertView.findViewById(R.id.name_text4);
        holder.nameText5 = (TextView) convertView.findViewById(R.id.name_text5);
        holder.nameText6 = (TextView) convertView.findViewById(R.id.name_text6);

        holder.priceText1 = (TextView)convertView.findViewById(R.id.price_text1);
        holder.priceText2 = (TextView)convertView.findViewById(R.id.price_text2);
        holder.priceText3 = (TextView)convertView.findViewById(R.id.price_text3);
        holder.priceText4 = (TextView)convertView.findViewById(R.id.price_text4);
        holder.priceText5 = (TextView)convertView.findViewById(R.id.price_text5);
        holder.priceText6 = (TextView)convertView.findViewById(R.id.price_text6);

        holder.currentPrice1 = (TextView)convertView.findViewById(R.id.current_price_text1);
        holder.currentPrice2 = (TextView)convertView.findViewById(R.id.current_price_text2);
        holder.currentPrice3 = (TextView)convertView.findViewById(R.id.current_price_text3);
        holder.currentPrice4 = (TextView)convertView.findViewById(R.id.current_price_text4);
        holder.currentPrice5 = (TextView)convertView.findViewById(R.id.current_price_text5);
        holder.currentPrice6 = (TextView)convertView.findViewById(R.id.current_price_text6);
    }
    public void setData(List<SubBean.SubjectBean> bean) {

        subjectBean.addAll(bean);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ZhuanActivity.class);
        Intent intent1 = new Intent(context, BuyActivity.class);
      switch(v.getId()){

    case R.id.pullto_item_image:
        intent.putExtra("key",subjectBean.get(mPosition)); context.startActivity(intent);
        break;
    case R.id.puul_image1:
        context.startActivity(intent1);
        break;
    case R.id.puul_image2:
        context.startActivity(intent1);
        break;
    case R.id.puul_image3:
        context.startActivity(intent1);
        break;
    case R.id.puul_image4:
        context.startActivity(intent1);
        break;
    case R.id.puul_image5:
        context.startActivity(intent1);
        break;
    case R.id.puul_image6:
        context.startActivity(intent1);
        break;
    case R.id.puul_image7:
        intent.putExtra("key",subjectBean.get(mPosition)); context.startActivity(intent);
        break;
       }

    }

    public static class ViewHolder {
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
//        ImageView hotImgae1;
//        ImageView hotImgae2;
//        ImageView hotImgae3;
//        ImageView hotImgae4;
//        ImageView hotImgae5;
//        ImageView hotImgae6;
        TextView nameText1;
        TextView nameText2;
        TextView nameText3;
        TextView nameText4;
        TextView nameText5;
        TextView nameText6;
        TextView priceText2,priceText1,priceText3,priceText4,priceText5,priceText6;
        TextView currentPrice1,currentPrice2,currentPrice3,currentPrice4,currentPrice5,currentPrice6;
    }
//    public class PullAdapter extends PagerAdapter{
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return false;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            View view = View.inflate(context,R.layout.viewpager_image_layout,null);
//            ImageView view1 = (ImageView) view.findViewById(R.id.head_image);
////            ImageLoader.getInstance().displayImage(subjectBean.get(position).goodsList.get(position).goods_img,view1);
//            container.addView(view);
//            return view;
//        }
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View)object);
//        }
//    }
}
