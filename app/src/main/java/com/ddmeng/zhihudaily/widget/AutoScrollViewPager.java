package com.ddmeng.zhihudaily.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.utils.LogUtils;

import java.lang.ref.WeakReference;

public class AutoScrollViewPager extends ViewPager {
    public static final String TAG = "AutoScrollViewPager";
    public static final int MESSAGE_SCROLL = 0;
    public static final int DEFAULT_SCROLL_INTERVAL_DURATION = 2000;

    private Handler scrollHandler;
    private int scrollIntervalDuration = DEFAULT_SCROLL_INTERVAL_DURATION;
    private boolean isAutoScrollEnabled = false;

    public AutoScrollViewPager(Context context) {
        super(context);
        init(context, null);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoScrollViewPager, 0, 0);
            scrollIntervalDuration = typedArray.getInteger(R.styleable.AutoScrollViewPager_scrollIntervalDuration,
                    DEFAULT_SCROLL_INTERVAL_DURATION);
            typedArray.recycle();
        }
        scrollHandler = new ScrollHandler(this);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtils.d(TAG, "onPageScrolled: " + position + ", " + positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.d(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtils.d(TAG, "onPageScrollStateChanged: " + state);
                switch (state) {
                    case SCROLL_STATE_IDLE: {
                        startAutoScroll();
                        break;
                    }
                    case SCROLL_STATE_DRAGGING: {
                        stopAutoScroll();
                        break;
                    }
                }
            }
        });
    }

    public int getScrollIntervalDuration() {
        return scrollIntervalDuration;
    }

    public void setScrollIntervalDuration(int scrollIntervalDuration) {
        this.scrollIntervalDuration = scrollIntervalDuration;
    }

    public void startAutoScroll() {
        isAutoScrollEnabled = true;
        sendScrollMessage();
    }

    public void stopAutoScroll() {
        isAutoScrollEnabled = false;
        scrollHandler.removeMessages(MESSAGE_SCROLL);
    }

    private void sendScrollMessage() {
        if (isAutoScrollEnabled) {
            scrollHandler.removeMessages(MESSAGE_SCROLL);
            scrollHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL, scrollIntervalDuration);
        }
    }

    private void scroll() {
        PagerAdapter adapter = getAdapter();
        int totalCount = adapter != null ? adapter.getCount() : 0;
        if (totalCount <= 1) {
            return;
        }
        int currentItem = getCurrentItem();
        setCurrentItem(currentItem == totalCount - 1 ? 0 : ++currentItem, true);
    }

    private static class ScrollHandler extends Handler {
        private WeakReference<AutoScrollViewPager> viewPagerWeakReference;

        public ScrollHandler(AutoScrollViewPager viewPager) {
            this.viewPagerWeakReference = new WeakReference<>(viewPager);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL: {
                    AutoScrollViewPager autoScrollViewPager = viewPagerWeakReference.get();
                    if (autoScrollViewPager != null) {
                        autoScrollViewPager.scroll();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }
}
