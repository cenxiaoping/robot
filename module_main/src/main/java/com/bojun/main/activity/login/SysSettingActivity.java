package com.bojun.main.activity.login;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bojun.bar.StatusBar;
import com.bojun.common.manager.ActivityManager;
import com.bojun.common.mvvm.BaseMvvmActivity;
import com.bojun.common.util.ToastUtil;
import com.bojun.main.BR;
import com.bojun.main.R;
import com.bojun.main.databinding.ActivitySysSettingBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.LoginViewModel;
import com.bojun.net.RetrofitManager;
import com.bojun.net.dto.KeyConstants;
import com.bojun.net.dto.RouteConstants;
import com.bojun.net.entity.SettingResponseBean;
import com.bojun.net.util.SpUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


/**
 * SysSettingActivity
 */
@Route(path = RouteConstants.ROUTE_SYS_SETTING_ACTIVITY)
public class SysSettingActivity extends BaseMvvmActivity<ActivitySysSettingBinding, LoginViewModel> {

    @Override
    public int onBindLayout() {
        return R.layout.activity_sys_setting;
    }

    @Override
    public void initView() {
        StatusBar.with(this).statusBarDarkFont(true).init();

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.btnConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(mBinding.etEquipmentCode.getText().toString().trim())) {
                    ToastUtil.showToast("请输入设备编码");
                    return;
                }
                if (TextUtils.isEmpty(mBinding.etIp.getText().toString().trim())) {
                    ToastUtil.showToast("请输入IP地址");
                    return;
                }
                if (TextUtils.isEmpty(mBinding.etPort.getText().toString().trim())) {
                    ToastUtil.showToast("请输入端口号");
                    return;
                }

                String address = SpUtil.getString(SysSettingActivity.this, KeyConstants.SERVER_ADDRESS);
                String port = SpUtil.getString(SysSettingActivity.this, KeyConstants.PORT_NUMBER);
                String equipmentCode = SpUtil.getString(SysSettingActivity.this, KeyConstants.EQUIPMENT_CODE);
                if (!mBinding.etIp.getText().toString().equals(address) || !mBinding.etPort.getText().toString().equals(port)
                        || !mBinding.etEquipmentCode.getText().toString().equals(equipmentCode)) {
                    RetrofitManager.release();
                }
                SpUtil.putString(SysSettingActivity.this, KeyConstants.SERVER_ADDRESS, mBinding.etIp.getText().toString().trim());
                SpUtil.putString(SysSettingActivity.this, KeyConstants.PORT_NUMBER, mBinding.etPort.getText().toString().trim());
                SpUtil.putString(SysSettingActivity.this, KeyConstants.EQUIPMENT_CODE, mBinding.etEquipmentCode.getText().toString().trim());

                ActivityManager.getInstance().finishAllActivity();
                startActivity(UserLoginActivity.class, null);
            }
        });
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    public void initData() {
        mViewModel.ip.set(SpUtil.getString(getContext(), KeyConstants.SERVER_ADDRESS, "192.168.1.92"));
        mViewModel.port.set(SpUtil.getString(getContext(), KeyConstants.PORT_NUMBER, "8684"));
        mViewModel.equipmentCode.set(SpUtil.getString(getContext(), KeyConstants.EQUIPMENT_CODE, ""));
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
        mViewModel.setResponse.observe(this, new Observer<SettingResponseBean>() {
            @Override
            public void onChanged(SettingResponseBean settingResponseBean) {

            }
        });
    }

    @Override
    public int onBindVariableId() {
        return BR.viewModel;
    }

}