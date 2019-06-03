package com.android.library.bridge.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

    private static final List<Activity> list = new ArrayList<>();

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static List<Activity> getAllActivity() {
        return list;
    }

    public static void removeAllActivity() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        list.clear();
    }
}
