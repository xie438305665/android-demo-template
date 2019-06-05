package com.android.library.net.entity.template;

import java.util.List;

/**
 * @author y
 */
public class ScoreNextEntity {


    private boolean isLast;
    private NextBean next;
    private List<ScoreEntity> list;

    public List<ScoreEntity> getList() {
        return list;
    }

    public void setList(List<ScoreEntity> list) {
        this.list = list;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public NextBean getNext() {
        return next;
    }

    public void setNext(NextBean next) {
        this.next = next;
    }

    public static class NextBean {

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
