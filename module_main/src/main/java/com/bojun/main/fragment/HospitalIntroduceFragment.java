package com.bojun.main.fragment;

import android.view.View;

import com.bojun.common.fragmentation.SupportFragment;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentHospitalIntroduceBinding;
import com.bojun.main.databinding.FragmentXiaoboHelpBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;

import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class HospitalIntroduceFragment extends BaseMvvmFragment<FragmentHospitalIntroduceBinding, MainViewModel> {

    public static HospitalIntroduceFragment newInstance() {
        HospitalIntroduceFragment fragment = new HospitalIntroduceFragment();
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
        return R.layout.fragment_hospital_introduce;
    }

    @Override
    public void initView(View view) {
        //默认选中医院介绍
        mBinding.consHos.setSelected(true);
        mBinding.consDepart.setSelected(false);
        loadRootFragment(R.id.fra_hospital_introduce,HospitalFragment.newInstance());

        mBinding.consHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.consHos.setSelected(true);
                mBinding.consDepart.setSelected(false);
                mBinding.consPaiban.setSelected(false);

                HospitalFragment fragment = findFragment(HospitalFragment.class);
                if (null == fragment) {
                    loadRootFragment(R.id.fra_hospital_introduce,HospitalFragment.newInstance());
                } else {
                    start(fragment, SupportFragment.SINGLETASK);
                }
            }
        });
        mBinding.consDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.consDepart.setSelected(true);
                mBinding.consHos.setSelected(false);
                mBinding.consPaiban.setSelected(false);

                DepartDescFragment fragment = findFragment(DepartDescFragment.class);
                if (null == fragment) {
//                    startWithPopTo(HospitalFragment.newInstance(), HospitalFragment.class, false);
                    loadRootFragment(R.id.fra_hospital_introduce,DepartDescFragment.newInstance());
                } else {
                    start(fragment, SupportFragment.SINGLETASK);
                }
            }
        });

        mBinding.consPaiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.consPaiban.setSelected(true);
                mBinding.consDepart.setSelected(false);
                mBinding.consHos.setSelected(false);

                SchedulingFragment fragment = findFragment(SchedulingFragment.class);
                if (null == fragment) {
//                    startWithPopTo(HospitalFragment.newInstance(), HospitalFragment.class, false);
                    loadRootFragment(R.id.fra_hospital_introduce,SchedulingFragment.newInstance());
                } else {
                    start(fragment, SupportFragment.SINGLETASK);
                }
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