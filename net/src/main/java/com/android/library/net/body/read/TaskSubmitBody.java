package com.android.library.net.body.read;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author y
 * @create 2019/4/8
 */
public class TaskSubmitBody implements IBody {

    private long batchNo;
    private String examGroupId;
    private String topicId;
    private List<StudentsBean> students;

    public TaskSubmitBody(long batchNo, String examGroupId, String topicId, List<StudentsBean> students) {
        this.batchNo = batchNo;
        this.examGroupId = examGroupId;
        this.topicId = topicId;
        this.students = students;
        Log();
    }

    @NonNull
    @Override
    public String toString() {
        return "TaskSubmitBody{" +
                "batchNo=" + batchNo +
                ", examGroupId='" + examGroupId + '\'' +
                ", topicId=" + topicId +
                ", students=" + students +
                '}';
    }


    public static class StudentsBean {

        private int isProblem;
        private double scoring;
        private String sptrId;
        private String studentId;
        private String trace;

        public StudentsBean(int isProblem, double scoring, String sptrId, String studentId, String trace) {
            this.isProblem = isProblem;
            this.scoring = scoring;
            this.sptrId = sptrId;
            this.studentId = studentId;
            this.trace = trace;
        }

        @NonNull
        @Override
        public String toString() {
            return "StudentsBean{" +
                    "isProblem=" + isProblem +
                    ", scoring=" + scoring +
                    ", sptrId='" + sptrId + '\'' +
                    ", studentId='" + studentId + '\'' +
                    ", trace='" + trace + '\'' +
                    '}';
        }
    }
}
