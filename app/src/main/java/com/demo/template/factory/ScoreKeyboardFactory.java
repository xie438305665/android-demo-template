package com.demo.template.factory;

import android.support.annotation.NonNull;

import com.android.library.bridge.util.NumberUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.db.annotation.KeyboardType;
import com.android.library.db.entity.KeyboardEntity;
import com.android.library.db.entity.KeyboardScoreEntity;
import com.demo.template.entity.ScoreMultiEntity;
import com.xadapter.adapter.multi.MultiCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcl
 */
public class ScoreKeyboardFactory {

    public static void initScoreKeyboardV1(@NonNull String key, double topicScore) {
        initScoreKeyboard(KeyboardType.V1, key, topicScore);
    }

    public static void initScoreKeyboardV2(@NonNull String key, double topicScore) {
        initScoreKeyboard(KeyboardType.V2, key, topicScore);
    }

    /**
     * 初始化打分键盘
     */
    private static void initScoreKeyboard(@KeyboardType int type, @NonNull String key, double topicScore) {
        boolean hasDecimal = NumberUtils.endWithZero(topicScore);
        KeyboardEntity keyboardEntity = new KeyboardEntity();
        keyboardEntity.setMarkingGroupId(key);
        keyboardEntity.setDefaultScoreList(getScoreList(topicScore, hasDecimal));
        keyboardEntity.setScoreList(getScoreList(topicScore, hasDecimal));
        keyboardEntity.setHasDecimal(hasDecimal);
        keyboardEntity.setHasReverse(false);
        keyboardEntity.setTopicCount(5); // fill score
        keyboardEntity.setTopicScore(topicScore);
        GreenDaoManager.putOrReplaceKeyboard(type, keyboardEntity);
    }

    public static ArrayList<ScoreMultiEntity> getKeyboardListV1(@NonNull String key) {
        return getKeyboardList(KeyboardType.V1, key);
    }

    public static ArrayList<ScoreMultiEntity> getKeyboardListV2(@NonNull String key) {
        return getKeyboardList(KeyboardType.V2, key);
    }

    /**
     * 获取键盘数据
     *
     * @param key key
     * @return {@link ArrayList}
     */
    private static ArrayList<ScoreMultiEntity> getKeyboardList(@KeyboardType int type, @NonNull String key) {
        KeyboardEntity keyboardEntity = GreenDaoManager.queryKeyboard(type, key);
        ArrayList<ScoreMultiEntity> portList = new ArrayList<>();
        if (keyboardEntity == null) {
            return portList;
        }
        ScoreMultiEntity scoreMultiEntity;
        List<KeyboardScoreEntity> scoreList = keyboardEntity.getScoreList();
        List<KeyboardScoreEntity> selectList = keyboardEntity.getSelectList();
        if (selectList != null) {
            for (int i = 0; i < selectList.size(); i++) {
                scoreMultiEntity = new ScoreMultiEntity(-1, selectList.get(i).getScore(), MultiCallBack.TYPE_ITEM, i);
                portList.add(scoreMultiEntity);
            }
        }
        for (int i = 0; i < scoreList.size(); i++) {
            scoreMultiEntity = new ScoreMultiEntity(-1, scoreList.get(i).getScore(), MultiCallBack.TYPE_ITEM, i);
            if (!portList.contains(scoreMultiEntity)) {
                portList.add(scoreMultiEntity);
            }
        }
        return portList;
    }

    /**
     * 初始化解答题打分数据
     */
    private static List<KeyboardScoreEntity> getScoreList(double topicScore, boolean hasDecimal) {
        List<KeyboardScoreEntity> scoreList = new ArrayList<>();
        for (int i = 0; i <= topicScore; i++) {
            scoreList.add(new KeyboardScoreEntity(String.valueOf(i), false));
            if (hasDecimal && i < topicScore)
                scoreList.add(new KeyboardScoreEntity(String.valueOf(i + 0.5), false));
        }
        return scoreList;
    }

    public static int getTopicCountV1(@NonNull String key) {
        return getTopicCount(KeyboardType.V1, key);
    }

    public static int getTopicCountV2(@NonNull String key) {
        return getTopicCount(KeyboardType.V2, key);
    }

    /**
     * 获取每屏显示数量
     *
     * @param key key
     * @return count
     */
    private static int getTopicCount(@KeyboardType int type, @NonNull String key) {
        KeyboardEntity keyboardEntity = GreenDaoManager.queryKeyboard(type, key);
        if (keyboardEntity == null) return 5;
        return keyboardEntity.getTopicCount();
    }

    public static void updateTopicCountV1(@NonNull String key, int topicCount) {
        updateTopicCount(KeyboardType.V1, key, topicCount);
    }

    public static void updateTopicCountV2(@NonNull String key, int topicCount) {
        updateTopicCount(KeyboardType.V2, key, topicCount);
    }

    /**
     * 更新每屏显示数量
     *
     * @param key        key
     * @param topicCount count
     */
    private static void updateTopicCount(@KeyboardType int type, @NonNull String key, int topicCount) {
        KeyboardEntity keyboardEntity = GreenDaoManager.queryKeyboard(type, key);
        if (keyboardEntity == null) {
            keyboardEntity = new KeyboardEntity();
            keyboardEntity.setMarkingGroupId(key);
        }
        keyboardEntity.setTopicCount(topicCount);
        GreenDaoManager.updateKeyboard(type, keyboardEntity);
    }

    /**
     * 解答题打分底部分数 ,更多
     *
     * @return {@link List}
     */
    public static List<String> getAnswerScorePortMoreList(int type, double maxCount, boolean showDouble) {
        List<String> scoreList = new ArrayList<>();
        switch (type) {
            case 0:
                double a = maxCount < 20 ? maxCount : showDouble ? 19.5 : 19;
                for (double i = 0; i <= a; i++) {
                    scoreList.add(String.valueOf((int) i));
                    if (showDouble && i < a)
                        scoreList.add(String.valueOf(i + 0.5));
                }
                break;
            case 1:
                double b = maxCount < 40 ? maxCount : showDouble ? 39.5 : 39;
                for (double i = 20; i <= b; i++) {
                    scoreList.add(String.valueOf((int) i));
                    if (showDouble && i < b)
                        scoreList.add(String.valueOf(i + 0.5));
                }
                break;
            case 2:
                for (double i = 40; i <= maxCount; i++) {
                    scoreList.add(String.valueOf((int) i));
                    if (showDouble && i < maxCount)
                        scoreList.add(String.valueOf(i + 0.5));
                }
                break;
        }
        return scoreList;
    }

    /**
     * 打分
     * 0.5
     *
     * @param max 最大分
     * @return {@link List}
     */
    public static List<KeyboardScoreEntity> getKeyboardList(double max) {
        List<KeyboardScoreEntity> list = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            list.add(new KeyboardScoreEntity(String.valueOf(i), false));
            if (i < max) {
                list.add(new KeyboardScoreEntity(String.valueOf(i + 0.5), false));
            }
        }
        return list;
    }
}
