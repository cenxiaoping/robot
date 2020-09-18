package com.bojun.main.activity.login;

import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bojun.bar.StatusBar;
import com.bojun.common.mvvm.BaseMvvmActivity;
import com.bojun.common.util.FastClickToActUtils;
import com.bojun.main.BR;
import com.bojun.main.R;
import com.bojun.main.databinding.ActivityLoginBinding;

import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.LoginViewModel;

import com.bojun.net.dto.RouteConstants;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


/**
 * LoginActivity
 */
@Route(path = RouteConstants.ROUTE_LOGIN_ACTIVITY)
public class SysLoginActivity extends BaseMvvmActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public int onBindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        StatusBar.with(this).statusBarDarkFont(true).init();

        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.login();
            }
        });
//        FastClickToActUtils.fastClick(mBinding.ivLogo);
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    public void initData() {

    }


    @Override
    public Class<LoginViewModel> onBindViewModel() {
        return LoginViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getApplication());
    }

    @Override
    public void initViewObservable() {
//        mViewModel.getmVoidSingleLiveEvent().observe(this, new Observer<Void>() {
//            @Override
//            public void onChanged(@Nullable Void aVoid) {
//                startMainActivity();
//            }
//        });

        mViewModel.getLoginEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                startActivity(new Intent(SysLoginActivity.this, SysSettingActivity.class));
                finish();
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return BR.viewModel;
    }

}