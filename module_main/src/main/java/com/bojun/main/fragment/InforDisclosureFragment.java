package com.bojun.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentInfoDisclosureBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;
import com.bojun.net.entity.InfoDisclosureBean;
import com.bojun.net.entity.InfoDisclosureMessage;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.Fade;

/**
 * 简  述  信息公示
 * 作  者  管流生
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class InforDisclosureFragment extends BaseMvvmFragment<FragmentInfoDisclosureBinding, SplashViewModel> {


    private BaseRecycerAdapter<InfoDisclosureBean> mAdapter;
    private ArrayList<InfoDisclosureBean> infoDisclosureBeans = new ArrayList<>();

    private BaseRecycerAdapter<InfoDisclosureMessage> messageAdapter;


    public static InforDisclosureFragment newInstance() {
        InforDisclosureFragment fragment = new InforDisclosureFragment();
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
        mViewModel.getInfoMessagesEvent().observe(this, new Observer<List<InfoDisclosureMessage>>() {
            @Override
            public void onChanged(List<InfoDisclosureMessage> infoDisclosureMessages) {
                if (infoDisclosureMessages != null) {
                    messageAdapter.getData().clear();
                    messageAdapter.getData().addAll(infoDisclosureMessages);
                } else {
                    messageAdapter.getData().clear();
                }
                messageAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_info_disclosure;
    }

    @Override
    public void initView(View view) {
        initDemoData();
        initAdapter();
    }


    private void initDemoData() {
        InfoDisclosureBean bean1 = new InfoDisclosureBean();
        bean1.setName("医院快讯");
        bean1.setNewsTypeCode("yykx");
        bean1.setBacRecId(R.drawable.shape_yiyuankuaixun);
        bean1.setImgRecId(R.mipmap.yiyuankuaixun);

        InfoDisclosureBean bean2 = new InfoDisclosureBean();
        bean2.setName("医院院报");
        bean2.setNewsTypeCode("yyyb");
        bean2.setBacRecId(R.drawable.shape_yiyuanyuanbao);
        bean2.setImgRecId(R.mipmap.yiyuanyuanbao);

        InfoDisclosureBean bean3 = new InfoDisclosureBean();
        bean3.setName("医疗动态");
        bean3.setNewsTypeCode("yldt");
        bean3.setBacRecId(R.drawable.shape_yiliaodongtai);
        bean3.setImgRecId(R.mipmap.yiliaodongtai);

        InfoDisclosureBean bean4 = new InfoDisclosureBean();
        bean4.setName("媒体报道");
        bean4.setNewsTypeCode("mtbd");
        bean4.setBacRecId(R.drawable.shape_meitibaodao);
        bean4.setImgRecId(R.mipmap.meitibaodao);

        InfoDisclosureBean bean5 = new InfoDisclosureBean();
        bean5.setName("图片新闻");
        bean5.setNewsTypeCode("tpxw");
        bean5.setBacRecId(R.drawable.shape_tupianxinwen);
        bean5.setImgRecId(R.mipmap.tupianxinwen);

        InfoDisclosureBean bean6 = new InfoDisclosureBean();
        bean6.setName("视频新闻");
        bean6.setNewsTypeCode("spxw");
        bean6.setBacRecId(R.drawable.shape_shipinxinwen);
        bean6.setImgRecId(R.mipmap.xinwenshipin);


        infoDisclosureBeans.add(bean1);
        infoDisclosureBeans.add(bean2);
        infoDisclosureBeans.add(bean3);
        infoDisclosureBeans.add(bean4);
        infoDisclosureBeans.add(bean5);
        infoDisclosureBeans.add(bean6);
    }

    private void initAdapter() {
        mAdapter = new BaseRecycerAdapter<InfoDisclosureBean>(getContext(), infoDisclosureBeans, R.layout.item_info_disclosure) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, InfoDisclosureBean itemVO, int position) {
                ((TextView) viewHolder.itemView.findViewById(R.id.tv_name)).setText(itemVO.getName());
                ((ImageView) viewHolder.itemView.findViewById(R.id.iv_icon)).setImageResource(itemVO.getImgRecId());
                ((LinearLayout) viewHolder.itemView.findViewById(R.id.ll_content)).setBackgroundResource(itemVO.getBacRecId());

            }
        };
        mAdapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                mBinding.infoTitle.setText(infoDisclosureBeans.get(position).getName());
                mViewModel.getInfoMessageList(infoDisclosureBeans.get(position).getNewsTypeCode());

                messageAdapter.getData().clear();
                messageAdapter.notifyDataSetChanged();
            }
        });
        mBinding.rvInfo.setAdapter(mAdapter);
        int spanCount = 2; // 2 columns
        int spacing = 15; // 20px
        boolean includeEdge = false;
        mBinding.rvInfo.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        messageAdapter = new BaseRecycerAdapter<InfoDisclosureMessage>(getContext(), null, R.layout.item_infodisclosure_message) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, InfoDisclosureMessage itemVO, int position) {
                viewHolder.setText(R.id.item_title, itemVO.getTitle());
                viewHolder.setText(R.id.item_message, itemVO.getSubTitle());
            }
        };
        messageAdapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                InfoDisclosureDetFragment fragment = InfoDisclosureDetFragment.newInstance(((InfoDisclosureMessage) o).getId() );
                setExitTransition(new Fade());
                fragment.setEnterTransition(new Fade());
                extraTransaction().start(fragment);
            }
        });
        mBinding.infoListview.setAdapter(messageAdapter);

        mBinding.infoTitle.setText(infoDisclosureBeans.get(0).getName());
        mViewModel.getInfoMessageList(infoDisclosureBeans.get(0).getNewsTypeCode());
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean enableLazyData() {
        return true;
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

}