package com.bojun.common.mvp.view;

import com.bojun.common.mvp.contract.BaseRefreshContract;

import java.util.List;

/**
 * BaseRefreshView
 */
public interface BaseRefreshView<T> extends BaseRefreshContract.View {
    //刷新数据
    void refreshData(List<T> data);
    //加载更多
    void loadMoreData(List<T> data);
}
