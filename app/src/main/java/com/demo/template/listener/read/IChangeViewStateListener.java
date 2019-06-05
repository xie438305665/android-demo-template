package com.demo.template.listener.read;

import com.android.library.bridge.annotation.QuestionType;
import com.demo.template.annotation.ReadUIMode;

/**
 * @author y
 * @create 2019/4/1
 */
public interface IChangeViewStateListener {
    /**
     * @param uiMode    {@link ReadUIMode}
     * @param type      {@link QuestionType}
     * @param arbitrate 是否为仲裁
     * @param mixing    是否是混合阅卷
     * @param problem   是否是问题卷
     */
    void onViewChangeState(@ReadUIMode int uiMode, @QuestionType int type, boolean problem, boolean mixing, boolean arbitrate);
}
