package com.android.library.net.entity.template;

public class GroupQuotaEntity {

    private double score;
    private String index;
    private int markedNum;
    private String correctRate;
    private int markingNum;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getMarkedNum() {
        return markedNum;
    }

    public void setMarkedNum(int markedNum) {
        this.markedNum = markedNum;
    }

    public String getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(String correctRate) {
        this.correctRate = correctRate;
    }

    public int getMarkingNum() {
        return markingNum;
    }

    public void setMarkingNum(int markingNum) {
        this.markingNum = markingNum;
    }
}
