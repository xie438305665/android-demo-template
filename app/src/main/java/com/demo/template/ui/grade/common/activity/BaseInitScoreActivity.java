package com.demo.template.ui.grade.common.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.bridge.util.SpUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.android.library.widget.custom.CustomViewPager;
import com.android.library.widget.detector.KeyboardStatusDetector;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.entity.ScoreZipV2Entity;
import com.demo.template.listener.action.DefaultLayoutAction;
import com.demo.template.listener.read.IChangeViewStateListener;
import com.demo.template.listener.read.OnScoreInitOwnListener;
import com.demo.template.ui.adapter.ScoreMenuAdapterListener;
import com.demo.template.widget.ScoreHeaderLayout;
import com.demo.template.widget.ScoreKeyboardLayout;
import com.demo.template.widget.ScoreToolbarLayout;
import com.demo.template.widget.answer.AnswerLandToolbarLayout;
import com.demo.template.widget.keyboard.AnswerLandKeyboardLayout;
import com.xadapter.adapter.multi.MultiAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author y
 * @create 2019/4/4
 */
public abstract class BaseInitScoreActivity<P extends IPresenter> extends MVPActivity<P, ScoreZipV2Entity>
        implements KeyboardStatusDetector.KeyboardListener, OnScoreInitOwnListener {

    @BindView(R.id.score_view_pager)
    public CustomViewPager viewPager;
    @BindView(R.id.drawer_menu)
    public RecyclerView drawerMenu;
    @BindView(R.id.score_drawer)
    public DrawerLayout drawer;
    @BindView(R.id.score_toolbar_view)
    public ScoreToolbarLayout toolbar;
    @BindView(R.id.score_header_view)
    public ScoreHeaderLayout scoreHeader;
    @BindView(R.id.score_keyboard)
    public ScoreKeyboardLayout keyboard;
    @BindView(R.id.answer_land_keyboard)
    public AnswerLandKeyboardLayout answerLandKeyboard;
    @BindView(R.id.answer_land_toolbar_view)
    public AnswerLandToolbarLayout answerLandToolbar;

    @Nullable
    private ScoreParameterEntity parameter;
    //所有布局容器监听事件集合
    private ArrayList<IChangeViewStateListener> changeViewStateListener;
    private KeyboardStatusDetector keyboardStatusDetector;
    private MultiAdapter<ScoreMultiEntity> menuAdapter;
    private boolean showUnScore;
    private boolean hasLand;

    /**
     * 横竖屏发生改变的时候回调  不会进入onCreate方法
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onChangedViewState(ReadUIMode.LANDSCAPE);
        boolean landscape = UIUtils.isLandscape(this);
        if (landscape) {
            UIUtils.hideStatusBar(this);
        } else {
            UIUtils.showStatusBar(this);
        }
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        parameter = bundle.getParcelable(BundleKey.GRADE_SCORE_ENTITY);
        if (UIUtils.checkNull(parameter)) {
            finish();
            return;
        }
        changeViewStateListener = new ArrayList<>();
        changeViewStateListener.add(answerLandToolbar);
        changeViewStateListener.add(answerLandKeyboard);
        changeViewStateListener.add(keyboard);
        changeViewStateListener.add(scoreHeader);
        changeViewStateListener.add(toolbar);
        keyboardStatusDetector = new KeyboardStatusDetector(this);
        drawer.getViewTreeObserver().addOnGlobalLayoutListener(keyboardStatusDetector);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ScoreMenuAdapterListener scoreMenuAdapterListener = new ScoreMenuAdapterListener(this);
        menuAdapter = new MultiAdapter<>(new ArrayList<ScoreMultiEntity>()).setXMultiAdapterListener(scoreMenuAdapterListener).setOnItemClickListener(scoreMenuAdapterListener);
        drawerMenu.setLayoutManager(new LinearLayoutManager(this));
        drawerMenu.setAdapter(menuAdapter);
        //给布局容器绑定监听容器,根据不同的条件,改变布局
        DefaultLayoutAction defaultLayoutAction = getDefaultLayoutAction();
        keyboard.register(defaultLayoutAction, defaultLayoutAction);
        toolbar.setOnScoreToolbarAction(defaultLayoutAction);
        answerLandKeyboard.setOnAnswerLandKeyboardAction(defaultLayoutAction);
        answerLandToolbar.setOnAnswerLandToolbarAction(defaultLayoutAction);
        scoreHeader.setOnScoreHeaderAction(defaultLayoutAction);
        // 当题型是 解答题 且 不是 问题卷的时候  设置横屏
        if (getScoreType() == TopicType.ANSWER && !isProblem()) {
            setHasLand(true);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        //所有布局容器初始化
        onChangedViewState(ReadUIMode.INIT);
    }

    @Override
    protected boolean showToolBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_activity_score;
    }

    @Override
    public View getKeyboardView() {
        return drawer;
    }

    /**
     * 根据uiMode改变布局
     *
     * @param uiMode {@link ReadUIMode}
     */
    @Override
    public void onChangedViewState(@ReadUIMode int uiMode) {
        for (IChangeViewStateListener iChangeViewStateListener : changeViewStateListener) {
            if (!UIUtils.checkNull(iChangeViewStateListener)) {
                iChangeViewStateListener.onViewChangeState(uiMode, getScoreType(), isProblem(), isMixing(), isArbitration());
            }
        }
    }

    /**
     * 根据试题改变侧边栏布局
     *
     * @param type 试题type {@link TopicType}
     */
    @Override
    public void onChangeDrawerMode(@TopicType int type) {
        menuAdapter.clearAll();
        menuAdapter.addAll(ScoreMultiEntity.getScoreDrawerMenu(type, isProblem(), isArbitration()));
    }

    @Override
    public void onOpenDrawer() {
        UIUtils.forceOffKeyboard(this);
        drawer.openDrawer(GravityCompat.END);
    }

    /**
     * 关闭侧边栏
     */
    @Override
    public void onCloseDrawer() {
        drawer.closeDrawer(GravityCompat.END);
    }

    @Override
    protected void onDestroy() {
        drawer.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardStatusDetector);
        super.onDestroy();
        changeViewStateListener.clear();
        ImageLoaderUtils.clearMemory(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            onCloseDrawer();
        } else {
            onRefreshPrevUI();
            super.onBackPressed();
        }
    }

    /**
     * @return 是否显示未阅卷
     */
    @Override
    public boolean isShowUnScore() {
        return showUnScore;
    }

    @Override
    public void setShowUnScore(boolean showUnScore) {
        this.showUnScore = showUnScore;
        menuAdapter.notifyDataSetChanged();
    }

    /**
     * @return 是否横屏
     */
    @Override
    public boolean isHasLand() {
        return hasLand;
    }

    @Override
    public void setHasLand(boolean hasLand) {
        this.hasLand = hasLand;
    }

    /**
     * @return 是否自动提交
     */
    @Override
    public boolean isAutomaticSubmit() {
        return SpUtils.getBoolean(BundleKey.AUTOMATIC_SUBMIT, false);
    }

    @Override
    public void setAutomaticSubmit(boolean automaticSubmit) {
        SpUtils.setBoolean(BundleKey.AUTOMATIC_SUBMIT, automaticSubmit);
    }

    @Override
    public void onRefreshPrevUI() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(BundleKey.GRADE_SCORE_REFRESH, true);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }

    @Nullable
    @Override
    public ScoreParameterEntity getParameter() {
        return parameter;
    }

    @TopicType
    @Override
    public int getScoreType() {
        if (UIUtils.checkNotNull(parameter)) {
            return parameter.getScoreType();
        }
        return TopicType.ANSWER;
    }

    /**
     * @return 是否问题卷
     */
    @Override
    public boolean isProblem() {
        if (UIUtils.checkNotNull(parameter)) {
            return parameter.isProblem();
        }
        return false;
    }

    /**
     * @return 是否混合阅卷
     */
    @Override
    public boolean isMixing() {
        if (UIUtils.checkNotNull(parameter)) {
            return parameter.isMixing();
        }
        return false;
    }

    /**
     * @return 是否仲裁
     */
    @Override
    public boolean isArbitration() {
        if (UIUtils.checkNotNull(parameter)) {
            return parameter.isArbitration();
        }
        return false;
    }

    /**
     * @return {@link DefaultLayoutAction}
     */
    @NonNull
    protected abstract DefaultLayoutAction getDefaultLayoutAction();
}
