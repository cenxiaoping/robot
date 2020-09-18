package com.bojun.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bojun.main.R;
import com.kelin.scrollablepanel.library.PanelAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.main.adapter
 * 时  间  2020/9/7 10:49
 */
public class SchedulingAdapter extends PanelAdapter {

    private List<List<String>> data;

    public SchedulingAdapter(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 20;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, int i1) {
//        String title = data.get(row).get(column);
//        TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
//        titleViewHolder.titleTextView.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_title, parent, false));
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }
}