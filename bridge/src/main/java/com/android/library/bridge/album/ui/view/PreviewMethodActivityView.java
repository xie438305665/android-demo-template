package com.android.library.bridge.album.ui.view;

import com.android.library.bridge.album.entity.AlbumEntity;

import java.util.ArrayList;

public interface PreviewMethodActivityView {
    void initBundle();

    void initViewPager(ArrayList<AlbumEntity> albumEntityList);

    void setTitles(int page, int imageSize);

    void setCount(int count, int size);

    void checkBoxClick();

    void isRefreshAlbumUI(boolean isRefresh, boolean isFinish);
}
