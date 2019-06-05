package com.android.library.net.entity.template;

import java.io.File;

/**
 * @author y
 */
public class ScoreEntity {

    private String clazzName;
    private String examGroupId;
    private int isProblem;
    private String markingGroupId;
    private int problemStatus;
    private int problemTime;
    private int status;
    private String studentId;
    private String studentName;
    private StudentPaperTopicBean studentPaperTopic;
    private String teacherId;
    private String userNo;
    @Deprecated
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getExamGroupId() {
        return examGroupId;
    }

    public void setExamGroupId(String examGroupId) {
        this.examGroupId = examGroupId;
    }

    public int getIsProblem() {
        return isProblem;
    }

    public void setIsProblem(int isProblem) {
        this.isProblem = isProblem;
    }

    public String getMarkingGroupId() {
        return markingGroupId;
    }

    public void setMarkingGroupId(String markingGroupId) {
        this.markingGroupId = markingGroupId;
    }

    public int getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(int problemStatus) {
        this.problemStatus = problemStatus;
    }

    public int getProblemTime() {
        return problemTime;
    }

    public void setProblemTime(int problemTime) {
        this.problemTime = problemTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public StudentPaperTopicBean getStudentPaperTopic() {
        return studentPaperTopic;
    }

    public void setStudentPaperTopic(StudentPaperTopicBean studentPaperTopic) {
        this.studentPaperTopic = studentPaperTopic;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public static class StudentPaperTopicBean {
        private String answerUrl;
        private String id;
        private int isProblem;
        private String isRight;
        private String paperId;
        private double scoring;
        private String studentId;
        private String studentName;
        private String topicId;
        private int type;
        private File file;
        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }


        public String getAnswerUrl() {
            return answerUrl;
        }

        public void setAnswerUrl(String answerUrl) {
            this.answerUrl = answerUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsProblem() {
            return isProblem;
        }

        public void setIsProblem(int isProblem) {
            this.isProblem = isProblem;
        }

        public String getIsRight() {
            return isRight;
        }

        public void setIsRight(String isRight) {
            this.isRight = isRight;
        }

        public String getPaperId() {
            return paperId;
        }

        public void setPaperId(String paperId) {
            this.paperId = paperId;
        }

        public double getScoring() {
            return scoring;
        }

        public void setScoring(double scoring) {
            this.scoring = scoring;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    }
}
