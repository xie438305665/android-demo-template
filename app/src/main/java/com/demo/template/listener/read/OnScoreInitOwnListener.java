package com.demo.template.listener.read;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.entity.ScoreMultiEntity;

/**
 * @author y
 * @create 2019/4/4
 */
public interface OnScoreInitOwnListener {
    /**
     * UI变化
     *
     * @param uiMode {@link ReadUIMode}
     */
    void onChangedViewState(@ReadUIMode int uiMode);

    /**
     * 更新侧边栏数据
     *
     * @param type 试题type {@link QuestionType}
     */
    void onChangeDrawerMode(@QuestionType int type);

    /**
     * 打开侧边栏
     */
    void onOpenDrawer();

    /**
     * 关闭侧边栏
     */
    void onCloseDrawer();

    /**
     * 获取只显示未阅卷状态
     *
     * @return true 打开
     */
    boolean isShowUnScore();

    /**
     * 设置只显示未阅卷状态
     *
     * @param showUnScore {@link Boolean} true 打开
     */
    void setShowUnScore(boolean showUnScore);

    /**
     * 获取横竖屏状态
     *
     * @return true 横屏
     */
    boolean isHasLand();

    /**
     * 设置横竖屏状态
     *
     * @param hasLand {@link Boolean} true 横屏
     */
    void setHasLand(boolean hasLand);

    /**
     * 获取自动提交状态
     *
     * @return true 打开
     */
    boolean isAutomaticSubmit();

    /**
     * 设置自动提交状态
     *
     * @param automaticSubmit {@link Boolean} true 自动提交
     */
    void setAutomaticSubmit(boolean automaticSubmit);

    /**
     * 获取参数
     *
     * @return {@link ScoreParameterEntity}
     */
    @Nullable
    ScoreParameterEntity getParameter();

    /**
     * 获取试题类型
     *
     * @return {@link QuestionType}
     */
    @QuestionType
    int getScoreType();

    /**
     * 是否问题卷
     *
     * @return true 是
     */
    boolean isProblem();

    /**
     * 是否混合阅卷
     *
     * @return true 是
     */
    boolean isMixing();

    /**
     * 是否双评
     *
     * @return true 是
     */
    boolean isArbitration();

    /**
     * 回退时刷新上一个页面
     */
    void onRefreshPrevUI();

    /**
     * 只显示未阅卷网络请求
     */
    void unAnswerNetWork();

    /**
     * 侧边栏点击
     */
    void onDrawerItemClick(@NonNull View view, int position, @NonNull ScoreMultiEntity info);
}
