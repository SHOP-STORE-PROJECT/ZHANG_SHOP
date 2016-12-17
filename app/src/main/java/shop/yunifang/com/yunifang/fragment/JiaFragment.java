package shop.yunifang.com.yunifang.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.Content;
import shop.yunifang.com.yunifang.bean.Goods;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class JiaFragment extends Fragment {
    private Context context;
    public static Fragment jiaInsTance(ArrayList<Goods.GoodsComments> list){
        JiaFragment jiaFragment = new JiaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list",list);
        jiaFragment.setArguments(bundle);
        return jiaFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = View.inflate(context, R.layout.pinglun_layout,null);
        initViews(view);
        return view;
    }
    private void initViews( View view ) {
        ListView view1 = (ListView) view.findViewById(R.id.pinglun_list);
//        TextView textView = view.findViewById(R.id.)
        List<Goods.GoodsComments>list = getArguments().getParcelableArrayList("list");
        List<Content>contents = new ArrayList<>();
        for(Goods.GoodsComments comments:list){
            contents.add(new Content("用户名:"+comments.user.nick_name+"\n\t用户体验心得:"+comments.content));
        }
        view1.setAdapter(new ArrayAdapter<Content>(context,android.R.layout.test_list_item,contents));
    }
}
