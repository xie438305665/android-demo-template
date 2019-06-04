package com.demo.template.annotation;

import android.support.annotation.IntDef;

/**
 * @author y
 * @create 2019/4/1
 */
@IntDef({
        GradeScoreType.FIRST,
        GradeScoreType.PREV,
        GradeScoreType.NEXT,
        GradeScoreType.UN,
        GradeScoreType.UN_NEXT,
        GradeScoreType.PROBLEM,
        GradeScoreType.PROBLEM_PREV,
        GradeScoreType.PROBLEM_NEXT,
        GradeScoreType.ARBITRATION,
        GradeScoreType.ARBITRATION_NEXT,
        GradeScoreType.ARBITRATION_PREV,
})
public @interface GradeScoreType {
    /**
     * 第一次请求
     */
    int FIRST = 0;
    /**
     * 上一道题
     */
    int PREV = 1;
    /**
     * 下一道题
     */
    int NEXT = 2;
    /**
     * 只显示未阅卷
     */
    int UN = 3;
    /**
     * 只显示未阅卷下一道题
     */
    int UN_NEXT = 4;
    /**
     * 问题卷第一次请求
     */
    int PROBLEM = 5;
    /**
     * 问题卷上一道题
     */
    int PROBLEM_PREV = 6;
    /**
     * 问题卷下一道题
     */
    int PROBLEM_NEXT = 7;
    /**
     * 仲裁
     */
    int ARBITRATION = 8;
    /**
     * 仲裁下一页
     */
    int ARBITRATION_NEXT = 9;
    /**
     * 仲裁上一页
     */
    int ARBITRATION_PREV = 10;
}
