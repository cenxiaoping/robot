package com.bojun.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 简  述  不可左右滑动viewpage
 * 作  者  管流生
 * 包  名  com.bojun.archives.view
 * 时  间  2020/7/17 14:25
 */
public class NoScrollViewPager extends ViewPager {

    //是否可以进行滑动
    private boolean isSlide = false;

    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSlide(boolean slide) {
        this.isSlide = slide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }

}
