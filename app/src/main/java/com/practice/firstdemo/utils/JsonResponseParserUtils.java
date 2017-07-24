package com.practice.firstdemo.utils;


import android.util.Log;


import com.practice.firstdemo.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hui on 2017/7/24.
 */

public class JsonResponseParserUtils {

    public static final List<News.NewliestBean> getList(String result) {

        List<News.NewliestBean> info = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(result);

            JSONArray array = obj.getJSONArray("newliest");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                News.NewliestBean newliest = new News.NewliestBean();
                newliest.setTitle(object.getString("title"));
                newliest.setAddtime(object.getString("addtime"));
                newliest.setThumb(object.getString("thumb"));
                newliest.setCopyfrom(object.getString("copyfrom"));
                info.add(newliest);
            }

            Log.i("asd", info.size() + "");
            return info;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
