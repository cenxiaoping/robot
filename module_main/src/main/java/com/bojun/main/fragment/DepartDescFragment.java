package com.bojun.main.fragment;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentDepartBinding;
import com.bojun.main.dialog.NavigationInfoDialog;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.DepartInfoBean;
import com.bojun.net.entity.DepartListBean;
import com.bojun.net.entity.DoctorListBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 简  述  科室介绍
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/31 11:26
 */
public class DepartDescFragment extends BaseMvvmFragment<FragmentDepartBinding, MainViewModel> {

    //科室列表有两个层级
    private int currentLeve = 1;

    private List<DepartListBean> mDatas = new ArrayList<>();
    private List<DoctorListBean> mDoctorDatas = new ArrayList<>();

    //科室名称
    private String departName;
    //导航地址
    private String navigation;

    public static DepartDescFragment newInstance() {
        DepartDescFragment fragment = new DepartDescFragment();
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
        mViewModel.getDepartListEvent().observe(this, new Observer<List<DepartListBean>>() {
            @Override
            public void onChanged(List<DepartListBean> departListBeans) {
                mDatas.clear();
                mDatas.addAll(departListBeans);
                mBinding.listDepart.getAdapter().notifyDataSetChanged();

                mBinding.consBack.setVisibility(currentLeve == 2 ? View.VISIBLE : View.GONE);
            }
        });

        mViewModel.getDepartInfoEvent().observe(this, new Observer<DepartInfoBean>() {
            @Override
            public void onChanged(DepartInfoBean departInfoBean) {
                //科室信息
                mBinding.tvDepartDesc.setText(Html.fromHtml(departInfoBean.getDescription()));
                mBinding.ivGoHere.setVisibility(View.VISIBLE);

                departName = departInfoBean.getName();
                navigation = departInfoBean.getPosition();
            }
        });

        mViewModel.getDoctorListEvent().observe(this, new Observer<List<DoctorListBean>>() {
            @Override
            public void onChanged(List<DoctorListBean> doctorListBeans) {
                //医生列表
                mDoctorDatas.clear();
                mDoctorDatas.addAll(doctorListBeans);
                mBinding.listDoctor.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_depart;
    }

    @Override
    public void initView(View view) {
        mBinding.listDepart.setAdapter(new BaseRecycerAdapter<DepartListBean>(getContext(), mDatas, R.layout.item_depart_list) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, DepartListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                tv_content.setSelected(itemVO.isSelect());
                tv_content.setText(itemVO.getName());
            }
        }.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<DepartListBean>() {
            @Override
            public void onItemClick(DepartListBean o, int position) {
                for (DepartListBean bean : mDatas) {
                    bean.setSelect(false);
                }

                mDatas.get(position).setSelect(true);
                mBinding.listDepart.getAdapter().notifyDataSetChanged();

                if (currentLeve == 1) {
                    //进入第二层级
                    mBinding.tvBack.setVisibility(View.VISIBLE);
                    currentLeve += 1;

                    //获取第二层级数据
                    mViewModel.mParentCode.set(o.getDeptCode());
                    mViewModel.getDeptList();
                } else {
                    //显示详情，查询科室信息、查询科室医生
                    mViewModel.mDepartCode.set(o.getDeptCode());
                    mBinding.ivGoHere.setVisibility(View.GONE);
                    mViewModel.getDeptInfo();
                    mViewModel.getDoctorList();

                    mDoctorDatas.clear();
                    mBinding.listDoctor.getAdapter().notifyDataSetChanged();
                }
            }
        }));

        mBinding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回第一层级
                mViewModel.mParentCode.set("0");
                currentLeve = 1;
                mViewModel.getDeptList();
            }
        });

        mBinding.listDoctor.setAdapter(new BaseRecycerAdapter<DoctorListBean>(getActivity(), mDoctorDatas, R.layout.item_doctor_depart) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, DoctorListBean itemVO, int position) {
                TextView tv_doctor_name = viewHolder.findViewById(R.id.tv_doctor_name);
                TextView tv_job_name = viewHolder.findViewById(R.id.tv_job_name);
                TextView tv_desc = viewHolder.findViewById(R.id.tv_desc);
                ImageView iv_icon = viewHolder.findViewById(R.id.iv_icon);

                tv_doctor_name.setText(itemVO.getName());
                tv_job_name.setText(itemVO.getPostName());
                tv_desc.setText(Html.fromHtml(itemVO.getIntroduce()));
                Glide.with(getActivity()).load(itemVO.getDoctorImage()).into(iv_icon);
            }
        });

        mBinding.ivGoHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NavigationInfoDialog().setDepartName(departName).setNavigation(navigation).show(getChildFragmentManager(), "");
            }
        });
    }

    @Override
    public void initData() {
        //获取第一层级数据
        mViewModel.mHospitalCode.set("123456");
        mViewModel.mParentCode.set("0");
        mViewModel.getDeptList();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}