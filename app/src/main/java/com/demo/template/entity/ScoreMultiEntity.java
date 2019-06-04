package com.demo.template.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.library.bridge.annotation.QuestionType;
import com.demo.template.R;
import com.demo.template.annotation.DrawerItemViewType;
import com.demo.template.annotation.DrawerPosition;
import com.xadapter.adapter.multi.MultiCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreMultiEntity implements MultiCallBack, Parcelable {
    public static final Creator<ScoreMultiEntity> CREATOR = new Creator<ScoreMultiEntity>() {
        @Override
        public ScoreMultiEntity createFromParcel(Parcel in) {
            return new ScoreMultiEntity(in);
        }

        @Override
        public ScoreMultiEntity[] newArray(int size) {
            return new ScoreMultiEntity[size];
        }
    };
    public int icon;
    public String title;
    private int itemType;
    private int itemPosition;

    /**
     * @param icon         {@link android.graphics.drawable.Drawable}
     * @param title        {@link Integer}
     * @param itemType     {@link Integer}
     * @param itemPosition {@link Integer}
     */
    public ScoreMultiEntity(int icon, String title, int itemType, int itemPosition) {
        this.icon = icon;
        this.title = title;
        this.itemType = itemType;
        this.itemPosition = itemPosition;
    }

    protected ScoreMultiEntity(Parcel in) {
        icon = in.readInt();
        title = in.readString();
        itemType = in.readInt();
        itemPosition = in.readInt();
    }

    /**
     * @param type        {@link QuestionType#ANSWER} {@link QuestionType#FILL}
     * @param arbitration 是否仲裁
     * @param problem     是否问题卷
     * @return {@link List<ScoreMultiEntity>}
     */
    public static List<ScoreMultiEntity> getScoreDrawerMenu(@QuestionType int type, boolean problem, boolean arbitration) {
        List<ScoreMultiEntity> menuList = new ArrayList<>();
        menuList.add(new ScoreMultiEntity(R.drawable.ic_arrow_forward_light, "设置", DrawerItemViewType.HEADER, DrawerPosition.SETTING));
        menuList.add(new ScoreMultiEntity(R.drawable.ic_score_detail, "试题详情", DrawerItemViewType.ITEM, DrawerPosition.QUESTIONS_DETAIL));
        if (type == QuestionType.FILL && !problem) {
            menuList.add(new ScoreMultiEntity(R.drawable.ic_score_num, "每屏显示数量", DrawerItemViewType.ITEM, DrawerPosition.FILL_NUM));
        }
        menuList.add(new ScoreMultiEntity(R.drawable.ic_score_keybaord_setting, "打分键盘设置", DrawerItemViewType.ITEM, DrawerPosition.KEYBOARD));
        if (type == QuestionType.ANSWER && !problem) {
            menuList.add(new ScoreMultiEntity(R.drawable.ic_land, "横屏", DrawerItemViewType.ITEM, DrawerPosition.LAND));
        }
        if (!problem && !arbitration) {
            menuList.add(new ScoreMultiEntity(R.drawable.ic_un_score, "只显示未阅卷", DrawerItemViewType.ITEM, DrawerPosition.UN_SCORE));
        }
        if (!problem) {
            menuList.add(new ScoreMultiEntity(R.drawable.ic_score_automatic_submit, "自动提交", DrawerItemViewType.ITEM, DrawerPosition.AUTOMATIC_SUBMIT));
        }
        return menuList;
    }

    @Override
    public int getPosition() {
        return itemPosition;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(title);
        dest.writeInt(itemType);
        dest.writeInt(itemPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreMultiEntity that = (ScoreMultiEntity) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, title, itemType, itemPosition);
    }
}
