package com.demo.template.entity;

import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.ScoreNextEntity;
import com.android.library.net.entity.template.ScorePrevEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;
import com.demo.template.annotation.ScorePageState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y
 */
@Deprecated
public class ScoreZipEntity {

    /**
     * 阅卷进度,正确率
     */
    private GroupQuotaEntity groupQuota;
    /**
     * 答案
     */
    private TopicAnswerEntity topicAnswerEntity;
    /**
     * 数据.解答题取第一个
     */
    private List<ScoreEntity> studentFirst;
    /**
     * 下一道题数据
     */
    private ScoreNextEntity studentNext;
    /**
     * 上一道题数据
     */
    private ScorePrevEntity studentPrev;

    @ScorePageState
    private int pageSate = ScorePageState.NO_STATE;

    @Deprecated
    public static ScoreZipEntity createFirst(GroupQuotaEntity group, List<ScoreEntity> data, TopicAnswerEntity answer) {
        return new ScoreZipEntity()
                .setGroupQuota(group)
                .setStudentFirst(data)
                .setTopicAnswerEntity(answer);
    }

    @Deprecated
    public static ScoreZipEntity createNext(ScoreNextEntity data) {
        return new ScoreZipEntity().setStudentNext(data);
    }

    @Deprecated
    public static ScoreZipEntity createPrev(ScorePrevEntity data) {
        return new ScoreZipEntity().setStudentPrev(data);
    }

    @Deprecated
    public static ScoreZipEntity createUn(List<ScoreEntity> data) {
        return new ScoreZipEntity().setStudentFirst(data);
    }

    @Deprecated
    public static ScoreZipEntity createUnNext(ScoreNextEntity data) {
        return new ScoreZipEntity().setStudentNext(data);
    }

    @Deprecated
    public static ScoreZipEntity createProblemNext(ScoreEntity data) {
        ArrayList<ScoreEntity> arrayList = new ArrayList<>();
        arrayList.add(data);
        return new ScoreZipEntity().setStudentFirst(arrayList);
    }

    @Deprecated
    public static ScoreZipEntity createProblemPrev(ScoreEntity data) {
        ArrayList<ScoreEntity> arrayList = new ArrayList<>();
        arrayList.add(data);
        return new ScoreZipEntity().setStudentFirst(arrayList);
    }

    public GroupQuotaEntity getGroupQuota() {
        return groupQuota;
    }

    public ScoreZipEntity setGroupQuota(GroupQuotaEntity groupQuota) {
        this.groupQuota = groupQuota;
        return this;
    }

    public ScoreNextEntity getStudentNext() {
        return studentNext;
    }

    public ScoreZipEntity setStudentNext(ScoreNextEntity studentNext) {
        this.studentNext = studentNext;
        return this;
    }

    public ScorePrevEntity getStudentPrev() {
        return studentPrev;
    }

    public ScoreZipEntity setStudentPrev(ScorePrevEntity studentPrev) {
        this.studentPrev = studentPrev;
        return this;
    }

    public List<ScoreEntity> getStudentFirst() {
        return studentFirst;
    }

    public ScoreZipEntity setStudentFirst(List<ScoreEntity> studentFirst) {
        this.studentFirst = studentFirst;
        return this;
    }

    public TopicAnswerEntity getTopicAnswerEntity() {
        return topicAnswerEntity;
    }

    public ScoreZipEntity setTopicAnswerEntity(TopicAnswerEntity topicAnswerEntity) {
        this.topicAnswerEntity = topicAnswerEntity;
        return this;
    }

    @ScorePageState
    public int getPageSate() {
        return pageSate;
    }

    public ScoreZipEntity setPageSate(@ScorePageState int pageSate) {
        this.pageSate = pageSate;
        return this;
    }
}
