package com.demo.template.ui.template.newx.rx;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.demo.template.entity.ScoreZipV2Entity;
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
 * @create 2019/4/8
 */
public class TaskFileFun implements Function<ScoreZipV2Entity, ObservableSource<ScoreZipV2Entity>> {

    private Activity activity;
    @GradeScoreType
    private int type;
    private int imageSize = 0;

    public TaskFileFun(@NonNull Activity activity, @GradeScoreType int type) {
        this.activity = activity;
        this.type = type;
    }

    @Override
    public ObservableSource<ScoreZipV2Entity> apply(ScoreZipV2Entity scoreZipEntity) {
        return Observable.create(e -> {
            List<ScoreTaskEntity> list = null;
            switch (type) {
                case GradeScoreType.FIRST:
                case GradeScoreType.ARBITRATION:
                case GradeScoreType.ARBITRATION_NEXT:
                case GradeScoreType.ARBITRATION_PREV:
                case GradeScoreType.NEXT:
                case GradeScoreType.PREV:
                case GradeScoreType.PROBLEM:
                case GradeScoreType.UN:
                case GradeScoreType.UN_NEXT:
                    list = scoreZipEntity.getTaskList().getList();
                    break;
            }
            if (list == null || list.isEmpty()) {
                e.onNext(scoreZipEntity);
                e.onComplete();
                return;
            }
            int size = list.size();
            for (ScoreTaskEntity scoreEntity : list) {
                ImageLoaderUtils.displayFile(activity, scoreEntity.getAnswerUrl(), new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        scoreEntity.setFile(resource);
                        imageSize++;
                        if (imageSize == size) {
                            e.onNext(scoreZipEntity);
                            e.onComplete();
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        onResourceReady(new File("file://error"), null);
                    }
                });
            }
        });
    }

}
