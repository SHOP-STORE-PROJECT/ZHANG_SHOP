package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import java.util.Arrays;
import java.util.LinkedList;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.activity.views.ViewsInterface;
import shop.yunifang.com.yunifang.modle.Api;
import shop.yunifang.com.yunifang.prent.MyPent;

/**
 * Created by ZhangDongXu on 2016/12/6.
 */
public class Fragment_sy extends Fragment implements ViewsInterface{
    private Context context;
    private View view;
    private PullToRefreshListView mPullRefreshListView;
    private LinkedList<String> mListItems;
    static final int MENU_MANUAL_REFRESH = 0;
    static final int MENU_DISABLE_SCROLL = 1;
    static final int MENU_SET_MODE = 2;
    static final int MENU_DEMO = 3;
    private ArrayAdapter<String> mAdapter;
    private MyPent pent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        pent = new MyPent();
        pent.setSy(this);
        MyPent.myPent(context, Api.homeUrl);
        view = View.inflate(context, R.layout.fragment_sy, null);
        mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.pull_refresh_sy);
        //刷新界面
        refreshFragment();
        return view;
    }

    private void refreshFragment() {
        // Set a listener to be invoked when the list should be refreshed.
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
//                new GetDataTask().execute();
            }
        });

        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "End of List!", Toast.LENGTH_SHORT).show();
            }
        });

        ListView actualListView = mPullRefreshListView.getRefreshableView();

        // Need to use the Actual ListView when registering for Context Menu
        registerForContextMenu(actualListView);

        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
//适配器
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mListItems);

        /**
         * Add Sound Event Listener
         */
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(getActivity());
//        soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
//        soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
//        soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
        mPullRefreshListView.setOnPullEventListener(soundListener);

        // You can also just use setListAdapter(mAdapter) or
        // mPullRefreshListView.setAdapter(mAdapter)
        actualListView.setAdapter(mAdapter);
    }

    @Override
    public void successGet(String datas) {
        Log.e("Fragment=======sy",datas);
    }

    @Override
    public void failedGet(String errCode) {

    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }
        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_MANUAL_REFRESH:
                new GetDataTask().execute();
                mPullRefreshListView.setRefreshing(false);
                break;
            case MENU_DISABLE_SCROLL:
                mPullRefreshListView.setScrollingWhileRefreshingEnabled(!mPullRefreshListView
                        .isScrollingWhileRefreshingEnabled());
                break;
            case MENU_DEMO:
                mPullRefreshListView.demo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler" };
}
