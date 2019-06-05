package com.demo.template.widget.answer;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.android.library.bridge.util.BitmapUtils;
import com.android.library.bridge.util.UIUtils;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.demo.template.BuildConfig;
import com.demo.template.R;
import com.demo.template.widget.ScoreLayout;
import com.image.edit.EditImageConfig;
import com.image.edit.EditImageView;
import com.image.edit.EditType;
import com.image.edit.OnEditImageListener;
import com.image.edit.cache.EditImageCache;
import com.image.edit.cache.EditImageText;
import com.image.edit.helper.EditTextType;
import com.image.edit.simple.SimpleOnEditImageInitializeListener;

import java.io.File;
import java.util.LinkedList;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * @author y
 * @create 2019-04-28
 */
public class AnswerImageLayout extends ScoreLayout {

    @BindView(R.id.grade_answer_image)
    EditImageView editImageView;

    public AnswerImageLayout(Context context) {
        super(context);
    }

    public AnswerImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        EditImageConfig editImageConfig = new EditImageConfig();
        editImageView
                .setOnEditImageInitializeListener(new SimpleOnEditImageInitializeListener())
                .setEditImageConfig(editImageConfig)
                .setMaxScale(10f);
        editImageView.setMinimumScaleType(EditImageView.SCALE_TYPE_START);
        editImageView.setDebug(BuildConfig.DEBUG);
    }

    public void setOnStateChangedListener(@NonNull SubsamplingScaleImageView.OnStateChangedListener onStateChangedListener) {
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.setOnStateChangedListener(onStateChangedListener);
        }
    }

    public void setOnEditImageListener(@NonNull OnEditImageListener onEditImageListener) {
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.setOnEditImageListener(onEditImageListener);
        }
    }

    /**
     * 获取图片编辑时的参数
     * 滑动时赋值,来保证同一套试卷下图片缩放比例一致
     *
     * @return {@link ImageViewState}
     */
    @Nullable
    public ImageViewState getImageViewState() {
        return UIUtils.checkNotNull(editImageView) ? editImageView.getState() : null;
    }

    /**
     * 获取一个试卷图片同等大小的只包含绘制痕迹的透明bitmap的base64
     *
     * @return {@link String}
     */
    @NonNull
    public String getBitmapBase64() {
        return UIUtils.checkNotNull(editImageView) ? BitmapUtils.bitmapToBase64HeaderPng(editImageView.getDrawBitmap()) : "";
    }

    /**
     * 处理阅卷时滑动冲突,如果是画笔或者橡皮擦或者文字时阻止{@link com.android.library.widget.custom.CustomViewPager}滑动
     *
     * @return {@link Boolean} true 阻止滑动
     */
    public boolean noScroll() {
        if (UIUtils.checkNull(editImageView)) {
            return false;
        }
        return editImageView.getEditType() == EditType.PAINT || editImageView.getEditType() == EditType.ERASER || !editImageView.getEditTextType().equals(EditTextType.NONE);
    }

    /**
     * 获取最小缩放比例
     *
     * @return {@link SubsamplingScaleImageView#getMinScale()}
     */
    public float getMinScale() {
        return UIUtils.checkNotNull(editImageView) ? editImageView.getMinScale() : -1;
    }

    /**
     * 添加图片
     *
     * @param file  {@link File}
     * @param state {@link ImageViewState}
     */
    public void displayFile(@Nullable File file, @Nullable ImageViewState state) {
        if (UIUtils.checkNotNull(file) && UIUtils.checkNotNull(editImageView)) {
            editImageView.setImage(ImageSource.uri(file.getAbsolutePath()).tilingDisabled(), state);
        }
    }

    /**
     * 退出编辑状态
     */
    public void quiteEdit() {
        text();
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.setEditType(EditType.NONE);
        }
    }

    /**
     * 保存文字
     */
    public void text() {
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.saveText().setEditTextType(EditTextType.NONE);
        }
    }

    /**
     * 清除所有绘制痕迹
     */
    public void clear() {
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.clearImage();
        }
    }

    /**
     * 回退上一步绘制痕迹
     */
    public void lastImage() {
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.lastImage();
        }
    }

    /**
     * 是否存在绘制痕迹
     *
     * @return false 没有绘制痕迹
     */
    public boolean cacheEmpty() {
        if (UIUtils.checkNull(editImageView)) {
            return false;
        }
        return editImageView.getCacheArrayList().isEmpty();
    }

    /**
     * 清除所有缓存,提交之后需要保留绘制痕迹,但是可以再次绘制
     * 如果提交成功之后图片刷新为新的图片则无需调用这个方法
     * 目前在打分成功之后需要清除缓存,否则回退会回退掉提交之前绘制的痕迹
     */
    public void removeCache() {
        if (UIUtils.checkNotNull(editImageView)) {
            LinkedList<EditImageCache> cacheArrayList = editImageView.getCacheArrayList();
            for (EditImageCache editImageCache : cacheArrayList) {
                editImageCache.reset();
            }
            editImageView.getCacheArrayList().clear();
        }
    }

    /**
     * 设置文字
     *
     * @param text {@link String}
     */
    public void setText(@NonNull String text) {
        text();
        if (!UIUtils.checkNull(editImageView)) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int widthPixels = UIUtils.isLandscape(getContext()) ? displayMetrics.heightPixels : displayMetrics.widthPixels;
            PointF pointF = new PointF((float) (widthPixels / 2), (float) (widthPixels / 2));
            EditImageText editImageText = new EditImageText(pointF, 1, 0, text, editImageView.getTextPaint().getColor(), editImageView.getTextPaint().getTextSize());
            editImageView.setText(editImageText).setEditType(EditType.TEXT);
        }
    }

    /**
     * 设置为画笔
     */
    public void paint() {
        text();
        if (UIUtils.checkNotNull(editImageView)) {
            editImageView.setEditType(EditType.PAINT);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_answer_image;
    }
}
