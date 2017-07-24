package com.practice.firstdemo.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.practice.firstdemo.R;
import com.practice.firstdemo.custom.CustomListView;
import com.practice.firstdemo.custom.CustomListView.IloadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AdamL on 2017/7/24.
 */

public class PagingPeactice extends AppCompatActivity implements IloadListener {
    private CustomListView listview;
    private List<String> listviewitems = new ArrayList<>();
    private ArrayAdapter adapter;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_paging);
        listview = (CustomListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listviewitems);
        listview.setAdapter(adapter);// 加载适配器
        listview.setInterface(this);//将接口传进来
        initItems();// 初始化数据

    }

    private void initItems() {
        for (int i = 0; i < 30; i++) {
            listviewitems.add("item" + i);
        }
    }


    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    listviewitems.add("load item" + count);
                    count++;
                }
                listview.loadCompleted();
            }
        }, 2000);
    }
}
