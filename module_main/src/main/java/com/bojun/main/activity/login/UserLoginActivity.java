package com.bojun.main.activity.login;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;

import com.bojun.common.mvvm.BaseMvvmActivity;
import com.bojun.common.util.FastClickToActUtils;
import com.bojun.common.util.ToastUtil;
import com.bojun.main.R;
import com.bojun.main.activity.main.MainActivity;
import com.bojun.main.databinding.ActivityUserLoginBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.LoginViewModel;
import com.bojun.net.dto.KeyConstants;
import com.bojun.net.entity.SettingResponseBean;
import com.bojun.net.util.SpUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.activity.login
 * 时  间  2020/9/7 17:03
 */
public class UserLoginActivity extends BaseMvvmActivity<ActivityUserLoginBinding, LoginViewModel> {
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
        mViewModel.getSetResponse().observe(this, new Observer<SettingResponseBean>() {
            @Override
            public void onChanged(SettingResponseBean settingResponseBean) {
                String token = settingResponseBean.getToken();
                SpUtil.putString(UserLoginActivity.this, KeyConstants.TOKEN, token);
                startActivity(MainActivity.class, null);
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    public void initView() {
        super.initView();
        FastClickToActUtils.fastClick(findViewById(R.id.iv_logo));
        findViewById(R.id.tv_barcode_title).setVisibility(View.GONE);
        findViewById(R.id.iv_barcode).setVisibility(View.GONE);
        findViewById(R.id.tv_tianqi).setVisibility(View.GONE);
        findViewById(R.id.iv_tianqi).setVisibility(View.GONE);

        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.rotCode.set(mBinding.tvCode.getText().toString());
                String code = mBinding.tvCode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    ToastUtil.showToast("请先配置设备编码");
                    return;
                }
                mViewModel.sysSet();
            }
        });

        //设置设备编码
        String equipmentCode = SpUtil.getString(this, KeyConstants.EQUIPMENT_CODE);
        mBinding.tvCode.setText(equipmentCode);

        //设置版本号
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            mViewModel.version.set(versionName);
            mBinding.tvVersionCode.setText(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}