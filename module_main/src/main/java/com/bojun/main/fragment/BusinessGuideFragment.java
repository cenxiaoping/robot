package com.bojun.main.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentBusinessGuideBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.BusinessListBean;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述   业务指引
 * 作  者  lanxianzheng
 * 包  名  com.bojun.main.fragment
 * 时  间  2020-08-29 8:26
 */
public class BusinessGuideFragment extends BaseMvvmFragment<FragmentBusinessGuideBinding, MainViewModel> {
    private BaseRecycerAdapter<BusinessListBean> adapter;

    private List<BusinessListBean> mDatas = new ArrayList<>();

    @Override
    public Class onBindViewModel() {
        return MainViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getActivity().getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getBusinessListEvent().observe(this, new Observer<List<BusinessListBean>>() {
            @Override
            public void onChanged(List<BusinessListBean> businessListBeans) {
                mDatas.clear();
                mDatas.addAll(businessListBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_business_guide;
    }

    @Override
    public void initView(View view) {
        adapter = new BaseRecycerAdapter<BusinessListBean>(getContext(), mDatas, R.layout.item_depart_list) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, BusinessListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                tv_content.setText(itemVO.getName());
                tv_content.setSelected(itemVO.isSelect());//改变选择item颜色
            }
        };
        adapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<BusinessListBean>() {
            @Override
            public void onItemClick(BusinessListBean o, int position) {

                for (BusinessListBean bean : mDatas) {
                    bean.setSelect(false);
                }

                mDatas.get(position).setSelect(true);
                adapter.notifyDataSetChanged();

                mBinding.tvContent.setText(Html.fromHtml(o.getContent()));
                mBinding.tvTitle.setText(o.getName());
            }
        });
        mBinding.rvMenu.setAdapter(adapter);
    }

    @Override
    public void initData() {
        mViewModel.mHospitalCode.set("123456");
        mViewModel.selectBusinessGuide();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    public static BusinessGuideFragment newInstance() {

        Bundle args = new Bundle();

        BusinessGuideFragment fragment = new BusinessGuideFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
