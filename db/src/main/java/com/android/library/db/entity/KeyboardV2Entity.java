package com.android.library.db.entity;


import com.android.library.db.converter.KeyboardConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

/**
 * @author xcl
 */
@Entity
public class KeyboardV2Entity {
    @Id
    private Long id;
    @NotNull
    private String topicId;
    private double topicScore;
    private int topicCount;
    private boolean hasLand;
    private boolean hasAutomaticSubmit;
    private boolean hasShowUnAnswerScore;
    private boolean hasDecimal;
    private boolean hasReverse;
    @Convert(columnType = String.class, converter = KeyboardConverter.class)
    private List<KeyboardScoreEntity> scoreList;
    @Convert(columnType = String.class, converter = KeyboardConverter.class)
    private List<KeyboardScoreEntity> selectList;
    @Convert(columnType = String.class, converter = KeyboardConverter.class)
    private List<KeyboardScoreEntity> defaultScoreList;
    @Generated(hash = 1035777361)
    public KeyboardV2Entity(Long id, @NotNull String topicId, double topicScore,
            int topicCount, boolean hasLand, boolean hasAutomaticSubmit,
            boolean hasShowUnAnswerScore, boolean hasDecimal, boolean hasReverse,
            List<KeyboardScoreEntity> scoreList,
            List<KeyboardScoreEntity> selectList,
            List<KeyboardScoreEntity> defaultScoreList) {
        this.id = id;
        this.topicId = topicId;
        this.topicScore = topicScore;
        this.topicCount = topicCount;
        this.hasLand = hasLand;
        this.hasAutomaticSubmit = hasAutomaticSubmit;
        this.hasShowUnAnswerScore = hasShowUnAnswerScore;
        this.hasDecimal = hasDecimal;
        this.hasReverse = hasReverse;
        this.scoreList = scoreList;
        this.selectList = selectList;
        this.defaultScoreList = defaultScoreList;
    }
    @Generated(hash = 530337018)
    public KeyboardV2Entity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTopicId() {
        return this.topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public double getTopicScore() {
        return this.topicScore;
    }
    public void setTopicScore(double topicScore) {
        this.topicScore = topicScore;
    }
    public int getTopicCount() {
        return this.topicCount;
    }
    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }
    public boolean getHasLand() {
        return this.hasLand;
    }
    public void setHasLand(boolean hasLand) {
        this.hasLand = hasLand;
    }
    public boolean getHasAutomaticSubmit() {
        return this.hasAutomaticSubmit;
    }
    public void setHasAutomaticSubmit(boolean hasAutomaticSubmit) {
        this.hasAutomaticSubmit = hasAutomaticSubmit;
    }
    public boolean getHasShowUnAnswerScore() {
        return this.hasShowUnAnswerScore;
    }
    public void setHasShowUnAnswerScore(boolean hasShowUnAnswerScore) {
        this.hasShowUnAnswerScore = hasShowUnAnswerScore;
    }
    public boolean getHasDecimal() {
        return this.hasDecimal;
    }
    public void setHasDecimal(boolean hasDecimal) {
        this.hasDecimal = hasDecimal;
    }
    public boolean getHasReverse() {
        return this.hasReverse;
    }
    public void setHasReverse(boolean hasReverse) {
        this.hasReverse = hasReverse;
    }
    public List<KeyboardScoreEntity> getScoreList() {
        return this.scoreList;
    }
    public void setScoreList(List<KeyboardScoreEntity> scoreList) {
        this.scoreList = scoreList;
    }
    public List<KeyboardScoreEntity> getSelectList() {
        return this.selectList;
    }
    public void setSelectList(List<KeyboardScoreEntity> selectList) {
        this.selectList = selectList;
    }
    public List<KeyboardScoreEntity> getDefaultScoreList() {
        return this.defaultScoreList;
    }
    public void setDefaultScoreList(List<KeyboardScoreEntity> defaultScoreList) {
        this.defaultScoreList = defaultScoreList;
    }

}
