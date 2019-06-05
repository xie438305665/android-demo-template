package com.demo.template.mvp.view;

import com.android.library.bridge.core.base.IView;
import com.android.library.net.entity.template.AnnotationEntity;

import java.util.List;

/**
 * 批注
 */
public interface NoteView extends IView<List<AnnotationEntity>> {
    /**
     * 删除成功
     *
     * @param labelId labelId
     */
    void deleteSuccess(int labelId);

    /**
     * 添加成功
     */
    void addSuccess();
}
