package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.date_header)
    TextView dateView;

    public DateHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(String date) {
        dateView.setText(DateUtils.getDateDisplayTitle(itemView.getContext(), date));
    }
}
