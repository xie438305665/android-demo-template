package com.demo.template.entity;

import android.graphics.drawable.Drawable;

import com.xadapter.adapter.multi.MultiCallBack;

public class MultiTemplateEntity implements MultiCallBack {

    public Drawable icon;
    public String title;
    private int itemType;
    private int itemPosition;

    /**
     * @param icon         {@link android.graphics.drawable.Drawable}
     * @param title        {@link Integer}
     * @param itemType     {@link Integer}
     * @param itemPosition {@link Integer}
     */
    public MultiTemplateEntity(Drawable icon, String title, int itemType, int itemPosition) {
        this.icon = icon;
        this.title = title;
        this.itemType = itemType;
        this.itemPosition = itemPosition;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public int getPosition() {
        return itemPosition;
    }
}
