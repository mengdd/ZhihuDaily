package com.ddmeng.zhihudaily.widget.ItemDecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class HeaderDecoration extends RecyclerView.ItemDecoration {
    private final HeaderAdapter adapter;
    private final HeaderProvider headerProvider;

    public HeaderDecoration(HeaderAdapter adapter) {
        this.adapter = adapter;
        this.headerProvider = new HeaderViewCache(adapter);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }
        if (hasNewHeader(itemPosition)) {
            View header = headerProvider.getHeader(parent, itemPosition);
            outRect.top = header.getHeight();
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        if (childCount <= 0 || adapter.getItemCount() <= 0) {
            return;
        }
        for (int i = 0; i < childCount; ++i) {
            View itemView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(itemView);
            if (position == RecyclerView.NO_POSITION) {
                continue;
            }
            if (hasNewHeader(position)) {
                ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
                int leftMargin = 0;
                int topMargin = 0;
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    leftMargin = marginLayoutParams.leftMargin;
                    topMargin = marginLayoutParams.topMargin;
                }
                View header = headerProvider.getHeader(parent, position);
                canvas.save();
                canvas.translate(itemView.getLeft() - leftMargin, itemView.getTop() - topMargin - header.getHeight());
                header.draw(canvas);
                canvas.restore();

            }
        }
    }

    public void invalidateHeaders() {
        headerProvider.invalidate();
    }

    private boolean hasNewHeader(int position) {
        if (indexOutOfBounds(position)) {
            return false;
        }

        long headerId = adapter.getHeaderId(position);
        if (headerId < 0) {
            return false;
        }
        long lastHeaderId = -1;
        int lastItemPosition = position - 1;
        if (!indexOutOfBounds(lastItemPosition)) {
            lastHeaderId = adapter.getHeaderId(lastItemPosition);
        }
        return position == 0 || headerId != lastHeaderId;
    }

    private boolean indexOutOfBounds(int position) {
        return position < 0 || position >= adapter.getItemCount();
    }
}
