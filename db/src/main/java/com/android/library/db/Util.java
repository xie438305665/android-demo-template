package com.android.library.db;

import com.android.library.db.entity.KeyboardEntity;
import com.android.library.db.entity.KeyboardV2Entity;

/**
 * @author xcl
 * @create 2019-05-13
 */
class Util {

    static KeyboardEntity transformV1(KeyboardV2Entity keyboardV2Entity) {
        if (keyboardV2Entity == null) {
            return null;
        }
        KeyboardEntity keyboardEntity = new KeyboardEntity();
        keyboardEntity.setId(keyboardV2Entity.getId());
        keyboardEntity.setMarkingGroupId(keyboardV2Entity.getTopicId());
        keyboardEntity.setTopicScore(keyboardV2Entity.getTopicScore());
        keyboardEntity.setTopicCount(keyboardV2Entity.getTopicCount());
        keyboardEntity.setHasLand(keyboardV2Entity.getHasLand());
        keyboardEntity.setHasAutomaticSubmit(keyboardV2Entity.getHasAutomaticSubmit());
        keyboardEntity.setHasShowUnAnswerScore(keyboardV2Entity.getHasShowUnAnswerScore());
        keyboardEntity.setHasDecimal(keyboardV2Entity.getHasDecimal());
        keyboardEntity.setHasReverse(keyboardV2Entity.getHasReverse());
        keyboardEntity.setScoreList(keyboardV2Entity.getScoreList());
        keyboardEntity.setSelectList(keyboardV2Entity.getSelectList());
        keyboardEntity.setDefaultScoreList(keyboardV2Entity.getDefaultScoreList());
        return keyboardEntity;
    }

    static KeyboardV2Entity transformV2(KeyboardEntity keyboardEntity) {
        if (keyboardEntity == null) {
            return null;
        }
        KeyboardV2Entity keyboardV2Entity = new KeyboardV2Entity();
        keyboardV2Entity.setId(keyboardEntity.getId());
        keyboardV2Entity.setTopicId(keyboardEntity.getMarkingGroupId());
        keyboardV2Entity.setTopicScore(keyboardEntity.getTopicScore());
        keyboardV2Entity.setTopicCount(keyboardEntity.getTopicCount());
        keyboardV2Entity.setHasLand(keyboardEntity.getHasLand());
        keyboardV2Entity.setHasAutomaticSubmit(keyboardEntity.getHasAutomaticSubmit());
        keyboardV2Entity.setHasShowUnAnswerScore(keyboardEntity.getHasShowUnAnswerScore());
        keyboardV2Entity.setHasDecimal(keyboardEntity.getHasDecimal());
        keyboardV2Entity.setHasReverse(keyboardEntity.getHasReverse());
        keyboardV2Entity.setScoreList(keyboardEntity.getScoreList());
        keyboardV2Entity.setSelectList(keyboardEntity.getSelectList());
        keyboardV2Entity.setDefaultScoreList(keyboardEntity.getDefaultScoreList());
        return keyboardV2Entity;
    }
}
