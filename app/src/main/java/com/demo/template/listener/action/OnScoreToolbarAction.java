package com.demo.template.listener.action;

import com.android.library.bridge.core.base.BaseAction;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnScoreToolbarAction extends BaseAction {

    /**
     * 退出
     */
    void onScoreToolbarFinish();

    /**
     * 打开侧边栏
     */
    void onScoreToolbarOpenDrawer();

}