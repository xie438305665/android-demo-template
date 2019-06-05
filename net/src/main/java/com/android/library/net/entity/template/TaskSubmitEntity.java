package com.android.library.net.entity.template;

/**
 * @author y
 * @create 2019/4/9
 */
public class TaskSubmitEntity {
    private int markedNum;
    private int markingNum;
    private String accuracyRate;

    public int getMarkedNum() {
        return markedNum;
    }

    public void setMarkedNum(int markedNum) {
        this.markedNum = markedNum;
    }

    public int getMarkingNum() {
        return markingNum;
    }

    public void setMarkingNum(int markingNum) {
        this.markingNum = markingNum;
    }

    public String getAccuracyRate() {
        return accuracyRate;
    }

    public void setAccuracyRate(String accuracyRate) {
        this.accuracyRate = accuracyRate;
    }
}
