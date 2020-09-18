package com.bojun.common.binding.viewadapter.daisyrefresh;

import androidx.databinding.BindingAdapter;

import com.bojun.common.binding.command.BindingCommand;
import com.bojun.common.view.refresh.BaseRefreshLayout;
import com.bojun.common.view.refresh.DaisyRefreshLayout;

/**
 * ViewAdapter
 */
public class ViewAdapter {
    @BindingAdapter(value = {"onRefreshCommand", "onLoadMoreCommand", "onAutoRefreshCommand"}, requireAll = false)
    public static void onRefreshCommand(DaisyRefreshLayout refreshLayout, final BindingCommand onRefreshCommand, final BindingCommand onLoadMoreCommond, final BindingCommand onAutoRerefeshCommond) {
        refreshLayout.setOnRefreshListener(new BaseRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new BaseRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (onLoadMoreCommond != null) {
                    onLoadMoreCommond.execute();
                }
            }
        });
        refreshLayout.setOnAutoLoadListener(new BaseRefreshLayout.OnAutoLoadListener() {
            @Override
            public void onAutoLoad() {
                if (onAutoRerefeshCommond != null) {
                    onAutoRerefeshCommond.execute();
                }
            }
        });
    }
}
