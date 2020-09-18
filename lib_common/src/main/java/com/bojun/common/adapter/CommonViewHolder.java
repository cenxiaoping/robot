package com.bojun.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojun.common.R;
import com.bojun.common.util.BitmapUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;

public class CommonViewHolder implements Serializable {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId,
                             int position) {
        this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder get(Context context, View convertView,
                                       ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }

        return (CommonViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
//        Typeface typeface = Typeface.createFromAsset(this.mContext.getAssets(), "hwxs.ttf");
//        view.setTypeface(typeface);
        return this;
    }

    /**
     * 为CheckBox设置是否选中
     *
     * @param viewId
     * @param check
     * @return
     */
    public CommonViewHolder setChecked(int viewId, boolean check) {
        CheckBox view = getView(viewId);
        view.setChecked(check);
        return this;
    }

    /**
     * 为CheckBox设置监听
     *
     * @param viewId
     * @return
     */
    public CommonViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CheckBox view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * 为TextView设置Html.fromHtml
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setTextHtml(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(Html.fromHtml(text));
        return this;
    }

    /**
     * 为TextView设置字体大小
     *
     * @param viewId
     * @param size
     * @return
     */
    public CommonViewHolder setTextSize(int viewId, float size) {
        TextView view = getView(viewId);
        view.setTextSize(size);
        return this;
    }

    /**
     * 为TextView设置字体颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public CommonViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    /**
     * 为TextView设置字体颜色
     *
     * @param viewId
     * @param gravity
     * @return
     */
    public CommonViewHolder setTextGravity(int viewId, int gravity) {
        TextView view = getView(viewId);
        view.setGravity(gravity);
        return this;
    }

    /**
     * xxx点击事件
     *
     * @param viewId
     * @param listener
     * @return
     */
    public CommonViewHolder setOnclick(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置item背景色
     *
     * @param viewId
     * @param color  Drawable
     * @return
     */
    public CommonViewHolder setItemBackground(int viewId, Drawable color) {
        View view = getView(viewId);
        view.setBackground(color);
        return this;
    }

    /**
     * 设置item背景色
     *
     * @param viewId
     * @param color  int
     * @return
     */
    public CommonViewHolder setItemBackground(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置item背景色
     *
     * @param viewId
     * @param drawableId int
     * @return
     */
    public CommonViewHolder setItemBackgroundFromRes(int viewId, int drawableId) {
        View view = getView(viewId);
        view.setBackgroundResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public CommonViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置背景颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public CommonViewHolder setImageColor(int viewId, int color) {
        ImageView view = getView(viewId);
        view.setColorFilter(color);
        return this;
    }

    /**
     * 为ImageView设置网络图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public CommonViewHolder setImageToGlide(int viewId, String url, Context context, int placeholder) {
        ImageView view = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(placeholder).error(placeholder);
        Glide.with(context).load(url).apply(options).into(view);
        return this;
    }


    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置控件背景
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public CommonViewHolder setViewBackground(int viewId, Drawable drawable) {
        View view = getView(viewId);
        view.setBackground(drawable);
        return this;
    }

    /**
     * 设置条目是否可见
     *
     * @param viewId
     * @return
     */
    public CommonViewHolder setVisibility(int viewId, int v) {
        View view = getView(viewId);
        view.setVisibility(v);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param isSelected
     * @return
     */
    public CommonViewHolder setSelected(int viewId, boolean isSelected) {
        TextView view = getView(viewId);
        view.setSelected(isSelected);
        return this;
    }


    public int getPosition() {
        return mPosition;
    }

}