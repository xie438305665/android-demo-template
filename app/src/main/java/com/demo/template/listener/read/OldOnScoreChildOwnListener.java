package com.demo.template.listener.read;

import android.support.annotation.NonNull;

import com.android.library.net.body.read.SubmitScoreBody;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.TouchMode;

import java.util.ArrayList;

/**
 * @author y
 * @create 2019/4/1
 */
@Deprecated
public interface OldOnScoreChildOwnListener {
    /**
     * 请求网络
     *
     * @param type {@link com.demo.template.annotation.GradeScoreType}
     */
    void netRequest(@GradeScoreType int type, boolean next);

    /**
     * 提交分数
     *
     * @param body     {@link ArrayList}
     * @param fraction {@link String}
     * @param type     区分是上一页还是下一页 {@link TouchMode}
     */
    void onPostSubmit(@NonNull ArrayList<SubmitScoreBody> body, @NonNull String fraction, @TouchMode int type);

    /**
     * 上一道题
     */
    void onPrevPage();

    /**
     * 下一道题
     */
    void onNextPage();

    /**
     * 向左滑动
     */
    void onScrollLeft();

    /**
     * 向右滑动
     */
    void onScrollRight();

    /**
     * 自动下一题
     *
     * @param type {@link TouchMode#NEXT}
     *             {@link TouchMode#PREV}
     */
    // NEXT AND PREV 滚动提交,直接请求下一页数据,否则判断自动提交再
    void onAutomaticSubmit(@TouchMode int type);
}
