package com.demo.template.ui.read.oldx.rx;

import android.text.TextUtils;

import com.android.library.net.entity.BaseEntity;
import com.android.library.net.entity.template.ScoreNextEntity;
import com.android.library.net.entity.template.ScorePrevEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.ScorePageState;

import io.reactivex.functions.Function;

/**
 * @author y
 * @create 2019/4/2
 */
@Deprecated
public class OldPageStateFun<T> implements Function<BaseEntity<T>, ScoreZipEntity> {

    @GradeScoreType
    private int type;

    public OldPageStateFun(@GradeScoreType int type) {
        this.type = type;
    }

    @Override
    public ScoreZipEntity apply(BaseEntity<T> entity) {
        if (type == GradeScoreType.UN_NEXT) {
            return pageStateUnNext((ScoreNextEntity) entity.getData());
        } else if (type == GradeScoreType.NEXT) {
            return pageStateNext((ScoreNextEntity) entity.getData());
        } else if (type == GradeScoreType.PREV) {
            return pageStatePrev((ScorePrevEntity) entity.getData());
        }
        return new ScoreZipEntity();
    }

    private ScoreZipEntity pageStatePrev(ScorePrevEntity entity) {
        @ScorePageState int pageState;
        ScorePrevEntity.PrevBean prevBean = entity.getPrev();
        if (entity.isIsFirst() && entity.getList() != null && entity.getList().isEmpty() && TextUtils.isEmpty(prevBean.getTopicIndex())) {
            pageState = ScorePageState.NO_PREV;
        } else if (entity.isIsFirst() && !TextUtils.isEmpty(prevBean.getTopicIndex())) {
            pageState = ScorePageState.ANOTHER_PREV;
        } else {
            pageState = ScorePageState.PREV;
        }
        return ScoreZipEntity.createPrev(entity).setPageSate(pageState);
    }

    private ScoreZipEntity pageStateNext(ScoreNextEntity entity) {
        @ScorePageState int pageState;
        ScoreNextEntity.NextBean next = entity.getNext();
        if (entity.isLast() && entity.getList() != null && entity.getList().isEmpty() && TextUtils.isEmpty(next.getTopicIndex())) {
            pageState = ScorePageState.NO_NEXT;
        } else if (entity.isLast() && !TextUtils.isEmpty(next.getTopicIndex())) {
            pageState = ScorePageState.ANOTHER_NEXT;
        } else {
            pageState = ScorePageState.NEXT;
        }
        return ScoreZipEntity.createNext(entity).setPageSate(pageState);
    }

    private ScoreZipEntity pageStateUnNext(ScoreNextEntity entity) {
        @ScorePageState int pageState;
        ScoreNextEntity.NextBean next = entity.getNext();
        if (entity.isLast() && entity.getList() != null && entity.getList().isEmpty() && TextUtils.isEmpty(next.getTopicIndex())) {
            pageState = ScorePageState.NO_NEXT;
        } else if (entity.isLast() && !TextUtils.isEmpty(next.getTopicIndex())) {
            pageState = ScorePageState.ANOTHER_NEXT;
        } else {
            pageState = ScorePageState.NEXT;
        }
        return ScoreZipEntity.createUnNext(entity).setPageSate(pageState);
    }

}
