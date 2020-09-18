package com.bojun.main.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bojun.main.mvvm.model.MainModel;
import com.bojun.main.mvvm.model.LoginModel;
import com.bojun.main.mvvm.model.SplashModel;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.main.mvvm.viewmodel.LoginViewModel;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;

/**
 * NewsViewModelFactory
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile MainViewModelFactory INSTANCE;
    private final Application mApplication;

    public static MainViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (MainViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private MainViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(mApplication, new SplashModel(mApplication));
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication, new MainModel(mApplication));
        }else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication, new LoginModel(mApplication));
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
