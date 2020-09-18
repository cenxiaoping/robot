package com.bojun.common.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bojun.common.mvp.model.BaseModel;
import com.bojun.common.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * BaseMvpActivity
 */
public abstract class BaseMvpActivity<M extends BaseModel, V, P extends BasePresenter<M, V>> extends BaseActivity {
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectPresenter();
        if (mPresenter != null) {
            mPresenter.injectLifecycle(this);
        }
        super.onCreate(savedInstanceState);

    }

    public abstract void injectPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
