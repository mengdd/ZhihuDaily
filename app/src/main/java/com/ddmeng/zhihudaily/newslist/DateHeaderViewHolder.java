package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.utils.DateUtils;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.date_header)
    TextView dateView;
    private final String dateFormat;
    public DateHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        dateFormat = itemView.getContext().getString(R.string.date_header_format);
    }
    public void populate(String date){
        DateTime dateTime = DateUtils.getDateTime(date);
        dateView.setText(dateTime.toString(dateFormat));
    }
}
