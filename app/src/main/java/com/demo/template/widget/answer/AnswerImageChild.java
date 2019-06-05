package com.demo.template.widget.answer;

import android.content.Context;
import android.util.AttributeSet;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.image.edit.EditImageView;

/**
 * @author y
 * @create 2019-04-28
 */
public class AnswerImageChild extends EditImageView {

    public AnswerImageChild(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public AnswerImageChild(Context context) {
        super(context);
    }

    @Override
    protected void onReady() {
        super.onReady();
        if (getSWidth() > getSHeight() * 2) {
            if (!UIUtils.isLandscape(getContext())) {
                UIUtils.show(R.string.grade_edit_width_tips);
            }
        }
    }
}
