package com.practice.firstdemo.interfaces;

import org.xutils.common.Callback;

/**
 * Created by hui on 2017/7/24.
 */

public class HttpCallBack<ResultType> implements Callback.CommonCallback<ResultType> {
    @Override
    public void onSuccess(ResultType result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
