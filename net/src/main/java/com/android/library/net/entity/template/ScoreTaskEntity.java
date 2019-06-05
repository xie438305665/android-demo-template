package com.android.library.net.entity.template;

import java.io.File;
import java.util.List;

/**
 * @author y
 * @create 2019/4/8
 */
public class ScoreTaskEntity {

    @Deprecated
    private boolean select;
    private String examGroupId;
    private String paperId;
    private String topicId;
    private String topicNo;
    private int topicType;
    private String studentId;
    private String studentName;
    private String clazzId;
    private String clazzName;
    private int status;
    private int isProblem;
    private int problemStatus;
    private double scoring;
    private double score;
    private String answerUrl;
    private String sptrId;
    private long batchNo;
    @Deprecated
    private File file;
    private List<TeacherMarkingScores> teacherMarkingScores;

    public String getExamGroupId() {
        return examGroupId;
    }

    public void setExamGroupId(String examGroupId) {
        this.examGroupId = examGroupId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
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

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
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

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsProblem() {
        return isProblem;
    }

    public void setIsProblem(int isProblem) {
        this.isProblem = isProblem;
    }

    public int getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(int problemStatus) {
        this.problemStatus = problemStatus;
    }

    public double getScoring() {
        return scoring;
    }

    public void setScoring(double scoring) {
        this.scoring = scoring;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAnswerUrl() {
        return answerUrl;
    }

    public void setAnswerUrl(String answerUrl) {
        this.answerUrl = answerUrl;
    }

    public String getSptrId() {
        return sptrId;
    }

    public void setSptrId(String sptrId) {
        this.sptrId = sptrId;
    }

    public long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(long batchNo) {
        this.batchNo = batchNo;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public List<TeacherMarkingScores> getTeacherMarkingScores() {
        return teacherMarkingScores;
    }

    public void setTeacherMarkingScores(List<TeacherMarkingScores> teacherMarkingScores) {
        this.teacherMarkingScores = teacherMarkingScores;
    }

    @Deprecated
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static class TeacherMarkingScores {
        private String teacherName;
        private String scoring;

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getScoring() {
            return scoring;
        }

        public void setScoring(String scoring) {
            this.scoring = scoring;
        }
    }
}
