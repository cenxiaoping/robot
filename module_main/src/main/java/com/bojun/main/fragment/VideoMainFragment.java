package com.bojun.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentBaseMainBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述
 * 作  者  管流生
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class VideoMainFragment extends BaseMvvmFragment<FragmentBaseMainBinding, MainViewModel> {

    public static VideoMainFragment newInstance() {
        VideoMainFragment fragment = new VideoMainFragment();
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

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(HospitalVideoFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HospitalVideoFragment.newInstance());
        }
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_base_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
//        getSupportDelegate().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        getSupportDelegate().onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        getSupportDelegate().getVisibleDelegate().;
    }
}