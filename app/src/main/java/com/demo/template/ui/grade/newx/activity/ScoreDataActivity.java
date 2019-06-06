package com.demo.template.ui.grade.newx.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.library.bridge.util.GsonUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.db.annotation.KeyboardType;
import com.android.library.net.entity.template.NewListEntity;
import com.android.library.net.entity.template.PairsArbitrationProgressEntity;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.ScorePageState;
import com.demo.template.listener.read.OnScoreDataOwnListener;
import com.demo.template.ui.activity.TopicDetailActivity;
import com.demo.template.ui.activity.ScoreKeyboardActivity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y
 * @create 2019/4/4
 */
public abstract class ScoreDataActivity extends ScoreViewPagerActivity implements OnScoreDataOwnListener {

    @Nullable
    private NewListEntity<ScoreTaskEntity> scoreEntityList;
    @Nullable
    private List<PairsMyProgressEntity> myProgressEntityList;
    @Nullable
    private TopicAnswerEntity topicAnswerEntity;

    public static final int RESULT_KEY_BOARD = 223;

    @Override
    public void openKeyboard() {
        Bundle keyboardBundle = new Bundle();
        keyboardBundle.putString(KeyboardType.KEY, getTopicId());
        keyboardBundle.putInt(KeyboardType.TYPE, KeyboardType.V2);
        UIUtils.startActivity(this, ScoreKeyboardActivity.class, RESULT_KEY_BOARD, keyboardBundle);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        if (UIUtils.checkNull(getParameter())) {
            return;
        }
        if (!isArbitration()) {
            myProgressEntityList = GsonUtils.jsonToList(getParameter().getValue(), new TypeToken<List<PairsMyProgressEntity>>() {
            });
        } else {
            List<PairsArbitrationProgressEntity.ProgressesBean> list = GsonUtils.jsonToList(getParameter().getValue(), new TypeToken<List<PairsArbitrationProgressEntity.ProgressesBean>>() {
            });
            if (UIUtils.isEmpty(list)) {
                return;
            }
            myProgressEntityList = new ArrayList<>();
            for (PairsArbitrationProgressEntity.ProgressesBean progressesBean : list) {
                PairsMyProgressEntity pairsMyProgressEntity = new PairsMyProgressEntity();
                pairsMyProgressEntity.setExamGroupId(progressesBean.getExamGroupId());
                pairsMyProgressEntity.setTopicId(progressesBean.getTopicId());
                pairsMyProgressEntity.setTopicNoText(progressesBean.getTopicNoText());
                pairsMyProgressEntity.setTopicScore(progressesBean.getTopicScore());
                pairsMyProgressEntity.setTopicType(progressesBean.getTopicType());
                pairsMyProgressEntity.setMarkedNum(progressesBean.getHandleNum());
                pairsMyProgressEntity.setMarkingNum(progressesBean.getTotalNum() - progressesBean.getHandleNum());
                myProgressEntityList.add(pairsMyProgressEntity);
            }
        }
        if (UIUtils.checkNotNull(getScoreAdapter())) {
            getScoreAdapter().setOnScoreDataOwnListener(this);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void openTopic() {
        if (TextUtils.isEmpty(getTopicId()) || TextUtils.isEmpty(getExamGroupId())) {
            return;
        }
        if (isProblem() && UIUtils.checkNull(getAnswerEntity())) {
            return;
        }
        if (!isProblem() && UIUtils.checkNull(getCurrentProgressItem())) {
            return;
        }
        Bundle topicDetailBundle = new Bundle();
        topicDetailBundle.putString(BundleKey.TOPIC_ID, getTopicId());
        topicDetailBundle.putString(BundleKey.MAX_SCORE, isProblem() ? String.valueOf(getAnswerEntity().getScore()) : String.valueOf(getMaxTopicScore()));
        topicDetailBundle.putInt(BundleKey.SUBJECT_ID, UIUtils.checkNotNull(getParameter()) ? getParameter().getSubjectId() : 0);
        topicDetailBundle.putString(BundleKey.EXAM_GROUP_ID, getExamGroupId());
        topicDetailBundle.putString(BundleKey.TOPIC_INDEX, isProblem() ? getAnswerEntity().getTopicNo() : getCurrentProgressItem().getTopicNoText());
        UIUtils.startActivity(TopicDetailActivity.class, topicDetailBundle);
    }

    @Override
    public void resetData() {
        scoreEntityList = null;
        topicAnswerEntity = null;
    }

    @Override
    public void onRefreshScoreList(@NonNull NewListEntity<ScoreTaskEntity> entityList) {
        scoreEntityList = entityList;
    }

    @Override
    public void onRefreshTopicAnswer(@NonNull TopicAnswerEntity entity) {
        topicAnswerEntity = entity;
    }

    /**
     * 刷新进度
     *
     * @param myProgressEntity {@link PairsMyProgressEntity}
     */
    @Override
    public void onRefreshProgressItem(@NonNull PairsMyProgressEntity myProgressEntity) {
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return;
        }
        PairsMyProgressEntity currentProgressItem = getCurrentProgressItem();
        currentProgressItem.setMarkedNum(myProgressEntity.getMarkedNum());
        currentProgressItem.setMarkingNum(myProgressEntity.getMarkingNum());
        currentProgressItem.setAccuracyRate(myProgressEntity.getAccuracyRate());
    }

    /**
     * @return 是否还有未阅卷
     */
    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean hasUnScore() {
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return false;
        }
        if (getCurrentProgressItem().getMarkingNum() + getCurrentProgressItem().getMarkedNum() == getCurrentProgressItem().getMarkedNum()) {
            return false;
        }
        return true;
    }

    /**
     * @return 是否是最后一道题
     */
    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean hasLastItem() {
        if (UIUtils.checkNull(myProgressEntityList)) {
            return false;
        }
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return false;
        }
        if (myProgressEntityList.indexOf(getCurrentProgressItem()) == myProgressEntityList.size() - 1) {
            return true;
        }
        return false;
    }

    /**
     * @return 是否还有下一题
     */
    @ScorePageState
    @Override
    public int hasNextPageState() {
        if (UIUtils.checkNull(scoreEntityList) || UIUtils.checkNull(myProgressEntityList)) {
            return ScorePageState.NO_NEXT;
        }
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return ScorePageState.NO_NEXT;
        }
        if (scoreEntityList.isLastPage() && myProgressEntityList.indexOf(getCurrentProgressItem()) == myProgressEntityList.size() - 1) {
            return ScorePageState.NO_NEXT;
        }
        if (scoreEntityList.isLastPage() && myProgressEntityList.indexOf(getCurrentProgressItem()) != myProgressEntityList.size() - 1) {
            return ScorePageState.ANOTHER_NEXT;
        }
        return ScorePageState.NEXT;
    }

    /**
     * @return 是否还有上一题
     */
    @ScorePageState
    @Override
    public int hasPrevPageState() {
        if (UIUtils.checkNull(scoreEntityList) || UIUtils.checkNull(myProgressEntityList)) {
            return ScorePageState.NO_PREV;
        }
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return ScorePageState.NO_PREV;
        }
        if (scoreEntityList.isFirstPage() && myProgressEntityList.indexOf(getCurrentProgressItem()) == 0) {
            return ScorePageState.NO_PREV;
        }
        if (scoreEntityList.isFirstPage() && myProgressEntityList.indexOf(getCurrentProgressItem()) != 0) {
            return ScorePageState.ANOTHER_PREV;
        }
        return ScorePageState.PREV;
    }

    /**
     * @return 是否双评阅卷
     */
    @Override
    public boolean hasNewMark() {
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return false;
        }
        return getCurrentProgressItem().getMode() == 1;
    }

    @Override
    public int getCurrentItemPageNum() {
        if (UIUtils.checkNull(scoreEntityList)) {
            return 1;
        }
        return scoreEntityList.getPageNum();
    }

    @Nullable
    @Override
    public PairsMyProgressEntity getCurrentProgressItem() {
        if (UIUtils.checkNull(myProgressEntityList)) {
            return null;
        }
        for (PairsMyProgressEntity myProgressEntity : myProgressEntityList) {
            if (TextUtils.equals(myProgressEntity.getTopicId(), getTopicId())) {
                return myProgressEntity;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public PairsMyProgressEntity getNextProgressItem() {
        if (UIUtils.checkNull(getCurrentProgressItem()) || UIUtils.checkNull(myProgressEntityList)) {
            return null;
        }
        for (int i = 0; i < myProgressEntityList.size(); i++) {
            if (TextUtils.equals(myProgressEntityList.get(i).getTopicId(), getTopicId())) {
                return i == myProgressEntityList.size() - 1 ? myProgressEntityList.get(i) : myProgressEntityList.get(i + 1);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public PairsMyProgressEntity getPrevProgressItem() {
        if (UIUtils.checkNull(getCurrentProgressItem()) || UIUtils.checkNull(myProgressEntityList)) {
            return null;
        }
        for (int i = 0; i < myProgressEntityList.size(); i++) {
            if (TextUtils.equals(myProgressEntityList.get(i).getTopicId(), getTopicId())) {
                return i == 0 ? myProgressEntityList.get(i) : myProgressEntityList.get(i - 1);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public TopicAnswerEntity getTopAnswerEntity() {
        return topicAnswerEntity;
    }

    @Nullable
    @Override
    public ScoreTaskEntity getAnswerEntity() {
        if (UIUtils.checkNull(scoreEntityList) || UIUtils.isEmpty(scoreEntityList.getList())) {
            return null;
        }
        return scoreEntityList.getList().get(0);
    }

    @Nullable
    @Override
    public List<ScoreTaskEntity> getFillEntity() {
        if (UIUtils.checkNull(scoreEntityList)) {
            return null;
        }
        return scoreEntityList.getList();
    }

    @Override
    public double getMaxTopicScore() {
        if (isProblem()) {
            return UIUtils.checkNull(getAnswerEntity()) ? 0 : getAnswerEntity().getScore();
        }
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return 0;
        }
        return getCurrentProgressItem().getTopicScore();
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
    public String getTopicId() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            if (UIUtils.checkNull(getParameter())) {
                return "";
            }
            return getParameter().getTopicId();
        }
        return getAnswerEntity().getTopicId();
    }
}
