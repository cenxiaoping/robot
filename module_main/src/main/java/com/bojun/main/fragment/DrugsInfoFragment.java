package com.bojun.main.fragment;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.fragmentation.SupportFragment;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.activity.main.MainActivity;
import com.bojun.main.databinding.FragmentDrugsinfoBinding;
import com.bojun.main.databinding.FragmentHospitalIntroduceBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.DepartListBean;
import com.bojun.net.entity.DrugInfoDetailBean;
import com.bojun.net.entity.DrugInfoListBean;
import com.bojun.net.entity.DrugTypeListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 简  述  药品信息
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class DrugsInfoFragment extends BaseMvvmFragment<FragmentDrugsinfoBinding, MainViewModel> {

    private List<DrugTypeListBean> mDrugTypeDatas = new ArrayList<>();
    private List<DrugInfoListBean> mDrugListDatas = new ArrayList<>();

    public static DrugsInfoFragment newInstance() {
        DrugsInfoFragment fragment = new DrugsInfoFragment();
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
        mViewModel.getDrugTypeEvent().observe(this, new Observer<List<DrugTypeListBean>>() {
            @Override
            public void onChanged(List<DrugTypeListBean> drugTypeListBeans) {
                mDrugTypeDatas.clear();
                mDrugTypeDatas.addAll(drugTypeListBeans);
                mBinding.listType.getAdapter().notifyDataSetChanged();
            }
        });

        mViewModel.getDrugListEvent().observe(this, new Observer<List<DrugInfoListBean>>() {
            @Override
            public void onChanged(List<DrugInfoListBean> drugInfoListBeans) {
                mDrugListDatas.clear();
                mDrugListDatas.addAll(drugInfoListBeans);
                mBinding.listDrug.getAdapter().notifyDataSetChanged();

                mBinding.tvSelectTitle.setText("已选择" + selectTypeName + "!");
            }
        });

        mViewModel.getDrugInfoDetailBean().observe(this, new Observer<DrugInfoDetailBean>() {
            @Override
            public void onChanged(DrugInfoDetailBean drugInfoDetailBean) {
                mBinding.tvDesc.setText(Html.fromHtml(drugInfoDetailBean.getDescription()));
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_drugsinfo;
    }

    private String selectTypeName;
    @Override
    public void initView(View view) {
        mBinding.listType.setAdapter(new BaseRecycerAdapter<DrugTypeListBean>(getContext(), mDrugTypeDatas, R.layout.item_depart_list) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, DrugTypeListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                tv_content.setText(itemVO.getTypeName());
                tv_content.setSelected(itemVO.isSelect());
            }
        }.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<DrugTypeListBean>() {
            @Override
            public void onItemClick(DrugTypeListBean o, int position) {
                for (DrugTypeListBean bean : mDrugTypeDatas) {
                    bean.setSelect(false);
                }

                mDrugTypeDatas.get(position).setSelect(true);
                mBinding.listType.getAdapter().notifyDataSetChanged();

                selectTypeName = o.getTypeName();
                mBinding.tvSelectTitle.setText("已选择" + selectTypeName + "!");

                mBinding.consBack.setVisibility(View.GONE);
                mBinding.tvDesc.setVisibility(View.GONE);
                mBinding.listDrug.setVisibility(View.VISIBLE);
                mBinding.llSearch.setVisibility(View.VISIBLE);

                mDrugListDatas.clear();
                mBinding.listDrug.getAdapter().notifyDataSetChanged();

                drugTypeCode = o.getTypeCode();
                keyword = "";
                getDrugList();
            }
        }));
        mBinding.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrugListDatas.clear();
                mBinding.listDrug.getAdapter().notifyDataSetChanged();

                keyword = mBinding.etSearch.getText().toString().trim();
                getDrugList();
            }
        });

        mBinding.listDrug.setAdapter(new BaseRecycerAdapter<DrugInfoListBean>(getContext(), mDrugListDatas, R.layout.item_depart_list) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, DrugInfoListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                tv_content.setText(itemVO.getDrugName());
                tv_content.setSelected(itemVO.isSelect());
            }
        }.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<DrugInfoListBean>() {
            @Override
            public void onItemClick(DrugInfoListBean o, int position) {
                for (DrugInfoListBean bean : mDrugListDatas) {
                    bean.setSelect(false);
                }

                mDrugListDatas.get(position).setSelect(true);
                mBinding.listDrug.getAdapter().notifyDataSetChanged();

                mBinding.tvSelectTitle.setText("已选择" + o.getDrugName() + "!");

                //获取药品详情
                mViewModel.getDrugInfoDetail(o.getId());
                mBinding.consBack.setVisibility(View.VISIBLE);
                mBinding.tvDesc.setVisibility(View.VISIBLE);
                mBinding.llSearch.setVisibility(View.GONE);
                mBinding.listDrug.setVisibility(View.GONE);
            }
        }));

        mBinding.consBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏返回按钮，隐藏TextView，显示ListView
                mBinding.consBack.setVisibility(View.GONE);
                mBinding.tvDesc.setVisibility(View.GONE);

                mBinding.llSearch.setVisibility(View.VISIBLE);
                mBinding.listDrug.setVisibility(View.VISIBLE);

            }
        });
    }

    private String drugTypeCode;
    private String isCommonUse = "0";
    private String keyword;
    private int pageNum = 1;
    private int pageSize = 100;

    /**
     * 获取药品列表
     */
    private void getDrugList() {
        Map<String, String> params = new HashMap();
        params.put("drugTypeCode", drugTypeCode);
//        params.put("isCommonUse", isCommonUse);
        if (!TextUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        params.put("pageNum", String.valueOf(pageNum));
        params.put("pageSize", String.valueOf(pageSize));
        mViewModel.getDrugList(params);
    }

    @Override
    public void initData() {
        mViewModel.mHospitalCode.set("123456");
        mViewModel.getDrugType();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}