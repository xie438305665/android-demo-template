package com.demo.template.ui.read.newx.impl;

import android.support.annotation.NonNull;

import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.net.body.read.ArbitrationTaskSubmitBody;
import com.android.library.net.body.read.ProblemTaskSubmitBody;
import com.android.library.net.body.read.TaskSubmitBody;
import com.demo.template.annotation.TouchMode;
import com.demo.template.ui.read.newx.view.ScoreView;

public abstract class ScorePresenterImpl extends MVPresenterImpl<ScoreView> implements ScorePresenter {

    ScorePresenterImpl(ScoreView view) {
        super(view);
    }

    @Override
    public void onAnnotationRequest() {
//        NetRequest.single().request(GradeUrl.MARKING_LABELS, NetRequest.single().getService().markLabels(), new SimpleNetSuccessListener<List<AnnotationEntity>>(getView()) {
//            @Override
//            protected void onNetSuccess(@Nullable List<AnnotationEntity> data) {
//                if (getView() instanceof AnswerScoreView && data != null) {
//                    ((AnswerScoreView) getView()).onAnnotationSuccess(data);
//                }
//            }
//        });
    }

    @Override
    public void onSubmit(@NonNull TaskSubmitBody taskSubmitBody, @NonNull String fraction, @TouchMode int type) {
//        NetRequest.single().getApi(GradeUrl.TASK_SUBMIT, NetRequest.single().getService().taskSubmit(taskSubmitBody).map(new NetFunc<>()), new SimpleNetZipSuccessListener<BaseEntity<TaskSubmitEntity>>(getView()) {
//
//            @Override
//            public void onNetWorkStart() {
//            }
//
//            @Override
//            public void onNetWorkSuccess(BaseEntity<TaskSubmitEntity> data) {
//                if (getView() == null || data.getData() == null) {
//                    return;
//                }
//                getView().onSubmitSuccess(data.getData(), fraction, type);
//            }
//
//            @Override
//            public void onNetWorkError(Throwable e) {
//                super.onNetWorkError(e);
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitError();
//            }
//        });
    }

    @Override
    public void onProblemSubmit(@NonNull ProblemTaskSubmitBody body, @NonNull String fraction, @TouchMode int type) {
//        NetRequest.single().getApi(GradeUrl.PROBLEM_TASK_SUBMIT, NetRequest.single().getService().problemSubmit(body).map(new NetFunc<>()), new SimpleNetZipSuccessListener<BaseEntity<TaskSubmitEntity>>(getView()) {
//            @Override
//            public void onNetWorkStart() {
//            }
//
//            @Override
//            public void onNetWorkSuccess(BaseEntity<TaskSubmitEntity> data) {
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitSuccess(new TaskSubmitEntity(), fraction, type);
//            }
//
//            @Override
//            public void onNetWorkError(Throwable e) {
//                super.onNetWorkError(e);
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitError();
//            }
//        });
    }

    @Override
    public void onArbitrationSubmit(@NonNull ArbitrationTaskSubmitBody body, @NonNull String fraction, @TouchMode int type) {
//        NetRequest.single().getApi(GradeUrl.ARBITRATION_TASK_SUBMIT, NetRequest.single().getService().arbitrationSubmit(body).map(new NetFunc<>()), new SimpleNetZipSuccessListener<BaseEntity<TaskSubmitEntity>>(getView()) {
//            @Override
//            public void onNetWorkStart() {
//            }
//
//            @Override
//            public void onNetWorkSuccess(BaseEntity<TaskSubmitEntity> data) {
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitSuccess(data.getData(), fraction, type);
//            }
//
//            @Override
//            public void onNetWorkError(Throwable e) {
//                super.onNetWorkError(e);
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitError();
//            }
//        });
    }

    @Override
    public void onScoreResetImage(@NonNull String id) {
//        NetRequest.single().request(GradeUrl.MARKING_RESTORE_IMG, NetRequest.single().getService().resetImg(id), new SimpleNetSuccessListener<String>(getView()) {
//            @Override
//            protected void onNetSuccess(@Nullable String data) {
//                if (getView() instanceof AnswerScoreView) {
//                    ImageLoaderUtils.displayFile(getView().getCurrentActivity(), data, new SimpleTarget<File>() {
//                        @Override
//                        public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
//                            getView().hideProgress();
//                            ((AnswerScoreView) getView()).onResetImageSuccess(resource);
//                        }
//
//                        @Override
//                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                            super.onLoadFailed(errorDrawable);
//                            getView().hideProgress();
//                            ((AnswerScoreView) getView()).onResetImageError();
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onNetWorkComplete() {
//            }
//        });
    }

    @Override
    public void onDestroy() {
//        NetRequest.single().cancel(
//                Booleans.TRUE + GradeUrl.MARKING_LABELS,
//                Booleans.TRUE + GradeUrl.TASK_PAGE,
//                Booleans.TRUE + GradeUrl.ARBITRATION_TASK_PAGE,
//                Booleans.TRUE + GradeUrl.UN_TASK_PAGE,
//                Booleans.TRUE + GradeUrl.PROBLEM_TASK,
//                Booleans.TRUE + GradeUrl.PROBLEM_TASK_PREV,
//                Booleans.TRUE + GradeUrl.PROBLEM_TASK_NEXT,
//                Booleans.TRUE + GradeUrl.TASK_SUBMIT,
//                Booleans.TRUE + GradeUrl.MARKING_RESTORE_IMG,
//                Booleans.TRUE + GradeUrl.PROBLEM_TASK_SUBMIT,
//                Booleans.TRUE + GradeUrl.ARBITRATION_TASK_SUBMIT,
//                Booleans.FALSE + GradeUrl.ARBITRATION_TASK_SUBMIT,
//                Booleans.FALSE + GradeUrl.MARKING_LABELS,
//                Booleans.FALSE + GradeUrl.TASK_PAGE,
//                Booleans.FALSE + GradeUrl.ARBITRATION_TASK_PAGE,
//                Booleans.FALSE + GradeUrl.UN_TASK_PAGE,
//                Booleans.FALSE + GradeUrl.PROBLEM_TASK,
//                Booleans.FALSE + GradeUrl.PROBLEM_TASK_PREV,
//                Booleans.FALSE + GradeUrl.PROBLEM_TASK_NEXT,
//                Booleans.FALSE + GradeUrl.TASK_SUBMIT,
//                Booleans.FALSE + GradeUrl.MARKING_RESTORE_IMG,
//                Booleans.FALSE + GradeUrl.PROBLEM_TASK_SUBMIT);
//        super.onDestroy();
    }
}
