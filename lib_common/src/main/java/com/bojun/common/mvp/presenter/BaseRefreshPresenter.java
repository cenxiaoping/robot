package com.bojun.common.mvp.presenter;

import android.content.Context;

import com.bojun.common.mvp.contract.BaseRefreshContract;
import com.bojun.common.mvp.view.BaseRefreshView;
import com.bojun.common.mvp.model.BaseModel;

/**
 * DBaseRefreshPresenter
 */
public abstract class BaseRefreshPresenter<M extends BaseModel,V extends BaseRefreshView<T>,T> extends BasePresenter<M,V> implements BaseRefreshContract.Presenter {
    public BaseRefreshPresenter(Context context, V view, M model) {
        super(context, view, model);
    }
}
