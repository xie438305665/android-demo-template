package com.demo.template.ui.template.oldx.rx;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.net.entity.template.ScoreEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.demo.template.annotation.GradeScoreType;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author y
 * @create 2019/3/21
 */
@Deprecated
public class OldAnswerFileFun implements Function<ScoreZipEntity, ObservableSource<ScoreZipEntity>> {

    private Activity activity;
    @GradeScoreType
    private int type;

    OldAnswerFileFun(@NonNull Activity activity, @GradeScoreType int type) {
        this.activity = activity;
        this.type = type;
    }

    @Override
    public ObservableSource<ScoreZipEntity> apply(ScoreZipEntity scoreZipEntity) {
        return Observable.create(e -> {
            List list = null;
            switch (type) {
                case GradeScoreType.FIRST:
                case GradeScoreType.UN:
                case GradeScoreType.PROBLEM:
                    list = scoreZipEntity.getStudentFirst();
                    break;
                case GradeScoreType.NEXT:
                case GradeScoreType.UN_NEXT:
                    list = scoreZipEntity.getStudentNext().getList();
                    break;
                case GradeScoreType.PREV:
                    list = scoreZipEntity.getStudentPrev().getList();
                    break;
                case GradeScoreType.PROBLEM_NEXT:
                    break;
                case GradeScoreType.PROBLEM_PREV:
                    break;
            }
            if (list == null || list.isEmpty()) {
                e.onNext(scoreZipEntity);
                e.onComplete();
                return;
            }
            ScoreEntity scoreEntity = (ScoreEntity) list.get(0);
            ScoreEntity.StudentPaperTopicBean studentPaperTopic = scoreEntity.getStudentPaperTopic();
            ImageLoaderUtils.displayFile(activity, scoreEntity.getStudentPaperTopic().getAnswerUrl(), new SimpleTarget<File>() {
                @Override
                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                    studentPaperTopic.setFile(resource);
                    e.onNext(scoreZipEntity);
                    e.onComplete();
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    onResourceReady(new File("file://error"), null);
                }
            });
        });
    }
}
