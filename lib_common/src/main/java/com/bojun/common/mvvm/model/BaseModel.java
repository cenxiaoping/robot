package com.bojun.common.mvvm.model;

import android.app.Application;

import com.google.gson.GsonBuilder;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * BaseModel
 */
public abstract class BaseModel implements IBaseModel {
    protected Application mApplication;
    private CompositeDisposable mCompositeDisposable;

    public BaseModel(Application application) {
        mApplication = application;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onCleared() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    /**
     * 转换RequestBody入参
     *
     * @param map
     * @return
     */
    public RequestBody getRequestBody(Map map) {
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), new GsonBuilder().disableHtmlEscaping().create().toJson(map));
    }

}
