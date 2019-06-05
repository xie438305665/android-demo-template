package com.demo.template.widget;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;

import java.util.Objects;

/**
 * @author y
 * @create 2019/3/28
 */
public class ScoreFractionEditTextLayout extends AppCompatEditText {

    private StateListDrawable emptyDrawable;
    private StateListDrawable zeroDrawable;
    private StateListDrawable unknownDrawable;
    private StateListDrawable selectDrawable;

    public ScoreFractionEditTextLayout(Context context) {
        super(context);
        init();
    }

    public ScoreFractionEditTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScoreFractionEditTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        emptyDrawable = getStateListDrawable(R.drawable.shape_round_primary, R.drawable.shape_round_gray);
        zeroDrawable = getStateListDrawable(R.drawable.shape_round_primary, R.drawable.shape_round_red);
        unknownDrawable = getStateListDrawable(R.drawable.shape_round_primary, R.drawable.shape_round_orange);
        selectDrawable = getStateListDrawable(R.drawable.shape_round_primary, R.drawable.shape_round_primary);
        setFractionText("");
    }

    private StateListDrawable getStateListDrawable(int selectId, int unSelectId) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, UIUtils.getDrawable(selectId));
        stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, UIUtils.getDrawable(unSelectId));
        return stateListDrawable;
    }

    public String getFractionText() {
        return TextUtils.isEmpty(super.getText()) ? "" : Objects.requireNonNull(super.getText()).toString().trim().replaceAll("分", "");
    }

    public void setFractionText(@NonNull String fraction) {
        this.setText(fraction);
        setBackground(null);
        String noSuffix = fraction.replaceAll("分", "");
        if (TextUtils.isEmpty(noSuffix)) {
            setBackground(emptyDrawable);
            setTextColor(UIUtils.getColor(R.color.colorPrimary));
        } else if (TextUtils.equals(noSuffix, "0")) {
            setBackground(zeroDrawable);
            setTextColor(UIUtils.getColor(R.color.colorRed_12));
        } else if (TextUtils.equals(noSuffix, UIUtils.getString(R.string.grade_unknown))) {
            setBackground(unknownDrawable);
            setTextColor(UIUtils.getColor(R.color.colorOrange));
        } else {
            setBackground(selectDrawable);
            setTextColor(UIUtils.getColor(R.color.colorPrimary));
        }
    }
}
