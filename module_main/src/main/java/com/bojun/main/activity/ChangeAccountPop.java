package com.bojun.main.activity;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.util.DisplayUtil;
import com.bojun.main.R;

/**
 * 简  述  订单状态选择弹窗
 * 作  者  chenxiaoping
 * 包  名  view
 * 时  间  2020/6/1 11:40
 */
public class ChangeAccountPop extends PopupWindow {

    private OnSelectListener listener;
    private boolean isShowing = false;

    private BaseRecycerAdapter mAdapter;

    public ChangeAccountPop(Context context) {
        View popupwindowRootView = View.inflate(context, R.layout.pop_change_account, null);
        setContentView(popupwindowRootView);
        setWidth(DisplayUtil.dip2px(184));
        setHeight(DisplayUtil.dip2px(170));

        popupwindowRootView.findViewById(R.id.tv_select1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onChange();
                }
                dismiss();
            }
        });

        popupwindowRootView.findViewById(R.id.tv_select2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLogOut();
                }
                dismiss();
            }
        });
    }

    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void showAsDropDown(View anchor) {
        this.showAsDropDown(anchor, 0, 0, Gravity.BOTTOM);
        isShowing = true;
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        this.showAsDropDown(anchor, xoff, yoff, Gravity.BOTTOM);
        isShowing = true;
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        isShowing = true;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        isShowing = false;
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void onChange();

        void onLogOut();
    }
}
