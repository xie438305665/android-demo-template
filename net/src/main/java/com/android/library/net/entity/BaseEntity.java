package com.android.library.net.entity;

import com.google.gson.annotations.SerializedName;

public class BaseEntity<T> {

    private T data;
    @SerializedName("errCode")
    private String code;
    @SerializedName("errMsg")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
