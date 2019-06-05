package com.demo.template.utils;

import android.app.Activity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author y
 * @create 2018/12/5
 */
@SuppressWarnings("ConstantConditions")
public class MDialogUtils {

    public static void annotationEmpty(Activity activity,
                                       MaterialDialog.SingleButtonCallback positiveCallback) {
        new MaterialDialog
                .Builder(activity)
                .cancelable(false)
                .title(R.string.grade_annotation_empty_title)
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .onPositive(positiveCallback)
                .show();
    }

    public static void openNextScore(Activity activity, boolean hasSubmit, MaterialDialog.SingleButtonCallback callback) {
        if (!hasSubmit) {
            callback.onClick(null, null);
            return;
        }
        new MaterialDialog
                .Builder(activity)
                .cancelable(false)
                .title(hasSubmit ? R.string.grade_score_tips_next_title : R.string.grade_dg_open_next_score_title)
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .onPositive(callback)
                .show();
    }

    public static void openPrevScore(Activity activity, boolean hasSubmit, MaterialDialog.SingleButtonCallback callback) {
        if (!hasSubmit) {
            callback.onClick(null, null);
            return;
        }
        new MaterialDialog
                .Builder(activity)
                .cancelable(false)
                .title(hasSubmit ? R.string.grade_score_tips_prev_title : R.string.grade_dg_open_prev_score_title)
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .onPositive(callback)
                .show();
    }

    public static void editTextInput(Activity activity, MaterialDialog.InputCallback inputCallback) {
        MaterialDialog dialog = new MaterialDialog
                .Builder(activity)
                .cancelable(false)
                .title(R.string.grade_edit_image_text_title)
                .alwaysCallInputCallback()
                .negativeText(R.string.cancel)
                .input(UIUtils.getString(R.string.grade_edit_image_text_hint), null, (dialog1, input) -> {
                    dialog1.getActionButton(DialogAction.POSITIVE).setEnabled(input.length() <= 10 && input.length() != 0);
                    if (input.length() > 10) {
                        if (dialog1.getInputEditText() != null) {
                            dialog1.getInputEditText().setError(UIUtils.getString(R.string.grade_score_annotation_error));
                        }
                    }
                    dialog1.getActionButton(DialogAction.POSITIVE).setOnClickListener(v -> {
                        inputCallback.onInput(dialog1, input);
                        dialog1.dismiss();
                    });
                })
                .show();
        if (dialog.getInputEditText() == null) return;
        dialog.getInputEditText().setFilters(new InputFilter[]{new InputFilter() {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_,.?!:;…~_\\-\"\"/@*+'()<>{}/[/]()<>{}\\[\\]=%&$|/♀♂#¥£¢€\"^` ，。？！：；……～“”、“（）”、（——）‘’＠‘·’＆＊＃《》￥《〈〉》〈＄〉［］￡［］｛｝｛｝￠【】【】％〖〗〖〗／〔〕〔〕＼『』『』＾「」「」｜﹁﹂｀．]");

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (!matcher.find()) {
                    return null;
                } else {
                    UIUtils.show(R.string.grade_annotation_unknown);
                    return "";
                }

            }
        }});
    }

    public static void scoreAnnotation(Activity activity,
                                       RecyclerView.Adapter adapter,
                                       MaterialDialog.SingleButtonCallback positiveCallback,
                                       MaterialDialog.SingleButtonCallback neutralCallback) {
        new MaterialDialog
                .Builder(activity)
                .title(R.string.grade_score_annotation)
                .cancelable(false)
                .adapter(adapter, new LinearLayoutManager(activity))
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .onPositive(positiveCallback)
                .neutralText(R.string.grade_grade_setting)
                .onNeutral(neutralCallback)
                .show().getRecyclerView().addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
    }

    public static void fillScoreNum(Activity activity, int pageSize, MaterialDialog.InputCallback inputCallback) {
        new MaterialDialog
                .Builder(activity)
                .title(R.string.grade_fill_score_dialog_title)
                .alwaysCallInputCallback()
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(UIUtils.getString(R.string.grade_search_dialog_hint), String.valueOf(pageSize), (dialog, input) -> {
                    int i = NumberUtils.parseInt(input.toString());
                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(i > 0 && i < 21);
                    if (i <= 0 || i >= 21) {
                        if (dialog.getInputEditText() != null) {
                            dialog.getInputEditText().setError(UIUtils.getString(R.string.grade_fill_score_dialog_title_tips));
                        }
                    }
                    dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(v -> {
                        inputCallback.onInput(dialog, input);
                        dialog.dismiss();
                    });
                }).show();
    }


    public static void annotationDelete(Activity activity, MaterialDialog.SingleButtonCallback singleButtonCallback) {
        new MaterialDialog
                .Builder(activity)
                .content(R.string.grade_annotation_dialog_delete_content)
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .onPositive(singleButtonCallback)
                .show();
    }

    public static void annotationAdd(Activity activity, MaterialDialog.InputCallback inputCallback) {
        MaterialDialog dialog = new MaterialDialog
                .Builder(activity)
                .title(R.string.grade_annotation_dialog_title)
                .alwaysCallInputCallback()
                .input(UIUtils.getString(R.string.grade_annotation_add_dialog_hint), null, (dialog1, input) -> {
                    dialog1.getActionButton(DialogAction.POSITIVE).setEnabled(input.length() <= 10 && input.length() != 0);
                    if (input.length() > 10) {
                        if (dialog1.getInputEditText() != null) {
                            dialog1.getInputEditText().setError(UIUtils.getString(R.string.grade_score_annotation_error));
                        }
                    }
                    dialog1.getActionButton(DialogAction.POSITIVE).setOnClickListener(v -> {
                        inputCallback.onInput(dialog1, input);
                        dialog1.dismiss();
                    });
                })
                .show();
        if (dialog.getInputEditText() == null) return;
        dialog.getInputEditText().setFilters(new InputFilter[]{new InputFilter() {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_,.?!:;…~_\\-\"\"/@*+'()<>{}/[/]()<>{}\\[\\]=%&$|/♀♂#¥£¢€\"^` ，。？！：；……～“”、“（）”、（——）‘’＠‘·’＆＊＃《》￥《〈〉》〈＄〉［］￡［］｛｝｛｝￠【】【】％〖〗〖〗／〔〕〔〕＼『』『』＾「」「」｜﹁﹂｀．]");

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (!matcher.find()) {
                    return null;
                } else {
                    UIUtils.show(R.string.grade_annotation_unknown);
                    return "";
                }

            }
        }});
    }
}
