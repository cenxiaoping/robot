package com.bojun.main.mvvm.model;

import android.app.Application;

import com.bojun.common.mvvm.model.BaseModel;
import com.bojun.net.RetrofitManager;
import com.bojun.net.dto.BadNumList;
import com.bojun.net.dto.CodeData;
import com.bojun.net.dto.DepartmentBean;
import com.bojun.net.dto.DepartmentDoctor;
import com.bojun.net.dto.DeptWardBean;
import com.bojun.net.dto.MainLeftMenu;
import com.bojun.net.dto.PatientInfoBean;
import com.bojun.net.entity.SettingResponseBean;
import com.bojun.net.http.RxAdapter;
import com.bojun.net.user.LoginDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * SplashModel
 */
public class LoginModel extends BaseModel {
    public LoginModel(Application application) {
        super(application);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Observable<CodeData<LoginDTO>> login(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("accountNo", username);
        params.put("passwords", password);
        return RetrofitManager.getInstance().getPatientInfoService().login(null)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 系统设置
     *
     * @param robotCode
     * @return
     */
    public Observable<CodeData<SettingResponseBean>> systemSeting(String robotCode,String version) {
        return RetrofitManager.getInstance().getPatientInfoService().systemSeting(robotCode,version)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }
}