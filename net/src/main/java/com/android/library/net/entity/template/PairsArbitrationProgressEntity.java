package com.android.library.net.entity.template;

import java.util.List;

/**
 * @Author xcl
 * @CreateDate 2019/4/8
 */
public class PairsArbitrationProgressEntity {

    private boolean finishedBtnShow;
    private boolean markingFinished;
    private boolean finished;
    private List<ProgressesBean> progresses;

    public boolean isFinishedBtnShow() {
        return finishedBtnShow;
    }

    public void setFinishedBtnShow(boolean finishedBtnShow) {
        this.finishedBtnShow = finishedBtnShow;
    }

    public boolean isMarkingFinished() {
        return markingFinished;
    }

    public void setMarkingFinished(boolean markingFinished) {
        this.markingFinished = markingFinished;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<ProgressesBean> getProgresses() {
        return progresses;
    }

    public void setProgresses(List<ProgressesBean> progresses) {
        this.progresses = progresses;
    }

    public static class ProgressesBean {

        /**
         * examGroupId : 26025920594829965
         * topicId : 143161
         * topicNoText : 13
         * topicType : 5
         * topicTypeText : 填空题
         * topicScore : 5.0
         * totalNum : 5
         * handleNum : 5
         * handlePercent : 100
         */

        private String examGroupId;
        private String topicId;
        private String topicNoText;
        private int topicType;
        private String topicTypeText;
        private double topicScore;
        private int totalNum;
        private int handleNum;
        private int handlePercent;

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

        public String getTopicTypeText() {
            return topicTypeText;
        }

        public void setTopicTypeText(String topicTypeText) {
            this.topicTypeText = topicTypeText;
        }

        public double getTopicScore() {
            return topicScore;
        }

        public void setTopicScore(double topicScore) {
            this.topicScore = topicScore;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getHandleNum() {
            return handleNum;
        }

        public void setHandleNum(int handleNum) {
            this.handleNum = handleNum;
        }

        public int getHandlePercent() {
            return handlePercent;
        }

        public void setHandlePercent(int handlePercent) {
            this.handlePercent = handlePercent;
        }
    }
}
