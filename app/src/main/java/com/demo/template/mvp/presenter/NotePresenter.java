package com.demo.template.mvp.presenter;

import android.support.annotation.NonNull;

import com.android.library.bridge.annotation.UIType;
import com.android.library.bridge.core.base.IPresenter;

public interface NotePresenter extends IPresenter {
    void onRequestList(@UIType int flag);

    void onRequestPost(@NonNull String markLabel);

    void onRequestDelete(int labelId);
}
