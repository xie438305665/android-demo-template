package com.demo.template.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.ActivityUtils;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.bridge.util.StatusBarUtil;
import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.MainTemplatePresenterImpl;
import com.demo.template.mvp.view.MainTemplateView;
import com.demo.template.ui.adapter.MainTemplateAdapter;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * @author xcl
 */
@Route(path = RoutePath.MAIN)
public class MainTemplateActivity extends MVPActivity<MainTemplatePresenterImpl, Object> implements MainTemplateView {

    @BindView(R.id.main_view_pager)
    CustomViewPager mainViewPager;
    @BindView(R.id.main_navigation)
    BottomNavigationView mainNavigation;

    @BindColor(R.color.colorWhite)
    int colorWhite;

    private long exitTime = 0;
//    private Upgrade upgrade;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mainNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        MainTemplateAdapter adapter = new MainTemplateAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(adapter);
        mainViewPager.setOffscreenPageLimit(adapter.getCount());
        mainNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_read:
                    mainViewPager.setCurrentItem(0, false);
                    break;
                case R.id.navigation_user:
                    mainViewPager.setCurrentItem(1, false);
                    break;
                default:
                    break;
            }
            StatusBarUtil.darkStyle(this, colorWhite);
            return true;
        });
//        upgrade = Upgrade
//                .getInstance(this)
//                .setType(Upgrade.TYPE_FIRST)
//                .setIcon(R.mipmap.ic_launcher)
//                .setApkName(BridgeConstant.UPGRADE_NAME)
//                .setSoftwareId(BridgeConstant.UPGRADE_ID)
//                .setPackageName(BridgeConstant.MASTER_ID)
//                .start();
    }

    @Override
    protected MainTemplatePresenterImpl initPresenter() {
        return new MainTemplatePresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean showToolBar() {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UIUtils.show(UIUtils.getString(R.string.exit_hints));
                exitTime = System.currentTimeMillis();
            } else {
                ImageLoaderUtils.clearMemory(this);
                ActivityUtils.removeAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (upgrade != null) {
//            upgrade.onDestroy();
//        }
    }
}
