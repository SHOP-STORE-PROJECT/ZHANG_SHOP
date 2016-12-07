package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_sy extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        Context context = getActivity();
        View view = View.inflate(context,R.layout.fragment_sy, null);
        return view;

    }
}
