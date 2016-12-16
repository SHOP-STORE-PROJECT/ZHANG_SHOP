package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.Goods;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class CanShuFragment extends Fragment {
    private Context context;
    public static Fragment canInsTance (ArrayList<Goods.GoodsAttributes> list){
        CanShuFragment shuFragment = new CanShuFragment();
         Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list",list);
        shuFragment.setArguments(bundle);
        return shuFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = View.inflate(context, R.layout.canshu_layout,null);
        initViews();
        return view;
    }

    private void initViews() {


    }
}
