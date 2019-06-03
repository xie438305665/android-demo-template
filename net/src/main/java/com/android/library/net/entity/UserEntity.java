package com.android.library.net.entity;

public class UserEntity {
    private String phone;
    private int status;
    private int subjects;
    private String teacherName;
    private boolean isUseHxb;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSubjects() {
        return subjects;
    }

    public void setSubjects(int subjects) {
        this.subjects = subjects;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean isUseHxb() {
        return isUseHxb;
    }

    public void setUseHxb(boolean useHxb) {
        isUseHxb = useHxb;
    }
}
