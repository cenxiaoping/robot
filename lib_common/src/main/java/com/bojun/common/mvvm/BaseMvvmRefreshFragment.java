package com.bojun.common.mvvm;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.bojun.common.adapter.BaseAdapter;
import com.bojun.common.mvvm.viewmodel.BaseRefreshViewModel;
import com.bojun.common.view.refresh.DaisyRefreshLayout;
import com.example.lib_utils.LogPrintUtil;

/**
 * 下拉刷新、上拉加载更多的Fragment
 */
public abstract class BaseMvvmRefreshFragment<T, V extends ViewDataBinding, VM extends BaseRefreshViewModel> extends BaseMvvmFragment<V, VM> {
    protected DaisyRefreshLayout mRefreshLayout;
    protected BaseAdapter.OnItemClickListener mItemClickListener;
    protected BaseAdapter.OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public void initCommonView(View view) {
        super.initCommonView(view);
        initRefreshView();
    }

    @Override
    protected void initBaseViewObservable() {
        super.initBaseViewObservable();
        initBaseViewRefreshObservable();
    }

    private void initBaseViewRefreshObservable() {

        mViewModel.getUCRefresh().getAutoRefresLiveEvent().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                autoLoadData();
            }
        });
        mViewModel.getUCRefresh().getStopRefresLiveEvent().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                stopRefresh();
            }
        });
        mViewModel.getUCRefresh().getStopLoadMoreLiveEvent().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                stopLoadMore();
            }
        });
    }

    public abstract DaisyRefreshLayout getRefreshLayout();

    public void initRefreshView() {
        mRefreshLayout = getRefreshLayout();
    }

    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    public void stopLoadMore() {
        mRefreshLayout.setLoadMore(false);
    }

    public void autoLoadData() {
        LogPrintUtil.v("MYTAG", "autoLoadData start...");
        if (mRefreshLayout != null) {
            LogPrintUtil.v("MYTAG", "autoLoadData1 start...");
            mRefreshLayout.autoRefresh();
        }
    }

    public void setItemClickListener(BaseAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(BaseAdapter.OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }
}
