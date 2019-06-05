package com.demo.template.annotation;

/**
 * @author y
 * @create 2019/4/1
 */
public @interface BundleKey {
    /**
     * topicId
     */
    String TOPIC_ID = "topicId";
    /**
     * examGroupId
     */
    String EXAM_GROUP_ID = "examGroupId";
    /**
     * maxScore
     */
    String MAX_SCORE = "maxScore";
    /**
     * topicIndex
     */
    String TOPIC_INDEX = "topicIndex";
    /**
     * subjectId
     */
    String SUBJECT_ID = "subjectId";
    /**
     * pageSize
     */
    String PAGE_SIZE = "pageSize";
    /**
     * 解答题更多分数个数
     */
    String FRACTION_MORE_COUNT = "ANSWER:SCORE:COUNT";
    /**
     * 解答题更多分数是否显示小数
     */
    String FRACTION_MORE_SHOW_DOUBLE = "ANSWER:SCORE:SHOW:DOUBLE";
    /**
     * 查看答案
     */
    String LOOK_ANSWER = "LOOK:ANSWER";
    /**
     * 阅卷数据
     */
    String GRADE_SCORE_ENTITY = "GRADE_SCORE_ENTITY";
    /**
     * 是否刷新
     */
    String GRADE_SCORE_REFRESH = "score:refresh";
}
