package com.android.library.bridge.util;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

    private static final List<AppCompatActivity> list = new ArrayList<>();

    public static void addActivity(AppCompatActivity activity) {
        list.add(activity);
    }

    public static void removeActivity(AppCompatActivity activity) {
        list.remove(activity);
    }

    public static List<AppCompatActivity> getAllActivity() {
        return list;
    }

    public static void removeAllActivity() {
        for (AppCompatActivity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        list.clear();
    }
}
