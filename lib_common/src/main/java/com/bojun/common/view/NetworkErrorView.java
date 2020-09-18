package com.bojun.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.bojun.common.R;

/**
 * NetworkErrorView
 * 网络错误视图
 */
public class NetworkErrorView extends RelativeLayout {

    public NetworkErrorView(Context context) {
        super(context);
        init();
    }

    public NetworkErrorView(Context context, int layoutID) {
        super(context);
        init();
    }

    public NetworkErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.button_network_err, this, true);
        this.setClickable(true);
        this.setFocusable(true);
    }
}
