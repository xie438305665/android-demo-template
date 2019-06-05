package com.demo.template.listener.action;

import android.support.v4.view.ViewPager;

/**
 * @author y
 * @create 2019/4/1
 */
public interface SimpleOnPageChangeAction extends ViewPager.OnPageChangeListener {
    @Override
    default void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    default void onPageScrollStateChanged(int i) {
    }
}
