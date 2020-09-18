package com.bojun.main.mvvm.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import com.bojun.common.BaseApplication;
import com.bojun.common.event.SingleLiveEvent;
import com.bojun.common.mvvm.viewmodel.BaseViewModel;
import com.bojun.common.util.ToastUtil;
import com.bojun.main.mvvm.model.LoginModel;
import com.bojun.main.mvvm.model.SplashModel;
import com.bojun.net.RetrofitManager;
import com.bojun.net.dto.BadNumList;
import com.bojun.net.dto.CodeData;
import com.bojun.net.dto.DepartmentBean;
import com.bojun.net.dto.DepartmentDoctor;
import com.bojun.net.dto.DeptWardBean;
import com.bojun.net.dto.KeyConstants;
import com.bojun.net.dto.MainLeftMenu;
import com.bojun.net.dto.PatientInfoBean;
import com.bojun.net.entity.DepartListBean;
import com.bojun.net.entity.SettingResponseBean;
import com.bojun.net.http.ExceptionHandler;
import com.bojun.net.user.LoginDTO;
import com.example.lib_utils.LogPrintUtil;
import com.example.lib_utils.SpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * LoginViewModel
 */
public class LoginViewModel extends BaseViewModel<LoginModel> {
    public static String TAG = LoginViewModel.class.getSimpleName();
    //账号
    public ObservableField<String> username = new ObservableField<>();
    //密码
    public ObservableField<String> password = new ObservableField<>();
    //IP地址
    public ObservableField<String> ip = new ObservableField<>();
    //PORT端口号
    public ObservableField<String> port = new ObservableField<>();
    //设备号
    public ObservableField<String> equipmentCode = new ObservableField<>();
    public ObservableField<String> rotCode = new ObservableField<>();
    public ObservableField<String> version = new ObservableField<>();

    public SingleLiveEvent<SettingResponseBean> setResponse = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> loginEvent = new SingleLiveEvent<>();


    public SingleLiveEvent<SettingResponseBean> getSetResponse() {
        return setResponse = createLiveData(setResponse);
    }

    public SingleLiveEvent<Void> getLoginEvent() {
        return loginEvent = createLiveData(loginEvent);
    }


    public LoginViewModel(@NonNull Application application, LoginModel model) {
        super(application, model);
    }

    /**
     * 系统设置
     */
    public void sysSet() {
        mModel.systemSeting(rotCode.get(), version.get()).subscribe(new Observer<CodeData<SettingResponseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<SettingResponseBean> settingResponseBeanCodeData) {
                if (settingResponseBeanCodeData.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getSetResponse().setValue(settingResponseBeanCodeData.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 系统管理员登录
     */
    public void login() {
        if (TextUtils.isEmpty(password.get())) {
            ToastUtil.showToast("请输入管理员密码");
            return;
        }

        if (password.get().equals("666666")) {
            getLoginEvent().call();
        } else {
            ToastUtil.showToast("密码错误，请重试");
        }
    }
}