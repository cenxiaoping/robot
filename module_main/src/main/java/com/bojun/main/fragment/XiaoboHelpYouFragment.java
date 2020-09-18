package com.bojun.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.fragmentation.SupportFragment;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentXiaoboHelpBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.dto.PatientInfoBean;
import com.bojun.net.entity.NavigationListBean;
import com.bojun.net.entity.PreDiagnosisListBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class XiaoboHelpYouFragment extends BaseMvvmFragment<FragmentXiaoboHelpBinding, MainViewModel> {

    private List<NavigationListBean> navigationDatas = new ArrayList<>();
    private List<PreDiagnosisListBean> preDiagnosisDatas = new ArrayList<>();

    public static XiaoboHelpYouFragment newInstance() {
        XiaoboHelpYouFragment fragment = new XiaoboHelpYouFragment();
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
        mViewModel.getNavigationListEvent().observe(this, new Observer<List<NavigationListBean>>() {
            @Override
            public void onChanged(List<NavigationListBean> navigationListBeans) {
                navigationDatas.clear();
                navigationDatas.addAll(navigationListBeans);
                mBinding.listDh.getAdapter().notifyDataSetChanged();
            }
        });

        mViewModel.getPreDiagnosisListEvent().observe(this, new Observer<List<PreDiagnosisListBean>>() {
            @Override
            public void onChanged(List<PreDiagnosisListBean> preDiagnosisListBeans) {
                preDiagnosisDatas.clear();
                preDiagnosisDatas.addAll(preDiagnosisListBeans);
                mBinding.listYjfz.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_xiaobo_help;
    }

    @Override
    public void initView(View view) {

        mBinding.listDh.setAdapter(new BaseRecycerAdapter<NavigationListBean>(getContext(), navigationDatas, R.layout.item_xiaobo_item) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, NavigationListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                ImageView iv_temp = viewHolder.findViewById(R.id.iv_temp);
                tv_content.setText(itemVO.getTitle());
                iv_temp.setImageResource(R.mipmap.daohang);

            }
        });
        mBinding.listYjfz.setAdapter(new BaseRecycerAdapter<PreDiagnosisListBean>(getContext(), preDiagnosisDatas, R.layout.item_xiaobo_item) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, PreDiagnosisListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                ImageView iv_temp = viewHolder.findViewById(R.id.iv_temp);
                tv_content.setText(itemVO.getTitle());
                iv_temp.setImageResource(R.mipmap.fenzhen);
            }
        });
    }

    @Override
    public void initData() {
        mViewModel.mHospitalCode.set("123456");
        mViewModel.navigationList();
        mViewModel.preDiagnosisList();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}