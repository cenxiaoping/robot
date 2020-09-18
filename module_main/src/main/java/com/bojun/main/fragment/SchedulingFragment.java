package com.bojun.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.adapter.SchedulingAdapter;
import com.bojun.main.databinding.FragmentDepartBinding;
import com.bojun.main.databinding.FragmentSchedulingBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.DepartInfoBean;
import com.bojun.net.entity.DepartListBean;
import com.bojun.net.entity.DoctorListBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  医生排班
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/31 11:26
 */
public class SchedulingFragment extends BaseMvvmFragment<FragmentSchedulingBinding, MainViewModel> {


    public static SchedulingFragment newInstance() {
        SchedulingFragment fragment = new SchedulingFragment();
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
        return R.layout.fragment_scheduling;
    }

    @Override
    public void initView(View view) {

        List<List<String>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        SchedulingAdapter adapter = new SchedulingAdapter(data);
        mBinding.scrollablePanel.setPanelAdapter(adapter);
    }

    @Override
    public void initData() {
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}