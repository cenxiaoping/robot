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
public class RoleChoicesMainFragment extends BaseMvvmFragment<FragmentBaseMainBinding, MainViewModel> {

    public static RoleChoicesMainFragment newInstance() {
        RoleChoicesMainFragment fragment = new RoleChoicesMainFragment();
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

        if (findChildFragment(RoleChoicesFragment.class) == null) {
            loadRootFragment(R.id.fl_container, RoleChoicesFragment.newInstance());
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
}