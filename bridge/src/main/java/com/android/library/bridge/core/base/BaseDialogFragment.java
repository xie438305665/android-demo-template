package com.android.library.bridge.core.base;

import androidx.fragment.app.DialogFragment;
import android.view.View;

/**
 * @author xcl
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected abstract int getLayoutId();

    protected abstract int themeResId();

    protected abstract boolean getCancelable();

    public View getRootView(int id) {
        return View.inflate(getActivity(), id, null);
    }
}
