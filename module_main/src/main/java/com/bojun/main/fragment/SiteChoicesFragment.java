package com.bojun.main.fragment;

import android.os.Bundle;
import android.transition.Fade;
import android.view.View;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.event.common.SiteEvent;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentSiteChoicesBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;
import com.bojun.net.entity.SiteVo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/31 16:38
 */
public class SiteChoicesFragment extends BaseMvvmFragment<FragmentSiteChoicesBinding, SplashViewModel> {

    public static SiteChoicesFragment newInstance(int role) {
        SiteChoicesFragment fragment = new SiteChoicesFragment();
        Bundle toBundle = new Bundle();
        toBundle.putInt("role", role);
        fragment.setArguments(toBundle);
        return fragment;
    }

    private int role = 1;

    private BaseRecycerAdapter<SiteVo> siteAdapter;

    @Override
    public Class<SplashViewModel> onBindViewModel() {
        return SplashViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getActivity().getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getSiteEvent().observe(this, new Observer<List<SiteVo>>() {
            @Override
            public void onChanged(List<SiteVo> siteVos) {
                if (siteVos != null) {
                    siteAdapter.getData().clear();
                    siteAdapter.getData().addAll(siteVos);
                    siteAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_site_choices;
    }

    @Override
    public void initView(View view) {
        role = getArguments().getInt("role", 1);
        mBinding.choiceRoleMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 1) {
                    return;
                }
                role = 1;
                showContent();
            }
        });
        mBinding.choiceRoleFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 2) {
                    return;
                }
                role = 2;
                showContent();
            }
        });
        mBinding.choiceRoleChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 3) {
                    return;
                }
                role = 3;
                showContent();
            }
        });

        mBinding.consBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        siteAdapter = new BaseRecycerAdapter<SiteVo>(getContext(), null, R.layout.item_site_choices) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, SiteVo itemVO, int position) {
                viewHolder.setText(R.id.item_site_name, itemVO.getBodyPartName());
                viewHolder.getRootView().setSelected(itemVO.isSelect());
            }
        };
        siteAdapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {

                ArrayList<SiteVo> list = (ArrayList<SiteVo>) siteAdapter.getData();
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setSelect(i == position);
                    }
                }
                siteAdapter.notifyDataSetChanged();

                SymptomChoicesFragment subFragment = SymptomChoicesFragment.newInstance(role, list.get(position));
                setExitTransition(new Fade());
                subFragment.setEnterTransition(new Fade());
                extraTransaction().start(subFragment);
            }
        });
        mBinding.siteListview.setAdapter(siteAdapter);
        showContent();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteChange(SiteEvent siteEvent) {
        if (role == siteEvent.getSite()) {
            return;
        }
        role = siteEvent.getSite();
        showContent();
    }


    @Override
    public void initData() {
        mViewModel.getBodyParts();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    private void showContent() {
        mBinding.choiceRoleMale.setSelected(role == 1);
        mBinding.choiceRoleFemale.setSelected(role == 2);
        mBinding.choiceRoleChild.setSelected(role == 3);
        switch (role) {
            case 1:
                mBinding.choiceRoleTv.setText("男性");
                mBinding.choiceRoleImgMale.setVisibility(View.VISIBLE);
                mBinding.choiceRoleImgFemale.setVisibility(View.GONE);
                mBinding.choiceRoleImgChild.setVisibility(View.GONE);
                break;
            case 2:
                mBinding.choiceRoleTv.setText("女性");
                mBinding.choiceRoleImgMale.setVisibility(View.GONE);
                mBinding.choiceRoleImgFemale.setVisibility(View.VISIBLE);
                mBinding.choiceRoleImgChild.setVisibility(View.GONE);
                break;
            case 3:
                mBinding.choiceRoleTv.setText("儿童");
                mBinding.choiceRoleImgMale.setVisibility(View.GONE);
                mBinding.choiceRoleImgFemale.setVisibility(View.GONE);
                mBinding.choiceRoleImgChild.setVisibility(View.VISIBLE);
                break;
        }

        ArrayList<SiteVo> list = (ArrayList<SiteVo>) siteAdapter.getData();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelect(false);
            }
        }
        siteAdapter.notifyDataSetChanged();
    }
}