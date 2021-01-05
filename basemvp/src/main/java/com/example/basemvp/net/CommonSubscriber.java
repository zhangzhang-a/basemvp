package com.example.basemvp.net;

import android.text.TextUtils;


import com.example.basemvp.Ibase.CallBack;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 处理网络请求结果的错误和完成
 * @param <T>
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private CallBack callback;
    private String errorMsg;
    private boolean isShowErrorState = false;

    protected CommonSubscriber(CallBack callback){
        this.callback = callback;
    }

    protected CommonSubscriber(CallBack callback, String emsg){
        this.callback = callback;
        errorMsg = emsg;
    }

    @Override
    public void onError(Throwable t) {
        //LoadingUtil.getInstance().hideLoading();
        if(callback == null) return;
        if(errorMsg != null && TextUtils.isEmpty(errorMsg)){
            callback.failedData(errorMsg);
        }
    }

    @Override
    public void onComplete() {
        //LoadingUtil.getInstance().hideLoading();
        //mView.showLoading(View.GONE);
    }
}
