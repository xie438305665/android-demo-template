package com.demo.template.listener.action;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnAnswerAnnotationAction {
    /**
     * 还原试题
     */
    void openAnnotationReduction();

    /**
     * 打开或者关闭批注工具
     */
    void openAnnotationUnFold();

    /**
     * 打开批注
     */
    void openAnnotation();

    /**
     * 批注删除
     */
    void openAnnotationDelete();

    /**
     * 批注画笔
     */
    void openAnnotationScribble();

    /**
     * 批注文字
     */
    void openAnnotationText();

    /**
     * 批注撤销
     */
    void openAnnotationUndo();

    /**
     * 是否双评
     *
     * @return true 是
     */
    boolean hasNewMark();
}
