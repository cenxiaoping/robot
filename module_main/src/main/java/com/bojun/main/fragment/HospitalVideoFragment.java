package com.bojun.main.fragment;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.common.util.DisplayUtil;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentHospitalVideoBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;
import com.bojun.net.entity.HospitalVideoVo;
import com.bojun.net.util.SpUtil;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.widget.PopupWindowCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  医院视频
 * 作  者  管流生
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class HospitalVideoFragment extends BaseMvvmFragment<FragmentHospitalVideoBinding, SplashViewModel> {
    OrientationUtils orientationUtils;
    private BaseRecycerAdapter<HospitalVideoVo> mAdapter;
    private ArrayList<HospitalVideoVo> videoData = new ArrayList<>();

    private PopupWindow settingPoP;

    public static HospitalVideoFragment newInstance() {
        HospitalVideoFragment fragment = new HospitalVideoFragment();
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

        mViewModel.getHospitalVideos().observe(this, new Observer<List<HospitalVideoVo>>() {
            @Override
            public void onChanged(List<HospitalVideoVo> hospitalVideoVos) {
                if (hospitalVideoVos != null) {
                    mAdapter.getData().clear();
                    mAdapter.getData().addAll(hospitalVideoVos);
                    mAdapter.notifyDataSetChanged();

                    if (hospitalVideoVos!=null&&hospitalVideoVos.size()>0&&!mBinding.videoPlayer.isInPlayingState()){
                        mBinding.videoPlayer.setUp(hospitalVideoVos.get(0).getDownloadUrl(), true, hospitalVideoVos.get(0).getName());
                        mBinding.videoPlayer.initUIState();
                    }
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
        return R.layout.fragment_hospital_video;
    }

    @Override
    public void initView(View view) {
        initAdapter();
        initVideo();
        mBinding.mvideoSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingPop();
            }
        });
    }


    private void initAdapter() {
        mAdapter = new BaseRecycerAdapter<HospitalVideoVo>(getContext(), videoData, R.layout.item_video) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, HospitalVideoVo itemVO, int position) {
                ((TextView) viewHolder.itemView.findViewById(R.id.tv_name)).setText(itemVO.getName());
                viewHolder.itemView.findViewById(R.id.ll_content).setSelected(itemVO.isSelect());
                ((TextView) viewHolder.itemView.findViewById(R.id.tv_bofang)).setVisibility(itemVO.isSelect() ? View.VISIBLE : View.GONE);
            }
        };
        mAdapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                for (int i = 0; i < videoData.size(); i++) {
                    if (position == i) {
                        videoData.get(i).setSelect(true);
                    } else {
                        videoData.get(i).setSelect(false);
                    }
                }
                mBinding.videoPlayer.setUp(mAdapter.getData().get(position).getDownloadUrl(), true, mAdapter.getData().get(position).getName());
                mBinding.videoPlayer.startPlayLogic();
                mAdapter.notifyDataSetChanged();
            }
        });
        mBinding.rvVideo.setAdapter(mAdapter);
        int spanCount = 1; // 2 columns
        int spacing = 9; // 20px
        boolean includeEdge = false;
        mBinding.rvVideo.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

    private void initVideo() {
//        String url = "https://www.w3school.com.cn/example/html5/mov_bbb.mp4";
//        String title = "";
//        String videoUrl = "";
//        PlayerFactory.setPlayManager(SystemPlayerManager.class);
////        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
//        mBinding.videoPlayer.setUp(url, true, title);
        mBinding.videoPlayer.setThumbImageView(new View(getContext()));

        //增加title
        mBinding.videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        mBinding.videoPlayer.getBackButton().setVisibility(View.GONE);

        //设置旋转
        orientationUtils = new OrientationUtils(getActivity(), mBinding.videoPlayer);
        orientationUtils.setScreenType(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        mBinding.videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                orientationUtils.resolveByClick();
                mBinding.videoPlayer.startWindowFullscreen(getActivity(), true, false);
            }
        });
        mBinding.videoPlayer.setShowFullAnimation(true);
        //是否可以滑动调整
        mBinding.videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        mBinding.videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressedSupport();
            }
        });
        mBinding.videoPlayer.setVideoAllCallBack(new VideoAllCallBack() {
            @Override
            public void onStartPrepared(String url, Object... objects) {

            }

            @Override
            public void onPrepared(String url, Object... objects) {

            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
//                Log.e("视频播放结束-----","-------objects:"+objects.toString());

                if (!SpUtil.getBoolean(getContext(), "videoMode", false)) {
                    //单曲循环
                    ((GSYVideoView)objects[1]).setUp(url, true, (String) objects[0]);
                    ((GSYVideoView)objects[1]).startPlayLogic();
                    return;
                }

                ArrayList<HospitalVideoVo> list = (ArrayList<HospitalVideoVo>) mAdapter.getData();
                if (list != null && list.size() > 0) {
                    int nextVideo = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getDownloadUrl().equals(url)) {
                            nextVideo = i;
                            list.get(i).setSelect(false);
                        } else {
                            list.get(i).setSelect(false);
                        }
                    }

                    nextVideo = (nextVideo + 1) >= list.size() ? 0 : nextVideo + 1;
                    list.get(nextVideo).setSelect(true);
                    mAdapter.notifyDataSetChanged();
                    ((GSYVideoView)objects[1]).setUp(list.get(nextVideo).getDownloadUrl(), true, list.get(nextVideo).getName());
                    ((GSYVideoView)objects[1]).startPlayLogic();
                }

            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {

            }

            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }
        });
//        mBinding.videoPlayer.startPlayLogic();
    }

    @Override
    public void initData() {
        mViewModel.getHospitalVideo();
    }

    @Override
    public boolean enableLazyData() {
        return false;
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.videoPlayer.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.videoPlayer.onVideoResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    View popContentView;

    public void showSettingPop() {

        if (settingPoP == null) {
            popContentView = getLayoutInflater().inflate(R.layout.pop_video_setting, null);

            popContentView.findViewById(R.id.video_set_one).setSelected(!SpUtil.getBoolean(getContext(), "videoMode", false));
            popContentView.findViewById(R.id.video_set_all).setSelected(SpUtil.getBoolean(getContext(), "videoMode", false));

            popContentView.findViewById(R.id.video_set_one).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setSelected(true);
                    popContentView.findViewById(R.id.video_set_all).setSelected(false);
                    SpUtil.putBoolean(getContext(), "videoMode", false);
                }
            });

            popContentView.findViewById(R.id.video_set_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setSelected(true);
                    popContentView.findViewById(R.id.video_set_one).setSelected(false);
                    SpUtil.putBoolean(getContext(), "videoMode", true);
                }
            });

            settingPoP = new PopupWindow(popContentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            settingPoP.setOutsideTouchable(true);
            settingPoP.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int offsetX = mBinding.mvideoSetting.getWidth() - DisplayUtil.dip2px(76);
            settingPoP.showAsDropDown(mBinding.mvideoSetting, offsetX, 1, Gravity.START);
//            PopupWindowCompat.showAsDropDown(settingPoP, mBinding.mvideoSetting, offsetX, 1, Gravity.START);
        } else {
//            if (!settingPoP.isShowing()) {
//            settingPoP.showAsDropDown(mBinding.mvideoSetting);
            int offsetX = mBinding.mvideoSetting.getWidth() - settingPoP.getContentView().getMeasuredWidth();
            PopupWindowCompat.showAsDropDown(settingPoP, mBinding.mvideoSetting, offsetX, 1, Gravity.START);
//            }
        }

    }


    @Override
    public boolean onBackPressedSupport() {
//        super.onBackPressedSupport();
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mBinding.videoPlayer.getFullscreenButton().performClick();
            return false;
        }
        //释放所有
        mBinding.videoPlayer.setVideoAllCallBack(null);

        return super.onBackPressedSupport();
    }
}