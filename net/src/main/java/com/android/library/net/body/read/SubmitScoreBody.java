package com.android.library.net.body.read;

/**
 * @author y
 */
public class SubmitScoreBody {

    public String examGroupId;
    public int isProblem;
    public String markingGroupId;
    public String paperId;
    public double scoring;
    public String studentId;
    public String topicId;
    public String trace;

    @Deprecated
    public SubmitScoreBody(String examGroupId, int isProblem, String markingGroupId, String paperId, double scoring, String studentId, String topicId, String trace) {
        this.examGroupId = examGroupId;
        this.isProblem = isProblem;
        this.markingGroupId = markingGroupId;
        this.paperId = paperId;
        this.scoring = scoring;
        this.studentId = studentId;
        this.topicId = topicId;
        this.trace = trace;
    }

    @Override
    public String toString() {
        return "SubmitScoreBody{" +
                "examGroupId='" + examGroupId + '\'' +
                ", isProblem=" + isProblem +
                ", markingGroupId='" + markingGroupId + '\'' +
                ", paperId='" + paperId + '\'' +
                ", scoring='" + scoring + '\'' +
                ", studentId='" + studentId + '\'' +
                ", topicId=" + topicId +
                ", trace='" + trace + '\'' +
                '}';
    }
}
