package com.practice.firstdemo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.practice.firstdemo.R;
import com.practice.firstdemo.adapter.MyAdapter;
import com.practice.firstdemo.bean.News;
import com.practice.firstdemo.custom.CustomListView;
import com.practice.firstdemo.interfaces.HttpCallBack;
import com.practice.firstdemo.utils.JsonResponseParserUtils;
import com.practice.firstdemo.utils.ViewFindUtils;
import com.practice.firstdemo.utils.XUrils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hui on 2017/7/24.
 */

public class FragNewFlash extends Fragment implements CustomListView.IloadListener {

    private Context mContext;
    private View view;
    private CustomListView listView;
    private MyAdapter adapter;
    private int count = 0;
    private List<News.NewliestBean> newliest;
    private List<News.NewliestBean> data;

    public static final FragNewFlash getInstance(Context context) {
        FragNewFlash frag1 = new FragNewFlash();
        frag1.mContext = context;
        return frag1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_flash, null);

        //初始化数据
        initView();
        //从网络端获取数据
        gainDataFromInternet();

        return view;
    }

    /**
     * 从网络端获取数据
     */
    private void gainDataFromInternet() {
        String url = "http://app.10brandchina.com/io/2.9.0/news.inc.php?action=newzixun&type=1&page=1";

        XUrils.Post(url, null, new HttpCallBack<String>() {


            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("asd", "start parser" + result);
                try {

                    newliest = JsonResponseParserUtils.getList(result);
                    if (newliest != null) {
                        Log.i("asd", "successful");
                        //设置数据源 & 适配ListView
                        setDataSourceAdapter(newliest);
                    }

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    Log.i("asd", "defeat");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }


    /**
     * 设置数据源 & 适配ListView
     */
    private void setDataSourceAdapter(List<News.NewliestBean> newliest) {
        data = new ArrayList<>();

        adapter = new MyAdapter(getActivity(), data);
        listView.setAdapter(adapter);
        listView.setInterface(this);
        for (int i = 0; i < 5; i++) {
            data.add(newliest.get(i));
            count++;
        }
        Log.i("asd", data.size() + "");
    }

    /**
     * 初始化数据
     */
    private void initView() {
        listView = ViewFindUtils.find(view, R.id.listNew);
        listView.setDivider(new ColorDrawable(Color.parseColor("#cccccc")));
        listView.setDividerHeight(2);
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count < newliest.size()) {
                    for (int i = count; i < newliest.size(); i++) {
                        data.add(newliest.get(i));
                        count++;
                    }
                    Log.i("zxc", count + "");
                    listView.loadCompleted();
                } else {
                    Toast.makeText(mContext, "没有更多数据了！！", Toast.LENGTH_SHORT).show();
                    listView.loadCompleted();
                }
            }
        }, 2000);
    }
}
