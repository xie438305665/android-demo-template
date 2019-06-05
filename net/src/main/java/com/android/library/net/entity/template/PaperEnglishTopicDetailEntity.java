package com.android.library.net.entity.template;

import java.util.List;

public class PaperEnglishTopicDetailEntity {

    private int listType;
    private int logicalTypeId;
    private String logicalTypeName;
    private double score;
    private String title;
    private String topicId;
    private String topicNo;
    private List<Integer> slaveTopicNos;
    private List<SlavesBean> slaves;
    private List<TopicOptionsBean> topicOptions;

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public int getLogicalTypeId() {
        return logicalTypeId;
    }

    public void setLogicalTypeId(int logicalTypeId) {
        this.logicalTypeId = logicalTypeId;
    }

    public String getLogicalTypeName() {
        return logicalTypeName;
    }

    public void setLogicalTypeName(String logicalTypeName) {
        this.logicalTypeName = logicalTypeName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicNo() {
        return topicNo;
    }

    public void setTopicNo(String topicNo) {
        this.topicNo = topicNo;
    }

    public List<Integer> getSlaveTopicNos() {
        return slaveTopicNos;
    }

    public void setSlaveTopicNos(List<Integer> slaveTopicNos) {
        this.slaveTopicNos = slaveTopicNos;
    }

    public List<SlavesBean> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<SlavesBean> slaves) {
        this.slaves = slaves;
    }

    public List<TopicOptionsBean> getTopicOptions() {
        return topicOptions;
    }

    public void setTopicOptions(List<TopicOptionsBean> topicOptions) {
        this.topicOptions = topicOptions;
    }

    public static class TopicOptionsBean {
        private String content;
        private String isTrue;
        private String optionNo;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(String isTrue) {
            this.isTrue = isTrue;
        }

        public String getOptionNo() {
            return optionNo;
        }

        public void setOptionNo(String optionNo) {
            this.optionNo = optionNo;
        }
    }

    public static class SlavesBean {
        private int listType;
        private double score;
        private String title;
        private int topicId;
        private String topicNo;
        private List<TopicOptionsBean> topicOptions;

        public int getListType() {
            return listType;
        }

        public void setListType(int listType) {
            this.listType = listType;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getTopicNo() {
            return topicNo;
        }

        public void setTopicNo(String topicNo) {
            this.topicNo = topicNo;
        }

        public List<TopicOptionsBean> getTopicOptions() {
            return topicOptions;
        }

        public void setTopicOptions(List<TopicOptionsBean> topicOptions) {
            this.topicOptions = topicOptions;
        }

        public static class TopicOptionsBean {
            private String content;
            private String isTrue;
            private String optionNo;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIsTrue() {
                return isTrue;
            }

            public void setIsTrue(String isTrue) {
                this.isTrue = isTrue;
            }

            public String getOptionNo() {
                return optionNo;
            }

            public void setOptionNo(String optionNo) {
                this.optionNo = optionNo;
            }
        }
    }
}
