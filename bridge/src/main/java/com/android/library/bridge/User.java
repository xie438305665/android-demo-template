package com.android.library.bridge;

import com.android.library.bridge.util.AESUtils;
import com.android.library.bridge.util.ActivityUtils;
import com.android.library.bridge.util.GsonUtils;
import com.android.library.bridge.util.SpUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.net.NetRequest;
import com.android.library.net.entity.UserEntity;

import javax.crypto.Cipher;

import static com.android.library.bridge.BridgeConstant.LOGIN_URI;
import static com.android.library.bridge.BridgeConstant.MAIN_URI;

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
        GreenDaoManager.deleteAll();
        UIUtils.startActivity(LOGIN_URI);
    }

    public static void reset() {
        ActivityUtils.removeAllActivity();
        NetRequest.single().cancelAll();
        UIUtils.startActivity(MAIN_URI);
    }
}
