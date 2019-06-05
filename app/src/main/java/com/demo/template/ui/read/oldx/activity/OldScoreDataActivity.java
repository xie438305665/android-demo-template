package com.demo.template.ui.read.oldx.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.library.bridge.util.UIUtils;
import com.android.library.db.annotation.KeyboardType;
import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;
import com.demo.template.annotation.BundleKey;
import com.demo.template.listener.read.OldOnScoreDataOwnListener;
import com.demo.template.ui.activity.PaperTopicDetailActivity;
import com.demo.template.ui.activity.ScoreKeyboardActivity;

import java.util.List;

/**
 * @author y
 * @create 2019/4/4
 */
@Deprecated
public abstract class OldScoreDataActivity extends OldScoreViewPagerActivity implements OldOnScoreDataOwnListener {

    @Nullable
    private List<ScoreEntity> scoreEntityList;
    @Nullable
    private GroupQuotaEntity gqEntity;
    @Nullable
    private TopicAnswerEntity topicAnswerEntity;

    public static final int RESULT_KEY_BOARD = 223;

    @Override
    public void openKeyboard() {
        if (TextUtils.isEmpty(getMarkingGroupId())) {
            return;
        }
        Bundle keyboardBundle = new Bundle();
        keyboardBundle.putString(KeyboardType.KEY, getMarkingGroupId());
        keyboardBundle.putInt(KeyboardType.TYPE, KeyboardType.V1);
        UIUtils.startActivity(this, ScoreKeyboardActivity.class, RESULT_KEY_BOARD, keyboardBundle);
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        if (UIUtils.checkNotNull(getScoreAdapter())) {
            getScoreAdapter().setOnScoreDataOwnListener(this);
        }
    }

    @Override
    public void openTopic() {
        if (UIUtils.checkNull(getParameter()) || UIUtils.isEmpty(scoreEntityList) || TextUtils.isEmpty(getTopicId()) || TextUtils.isEmpty(getExamGroupId()) || UIUtils.checkNull(getGQEntity())) {
            return;
        }
        Bundle topicDetailBundle = new Bundle();
        topicDetailBundle.putString(BundleKey.TOPIC_ID, getTopicId());
        topicDetailBundle.putString(BundleKey.MAX_SCORE, String.valueOf(getMaxTopicScore()));
        topicDetailBundle.putInt(BundleKey.SUBJECT_ID, getParameter().getSubjectId());
        topicDetailBundle.putString(BundleKey.EXAM_GROUP_ID, getExamGroupId());
        topicDetailBundle.putString(BundleKey.TOPIC_INDEX, getGQEntity().getIndex());
        UIUtils.startActivity(PaperTopicDetailActivity.class, topicDetailBundle);
    }

    @Override
    public void resetData() {
        scoreEntityList = null;
        gqEntity = null;
        topicAnswerEntity = null;
    }

    @Override
    public void onRefreshScoreList(@NonNull List<ScoreEntity> entityList) {
        scoreEntityList = entityList;
    }

    @Override
    public void onRefreshGroupQuota(@NonNull GroupQuotaEntity entity) {
        gqEntity = entity;
    }

    @Override
    public void onRefreshTopicAnswer(@NonNull TopicAnswerEntity entity) {
        topicAnswerEntity = entity;
    }

    @Nullable
    @Override
    public List<ScoreEntity> getScoreList() {
        return scoreEntityList;
    }

    @Nullable
    @Override
    public GroupQuotaEntity getGQEntity() {
        return gqEntity;
    }

    @Nullable
    @Override
    public TopicAnswerEntity getTopAnswerEntity() {
        return topicAnswerEntity;
    }

    @Nullable
    @Override
    public ScoreEntity getAnswerEntity() {
        if (UIUtils.isEmpty(scoreEntityList)) {
            return null;
        }
        return scoreEntityList.get(0);
    }

    @Nullable
    @Override
    public List<ScoreEntity> getFillEntity() {
        if (UIUtils.isEmpty(scoreEntityList)) {
            return null;
        }
        return scoreEntityList;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public double getMaxTopicScore() {
        if (UIUtils.checkNull(getGQEntity())) {
            return 0;
        }
        return gqEntity.getScore();
    }

    @NonNull
    @Override
    public String getExamGroupId() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            if (UIUtils.checkNull(getParameter())) {
                return "";
            }
            return getParameter().getExamGroupId();
        }
        return getAnswerEntity().getExamGroupId();
    }

    @NonNull
    @Override
    public String getMarkingGroupId() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            if (UIUtils.checkNull(getParameter())) {
                return "";
            }
            return getParameter().getMarkingGroupId();
        }
        return getAnswerEntity().getMarkingGroupId();
    }

    @NonNull
    @Override
    public String getPrevStudentId() {
        if (UIUtils.checkNull(getAnswerEntity()) || UIUtils.checkNull(scoreEntityList)) {
            if (UIUtils.checkNull(getParameter())) {
                return "";
            }
            return getParameter().getStudentId();
        }
        return scoreEntityList.get(0).getStudentPaperTopic().getStudentId();
    }

    @NonNull
    @Override
    public String getNextStudentId() {
        if (UIUtils.checkNull(getAnswerEntity()) || UIUtils.checkNull(scoreEntityList)) {
            if (UIUtils.checkNull(getParameter())) {
                return "";
            }
            return getParameter().getStudentId();
        }
        return scoreEntityList.get(scoreEntityList.size() - 1).getStudentPaperTopic().getStudentId();
    }

    @Nullable
    @Override
    public String getClassId() {
        if (UIUtils.checkNull(getParameter())) {
            return "";
        }
        return getParameter().getClassId();
    }

    @Nullable
    @Override
    public String getPaperId() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            return "";
        }
        return getAnswerEntity().getStudentPaperTopic().getPaperId();
    }

    @Nullable
    @Override
    public String getTopicId() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            return "";
        }
        return getAnswerEntity().getStudentPaperTopic().getTopicId();
    }
}
