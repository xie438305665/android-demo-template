package com.demo.template.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.demo.template.R;

/**
 * @author y
 * @create 2019/3/26
 */
public class ScoreUtils {

    private ScoreUtils() {
    }

    /**
     * 获取当前需要显示的分数
     *
     * @param entity {@link ScoreEntity}
     * @return 需要展示的分数
     */
    @Deprecated
    public static String getCurrentFraction(@Nullable ScoreEntity entity) {
        String temp;
        if (entity == null || entity.getStatus() == 0 || entity.getStudentPaperTopic() == null) {
            return "";
        }
        temp = entity.getStudentPaperTopic().getIsProblem() == 1 && entity.getProblemStatus() != 1 ?
                UIUtils.getString(R.string.grade_unknown) :
                String.valueOf(entity.getStudentPaperTopic().getScoring());
        if (temp.endsWith(".0")) {
            temp = temp.substring(0, temp.length() - 2);
        }
        return temp;
    }

    /**
     * 获取当前需要显示的分数
     *
     * @param entity {@link ScoreEntity}
     * @return 需要展示的分数
     */
    public static String getCurrentFraction(@Nullable ScoreTaskEntity entity) {
        String temp;
        if (entity == null || entity.getStatus() == 0) {
            return "";
        }
        temp = entity.getIsProblem() == 1 && entity.getProblemStatus() != 1 ?
                UIUtils.getString(R.string.grade_unknown) :
                String.valueOf(entity.getScoring());
        if (temp.endsWith(".0")) {
            temp = temp.substring(0, temp.length() - 2);
        }
        return temp;
    }

    /**
     * 检查分数是否正确
     *
     * @param fraction {@link String}
     * @return true 不符合
     */
    public static boolean checkFraction(@NonNull String fraction) {
        return fraction.startsWith(".") || fraction.endsWith(".");
    }

    /**
     * 纠正解答题输入提交分数
     *
     * @param fraction   输入分数
     * @param topicScore 最大分
     * @return 正确的分数
     */
    public static String correctAnswerFraction(String fraction, double topicScore) {
        if (TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown))) {
            return fraction;
        }
        if (fraction.contains(".") && !fraction.endsWith(".") && !TextUtils.equals(fraction.substring(fraction.length() - 2), ".0")) {
            fraction = fraction.replace(fraction.substring(fraction.length() - 2), ".5");
        }
        double parseDouble = NumberUtils.parseDouble(fraction);
        if (parseDouble > topicScore) {
            parseDouble = topicScore;
        }
        return String.valueOf(parseDouble);
    }

    /**
     * 滑动是否需要提交
     *
     * @param answerEntity    {@link ScoreEntity}
     * @param currentFraction {@link String}
     * @param hasCache        {@link Boolean}
     * @return true 不需要提交，false需要提交之后再翻页
     */
    public static boolean scrollHasSubmit(@Nullable ScoreTaskEntity answerEntity, @Nullable String currentFraction, boolean hasCache) {
        if (answerEntity == null || TextUtils.isEmpty(currentFraction)) {
            return true;
        }
        if (!hasCache || answerEntity.getStatus() == 0) {
            return false;
        }
        if (answerEntity.getIsProblem() == 1 && answerEntity.getProblemStatus() == 0 && TextUtils.equals(currentFraction, UIUtils.getString(R.string.grade_unknown))) {
            return true;
        }
        if (answerEntity.getIsProblem() == 1 && answerEntity.getProblemStatus() == 0 && NumberUtils.parseDouble(currentFraction) == 0) {
            return false;
        }
        return NumberUtils.parseDouble(currentFraction) == answerEntity.getScoring();
    }

    /**
     * 滑动是否需要提交
     *
     * @param answerEntity    {@link ScoreEntity}
     * @param currentFraction {@link String}
     * @param hasCache        {@link Boolean}
     * @return true 不需要提交，false需要提交之后再翻页
     */
    @Deprecated
    public static boolean scrollHasSubmit(@Nullable ScoreEntity answerEntity, @Nullable String currentFraction, boolean hasCache) {
        if (answerEntity == null || TextUtils.isEmpty(currentFraction)) {
            return true;
        }
        if (!hasCache || answerEntity.getStatus() == 0) {
            return false;
        }
        if (answerEntity.getStudentPaperTopic().getIsProblem() == 1 && answerEntity.getProblemStatus() == 0 && TextUtils.equals(currentFraction, UIUtils.getString(R.string.grade_unknown))) {
            return true;
        }
        if (answerEntity.getStudentPaperTopic().getIsProblem() == 1 && answerEntity.getProblemStatus() == 0 && NumberUtils.parseDouble(currentFraction) == 0) {
            return false;
        }
        return NumberUtils.parseDouble(currentFraction) == answerEntity.getStudentPaperTopic().getScoring();
    }

}
