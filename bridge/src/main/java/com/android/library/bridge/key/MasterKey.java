package com.android.library.bridge.key;

/**
 * @author xcl
 * @create 2019/4/1
 */
public @interface MasterKey {
    /**
     * 压缩缓存目录
     */
    String IMAGE_DISK_CACHE = "luban_disk_cache";
    /**
     * 升级apk名称
     */
    String UPGRADE_NAME = "demo_template";
    /**
     * 主app包名
     */
    String MASTER_ID = "com.demo.template";
    /**
     * 升级检测Id
     */
    String UPGRADE_ID = "后台规定的ID";
    /**
     * token
     */
    String TOKEN = "TOKEN";
    /**
     * 用户信息
     */
    String USER = "USER";
    /**
     * 阅卷时是否打开自动提交
     */
    String AUTOMATIC_SUBMIT = "automaticSubmit";
}
