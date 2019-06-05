package com.android.library.net.body.read;

import android.support.annotation.NonNull;

/**
 * @author y
 * @create 2019/4/8
 */
public class TaskPageBody implements IBody {

    private String examGroupId;
    private boolean firstGet;
    private int pageIndex;
    private int pageSize;
    private String topicId;

    public TaskPageBody(String examGroupId, boolean firstGet, int pageIndex, int pageSize, String topicId) {
        this.examGroupId = examGroupId;
        this.firstGet = firstGet;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.topicId = topicId;
        Log();
    }

    /**
     * @param examGroupId examGroupId
     * @param pageSize    pageSize
     * @param topicId     topicId
     */
    public TaskPageBody(String examGroupId, int pageSize, String topicId) {
        this.examGroupId = examGroupId;
        this.pageSize = pageSize;
        this.topicId = topicId;
    }

    @NonNull
    @Override
    public String toString() {
        return "TaskPageBody{" +
                "examGroupId='" + examGroupId + '\'' +
                ", firstGet=" + firstGet +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", topicId=" + topicId +
                '}';
    }
}
