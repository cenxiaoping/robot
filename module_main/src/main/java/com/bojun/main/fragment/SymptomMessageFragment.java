package com.bojun.main.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import com.bojun.common.event.common.SiteEvent;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentSymptomMessageBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;
import com.bojun.net.entity.DiseaseInfoVo;
import com.bojun.net.entity.SymptomInfoVo;

import org.greenrobot.eventbus.EventBus;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/31 16:38
 */
public class SymptomMessageFragment extends BaseMvvmFragment<FragmentSymptomMessageBinding, SplashViewModel> {

    public static SymptomMessageFragment newInstance(int role, SymptomInfoVo symptomInfoVo) {
        SymptomMessageFragment fragment = new SymptomMessageFragment();
        Bundle toBundle = new Bundle();
        toBundle.putInt("role", role);
        toBundle.putSerializable("SymptomInfoVo", symptomInfoVo);
        fragment.setArguments(toBundle);
        return fragment;
    }

    private int role = 1;
    private SymptomInfoVo symptomInfoVo;

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
        mViewModel.getDiseaseInfoEvent().observe(this, new Observer<DiseaseInfoVo>() {
            @Override
            public void onChanged(DiseaseInfoVo siteVos) {
                if (siteVos != null) {
                    mBinding.messageJianyi.setText(Html.fromHtml(siteVos.getCureDesc() + ""));
                    mBinding.messageMiaoshu.setText(Html.fromHtml(siteVos.getSymptomDesc() + ""));

                    mBinding.messageJianyi.setMovementMethod(new ScrollingMovementMethod());
                    mBinding.messageMiaoshu.setMovementMethod(new ScrollingMovementMethod());
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
        return R.layout.fragment_symptom_message;
    }

    @Override
    public void initView(View view) {
        role = getArguments().getInt("role", 1);
        symptomInfoVo = (SymptomInfoVo) getArguments().getSerializable("SymptomInfoVo");
        mBinding.symptomTitle.setText(symptomInfoVo.getSymptomName());
        mBinding.choiceRoleMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SiteEvent(1));
                popTo(SiteChoicesFragment.class, false);
//                if (role == 1) {
//                    return;
//                }
//                role = 1;
//                showContent();
//                initData();
            }
        });
        mBinding.choiceRoleFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SiteEvent(2));
                popTo(SiteChoicesFragment.class, false);
//                if (role == 2) {
//                    return;
//                }
//                role = 2;
//                showContent();
//                initData();
            }
        });
        mBinding.choiceRoleChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SiteEvent(3));
                popTo(SiteChoicesFragment.class, false);
//                if (role == 3) {
//                    return;
//                }
//                role = 3;
//                showContent();
//                initData();
            }
        });
        mBinding.consBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        showContent();
    }


    @Override
    public void initData() {
        mViewModel.getDiseaseInfo(symptomInfoVo.getSymptomCode(), role);
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
    }
}