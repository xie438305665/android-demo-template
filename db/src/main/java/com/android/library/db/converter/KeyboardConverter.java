package com.android.library.db.converter;


import com.google.gson.reflect.TypeToken;
import com.android.library.db.DbStringHelper;
import com.android.library.db.entity.KeyboardScoreEntity;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * @author xcl
 */
public class KeyboardConverter implements PropertyConverter<List<KeyboardScoreEntity>, String> {

    @SuppressWarnings("unchecked")
    @Override
    public List<KeyboardScoreEntity> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) return null;
        return DbStringHelper.jsonToList(databaseValue, new TypeToken<List<KeyboardScoreEntity>>() {
        });
    }

    @Override
    public String convertToDatabaseValue(List<KeyboardScoreEntity> entityProperty) {
        if (entityProperty == null) return null;
        return DbStringHelper.writeValue(entityProperty);
    }
}
