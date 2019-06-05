package com.demo.template.listener.read;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnScoreOwnListener {
    /**
     * 更新阅卷UI
     *
     * @param hasNext 是否是下一题
     */
    void onChangedFragment(boolean hasNext);
}
