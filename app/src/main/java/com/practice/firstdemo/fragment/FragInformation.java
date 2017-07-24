package com.practice.firstdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practice.firstdemo.R;


/**
 * Created by hui on 2017/7/24.
 */

public class FragInformation extends Fragment {

    private Context mContext;

    public static final FragInformation getInstance(Context context) {
        FragInformation frag2 = new FragInformation();
        frag2.mContext = context;
        return frag2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, null);


        return view;
    }
}
