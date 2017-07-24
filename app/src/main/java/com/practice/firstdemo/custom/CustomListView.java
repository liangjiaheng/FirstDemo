package com.practice.firstdemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.practice.firstdemo.R;

/**
 * Created by AdamL on 2017/7/24.
 */

public class CustomListView extends ListView implements OnScrollListener {

    private View footer;//底部布局
    int totalItemCount;//总数量
    int lastVisibieItem;//最后一个可见的item
    boolean isLoading;//判断变量
    IloadListener iloadListener;//接口变量

    public CustomListView(Context context) {
        super(context);
        initView(context);
    }


    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    // listview加载底部布局
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_more, null);
        // 设置隐藏底部布局
        footer.findViewById(R.id.footer_layout).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibieItem && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footer.findViewById(R.id.footer_layout).setVisibility(View.VISIBLE);
                // 加载更多（获取接口）
                iloadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        this.lastVisibieItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void setInterface(IloadListener iLoadListener) {
        this.iloadListener = iLoadListener;
    }

    // 加载更多数据的回调接口
    public interface IloadListener {
        public void onLoad();
    }

    /**
     * 加载完成通知隐藏
     */
    public void loadCompleted() {
        isLoading = false;
        footer.findViewById(R.id.footer_layout).setVisibility(View.GONE);
    }


}
