package com.android.library.net.body.read;

import android.support.annotation.NonNull;


import java.util.List;

/**
 * @author y
 * @create 2019/4/8
 */
public class ArbitrationTaskSubmitBody implements IBody {

    private String topicId;
    private String examGroupId;
    private List<StudentsBean> students;

    public ArbitrationTaskSubmitBody(String topicId, String examGroupId, List<StudentsBean> students) {
        this.topicId = topicId;
        this.examGroupId = examGroupId;
        this.students = students;
        Log();
    }

    @NonNull
    @Override
    public String toString() {
        return "ArbitrationTaskSubmitBody{" +
                "topicId=" + topicId +
                ", examGroupId='" + examGroupId + '\'' +
                ", students=" + students +
                '}';
    }

    public static class StudentsBean {
        private double scoring;
        private String sptrId;
        private String studentId;

        public StudentsBean(double scoring, String sptrId, String studentId) {
            this.scoring = scoring;
            this.sptrId = sptrId;
            this.studentId = studentId;
        }

        @NonNull
        @Override
        public String toString() {
            return "StudentsBean{" +
                    "scoring=" + scoring +
                    ", sptrId='" + sptrId + '\'' +
                    ", studentId='" + studentId + '\'' +
                    '}';
        }
    }
}
