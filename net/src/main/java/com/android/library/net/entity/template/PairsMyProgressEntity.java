package com.android.library.net.entity.template;

import java.util.Objects;

public class PairsMyProgressEntity {
    private String examGroupId;
    private String topicId;
    private String topicNoText;
    private int topicType;
    private String topicTypeName;
    private double topicScore;
    private int markingNum;
    private int markedNum;
    private int markedPercent;
    private int modelEssayNum;
    private int batchNo;
    private int mode;
    private String accuracyRate;

    public String getExamGroupId() {
        return examGroupId;
    }

    public void setExamGroupId(String examGroupId) {
        this.examGroupId = examGroupId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicNoText() {
        return topicNoText;
    }

    public void setTopicNoText(String topicNoText) {
        this.topicNoText = topicNoText;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public String getTopicTypeName() {
        return topicTypeName;
    }

    public void setTopicTypeName(String topicTypeName) {
        this.topicTypeName = topicTypeName;
    }

    public double getTopicScore() {
        return topicScore;
    }

    public void setTopicScore(double topicScore) {
        this.topicScore = topicScore;
    }

    public int getMarkingNum() {
        return markingNum;
    }

    public void setMarkingNum(int markingNum) {
        this.markingNum = markingNum;
    }

    public int getMarkedNum() {
        return markedNum;
    }

    public void setMarkedNum(int markedNum) {
        this.markedNum = markedNum;
    }

    public int getMarkedPercent() {
        return markedPercent;
    }

    public void setMarkedPercent(int markedPercent) {
        this.markedPercent = markedPercent;
    }

    public int getModelEssayNum() {
        return modelEssayNum;
    }

    public void setModelEssayNum(int modelEssayNum) {
        this.modelEssayNum = modelEssayNum;
    }

    public int getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(int batchNo) {
        this.batchNo = batchNo;
    }

    public String getAccuracyRate() {
        return accuracyRate;
    }

    public void setAccuracyRate(String accuracyRate) {
        this.accuracyRate = accuracyRate;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairsMyProgressEntity that = (PairsMyProgressEntity) o;
        return topicType == that.topicType &&
                Double.compare(that.topicScore, topicScore) == 0 &&
                markingNum == that.markingNum &&
                markedNum == that.markedNum &&
                markedPercent == that.markedPercent &&
                modelEssayNum == that.modelEssayNum &&
                batchNo == that.batchNo &&
                mode == that.mode &&
                Objects.equals(examGroupId, that.examGroupId) &&
                Objects.equals(topicId, that.topicId) &&
                Objects.equals(topicNoText, that.topicNoText) &&
                Objects.equals(topicTypeName, that.topicTypeName) &&
                Objects.equals(accuracyRate, that.accuracyRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examGroupId, topicId, topicNoText, topicType, topicTypeName, topicScore, markingNum, markedNum, markedPercent, modelEssayNum, batchNo, mode, accuracyRate);
    }
}
