package com.bojun.common.mvp.model;

import android.content.Context;

import com.trello.rxlifecycle3.LifecycleProvider;


/**
 * BaseModel
 */
public class BaseModel {
    private Context mContext;
    private LifecycleProvider lifecycle;

    public BaseModel(Context context) {
        mContext = context;
    }

    public void injectLifecycle(LifecycleProvider lifecycle) {
        this.lifecycle = lifecycle;
    }

    public LifecycleProvider getLifecycle() {
        return lifecycle;
    }

    public Context getContext() {
        return mContext;
    }

    public void destory() {
    }
}
