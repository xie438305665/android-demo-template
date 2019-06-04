package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.ViewUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.db.annotation.KeyboardType;
import com.android.library.db.entity.KeyboardEntity;
import com.android.library.db.entity.KeyboardScoreEntity;
import com.demo.template.R;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.rm.rmswitch.RMSwitch;
import com.xadapter.adapter.XRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xcl
 * 分数键盘设置
 */
public class ScoreKeyboardActivity extends MVPActivity {

    @BindView(R.id.keyboard_switch_reverse)
    RMSwitch mSwitchReverse;
    @BindView(R.id.keyboard_switch_join)
    RMSwitch mSwitchJoin;
    @BindView(R.id.recyclerView)
    RecyclerView scoreRecyclerView;
    @BindView(R.id.sortRecyclerView)
    RecyclerView selectRecyclerView;
    @BindView(R.id.btn_keyboard_again_reverse)
    AppCompatButton againReverse;
    @BindView(R.id.btn_keyboard_save)
    AppCompatButton againSave;
    @BindView(R.id.join_root_view)
    LinearLayout joinRootView;
    private XRecyclerViewAdapter<KeyboardScoreEntity> scoreAdapter;
    private XRecyclerViewAdapter<KeyboardScoreEntity> selectAdapter;
    private KeyboardEntity keyboardEntity;

    private String dbKey;
    private int keyboardType;
    private boolean hasDecimal;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        if (bundle != null) {
            dbKey = bundle.getString(KeyboardType.KEY, "");
            keyboardType = bundle.getInt(KeyboardType.TYPE, KeyboardType.V1);
        }
        if (TextUtils.isEmpty(dbKey)) {
            finish();
            return;
        }
        mCustomToolBar.setCenterTvText(R.string.fill_score_keyboard_title);
        keyboardEntity = GreenDaoManager.queryKeyboard(keyboardType, dbKey);
        if (keyboardEntity == null) {
            finish();
            return;
        }
        //是否存在小数
        hasDecimal = NumberUtils.endWithZero(keyboardEntity.getTopicScore());
        initScoreAdapter();
        initSelectAdapter();
        joinRootView.setVisibility(hasDecimal ? View.INVISIBLE : View.VISIBLE);
        mSwitchReverse.setChecked(keyboardEntity.getHasReverse());
        mSwitchJoin.setChecked(keyboardEntity.getHasDecimal());
        mSwitchReverse.addSwitchObserver((switchView, isChecked) -> switchReverse());
        mSwitchJoin.addSwitchObserver((switchView, isChecked) -> switchJoin(isChecked, mSwitchReverse.isChecked()));
        if (hasChangeKeyboard()) {
            showButton();
        }
    }

    private void showButton() {
        if (againSave.getVisibility() == View.VISIBLE || againReverse.getVisibility() == View.VISIBLE) {
            return;
        }
        ViewUtils.visibleView(againSave, againReverse);
    }

    private void hideButton() {
        if (againSave.getVisibility() == View.GONE || againReverse.getVisibility() == View.GONE) {
            return;
        }
        ViewUtils.goneView(againSave, againReverse);
    }

    /**
     * 分数倒序
     */
    private void switchReverse() {
        showButton();
        Collections.reverse(scoreAdapter.getData());
        scoreAdapter.notifyDataSetChanged();
    }

    /**
     * @param isJoinChecked    是否加入0.5分
     * @param isReverseChecked 是否倒序排列
     */
    private void switchJoin(boolean isJoinChecked, boolean isReverseChecked) {
        showButton();
        List<KeyboardScoreEntity> scoreAdapterData = scoreAdapter.getData();
        if (isReverseChecked) {
            Collections.reverse(scoreAdapterData);
        }
        if (!isJoinChecked) {
            Iterator<KeyboardScoreEntity> iterator = scoreAdapterData.iterator();
            while (iterator.hasNext()) {
                KeyboardScoreEntity next = iterator.next();
                if (next.getScore().contains(".")) {
                    iterator.remove();
                } else {
                    next.setSelect(false);
                }
            }
        } else {
            scoreAdapter.removeAll();
            scoreAdapter.addAllData(ScoreKeyboardFactory.getKeyboardList(keyboardEntity.getTopicScore()));
        }
        if (isReverseChecked) {
            Collections.reverse(scoreAdapterData);
        }
        selectAdapter.removeAll();
        scoreAdapter.notifyDataSetChanged();
    }

    private void initSelectAdapter() {
        selectAdapter = (XRecyclerViewAdapter<KeyboardScoreEntity>) new XRecyclerViewAdapter<KeyboardScoreEntity>()
                .initXData(keyboardEntity.getSelectList() == null ? new ArrayList<>() : keyboardEntity.getSelectList())
                .setLayoutId(R.layout.item_key_board_sort)
                .onXBind((holder, position, entity) -> holder.setTextView(R.id.item_key_board_tv, entity.getScore()));
        selectRecyclerView.setHasFixedSize(true);
        selectRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 6));
        selectRecyclerView.setAdapter(selectAdapter);
    }

    private void initScoreAdapter() {
        scoreAdapter = (XRecyclerViewAdapter<KeyboardScoreEntity>) new XRecyclerViewAdapter<KeyboardScoreEntity>()
                .initXData(newScoreData())
                .setLayoutId(R.layout.item_key_board)
                .onXBind((holder, position, entity) -> {
                    holder.setTextView(R.id.item_key_board_tv, entity.getScore());
                    holder.getTextView(R.id.item_key_board_tv).setSelected(entity.isSelect());
                })
                .setOnItemClickListener((view, position, info) -> {
                    showButton();
                    if (selectAdapter.getData().contains(info)) {
                        selectAdapter.getData().remove(info);
                        selectAdapter.notifyDataSetChanged();
                        info.setSelect(false);
                    } else {
                        selectAdapter.addData(new KeyboardScoreEntity(info.getScore(), true));
                        info.setSelect(true);
                    }
                    scoreAdapter.notifyDataSetChanged();
                });
        scoreRecyclerView.setHasFixedSize(true);
        scoreRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 6));
        scoreRecyclerView.setAdapter(scoreAdapter);
    }

    @Override
    protected MVPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score_keyboard;
    }

    @Override
    public void onLeftClick() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @OnClick({R.id.btn_keyboard_again_reverse, R.id.btn_keyboard_save})
    public void onViewClicked(View view) {
        int i = view.getId();
        //重新排序,初始化所有设置值
        if (i == R.id.btn_keyboard_again_reverse) {
            hideButton();
            mSwitchJoin.setChecked(hasDecimal);
            mSwitchReverse.setChecked(false);
            selectAdapter.removeAll();
            scoreAdapter.removeAll();
            scoreAdapter.addAllData(newDefaultScoreData());
            keyboardEntity.setHasDecimal(false);
            keyboardEntity.setHasReverse(hasDecimal);
            keyboardEntity.setScoreList(newDefaultScoreData());
            keyboardEntity.setSelectList(null);
            GreenDaoManager.putOrReplaceKeyboard(keyboardType, keyboardEntity);

        } else if (i == R.id.btn_keyboard_save) {
            keyboardEntity.setHasDecimal(mSwitchJoin.isChecked());
            keyboardEntity.setHasReverse(mSwitchReverse.isChecked());
            keyboardEntity.setScoreList(scoreAdapter.getData());
            keyboardEntity.setSelectList(selectAdapter.getData());
            GreenDaoManager.putOrReplaceKeyboard(keyboardType, keyboardEntity);
            setResult(RESULT_OK);
            finish();

        }
    }


    /**
     * @return 倒序排列是否选中
     */
    private boolean hasChangeKeyboard() {
        if (mSwitchReverse.isChecked()) {
            return true;
        }
        List<KeyboardScoreEntity> scoreList = keyboardEntity.getScoreList();
        for (KeyboardScoreEntity keyboardScoreEntity : scoreList) {
            if (keyboardScoreEntity.isSelect()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 键盘分数最开始的默认值
     */
    private List<KeyboardScoreEntity> newDefaultScoreData() {
        List<KeyboardScoreEntity> defaultScoreList = keyboardEntity.getDefaultScoreList();
        List<KeyboardScoreEntity> data = new ArrayList<>();
        for (KeyboardScoreEntity keyboardScoreEntity : defaultScoreList) {
            data.add(new KeyboardScoreEntity(keyboardScoreEntity.getScore(), keyboardScoreEntity.isSelect()));
        }
        return data;
    }

    /**
     * @return 已经调整过的键盘分数值
     */
    private List<KeyboardScoreEntity> newScoreData() {
        List<KeyboardScoreEntity> defaultScoreList = keyboardEntity.getScoreList();
        List<KeyboardScoreEntity> data = new ArrayList<>();
        for (KeyboardScoreEntity keyboardScoreEntity : defaultScoreList) {
            data.add(new KeyboardScoreEntity(keyboardScoreEntity.getScore(), keyboardScoreEntity.isSelect()));
        }
        return data;
    }
}
