//package com.bojun.main.activity.main;
//
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bojun.bar.StatusBar;
//import com.bojun.common.adapter.BaseRecycerAdapter;
//import com.bojun.common.adapter.ViewHolder;
//import com.bojun.common.fragmentation.SupportFragment;
//import com.bojun.common.mvvm.BaseMvvmActivity;
//import com.bojun.common.util.FastClickToActUtils;
//import com.bojun.main.R;
//import com.bojun.main.databinding.ActivityMainViewBinding;
//import com.bojun.main.fragment.BusinessGuideFragment;
//import com.bojun.main.fragment.DrugsInfoFragment;
//import com.bojun.main.fragment.HospitalIntroduceFragment;
//import com.bojun.main.fragment.HospitalMainNetFragment;
//import com.bojun.main.fragment.HospitalNavigationFragment;
//import com.bojun.main.fragment.HospitalVideoFragment;
//import com.bojun.main.fragment.InfoDisclosureMainFragment;
//import com.bojun.main.fragment.ProcessGuideFragment;
//import com.bojun.main.fragment.RoleChoicesFragment;
//import com.bojun.main.fragment.XiaoboHelpYouFragment;
//import com.bojun.main.fragment.XiaoboHelpYouFragment3;
//import com.bojun.main.mvvm.factory.MainViewModelFactory;
//import com.bojun.main.mvvm.viewmodel.SplashViewModel;
//
//import java.util.Arrays;
//import java.util.List;
//
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.RecyclerView;
//
///**
// * MainActivity
// */
//public class MainActivity_2 extends BaseMvvmActivity<ActivityMainViewBinding, SplashViewModel> {
//    private RecyclerView listMainMenu;
//
//    private int currentSelect;
//    public static final int FIRST = 0;
//    public static final int SECOND = 1;
//    public static final int THIRD = 2;
//    public static final int FOURTH = 3;
//    public static final int FIFTH = 4;
//    public static final int SIXTH = 5;
//    public static final int SEVENTH = 6;
//    public static final int EIGHTH = 7;
//    public static final int NINTH = 8;
//    public static final int TENTH = 9;
//
//    private SupportFragment[] mFragments = new SupportFragment[12];
//    private int[] mainMenuIcons = {R.mipmap.icon_xbbn, R.mipmap.icon_zhdz,
//            R.mipmap.icon_yyjs, R.mipmap.icon_yndh,
//            R.mipmap.icon_xxgs, R.mipmap.icon_yysp,
//            R.mipmap.icon_ypxx, R.mipmap.icon_lccx,
//            R.mipmap.icon_ywzy, R.mipmap.icon_yygw,};
//
//    @Override
//    public int onBindLayout() {
//        return R.layout.activity_main_view;
//    }
//
//    @Override
//    public Class<SplashViewModel> onBindViewModel() {
//        return SplashViewModel.class;
//    }
//
//    @Override
//    public ViewModelProvider.Factory onBindViewModelFactory() {
//        return MainViewModelFactory.getInstance(getApplication());
//    }
//
//    @Override
//    public void initViewObservable() {
//    }
//
//    @Override
//    public int onBindVariableId() {
//        return 0;
//    }
//
//    @Override
//    public void initView() {
//        StatusBar.with(this).statusBarDarkFont(true).init();
//        FastClickToActUtils.fastClick(findViewById(R.id.iv_logo));
//        findViewById(R.id.tv_barcode_title).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportDelegate().showFragmentStackHierarchyView();
//            }
//        });
//        initFragment();
//        String[] mainMenuNames = getResources().getStringArray(R.array.main_menu);
//
//        listMainMenu = findViewById(R.id.list_main_menu);
//        List<String> list = Arrays.asList(mainMenuNames);
//        listMainMenu.setAdapter(new BaseRecycerAdapter<String>(this, list, R.layout.item_main_menu) {
//            @Override
//            public void onBindViewHolder(ViewHolder viewHolder, String itemVO, int position) {
//                TextView tv_name = viewHolder.findViewById(R.id.tv_name);
//                ImageView iv_icon = viewHolder.findViewById(R.id.iv_icon);
//
//                tv_name.setText(itemVO);
//                iv_icon.setImageResource(mainMenuIcons[position]);
//
//                viewHolder.getRootView().setSelected(currentSelect == position);
//            }
//
//        }.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<String>() {
//            @Override
//            public void onItemClick(String o, int position) {
//                listMainMenu.getAdapter().notifyDataSetChanged();
//                if (currentSelect == position) {
//                    return;
//                }
//                showHideFragment(mFragments[position], mFragments[currentSelect]);
//                currentSelect = position;
//
////                currentSelect = position;
////                listMainMenu.getAdapter().notifyDataSetChanged();
////                toPage(o);
//            }
//        }));
//    }
//
//    private void initFragment() {
//        SupportFragment firstFragment = findFragment(XiaoboHelpYouFragment.class);
//        if (firstFragment == null) {
//            mFragments[FIRST] = XiaoboHelpYouFragment.newInstance();
//            mFragments[SECOND] = RoleChoicesFragment.newInstance();
//            mFragments[THIRD] = HospitalIntroduceFragment.newInstance();
//            mFragments[FOURTH] = HospitalNavigationFragment.newInstance();
//            mFragments[FIFTH] = InfoDisclosureMainFragment.newInstance();
//            mFragments[SIXTH] = HospitalVideoFragment.newInstance();
//            mFragments[SEVENTH] = DrugsInfoFragment.newInstance();
//            mFragments[EIGHTH] = ProcessGuideFragment.newInstance();
//            mFragments[NINTH] = BusinessGuideFragment.newInstance();
//            mFragments[TENTH] = HospitalMainNetFragment.newInstance();
//
//
//            loadMultipleRootFragment(R.id.content, FIRST,
//                    mFragments[FIRST],
//                    mFragments[SECOND],
//                    mFragments[THIRD],
//                    mFragments[FOURTH],
//                    mFragments[FIFTH],
//                    mFragments[SIXTH],
//                    mFragments[SEVENTH],
//                    mFragments[EIGHTH],
//                    mFragments[NINTH],
//                    mFragments[TENTH]);
//        } else {
//            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
//            // 这里我们需要拿到mFragments的引用
//            mFragments[FIRST] = firstFragment;
//            mFragments[SECOND] = findFragment(RoleChoicesFragment.class);
//            mFragments[THIRD] = findFragment(XiaoboHelpYouFragment3.class);
//            mFragments[FOURTH] = findFragment(HospitalNavigationFragment.class);
//            mFragments[FIFTH] = findFragment(InfoDisclosureMainFragment.class);
//            mFragments[SIXTH] = findFragment(HospitalVideoFragment.class);
//            mFragments[SEVENTH] = findFragment(DrugsInfoFragment.class);
//            mFragments[EIGHTH] = findFragment(ProcessGuideFragment.class);
//            mFragments[NINTH] = findFragment(BusinessGuideFragment.class);
//            mFragments[TENTH] = findFragment(HospitalMainNetFragment.class);
//
//
////            case "业务指引":
////                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_business_guide);
////                break;
//        }
//    }
//
////    private void toPage(String menuName) {
////        switch (menuName) {
////            case "小博帮您":
////                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_xiaobo_help);
////                break;
////            case "医院介绍":
////                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_hospital_introduce);
////                break;
////            case "信息公示":
////                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_info_disclosure);
////                break;
////            case "院内导航":
////                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_hospital_navigation);
////                break;
////            case "医院视频":
////                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.fragment_hospital_video);
////                break;
////        }
////    }
//
//
//    @Override
//    public void initListener() {
//    }
//
//    @Override
//    public String getTootBarTitle() {
//        return null;
//    }
//
//    public boolean enableToolbar() {
//        return true;
//    }
////    @Override
////    public void onBackPressedSupport() {
//////        pop();
//////        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//////            pop();
//////        } else {
//////            ActivityCompat.finishAfterTransition(this);
//////        }
////    }
//}
