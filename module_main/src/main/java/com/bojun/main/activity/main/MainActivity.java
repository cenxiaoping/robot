package com.bojun.main.activity.main;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojun.bar.StatusBar;
import com.bojun.common.BaseApplication;
import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.fragmentation.SupportFragment;
import com.bojun.common.fragmentation.fragmentation.ISupportFragment;
import com.bojun.common.mvvm.BaseMvvmActivity;
import com.bojun.common.serivce.AppUtils;
import com.bojun.common.serivce.BedsInformationServiceImpl;
import com.bojun.common.serivce.LogUtil;
import com.bojun.common.util.FastClickToActUtils;
import com.bojun.main.R;
import com.bojun.main.databinding.ActivityMainViewBinding;
import com.bojun.main.dialog.BarcodeDialog;
import com.bojun.main.fragment.BusinessGuideFragment;
import com.bojun.main.fragment.DrugsInfoFragment;
import com.bojun.main.fragment.HospitalIntroduceFragment;
import com.bojun.main.fragment.HospitalMainNetFragment;
import com.bojun.main.fragment.HospitalNavigationFragment;
import com.bojun.main.fragment.InfoDisclosureMainFragment;
import com.bojun.main.fragment.ProcessGuideFragment;
import com.bojun.main.fragment.RoleChoicesMainFragment;
import com.bojun.main.fragment.VideoMainFragment;
import com.bojun.main.fragment.XiaoboHelpYouFragment;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.MenuListBean;
import com.bojun.net.entity.UpdateVo;
import com.bojun.net.entity.NavigationListBean;
import com.bojun.net.entity.PreDiagnosisListBean;
import com.bojun.net.entity.WeatherBean;
import com.demons.alivelibrary.DaemonEnv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * MainActivity
 */
public class MainActivity extends BaseMvvmActivity<ActivityMainViewBinding, MainViewModel> {
    private RecyclerView listMainMenu;

    private int currentSelect;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    public static final int SIXTH = 5;
    public static final int SEVENTH = 6;
    public static final int EIGHTH = 7;
    public static final int NINTH = 8;
    public static final int TENTH = 9;

    private ArrayList<SupportFragment> mFragments = new ArrayList<>();
    private int[] mainMenuIcons = {R.mipmap.icon_xbbn, R.mipmap.icon_zhdz,
            R.mipmap.icon_yyjs, R.mipmap.icon_yndh,
            R.mipmap.icon_xxgs, R.mipmap.icon_yysp,
            R.mipmap.icon_ypxx, R.mipmap.icon_lccx,
            R.mipmap.icon_ywzy, R.mipmap.icon_yygw,};

    private List<MenuListBean> menuListDatas = new ArrayList<>();

    @Override
    public int onBindLayout() {
        return R.layout.activity_main_view;
    }

    @Override
    public Class<MainViewModel> onBindViewModel() {
        return MainViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getApplication());
    }

    @Override
    public void initViewObservable() {

        mViewModel.getWeatherEvent().observe(this, new Observer<WeatherBean>() {
            @Override
            public void onChanged(WeatherBean weatherBean) {
                if (weatherBean != null & weatherBean.getCode() == 10000) {
                    WeatherBean.ResultBean result = weatherBean.getResult();
                    WeatherBean.HeWeather5Bean heWeather5 = result.getHeWeather5().get(0);
                    List<WeatherBean.Daily_forecastBean> daily_forecast = heWeather5.getDaily_forecast();
                    WeatherBean.Daily_forecastBean daily_forecastBean = daily_forecast.get(0);
                    WeatherBean.TmpBean tmp = daily_forecastBean.getTmp();
                    //最高气温
                    String max = tmp.getMax();
                    //最多气温
                    String min = tmp.getMin();

                    TextView tv_tianqi = findViewById(R.id.tv_tianqi);
                    tv_tianqi.setText(min + "~" + max + "℃");
                }
            }
        });
        mViewModel.getMenuListBean().observe(this, new Observer<List<MenuListBean>>() {
            @Override
            public void onChanged(List<MenuListBean> menuListBeans) {
                menuListDatas.clear();
                menuListDatas.addAll(menuListBeans);
                initFragment();

                listMainMenu.getAdapter().notifyDataSetChanged();
            }
        });

        mViewModel.getUpdateVoEvent().observe(this, new Observer<UpdateVo>() {
            @Override
            public void onChanged(UpdateVo updateVo) {
                if (updateVo != null) {
                    if (updateVo.getVersionCode() > AppUtils.getLocalVersion(getContext())) {
                        updateApk(updateVo.getFileName(), "V" + updateVo.getVersionCode());
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mViewModel.getWeather();
        mViewModel.robotMenu();
//        mViewModel.update();
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public void initView() {
        StatusBar.with(this).statusBarDarkFont(true).init();
        FastClickToActUtils.fastClick(findViewById(R.id.iv_logo));

        findViewById(R.id.iv_barcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //二维码
                new BarcodeDialog().show(getSupportFragmentManager(), "BarcodeDialog");
            }
        });
//        initFragment();
//        String[] mainMenuNames = getResources().getStringArray(R.array.main_menu);

        listMainMenu = findViewById(R.id.list_main_menu);
        listMainMenu.setAdapter(new BaseRecycerAdapter<MenuListBean>(this, menuListDatas, R.layout.item_main_menu) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, MenuListBean itemVO, int position) {
                TextView tv_name = viewHolder.findViewById(R.id.tv_name);
                ImageView iv_icon = viewHolder.findViewById(R.id.iv_icon);

                tv_name.setText(itemVO.getMenuName());
                iv_icon.setImageResource(mainMenuIcons[position]);

                viewHolder.getRootView().setSelected(currentSelect == position);
            }

        }.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<MenuListBean>() {
            @Override
            public void onItemClick(MenuListBean o, int position) {
                listMainMenu.getAdapter().notifyDataSetChanged();
                if (currentSelect == position) {
                    return;
                }
                showHideFragment(mFragments.get(position), mFragments.get(currentSelect));
                currentSelect = position;

//                currentSelect = position;
//                listMainMenu.getAdapter().notifyDataSetChanged();
//                toPage(o);
            }
        }));
    }

    private void initFragment() {

        if (menuListDatas == null || menuListDatas.size() == 0) {
            return;
        }
        SupportFragment firstFragment = findFragment(XiaoboHelpYouFragment.class);
        for (MenuListBean bean : menuListDatas) {
            String menuCode = bean.getMenuCode();
            if ("m01".equals(menuCode)) {//小博帮您
                mFragments.add(XiaoboHelpYouFragment.newInstance());
            } else if ("m02".equals(menuCode)) {//智慧导诊
                mFragments.add(RoleChoicesMainFragment.newInstance());
            } else if ("m03".equals(menuCode)) {//医院介绍
                mFragments.add(HospitalIntroduceFragment.newInstance());
            } else if ("m04".equals(menuCode)) {//院内导航
                mFragments.add(HospitalNavigationFragment.newInstance());
            } else if ("m05".equals(menuCode)) {//信息公示
                mFragments.add(InfoDisclosureMainFragment.newInstance());
            } else if ("m06".equals(menuCode)) {//医院视频
                mFragments.add(VideoMainFragment.newInstance());
            } else if ("m07".equals(menuCode)) {//药品信息
                mFragments.add(DrugsInfoFragment.newInstance());
            } else if ("m08".equals(menuCode)) {//流程查询
                mFragments.add(ProcessGuideFragment.newInstance());
            } else if ("m09".equals(menuCode)) {//业务指引
                mFragments.add(BusinessGuideFragment.newInstance());
            } else if ("m10".equals(menuCode)) {//医院官网
                mFragments.add(HospitalMainNetFragment.newInstance());
            }
        }

        loadMultipleRootFragment(R.id.content, FIRST, mFragments.toArray(new ISupportFragment[]{}));
//        SupportFragment firstFragment = findFragment(XiaoboHelpYouFragment.class);
//        if (firstFragment == null) {
//
//        } else {
//            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
//            // 这里我们需要拿到mFragments的引用
//            mFragments[FIRST] = firstFragment;
//            mFragments[SECOND] = findFragment(RoleChoicesMainFragment.class);
//            mFragments[THIRD] = findFragment(HospitalIntroduceFragment.class);
//            mFragments[FOURTH] = findFragment(HospitalNavigationFragment.class);
//            mFragments[FIFTH] = findFragment(InfoDisclosureMainFragment.class);
//            mFragments[SIXTH] = findFragment(VideoMainFragment.class);
//            mFragments[SEVENTH] = findFragment(DrugsInfoFragment.class);
//            mFragments[EIGHTH] = findFragment(ProcessGuideFragment.class);
//            mFragments[NINTH] = findFragment(BusinessGuideFragment.class);
//            mFragments[TENTH] = findFragment(HospitalMainNetFragment.class);
//
//        }
    }

    /**
     * @param position
     * @return
     */
    private ISupportFragment findFragment(int position) {
        return mFragments.get(position);
    }

//    private void toPage(String menuName) {
//        switch (menuName) {
//            case "小博帮您":
//                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_xiaobo_help);
//                break;
//            case "医院介绍":
//                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_hospital_introduce);
//                break;
//            case "信息公示":
//                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_info_disclosure);
//                break;
//            case "院内导航":
//                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_hospital_navigation);
//                break;
//            case "医院视频":
//                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_hospital_video);
//                break;
//        }
//    }


    @Override
    public void initListener() {
    }

    @Override
    public String getTootBarTitle() {
        return null;
    }

    public boolean enableToolbar() {
        return true;
    }
//    @Override
//    public void onBackPressedSupport() {
////        pop();
////        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
////            pop();
////        } else {
////            ActivityCompat.finishAfterTransition(this);
////        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        com.bojun.common.serivce.BedsInformationServiceImpl.sShouldStopService = false;
        LogUtil.e("打开BedsInformationServiceImpl");
        DaemonEnv.startServiceMayBind(BedsInformationServiceImpl.class);
    }

    @Override
    public void finish() {
        super.finish();
        LogUtil.e("关闭BedsInformationServiceImpl");
        BedsInformationServiceImpl.stopService();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            mViewModel.update();
        }
    }

    private void updateApk(String url, String apkName) {

        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, 123);
            return;
        } else {
            //TODO do something you need
        }

        final ProgressDialog pd = new ProgressDialog(BaseApplication.getInstance().getBaseContext());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.setMessage("正在下载新版本,请稍后...");
        //在dialog  show方法之前添加如下代码，表示该dialog是一个系统的dialog
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pd.getWindow().setType((WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY));
        } else {
            pd.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        }
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = AppUtils.getFileFromServer(url, apkName, pd);

                    pd.dismiss();
                    sleep(500);
                    AppUtils.silenceInstall(file, BaseApplication.getCurrentActivity());
                } catch (Exception e) {
                    //下载apk失败
                    Log.e("下载新版本失败", e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
