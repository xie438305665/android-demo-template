package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.library.bridge.BridgeConstant;
import com.android.library.bridge.RoutePath;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.ActivityUtils;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.bridge.util.StatusBarUtil;
import com.android.library.bridge.util.UIUtils;
import com.android.library.upgrade.Upgrade;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.MainPresenterImpl;
import com.demo.template.mvp.view.MainView;
import com.demo.template.ui.adapter.MainAdapter;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * @author xcl
 * 主界面
 */
@Route(path = RoutePath.MAIN)
public class MainActivity extends MVPActivity<MainPresenterImpl, Object> implements MainView {

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
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
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
    protected MainPresenterImpl initPresenter() {
        return new MainPresenterImpl(this);
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
