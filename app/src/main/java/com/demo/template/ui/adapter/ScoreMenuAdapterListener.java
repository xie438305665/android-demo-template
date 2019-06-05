package com.demo.template.ui.adapter;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.library.bridge.core.listener.SimpleXMultiAdapterListener;
import com.demo.template.R;
import com.demo.template.annotation.DrawerItemViewType;
import com.demo.template.annotation.DrawerPosition;
import com.demo.template.listener.read.OnScoreInitOwnListener;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.utils.DrawableUtils;
import com.rm.rmswitch.RMSwitch;
import com.xadapter.holder.XViewHolder;
import com.xadapter.listener.OnItemClickListener;

/**
 * 阅卷侧边栏Adapter
 */
public class ScoreMenuAdapterListener extends SimpleXMultiAdapterListener<ScoreMultiEntity> implements OnItemClickListener<ScoreMultiEntity> {

    private OnScoreInitOwnListener initOwnListener;

    public ScoreMenuAdapterListener(@NonNull OnScoreInitOwnListener initOwnListener) {
        this.initOwnListener = initOwnListener;
    }

    @Override
    public int multiLayoutId(int viewItemType) {
        switch (viewItemType) {
            case DrawerItemViewType.HEADER:
                return R.layout.grade_item_score_menu_header;
            default:
                return R.layout.grade_item_score_menu;
        }
    }

    @Override
    public void onXMultiBind(XViewHolder holder, ScoreMultiEntity scoreMultiEntity, int itemViewType, int position) {
        if (itemViewType != DrawerItemViewType.ITEM) {
            holder.setTextView(R.id.score_menu_title, scoreMultiEntity.title);
            holder.getView(R.id.score_menu_close).setOnClickListener(v -> initOwnListener.onCloseDrawer());
            return;
        }
        holder.itemView.setEnabled(position < DrawerPosition.LAND);
        holder.setTextView(R.id.score_menu_title, scoreMultiEntity.title);
        holder.getView(R.id.score_menu_forward).setVisibility(position < DrawerPosition.LAND ? View.VISIBLE : View.GONE);
        DrawableUtils.setScoreItemIcon(holder.getTextView(R.id.score_menu_title), scoreMultiEntity.icon);
        RMSwitch mSwitch = holder.getView(R.id.score_menu_switch);
        if (mSwitch.getTag() instanceof RMSwitch.RMSwitchObserver) {
            mSwitch.removeSwitchObserver((RMSwitch.RMSwitchObserver) mSwitch.getTag());
        }
        mSwitch.setVisibility(position > DrawerPosition.KEYBOARD ? View.VISIBLE : View.GONE);
        mSwitch.setSelected(false);
        if (position == DrawerPosition.LAND) {
            mSwitch.setChecked(initOwnListener.isHasLand());
        }
        if (position == DrawerPosition.UN_SCORE) {
            mSwitch.setChecked(initOwnListener.isShowUnScore());
        }
        if (position == DrawerPosition.AUTOMATIC_SUBMIT) {
            mSwitch.setChecked(initOwnListener.isAutomaticSubmit());
        }
        RMSwitch.RMSwitchObserver rmSwitchObserver = (switchView, isChecked) -> {
            if (position == DrawerPosition.LAND) {
                initOwnListener.setHasLand(isChecked);
                ((Activity) holder.itemView.getContext()).setRequestedOrientation(initOwnListener.isHasLand() ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else if (position == DrawerPosition.UN_SCORE) {
                initOwnListener.setShowUnScore(isChecked);
                initOwnListener.unAnswerNetWork();
            } else if (position == DrawerPosition.AUTOMATIC_SUBMIT) {
                initOwnListener.setAutomaticSubmit(isChecked);
            }
        };
        mSwitch.addSwitchObserver(rmSwitchObserver);
        mSwitch.setTag(rmSwitchObserver);
    }

    @Override
    public void onItemClick(View view, int position, ScoreMultiEntity info) {
        initOwnListener.onDrawerItemClick(view, position, info);
        initOwnListener.onCloseDrawer();
    }
}
