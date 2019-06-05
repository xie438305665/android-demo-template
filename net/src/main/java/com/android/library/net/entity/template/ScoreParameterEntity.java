package com.android.library.net.entity.template;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


/**
 * @author y
 * @create 2018/11/22
 */
public class ScoreParameterEntity implements Parcelable {

    public static ScoreParameterEntity newInstance() {
        return new ScoreParameterEntity();
    }

    public static ScoreParameterEntity newArbitrationInstanceV2(int scoreType, int subjectId, String examGroupId, String topicId, String value, boolean mixing,int versionType) {
        return newInstance()
                .setSubjectId(subjectId)
                .setValue(value)
                .setScoreType(scoreType)
                .setExamGroupId(examGroupId)
                .setTopicId(topicId)
                .setArbitration(true)
                .setVersionType(versionType)
                .setMixing(mixing);
    }

    public static ScoreParameterEntity newProblemInstanceV2(int scoreType, int subjectId, String examGroupId, String topicId, String studentId, boolean mixing,int versionType) {
        return newInstance()
                .setSubjectId(subjectId)
                .setScoreType(scoreType)
                .setExamGroupId(examGroupId)
                .setTopicId(topicId)
                .setStudentId(studentId)
                .setProblem(true)
                .setVersionType(versionType)
                .setMixing(mixing);

    }

    public static ScoreParameterEntity newTaskInstanceV2(int scoreType, int subjectId, String examGroupId, String topicId, String value, boolean mixing,int versionType) {
        return newInstance()
                .setSubjectId(subjectId)
                .setValue(value)
                .setScoreType(scoreType)
                .setExamGroupId(examGroupId)
                .setTopicId(topicId)
                .setVersionType(versionType)
                .setMixing(mixing);
    }

    public static ScoreParameterEntity newReplaceInstanceV2(int scoreType, String examGroupId, String topicId, String studentId,int versionType) {
        return newInstance()
                .setScoreType(scoreType)
                .setStudentId(studentId)
                .setExamGroupId(examGroupId)
                .setVersionType(versionType)
                .setTopicId(topicId);
    }

    public static ScoreParameterEntity newReplaceInstanceV1(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, int scoreType,int versionType) {
        return newInstance()
                .setExamGroupId(examGroupId)
                .setMarkingGroupId(markingGroupId)
                .setStudentId(studentId)
                .setVersionType(versionType)
                .setScoreType(scoreType);
    }

    public static ScoreParameterEntity newProblemInstanceV1(int questionType, String examGroupId, String markingGroupId, String studentId, int subjectId,int versionType) {
        return newInstance()
                .setScoreType(questionType)
                .setExamGroupId(examGroupId)
                .setMarkingGroupId(markingGroupId)
                .setSubjectId(subjectId)
                .setStudentId(studentId)
                .setVersionType(versionType)
                .setProblem(true)
                .setMixing(false);
    }


    public static ScoreParameterEntity newCorrectProgressV1(int questionType, String examGroupId, String markingGroupId, String classId, String value, int subjectId, boolean mixing,int versionType) {
        return newInstance()
                .setScoreType(questionType)
                .setExamGroupId(examGroupId)
                .setMarkingGroupId(markingGroupId)
                .setClassId(classId)
                .setValue(value)
                .setSubjectId(subjectId)
                .setVersionType(versionType)
                .setProblem(false)
                .setMixing(mixing);
    }


    public static ScoreParameterEntity newSelectReadV1(int questionType, String examGroupId, String markingGroupId, String classId, String value, int subjectId,int versionType) {
        return newInstance()
                .setScoreType(questionType)
                .setExamGroupId(examGroupId)
                .setMarkingGroupId(markingGroupId)
                .setClassId(classId)
                .setSubjectId(subjectId)
                .setValue(value)
                .setVersionType(versionType)
                .setProblem(false)
                .setMixing(false);
    }


    public static ScoreParameterEntity newPairsSelectReadV1(int questionType, String examGroupId, String markingGroupId, String classId, String value, int subjectId,int versionType) {
        return newInstance()
                .setScoreType(questionType)
                .setExamGroupId(examGroupId)
                .setMarkingGroupId(markingGroupId)
                .setClassId(classId)
                .setSubjectId(subjectId)
                .setValue(value)
                .setProblem(false)
                .setVersionType(versionType)
                .setMixing(false);
    }

    private int scoreType;
    private String examGroupId;
    private String markingGroupId;
    private String classId;
    private String studentId;
    private int subjectId;
    private boolean problem;
    private boolean mixing;
    private String topicId;
    private boolean arbitration;
    private String value;
    private int versionType;

    private ScoreParameterEntity() {
    }

    @Deprecated
    public ScoreParameterEntity(int scoreType, String examGroupId, String markingGroupId, String classId, String studentId, int subjectId, boolean problem, boolean mixing, int versionType) {
        this.scoreType = scoreType;
        this.examGroupId = examGroupId;
        this.markingGroupId = markingGroupId;
        this.classId = classId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.problem = problem;
        this.mixing = mixing;
        this.versionType = versionType;
    }

    public ScoreParameterEntity setScoreType(int scoreType) {
        this.scoreType = scoreType;
        return this;
    }

    public String getExamGroupId() {
        return examGroupId;
    }

    public ScoreParameterEntity setExamGroupId(String examGroupId) {
        this.examGroupId = examGroupId;
        return this;
    }

    public String getMarkingGroupId() {
        return markingGroupId;
    }

    public ScoreParameterEntity setMarkingGroupId(String markingGroupId) {
        this.markingGroupId = markingGroupId;
        return this;
    }

    public String getClassId() {
        return classId;
    }

    public ScoreParameterEntity setClassId(String classId) {
        this.classId = classId;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public ScoreParameterEntity setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public ScoreParameterEntity setSubjectId(int subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public boolean isProblem() {
        return problem;
    }

    public ScoreParameterEntity setProblem(boolean problem) {
        this.problem = problem;
        return this;
    }

    public boolean isMixing() {
        return mixing;
    }

    public ScoreParameterEntity setMixing(boolean mixing) {
        this.mixing = mixing;
        return this;
    }

    public int getScoreType() {
        return scoreType;
    }

    public String getTopicId() {
        return topicId;
    }

    public ScoreParameterEntity setTopicId(String topicId) {
        this.topicId = topicId;
        return this;
    }

    public boolean isArbitration() {
        return arbitration;
    }

    public ScoreParameterEntity setArbitration(boolean arbitration) {
        this.arbitration = arbitration;
        return this;
    }

    public String getValue() {
        return value;
    }

    public int getVersionType() {
        return versionType;
    }

    public ScoreParameterEntity setVersionType(int versionType) {
        this.versionType = versionType;
        return this;
    }

    public ScoreParameterEntity setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.scoreType);
        dest.writeString(this.examGroupId);
        dest.writeString(this.markingGroupId);
        dest.writeString(this.classId);
        dest.writeString(this.studentId);
        dest.writeInt(this.subjectId);
        dest.writeByte(this.problem ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mixing ? (byte) 1 : (byte) 0);
        dest.writeString(this.topicId);
        dest.writeByte(this.arbitration ? (byte) 1 : (byte) 0);
        dest.writeString(this.value);
        dest.writeInt(this.versionType);
    }

    protected ScoreParameterEntity(Parcel in) {
        this.scoreType = in.readInt();
        this.examGroupId = in.readString();
        this.markingGroupId = in.readString();
        this.classId = in.readString();
        this.studentId = in.readString();
        this.subjectId = in.readInt();
        this.problem = in.readByte() != 0;
        this.mixing = in.readByte() != 0;
        this.topicId = in.readString();
        this.arbitration = in.readByte() != 0;
        this.value = in.readString();
        this.versionType = in.readInt();
    }

    public static final Creator<ScoreParameterEntity> CREATOR = new Creator<ScoreParameterEntity>() {
        @Override
        public ScoreParameterEntity createFromParcel(Parcel source) {
            return new ScoreParameterEntity(source);
        }

        @Override
        public ScoreParameterEntity[] newArray(int size) {
            return new ScoreParameterEntity[size];
        }
    };
}
