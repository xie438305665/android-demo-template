package com.demo.template.ui.template.oldx.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.key.Booleans;
import com.demo.template.ui.template.oldx.view.OldScoreView;

/**
 * @author y
 * @create 2019/4/2
 */
@Deprecated
public class OldScoreImpl extends OldScorePresenterImpl {

    public OldScoreImpl(OldScoreView view) {
        super(view);
    }

    @Override
    public void onScoreRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @QuestionType int type, boolean first, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> zip;
//        Observable<BaseEntity<List<ScoreEntity>>> studentFirst = NetRequest.single().getService().markingStudentFirst(examGroupId, markingGroupId, classId, 0, getPageSize(type, ScoreKeyboardFactory.getTopicCountV1(markingGroupId))).map(new NetFunc<>());
//        Observable<BaseEntity<GroupQuotaEntity>> groupQuota = NetRequest.single().getService().groupQuota(examGroupId, markingGroupId, classId).map(new NetFunc<>());
//        Observable<BaseEntity<TopicAnswerEntity>> topicAnswer = NetRequest.single().getService().topicAnswer(examGroupId, markingGroupId).map(new NetFunc<>());
//        zip = Observable
//                .zip(groupQuota, studentFirst, topicAnswer, (groupEntity, dataEntity, answerEntity) -> ScoreZipEntity.createFirst(groupEntity.getData(), dataEntity.getData(), answerEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(OldRxFactory.getFileFun(getView().getCurrentActivity(), GradeScoreType.FIRST, type));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_FIRST, zip, new OldScoreNetCallbackImpl(getView(), GradeScoreType.FIRST, hasPreLoading).setHasStatus(first));
    }

    @Override
    public void onScoreNextRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> observable = NetRequest.single().getService().markingStudentNext(examGroupId, markingGroupId, classId, studentId, getPageSize(type, ScoreKeyboardFactory.getTopicCountV1(markingGroupId)))
//                .map(new OldPageStateFun<>(GradeScoreType.NEXT))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(OldRxFactory.getFileFun(getView().getCurrentActivity(), GradeScoreType.NEXT, type));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_NEXT, observable, new OldScoreNetCallbackImpl(getView(), GradeScoreType.NEXT, hasPreLoading));
    }

    @Override
    public void onScorePrevRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> observable = NetRequest.single().getService().markingStudentPrev(examGroupId, markingGroupId, classId, studentId, getPageSize(type, ScoreKeyboardFactory.getTopicCountV1(markingGroupId)))
//                .map(new OldPageStateFun<>(GradeScoreType.PREV))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(OldRxFactory.getFileFun(getView().getCurrentActivity(), GradeScoreType.PREV, type));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_PREV, observable, new OldScoreNetCallbackImpl(getView(), GradeScoreType.PREV, hasPreLoading));
    }

    @Override
    public void onScoreUnRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @QuestionType int type, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> observable = NetRequest.single().getService().markingStudentUn(examGroupId, markingGroupId, classId, getPageSize(type, ScoreKeyboardFactory.getTopicCountV1(markingGroupId)))
//                .map(listBaseEntity -> ScoreZipEntity.createUn(listBaseEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(OldRxFactory.getFileFun(getView().getCurrentActivity(), GradeScoreType.UN, type));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_UN, observable, new OldScoreNetCallbackImpl(getView(), GradeScoreType.UN, hasPreLoading));
    }

    @Override
    public void onScoreUnNextRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @QuestionType int type, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> observable = NetRequest.single().getService().markingStudentUnNext(examGroupId, markingGroupId, classId, getPageSize(type, ScoreKeyboardFactory.getTopicCountV1(markingGroupId)))
//                .map(new OldPageStateFun<>(GradeScoreType.UN_NEXT))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(OldRxFactory.getFileFun(getView().getCurrentActivity(), GradeScoreType.UN_NEXT, type));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_UN_NEXT, observable, new OldScoreNetCallbackImpl(getView(), GradeScoreType.UN_NEXT, hasPreLoading));
    }

    @Override
    public void onScoreProblemRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading) {
//        Observable<BaseEntity<TopicAnswerEntity>> topicAnswer = NetRequest.single().getService().topicAnswer(examGroupId, markingGroupId).map(new NetFunc<>());
//        Observable<BaseEntity<GroupQuotaEntity>> groupQuota = NetRequest.single().getService().groupQuota(examGroupId, markingGroupId, "0").map(new NetFunc<>());
//        Observable<BaseEntity<ScoreEntity>> problem = NetRequest.single().getService().problem(examGroupId, markingGroupId, studentId).map(new NetFunc<>());
//        Observable<ScoreZipEntity> zip = Observable.zip(groupQuota, problem, topicAnswer, OldRxFactory.getProblemFun3())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(OldRxFactory.getFileFun(getView().getCurrentActivity(), GradeScoreType.PROBLEM, type));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_PROBLEM, zip, new OldScoreNetCallbackImpl(getView(), GradeScoreType.PROBLEM, hasPreLoading).setHasStatus(first));
    }

    @Override
    public void onScoreProblemNextRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> flatMap = NetRequest.single().getService().problemNext(examGroupId, markingGroupId, studentId)
//                .map(data -> ScoreZipEntity.createProblemNext(data.getData()).setPageSate(UIUtils.checkNull(data.getData().getStudentPaperTopic()) ? ScorePageState.NO_NEXT : ScorePageState.ANOTHER_NEXT));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_PROBLEM_NEXT, flatMap, new OldScoreNetCallbackImpl(getView(), GradeScoreType.PROBLEM_NEXT, hasPreLoading));
    }

    @Override
    public void onScoreProblemPrevRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading) {
//        Observable<ScoreZipEntity> flatMap = NetRequest.single().getService().problemPrev(examGroupId, markingGroupId, studentId)
//                .map(data -> ScoreZipEntity.createProblemPrev(data.getData()).setPageSate(UIUtils.checkNull(data.getData().getStudentPaperTopic()) ? ScorePageState.NO_PREV : ScorePageState.ANOTHER_PREV));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.MARKING_STUDENT_PROBLEM_PREV, flatMap, new OldScoreNetCallbackImpl(getView(), GradeScoreType.PROBLEM_PREV, hasPreLoading));
    }

}
