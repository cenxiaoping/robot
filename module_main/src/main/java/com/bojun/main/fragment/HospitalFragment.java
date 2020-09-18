package com.bojun.main.fragment;

import android.text.Html;
import android.text.method.BaseMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentHospitalBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.HospitalBarCodeBean;
import com.bumptech.glide.Glide;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  医院介绍
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/31 11:26
 */
public class HospitalFragment extends BaseMvvmFragment<FragmentHospitalBinding, MainViewModel> {

    public static HospitalFragment newInstance() {
        HospitalFragment fragment = new HospitalFragment();
        return fragment;
    }

    @Override
    public Class<MainViewModel> onBindViewModel() {
        return MainViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getActivity().getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getHospitalInfoEvent().observe(this, new Observer<HospitalBarCodeBean>() {
            @Override
            public void onChanged(HospitalBarCodeBean hospitalBarCodeBean) {
                Glide.with(getActivity()).load(hospitalBarCodeBean.getImage()).into(mBinding.ivImg);
                mBinding.tvHospitalName.setText(hospitalBarCodeBean.getName());
                mBinding.tvDesc.setText(Html.fromHtml(hospitalBarCodeBean.getDescription()));
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_hospital;
    }

    @Override
    public void initView(View view) {
        mBinding.tvDesc.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void initData() {
        mViewModel.mHospitalCode.set("123456");
        mViewModel.getHospitalBarCode();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}