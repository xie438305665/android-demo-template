package com.demo.template.ui.grade.oldx.rx;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.android.library.bridge.annotation.TopicType;
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
    public static Function<ScoreZipEntity, ObservableSource<ScoreZipEntity>> getFileFun(@NonNull Activity activity, @GradeScoreType int scoreNetType, @TopicType int type) {
        switch (type) {
            case TopicType.ANSWER:
                return new OldAnswerFileFun(activity, scoreNetType);
            case TopicType.ELECTIVE_QUESTION:
                break;
            case TopicType.ENGLISH:
                break;
            case TopicType.FILL:
                return new OldFillFileFun(activity, scoreNetType);
            case TopicType.MULTIPLE_CHOICE:
                break;
        }
        throw new NullPointerException();
    }

    @Deprecated
    public static Function3<BaseEntity<GroupQuotaEntity>, BaseEntity<ScoreEntity>, BaseEntity<TopicAnswerEntity>, ScoreZipEntity> getProblemFun3() {
        return new OldProblemFun3();
    }

}
