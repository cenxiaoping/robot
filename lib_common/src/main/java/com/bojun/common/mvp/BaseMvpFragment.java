package com.bojun.common.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bojun.common.mvp.model.BaseModel;
import com.bojun.common.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * BaseMvpFragment
 */
public abstract class BaseMvpFragment<M extends BaseModel, V, P extends BasePresenter<M, V>> extends BaseFragment {
    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectPresenter();
        if (mPresenter != null) {
            mPresenter.injectLifecycle(mActivity);
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }

    public abstract void injectPresenter();
}
