package com.practice.firstdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.practice.firstdemo.R;
import com.practice.firstdemo.bean.News;
import com.practice.firstdemo.custom.CustomImageView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by hui on 2017/7/24.
 */

public class MyAdapter extends BaseAdapter {

    Context mcontext;
    LayoutInflater inflater;
    final int VIEW_TYPE = 2; // 总的item样式数
    final int TYPE_0 = 0;
    final int TYPE_1 = 1;
    private List<News.NewliestBean> mNews = new ArrayList<>();
    private int TYPE_NUM = 0;


    public MyAdapter(Context context, List<News.NewliestBean> news) {
        this.mNews = news;
        this.mcontext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {  //item样式的返回类型绑定
        if (position == 0) {
            return TYPE_0;
        } else {
            return TYPE_1;
        }

    }

    @Override
    public int getViewTypeCount() {  //item样式类型总数

        return VIEW_TYPE;
    }

    @Override
    public int getCount() {
        return mNews.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        ViewHolder1 holder1 = new ViewHolder1();
        Log.i("asd", "" + position);
        if (!mNews.get(position).getThumb().equals("") || position == 9) {
            TYPE_NUM = 0;
        } else {
            TYPE_NUM = 1;
        }

        int type = getItemViewType(TYPE_NUM);  //获取所有样式类型总数
        if (convertView == null) {
            switch (type) {
                case TYPE_0:
                    convertView = inflater.inflate(R.layout.item_news_type_01, parent, false);
                    holder.title = (TextView) convertView.findViewById(R.id.title_);
                    holder.source = (TextView) convertView.findViewById(R.id.source_);
                    holder.data = (TextView) convertView.findViewById(R.id.data_);
                    holder.img_show = (CustomImageView) convertView.findViewById(R.id.img_show);
                    convertView.setTag(holder);
                    break;
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.item_news_type_02, parent, false);
                    holder1.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                    holder1.tv_source = (TextView) convertView.findViewById(R.id.tv_source);
                    holder1.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
                    convertView.setTag(holder1);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE_0:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TYPE_0:
                holder.title.setText(mNews.get(position).getTitle());
                holder.source.setText(mNews.get(position).getCopyfrom());
                holder.data.setText(mNews.get(position).getAddtime());
                Glide.with(mcontext).load(mNews.get(position).getThumb()).into(holder.img_show);
                break;
            case TYPE_1:
                holder1.tv_title.setText(mNews.get(position).getTitle());
                holder1.tv_source.setText(mNews.get(position).getCopyfrom());
                holder1.tv_data.setText(mNews.get(position).getAddtime());
                break;
        }

        return convertView;
    }

    class ViewHolder {
        private TextView title, source, data;
        private CustomImageView img_show;
    }

    class ViewHolder1 {
        private TextView tv_title, tv_source, tv_data;
    }

}
