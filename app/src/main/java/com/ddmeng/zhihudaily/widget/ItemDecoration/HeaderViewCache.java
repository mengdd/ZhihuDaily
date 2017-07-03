package com.ddmeng.zhihudaily.widget.ItemDecoration;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class HeaderViewCache implements HeaderProvider {
    private final HeaderAdapter adapter;
    private final LongSparseArray<View> headerViews = new LongSparseArray<>();

    public HeaderViewCache(HeaderAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View getHeader(RecyclerView recyclerView, int position) {
        long headerId = adapter.getHeaderId(position);
        View headerView = headerViews.get(headerId);
        if (headerView == null) {
            RecyclerView.ViewHolder viewHolder = adapter.onCreateHeaderViewHolder(recyclerView);
            adapter.onBindHeaderViewHolder(viewHolder, position);
            headerView = viewHolder.itemView;

            int widthSpec = View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), View.MeasureSpec.UNSPECIFIED);
            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    recyclerView.getPaddingLeft() + recyclerView.getPaddingRight(), headerView.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    recyclerView.getPaddingTop() + recyclerView.getPaddingBottom(), headerView.getLayoutParams().height);
            headerView.measure(childWidth, childHeight);
            headerView.layout(0, 0, headerView.getMeasuredWidth(), headerView.getMeasuredHeight());
            headerViews.put(headerId, headerView);

        }
        return headerView;
    }

    @Override
    public void invalidate() {
        headerViews.clear();
    }
}
