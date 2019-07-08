package com.android.library.bridge;

import android.content.Intent;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;

/**
 * @author xcl
 */
public class FragmentARouter {

    public static void start(Fragment fragment, int requestCode, Postcard postcard) {
        LogisticsCenter.completion(postcard);
        Intent intent = new Intent(fragment.getActivity(), postcard.getDestination());
        intent.putExtras(postcard.getExtras());
        fragment.startActivityForResult(intent, requestCode);
    }
}
