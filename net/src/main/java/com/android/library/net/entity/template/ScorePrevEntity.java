package com.android.library.net.entity.template;

import java.util.List;

/**
 * @author y
 */
public class ScorePrevEntity {

    private boolean isFirst;
    private PrevBean prev;
    private List<ScoreEntity> list;

    public boolean isIsFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public PrevBean getPrev() {
        return prev;
    }

    public void setPrev(PrevBean prev) {
        this.prev = prev;
    }

    public List<ScoreEntity> getList() {
        return list;
    }

    public void setList(List<ScoreEntity> list) {
        this.list = list;
    }

    public static class PrevBean {

        private String markingGroupId;
        private String examGroupId;
        private String topicIndex;
        private String classId;
        private String studentId;
        private int topicType;


        public String getMarkingGroupId() {
            return markingGroupId;
        }

        public void setMarkingGroupId(String markingGroupId) {
            this.markingGroupId = markingGroupId;
        }

        public String getExamGroupId() {
            return examGroupId;
        }

        public void setExamGroupId(String examGroupId) {
            this.examGroupId = examGroupId;
        }

        public String getTopicIndex() {
            return topicIndex;
        }

        public void setTopicIndex(String topicIndex) {
            this.topicIndex = topicIndex;
        }

        public int getTopicType() {
            return topicType;
        }

        public void setTopicType(int topicType) {
            this.topicType = topicType;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }
    }

}
