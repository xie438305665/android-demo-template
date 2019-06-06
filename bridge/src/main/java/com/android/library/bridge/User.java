package com.android.library.bridge;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.library.bridge.util.AESUtils;
import com.android.library.bridge.util.ActivityUtils;
import com.android.library.bridge.util.GsonUtils;
import com.android.library.bridge.util.SpUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.net.NetRequest;
import com.android.library.net.entity.UserEntity;

import javax.crypto.Cipher;

/**
 * @author xcl
 */
public class User {

    public static UserEntity getUserInfo() {
        try {
            UserEntity userEntity = GsonUtils.jsonToObj(AESUtils.des(SpUtils.getString(BridgeConstant.USER), BridgeConstant.MASTER_ID, Cipher.DECRYPT_MODE), UserEntity.class);
            if (userEntity == null) {
                quit();
            } else {
                return userEntity;
            }
        } catch (Exception e) {
            quit();
        }
        return new UserEntity();
    }

    public static void quit() {
        SpUtils.clearAll();
        ActivityUtils.removeAllActivity();
        NetRequest.single().cancelAll();
        GreenDaoManager.deleteKeyboard();
        ARouter.getInstance().build(RoutePath.LOGIN).navigation();
    }

    public static void reset() {
        ActivityUtils.removeAllActivity();
        NetRequest.single().cancelAll();
        ARouter.getInstance().build(RoutePath.MAIN).navigation();
    }
}
