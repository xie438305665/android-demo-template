package com.android.library.bridge.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xcl
 * @create 2019/4/1
 */
@IntDef({
        TopicType.FILL,
        TopicType.ANSWER,
        TopicType.ENGLISH,
        TopicType.MULTIPLE_CHOICE,
        TopicType.ELECTIVE_QUESTION
})
@Retention(RetentionPolicy.SOURCE)
public @interface TopicType {
    /**
     * 填空题
     */
    int FILL = 5;
    /**
     * 解答题
     */
    int ANSWER = 7;
    /**
     * 英语题
     */
    int ENGLISH = 8;
    /**
     * 选择题
     */
    int MULTIPLE_CHOICE = 1;
    /**
     * 选做题
     */
    int ELECTIVE_QUESTION = 17;
    /**
     * 多选题
     */
    int OPTION_SELECT = 2;
}
