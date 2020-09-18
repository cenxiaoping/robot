package com.bojun.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentProcessGuideBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.entity.FlowPathListBean;
import com.bumptech.glide.Glide;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 简  述  流程查询
 * 作  者  lanxianzheng
 * 包  名  com.bojun.main.fragment
 * 时  间  2020-08-31 09:18
 */
public class ProcessGuideFragment extends BaseMvvmFragment<FragmentProcessGuideBinding, MainViewModel> {
    private BaseRecycerAdapter<FlowPathListBean> adapter;
    private List<FlowPathListBean> mDatas = new ArrayList<>();

    @Override
    public Class onBindViewModel() {
        return MainViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getActivity().getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getFlowPathListEvent().observe(this, new Observer<List<FlowPathListBean>>() {
            @Override
            public void onChanged(List<FlowPathListBean> flowPathListBeans) {
                mDatas.clear();
                if (flowPathListBeans != null) {
                    mDatas.addAll(flowPathListBeans);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_process_guide;
    }

    @Override
    public void initView(View view) {
        mBinding.rvMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new BaseRecycerAdapter<FlowPathListBean>(getContext(), mDatas, R.layout.item_depart_list) {
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, FlowPathListBean itemVO, int position) {
                TextView tv_content = viewHolder.findViewById(R.id.tv_content);
                tv_content.setText(itemVO.getFlowName());
                tv_content.setSelected(itemVO.isSelect());//改变选择item颜色
            }
        };
        adapter.setOnItemClickListener(new BaseRecycerAdapter.OnItemClickListener<FlowPathListBean>() {
            @Override
            public void onItemClick(FlowPathListBean o, int position) {

                for (FlowPathListBean bean : mDatas) {
                    bean.setSelect(false);
                }

                mDatas.get(position).setSelect(true);
                adapter.notifyDataSetChanged();

                mBinding.tvTitle.setText(o.getFlowName());

                if (o.getFilePath().endsWith("pdf")) {
                    mBinding.tvContent.setVisibility(View.GONE);
                    mBinding.pdfview.setVisibility(View.VISIBLE);

                    showPdf(o.getFilePath());

                } else {
                    mBinding.tvContent.setVisibility(View.VISIBLE);
                    mBinding.pdfview.setVisibility(View.GONE);
                    Glide.with(getActivity()).load(o.getFilePath()).into(mBinding.tvContent);
                }
            }
        });
        mBinding.rvMenu.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @SuppressLint("CheckResult")
    private void showPdf(String urlPath) {
        Flowable.just(urlPath).observeOn(Schedulers.io()).map(new Function<String, InputStream>() {
            @Override
            public InputStream apply(String s) throws Exception {
                URL url = new URL(s);
                HttpURLConnection connection = (HttpURLConnection)
                        url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    return connection.getInputStream();
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<InputStream>() {
            @Override
            public void accept(InputStream inputStream) throws Exception {
                if (inputStream == null) {
                    return;
                }
                mBinding.pdfview.fromStream(inputStream)
                        .onPageChange(new OnPageChangeListener() {
                            @Override
                            public void onPageChanged(int page, int pageCount) {
//                                                tvCurrentIndex.setText("页数：" + (page+1) + "/" + pageCount);
                            }
                        })//设置翻页监听
                        .onRender(new OnRenderListener() {
                            @Override
                            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                                mBinding.pdfview.fitToWidth();
                            }
                        })//设置每一页适应屏幕宽，默认适应屏幕高
                        .swipeHorizontal(false)//设置不可水平滑动
                        .onLoad(new OnLoadCompleteListener() {
                            @Override
                            public void loadComplete(int nbPages) {

                            }
                        })
                        .load();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
            }
        });
    }

    @Override
    public void initData() {
        mViewModel.mHospitalCode.set("123456");
        mViewModel.selectFlowPath();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    public static ProcessGuideFragment newInstance() {

        Bundle args = new Bundle();

        ProcessGuideFragment fragment = new ProcessGuideFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
