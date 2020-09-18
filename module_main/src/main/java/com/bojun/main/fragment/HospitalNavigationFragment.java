package com.bojun.main.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentHospitalNavigationBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;
import com.bojun.net.entity.BuildingInfo;
import com.bojun.net.entity.FloorInfo;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  院内导航
 * 作  者  管流生
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class HospitalNavigationFragment extends BaseMvvmFragment<FragmentHospitalNavigationBinding, SplashViewModel> {
    private BaseRecycerAdapter<BuildingInfo> buildingAdapter;
    private BaseRecycerAdapter<FloorInfo> floorAdapter;

    public static HospitalNavigationFragment newInstance() {
        HospitalNavigationFragment fragment = new HospitalNavigationFragment();
        return fragment;
    }

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

        mViewModel.getBuildingInfoEvent().observe(this, new Observer<List<BuildingInfo>>() {
            @Override
            public void onChanged(List<BuildingInfo> buildingInfos) {
                if (buildingInfos != null) {
                    buildingAdapter.getData().clear();
                    if (buildingInfos.size() > 0) {
                        buildingInfos.get(0).setSelected(true);
                        buildingAdapter.getData().addAll(buildingInfos);
                        mViewModel.getFloorList(buildingInfos.get(0).getBuildingCode());
                    }
                    buildingAdapter.notifyDataSetChanged();

                    floorAdapter.getData().clear();
                    floorAdapter.notifyDataSetChanged();
                }
            }
        });

        mViewModel.getFloorInfoEvent().observe(this, new Observer<List<FloorInfo>>() {
            @Override
            public void onChanged(List<FloorInfo> floorInfos) {
                if (floorInfos != null) {
                    floorAdapter.getData().clear();
                    if (floorInfos.size() > 0) {
                        floorInfos.get(0).setSelected(true);
                        floorAdapter.getData().addAll(floorInfos);
                        String navigationMapURL = ((FloorInfo) floorInfos.get(0)).getNavigationMapURL();
                        if(TextUtils.isEmpty(navigationMapURL)){
                            mBinding.ivNavigationPic.setVisibility(View.GONE);
                        }else{
                            mBinding.ivNavigationPic.setVisibility(View.VISIBLE);
                            Glide.with(getContext()).load((navigationMapURL)).into(mBinding.ivNavigationPic);
                        }
                    }
                    floorAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_hospital_navigation;
    }

    @Override
    public void initView(View view) {
        initAdapter();
    }

    private void initAdapter() {
        buildingAdapter = new BaseRecycerAdapter<BuildingInfo>(getContext(), null, R.layout.item_navigation) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, BuildingInfo itemVO, int position) {
                ((TextView) viewHolder.itemView.findViewById(R.id.tv_name)).setText(itemVO.getBuildingName());
                viewHolder.itemView.findViewById(R.id.ll_content).setSelected(itemVO.isSelected());
            }
        };
        buildingAdapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                for (int i = 0; i < buildingAdapter.getData().size(); i++) {
                    if (position == i) {
                        buildingAdapter.getData().get(i).setSelected(true);
                    } else {
                        buildingAdapter.getData().get(i).setSelected(false);
                    }
                }

                floorAdapter.getData().clear();
                floorAdapter.notifyDataSetChanged();
                mBinding.ivNavigationPic.setVisibility(View.GONE);

                buildingAdapter.notifyDataSetChanged();
                mViewModel.getFloorList(((BuildingInfo) o).getBuildingCode());
            }
        });
        mBinding.rvBuilding.setAdapter(buildingAdapter);
        int spanCount = 2; // 2 columns
        int spacing = 10; // 20px
        boolean includeEdge = false;
        mBinding.rvBuilding.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        floorAdapter = new BaseRecycerAdapter<FloorInfo>(getContext(), null, R.layout.item_navigation) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, FloorInfo itemVO, int position) {
                ((TextView) viewHolder.itemView.findViewById(R.id.tv_name)).setText(itemVO.getFloorName());
                viewHolder.itemView.findViewById(R.id.ll_content).setSelected(itemVO.isSelected());
            }
        };
        floorAdapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                for (int i = 0; i < floorAdapter.getData().size(); i++) {
                    if (position == i) {
                        floorAdapter.getData().get(i).setSelected(true);
                    } else {
                        floorAdapter.getData().get(i).setSelected(false);
                    }
                }
                floorAdapter.notifyDataSetChanged();

                String navigationMapURL = ((FloorInfo) o).getNavigationMapURL();
                if(TextUtils.isEmpty(navigationMapURL)){
                    mBinding.ivNavigationPic.setVisibility(View.GONE);
                }else{
                    mBinding.ivNavigationPic.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load((navigationMapURL)).into(mBinding.ivNavigationPic);
                }

            }
        });
        mBinding.rvFloor.setAdapter(floorAdapter);
        mBinding.rvFloor.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

    @Override
    public void initData() {
        mViewModel.getBuildingList();
    }

    @Override
    public boolean enableLazyData() {
        return false;
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

}