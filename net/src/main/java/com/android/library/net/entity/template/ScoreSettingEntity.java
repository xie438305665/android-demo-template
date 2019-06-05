package com.android.library.net.entity.template;

import java.util.List;

/**
 * by xcl on 2018/11/27.
 */
public class ScoreSettingEntity {
    private String topicType;
    private List<TopicBean> topicBeans;

    public ScoreSettingEntity(String topicType, List<TopicBean> topicBeans) {
        this.topicType = topicType;
        this.topicBeans = topicBeans;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public List<TopicBean> getTopicBeans() {
        return topicBeans;
    }

    public void setTopicBeans(List<TopicBean> topicBeans) {
        this.topicBeans = topicBeans;
    }

    public static class TopicBean {
        private int score;
        private String topicNum;
        private int countScore;

        public TopicBean(int score, String topicNum, int countScore) {
            this.score = score;
            this.topicNum = topicNum;
            this.countScore = countScore;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getTopicNum() {
            return topicNum;
        }

        public void setTopicNum(String topicNum) {
            this.topicNum = topicNum;
        }

        public int getCountScore() {
            return countScore;
        }

        public void setCountScore(int countScore) {
            this.countScore = countScore;
        }
    }
}
