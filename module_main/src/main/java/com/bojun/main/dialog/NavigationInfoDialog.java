package com.bojun.main.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bojun.common.adapter.BaseRecycerAdapter;
import com.bojun.common.adapter.ViewHolder;
import com.bojun.main.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 简  述  添加文字备忘录
 * 作  者  chenxiaoping
 * 包  名  com.bojun.common
 * 时  间  2020/8/20 17:04
 */
public class NavigationInfoDialog extends DialogFragment implements View.OnClickListener {

    private String navigationStr;
    private String departName;

    public NavigationInfoDialog setNavigation(String position) {
        this.navigationStr = position;
        return this;
    }

    public NavigationInfoDialog setDepartName(String departName) {
        this.departName = departName;
        return this;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_navigation);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT; //设置宽度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tv_depart_name = dialog.findViewById(R.id.tv_depart_name);
        TextView tv_navigation = dialog.findViewById(R.id.tv_navigation);
        if (!TextUtils.isEmpty(navigationStr)) {
            tv_navigation.setText(navigationStr);
        }
        if (!TextUtils.isEmpty(departName)) {
            tv_depart_name.setText(departName);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mViewModel = ViewModelProviders.of(this, InfoModelFactory.getInstance(getActivity().getApplication())).get(MemorandumViewModel.class);
//        getLifecycle().addObserver(mViewModel);
        callBack();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCanceledOnTouchOutside(true);
    }

    public void callBack() {
    }

    @Override
    public void onClick(View v) {
    }
}