package com.demo.template.ui.grade.oldx.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.net.body.read.SubmitScoreBody;
import com.demo.template.annotation.BundleKey;
import com.demo.template.ui.grade.oldx.view.OldScoreView;

import java.util.ArrayList;
import java.util.HashMap;

@Deprecated
public abstract class OldScorePresenterImpl extends MVPresenterImpl<OldScoreView> implements OldScorePresenter {

    OldScorePresenterImpl(OldScoreView view) {
        super(view);
    }

    @Override
    public void onAnnotationRequest() {
//        NetRequest.single().request(GradeUrl.MARKING_LABELS, NetRequest.single().getService().markLabels(), new SimpleNetSuccessListener<List<AnnotationEntity>>(getView()) {
//            @Override
//            protected void onNetSuccess(@Nullable List<AnnotationEntity> data) {
//                if (getView() instanceof OldAnswerScoreView && data != null) {
//                    ((OldAnswerScoreView) getView()).onAnnotationSuccess(data);
//                }
//            }
//        });
    }

    @Override
    public void onSubmit(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull ArrayList<SubmitScoreBody> list, @NonNull String fraction, @TopicType int type) {
//        Observable<BaseEntity<GroupQuotaEntity>> groupQuota = NetRequest.single().getService().groupQuota(examGroupId, markingGroupId, classId).map(new NetFunc<>());
//        Observable<BaseEntity<ScoreSubmitEntity>> submit = NetRequest.single().getService().scoreSubmit(list).map(new NetFunc<>());
//        Observable<ScoreZipEntity> zip = Observable.zip(submit, groupQuota, (scoreSubmitEntityBaseEntity, groupQuotaEntityBaseEntity) -> new ScoreZipEntity().setGroupQuota(groupQuotaEntityBaseEntity.getData()));
//        NetRequest.single().getApi(GradeUrl.MARKING_SCORE_SUBMIT, zip, new SimpleNetZipSuccessListener<ScoreZipEntity>(getView()) {
//
//            @Override
//            public void onNetWorkStart() {
//            }
//
//            @Override
//            public void onNetWorkSuccess(ScoreZipEntity data) {
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitSuccess(data, fraction, type);
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
    public void onProblemSubmit(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull ArrayList<SubmitScoreBody> bodyList, @NonNull String fraction, @TopicType int type) {
//        Observable<BaseEntity<ScoreSubmitEntity>> submit = NetRequest.single().getService().scoreProblemSubmit(bodyList).map(new NetFunc<>());
//        NetRequest.single().getApi(GradeUrl.MARKING_PROBLEM_SUBMIT, submit.map(scoreSubmitEntityBaseEntity -> new ScoreZipEntity()), new SimpleNetZipSuccessListener<ScoreZipEntity>(getView()) {
//            @Override
//            public void onNetWorkStart() {
//            }
//
//            @Override
//            public void onNetWorkSuccess(ScoreZipEntity data) {
//                if (getView() == null) {
//                    return;
//                }
//                getView().onSubmitSuccess(data, fraction, type);
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
//                if (getView() instanceof OldAnswerScoreView) {
//                    ImageLoaderUtils.displayFile(getView().getCurrentActivity(), data, new SimpleTarget<File>() {
//                        @Override
//                        public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
//                            getView().hideProgress();
//                            ((OldAnswerScoreView) getView()).onResetImageSuccess(resource);
//                        }
//
//                        @Override
//                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                            super.onLoadFailed(errorDrawable);
//                            getView().hideProgress();
//                            ((OldAnswerScoreView) getView()).onResetImageError();
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

    /**
     * 每屏显示多少页
     *
     * @param type     {@link TopicType}
     * @param pageSize count
     * @return {@link java.util.Map}
     */
    HashMap<String, Integer> getPageSize(@TopicType int type, int pageSize) {
        HashMap<String, Integer> map = new HashMap<>();
        if (type == TopicType.FILL) {
            map.put(BundleKey.PAGE_SIZE, pageSize);
        }
        return map;
    }

    @Override
    public void onDestroy() {
//        NetRequest.single().cancel(
//                Booleans.TRUE + GradeUrl.MARKING_LABELS,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_NEXT,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_PREV,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_FIRST,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_UN,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_UN_NEXT,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_PROBLEM,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_PROBLEM_NEXT,
//                Booleans.TRUE + GradeUrl.MARKING_SCORE_SUBMIT,
//                Booleans.TRUE + GradeUrl.MARKING_RESTORE_IMG,
//                Booleans.TRUE + GradeUrl.MARKING_PROBLEM_SUBMIT,
//                Booleans.TRUE + GradeUrl.MARKING_STUDENT_PROBLEM_PREV,
//                Booleans.FALSE + GradeUrl.MARKING_LABELS,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_NEXT,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_PREV,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_FIRST,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_UN,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_UN_NEXT,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_PROBLEM,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_PROBLEM_NEXT,
//                Booleans.FALSE + GradeUrl.MARKING_SCORE_SUBMIT,
//                Booleans.FALSE + GradeUrl.MARKING_RESTORE_IMG,
//                Booleans.FALSE + GradeUrl.MARKING_PROBLEM_SUBMIT,
//                Booleans.FALSE + GradeUrl.MARKING_STUDENT_PROBLEM_PREV);
        super.onDestroy();
    }
}
