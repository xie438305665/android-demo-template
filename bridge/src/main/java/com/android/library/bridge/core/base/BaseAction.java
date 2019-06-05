package com.android.library.bridge.core.base;

import com.status.layout.Status;

/**
 * @author y
 * @create 2019-04-25
 */
public interface BaseAction {
    /**
     * 获取当前页面网络状态
     *
     * @return {@link Status}
     */
    @Status
    default String getCurrentStatus() {
        return Status.NORMAL;
    }
}
