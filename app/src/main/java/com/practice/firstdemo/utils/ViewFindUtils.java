package com.practice.firstdemo.utils;

import android.view.View;


/**
 * Created by hui on 2017/7/24.
 */

public class ViewFindUtils {

    public static <T extends View> T find(View view,int id){
        return (T)view.findViewById(id);
    }

}
