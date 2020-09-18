package com.bojun.main.fragment;

import android.transition.Fade;
import android.view.View;

import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentRoleChoicesBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;

import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  导诊首页
 * 作  者  ZHANGYU
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 18:04
 */
public class RoleChoicesFragment extends BaseMvvmFragment<FragmentRoleChoicesBinding, MainViewModel> {


    public static RoleChoicesFragment newInstance() {
        RoleChoicesFragment fragment = new RoleChoicesFragment();
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
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_role_choices;
    }

    @Override
    public void initView(View view) {

        mBinding.tempVBMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteChoicesFragment subFragment = SiteChoicesFragment.newInstance(1);
                setExitTransition(new Fade());
                subFragment.setEnterTransition(new Fade());
                extraTransaction().start(subFragment);
            }
        });
        mBinding.tempVBFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteChoicesFragment subFragment = SiteChoicesFragment.newInstance(2);
                setExitTransition(new Fade());
                subFragment.setEnterTransition(new Fade());
                extraTransaction().start(subFragment);
            }
        });
        mBinding.tempVBChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteChoicesFragment subFragment = SiteChoicesFragment.newInstance(3);
                setExitTransition(new Fade());
                subFragment.setEnterTransition(new Fade());
                extraTransaction().start(subFragment);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}