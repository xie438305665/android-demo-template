package com.demo.template.listener.read;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;

import java.util.List;

/**
 * @author y
 * @create 2019/4/4
 */
@Deprecated
public interface OldOnScoreDataOwnListener {

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
     * @param entityList {@link java.util.ArrayList}
     */
    void onRefreshScoreList(@NonNull List<ScoreEntity> entityList);

    /**
     * 更新正确率
     *
     * @param entity {@link GroupQuotaEntity}
     */
    void onRefreshGroupQuota(@NonNull GroupQuotaEntity entity);

    /**
     * 更新试题答案
     *
     * @param entity {@link TopicAnswerEntity}
     */
    void onRefreshTopicAnswer(@NonNull TopicAnswerEntity entity);

    /**
     * 获取数据
     *
     * @return {@link java.util.ArrayList}
     */
    @Nullable
    List<ScoreEntity> getScoreList();

    /**
     * 获取{@link GroupQuotaEntity}
     */
    @Nullable
    GroupQuotaEntity getGQEntity();

    /**
     * 获取{@link TopicAnswerEntity}
     */
    @Nullable
    TopicAnswerEntity getTopAnswerEntity();

    @Nullable
    ScoreEntity getAnswerEntity();

    @Nullable
    List<ScoreEntity> getFillEntity();

    double getMaxTopicScore();

    @NonNull
    String getExamGroupId();

    @NonNull
    String getMarkingGroupId();

    @NonNull
    String getPrevStudentId();

    @NonNull
    String getNextStudentId();

    @Nullable
    String getClassId();

    @Nullable
    String getPaperId();

    @Nullable
    String getTopicId();

}
