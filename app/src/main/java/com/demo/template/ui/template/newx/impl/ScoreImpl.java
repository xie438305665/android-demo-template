package com.demo.template.ui.template.newx.impl;

import android.support.annotation.NonNull;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.key.Booleans;
import com.android.library.net.body.read.TaskPageBody;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.ui.template.newx.view.ScoreView;

/**
 * @author y
 * @create 2019/4/2
 */
public class ScoreImpl extends ScorePresenterImpl {

    public ScoreImpl(ScoreView view) {
        super(view);
    }

    @Override
    public void onScoreRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading) {
        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, first, pageIndex, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> zip;
//        Observable<NewListEntity<ScoreTaskEntity>> taskPage = NetRequest.single().getService().taskPage(taskPageBody).map(new NetFunc<>());
//        Observable<BaseEntity<TopicAnswerEntity>> topicAnswer = NetRequest.single().getService().v2TopicAnswer(topicId).map(new NetFunc<>());
//        zip = Observable
//                .zip(taskPage, topicAnswer, (dataEntity, answerEntity) -> ScoreZipV2Entity.createFirst(dataEntity.getData(), answerEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.FIRST));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.TASK_PAGE, zip, new ScoreNetCallbackImpl(getView(), GradeScoreType.FIRST, hasPreLoading).setHasStatus(first));
    }

    @Override
    public void onScoreNextRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, first, pageIndex, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> observable = NetRequest.single().getService().taskPage(taskPageBody)
//                .map(scoreTaskEntityNewListEntity -> ScoreZipV2Entity.createNext(scoreTaskEntityNewListEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.NEXT));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.TASK_PAGE, observable, new ScoreNetCallbackImpl(getView(), GradeScoreType.NEXT, hasPreLoading));
    }

    @Override
    public void onScorePrevRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, first, pageIndex, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> observable = NetRequest.single().getService().taskPage(taskPageBody)
//                .map(scoreTaskEntityNewListEntity -> ScoreZipV2Entity.createPrev(scoreTaskEntityNewListEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.PREV));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.TASK_PAGE, observable, new ScoreNetCallbackImpl(getView(), GradeScoreType.PREV, hasPreLoading));
    }

    @Override
    public void onArbitrationScoreRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, first, pageIndex, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> zip;
//        Observable<NewListEntity<ScoreTaskEntity>> taskPage = NetRequest.single().getService().arbitrationTaskPage(taskPageBody).map(new NetFunc<>());
//        Observable<BaseEntity<TopicAnswerEntity>> topicAnswer = NetRequest.single().getService().v2TopicAnswer(topicId).map(new NetFunc<>());
//        zip = Observable
//                .zip(taskPage, topicAnswer, (dataEntity, answerEntity) -> ScoreZipV2Entity.createArbitrationFirst(dataEntity.getData(), answerEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.ARBITRATION));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.ARBITRATION_TASK_PAGE, zip, new ScoreNetCallbackImpl(getView(), GradeScoreType.ARBITRATION, hasPreLoading).setHasStatus(first));
    }

    @Override
    public void onArbitrationScorePrevRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, first, pageIndex, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> observable = NetRequest.single().getService().arbitrationTaskPage(taskPageBody)
//                .map(scoreTaskEntityNewListEntity -> ScoreZipV2Entity.createArbitrationPrev(scoreTaskEntityNewListEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.ARBITRATION_PREV));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.ARBITRATION_TASK_PAGE, observable, new ScoreNetCallbackImpl(getView(), GradeScoreType.ARBITRATION_PREV, hasPreLoading));
    }

    @Override
    public void onArbitrationScoreNextRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, first, pageIndex, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> observable = NetRequest.single().getService().arbitrationTaskPage(taskPageBody)
//                .map(scoreTaskEntityNewListEntity -> ScoreZipV2Entity.createArbitrationNext(scoreTaskEntityNewListEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.ARBITRATION_NEXT));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.ARBITRATION_TASK_PAGE, observable, new ScoreNetCallbackImpl(getView(), GradeScoreType.ARBITRATION_NEXT, hasPreLoading));
    }

    @Override
    public void onScoreUnRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> observable = NetRequest.single().getService().unTaskPage(taskPageBody)
//                .map(scoreTaskEntityNewListEntity -> ScoreZipV2Entity.createUn(scoreTaskEntityNewListEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.UN));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.UN_TASK_PAGE, observable, new ScoreNetCallbackImpl(getView(), GradeScoreType.UN, hasPreLoading));
    }

    @Override
    public void onScoreUnNextRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, @Booleans int hasPreLoading) {
//        TaskPageBody taskPageBody = new TaskPageBody(examGroupId, ScoreKeyboardFactory.getTopicCountV2(topicId), topicId);
//        Observable<ScoreZipV2Entity> observable = NetRequest.single().getService().unTaskPage(taskPageBody)
//                .map(scoreTaskEntityNewListEntity -> ScoreZipV2Entity.createUn(scoreTaskEntityNewListEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.UN_NEXT));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.UN_TASK_PAGE, observable, new ScoreNetCallbackImpl(getView(), GradeScoreType.UN_NEXT, hasPreLoading));
    }

    @Override
    public void onScoreProblemRequest(@NonNull String examGroupId, @NonNull String topicId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading) {
//        Observable<BaseEntity<TopicAnswerEntity>> topicAnswer = NetRequest.single().getService().v2TopicAnswer(topicId).map(new NetFunc<>());
//        Observable<BaseEntity<ScoreTaskEntity>> problem = NetRequest.single().getService().problemTask(examGroupId, topicId, studentId).map(new NetFunc<>());
//        Observable<ScoreZipV2Entity> zip = Observable.zip(problem, topicAnswer,
//                (taskEntity, topicAnswerEntity) -> ScoreZipV2Entity.createProblem(taskEntity.getData(), topicAnswerEntity.getData()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new TaskFileFun(getView().getCurrentActivity(), GradeScoreType.PROBLEM));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.PROBLEM_TASK, zip, new ScoreNetCallbackImpl(getView(), GradeScoreType.PROBLEM, hasPreLoading).setHasStatus(first));
    }

    @Override
    public void onScoreProblemNextRequest(@NonNull String examGroupId, @NonNull String topicId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading) {
//        Observable<ScoreZipV2Entity> flatMap = NetRequest.single().getService().problemNextTask(examGroupId, topicId, studentId)
//                .map(new NetFunc<>())
//                .map(taskEntity -> ScoreZipV2Entity.createProblemNext(taskEntity.getData()));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.PROBLEM_TASK_NEXT, flatMap, new ScoreNetCallbackImpl(getView(), GradeScoreType.PROBLEM_NEXT, hasPreLoading));
    }

    @Override
    public void onScoreProblemPrevRequest(@NonNull String examGroupId, @NonNull String topicId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading) {
//        Observable<ScoreZipV2Entity> flatMap = NetRequest.single().getService().problemPrevTask(examGroupId, topicId, studentId)
//                .map(new NetFunc<>())
//                .map(taskEntity -> ScoreZipV2Entity.createProblemPrev(taskEntity.getData()));
//        NetRequest.single().getApi(hasPreLoading + GradeUrl.PROBLEM_TASK_PREV, flatMap, new ScoreNetCallbackImpl(getView(), GradeScoreType.PROBLEM_PREV, hasPreLoading));
    }

}
