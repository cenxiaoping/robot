package com.bojun.common.mvp.view;

import android.content.Context;

/**
 * BaseView
 */
public interface BaseView extends ILoadView,INoDataView,ITransView,INetErrView{
    void initView();
    void initListener();
    void initData();
    void finishActivity();
    Context getContext();
}
