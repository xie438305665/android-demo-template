package com.demo.template.listener.read;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.net.entity.template.NewListEntity;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;
import com.demo.template.annotation.ScorePageState;

import java.util.List;

/**
 * @author y
 * @create 2019/4/4
 */
public interface OnScoreDataOwnListener {

    /**
     * 打开键盘设置
     */
    void openKeyboard();

    /**
     * 打开试题详情
     */
    void openTopic();

    /**
     * 重置数据
     */
    void resetData();

    /**
     * 更新数据
     *
     * @param entityList {@link NewListEntity}
     */
    void onRefreshScoreList(@NonNull NewListEntity<ScoreTaskEntity> entityList);

    /**
     * 更新当前进度
     *
     * @param myProgressEntity {@link PairsMyProgressEntity}
     */
    void onRefreshProgressItem(@NonNull PairsMyProgressEntity myProgressEntity);

    /**
     * 更新试题答案
     *
     * @param entity {@link TopicAnswerEntity}
     */
    void onRefreshTopicAnswer(@NonNull TopicAnswerEntity entity);

    /**
     * 获取当前试题数据
     */
    @Nullable
    PairsMyProgressEntity getCurrentProgressItem();

    /**
     * 获取Prev试题数据
     */
    @Nullable
    PairsMyProgressEntity getPrevProgressItem();

    /**
     * 获取Next数据
     */
    @Nullable
    PairsMyProgressEntity getNextProgressItem();

    @ScorePageState
    int hasPrevPageState();

    @ScorePageState
    int hasNextPageState();

    boolean hasUnScore();

    boolean hasLastItem();

    boolean hasNewMark();

    /**
     * 获取当前页码
     *
     * @return {@link Integer}
     */
    int getCurrentItemPageNum();

    /**
     * 获取{@link TopicAnswerEntity}
     */
    @Nullable
    TopicAnswerEntity getTopAnswerEntity();

    @Nullable
    ScoreTaskEntity getAnswerEntity();

    @Nullable
    List<ScoreTaskEntity> getFillEntity();

    double getMaxTopicScore();

    @NonNull
    String getExamGroupId();

    @NonNull
    String getTopicId();
}
