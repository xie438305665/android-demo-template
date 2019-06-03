package com.android.library.db.annotation;

/**
 * @author xcl
 * @create 2019-05-13
 */
public @interface KeyboardType {
    /**
     * 以markGroupId为key
     */
    int V1 = 1;
    /**
     * 以topicId为key
     */
    int V2 = 2;
    String TYPE = "KeyboardType";
    String KEY = "KeyboardKey";
}
