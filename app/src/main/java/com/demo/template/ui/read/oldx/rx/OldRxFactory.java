package com.demo.template.ui.read.oldx.rx;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.net.entity.BaseEntity;
import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.ScoreEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;
import com.demo.template.annotation.GradeScoreType;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

/**
 * @author y
 * @create 2019/3/21
 */
@Deprecated
public class OldRxFactory {

    @MainThread
    @NonNull
    public static Function<ScoreZipEntity, ObservableSource<ScoreZipEntity>> getFileFun(@NonNull Activity activity, @GradeScoreType int scoreNetType, @QuestionType int type) {
        switch (type) {
            case QuestionType.ANSWER:
                return new OldAnswerFileFun(activity, scoreNetType);
            case QuestionType.ELECTIVE_QUESTION:
                break;
            case QuestionType.ENGLISH:
                break;
            case QuestionType.FILL:
                return new OldFillFileFun(activity, scoreNetType);
            case QuestionType.MULTIPLE_CHOICE:
                break;
        }
        throw new NullPointerException();
    }

    @Deprecated
    public static Function3<BaseEntity<GroupQuotaEntity>, BaseEntity<ScoreEntity>, BaseEntity<TopicAnswerEntity>, ScoreZipEntity> getProblemFun3() {
        return new OldProblemFun3();
    }

}
