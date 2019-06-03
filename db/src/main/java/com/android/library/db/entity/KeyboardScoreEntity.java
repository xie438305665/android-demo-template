package com.android.library.db.entity;

import java.util.Objects;

/**
 * @author xcl
 */
public class KeyboardScoreEntity {
    private String score;
    private boolean select;

    public KeyboardScoreEntity(String score, boolean select) {
        this.score = score;
        this.select = select;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyboardScoreEntity that = (KeyboardScoreEntity) o;
        return select == that.select &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {

        return Objects.hash(score, select);
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
