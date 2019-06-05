package com.android.library.net.body.read;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author y
 * @create 2019/4/8
 */
public class ProblemTaskSubmitBody implements IBody {

    private String examGroupId;
    private List<StudentsBean> students;

    public ProblemTaskSubmitBody(String examGroupId, List<StudentsBean> students) {
        this.examGroupId = examGroupId;
        this.students = students;
        Log();
    }

    @NonNull
    @Override
    public String toString() {
        return "ProblemTaskSubmitBody{" +
                "examGroupId='" + examGroupId + '\'' +
                ", students=" + students +
                '}';
    }

    public static class StudentsBean {

        private long batchNo;
        private double scoring;
        private String studentId;
        private String topicId;

        public StudentsBean(long batchNo, double scoring, String studentId, String topicId) {
            this.batchNo = batchNo;
            this.scoring = scoring;
            this.studentId = studentId;
            this.topicId = topicId;
        }

        @NonNull
        @Override
        public String toString() {
            return "StudentsBean{" +
                    "batchNo=" + batchNo +
                    ", scoring=" + scoring +
                    ", studentId='" + studentId + '\'' +
                    ", topicId=" + topicId +
                    '}';
        }

        public long getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(long batchNo) {
            this.batchNo = batchNo;
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

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }
    }
}
