package com.android.library.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DbStringHelper {

    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static Gson gson = null;

    private static Gson getDeserializer() {
        if (gson == null) {
            gson = GSON_BUILDER.create();
        }
        return gson;
    }

    public static <T> List jsonToList(String paramString, TypeToken<List<T>> paramTypeToken) {
        try {
            return (List) getDeserializer().fromJson(paramString,
                    paramTypeToken.getType());
        } catch (Exception localException) {
            return null;
        }
    }

    public static String writeValue(Object paramObject) {
        try {
            return getDeserializer().toJson(paramObject);
        } catch (Exception localException) {
            return null;
        }
    }
}
