package com.demo.template.ui.grade.common.adapter;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.android.library.bridge.util.BitmapUtils;
import com.android.library.bridge.util.UIUtils;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.demo.template.R;
import com.demo.template.ui.grade.newx.activity.ScoreActivity;
import com.demo.template.ui.grade.newx.fragment.AnswerScoreFragment;
import com.demo.template.ui.grade.oldx.activity.OldScoreActivity;
import com.demo.template.ui.grade.oldx.fragment.OldAnswerScoreFragment;
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

/**
 * {@link android.support.v4.app.FragmentStatePagerAdapter}
 */
public class AnswerScoreAdapter extends PagerAdapter {

    @Nullable
    private EditImageView layout;
    @Nullable
    private ImageViewState imageViewState;

    private OnEditImageListener editImageListener;
    private SubsamplingScaleImageView.OnStateChangedListener onStateChangedListener;

    public AnswerScoreAdapter(@NonNull OnEditImageListener editImageListener, @NonNull SubsamplingScaleImageView.OnStateChangedListener onStateChangedListener) {
        this.editImageListener = editImageListener;
        this.onStateChangedListener = onStateChangedListener;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        EditImageView editImageView = new EditImageView(container.getContext()) {
            @Override
            protected void onReady() {
                super.onReady();
                if (getSWidth() > getSHeight() * 2) {
                    if (!UIUtils.isLandscape(getContext())) {
                        UIUtils.show(R.string.grade_edit_width_tips);
                    }
                }
            }
        };
        EditImageConfig editImageConfig = new EditImageConfig();
        editImageView
                .setOnEditImageInitializeListener(new SimpleOnEditImageInitializeListener())
                .setEditImageConfig(editImageConfig)
                .setOnEditImageListener(editImageListener)
                .setMaxScale(10f);
        editImageView.setOnStateChangedListener(onStateChangedListener);
        container.addView(editImageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return editImageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((EditImageView) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (layout != object) {
            layout = (EditImageView) object;
            if (editImageListener instanceof AnswerScoreFragment) {
                ScoreActivity scoreActivity = ((AnswerScoreFragment) editImageListener).getScoreActivity();
                if (UIUtils.checkNotNull(scoreActivity) && !UIUtils.checkNull(scoreActivity.getAnswerEntity())) {
                    displayFile(scoreActivity.getAnswerEntity().getFile());
                }
            } else if (editImageListener instanceof OldAnswerScoreFragment) {
                OldScoreActivity scoreActivity = ((OldAnswerScoreFragment) editImageListener).getScoreActivity();
                if (UIUtils.checkNotNull(scoreActivity) && !UIUtils.checkNull(scoreActivity.getAnswerEntity())) {
                    displayFile(scoreActivity.getAnswerEntity().getStudentPaperTopic().getFile());
                }
            }

        }
    }

    public void setImageViewState(@Nullable ImageViewState imageViewState) {
        this.imageViewState = imageViewState;
    }

    public float getMinScale() {
        if (UIUtils.checkNull(layout)) return -1;
        return layout.getMinScale();
    }

    @Nullable
    public ImageViewState getCurrentViewState() {
        if (UIUtils.checkNull(layout)) {
            return null;
        }
        return layout.getState();
//        return new ImageViewState(layout.getScale(), new PointF(), layout.getOrientation());
    }

    @Nullable
    public ImageViewState state() {
//        if (imageViewState == null) {
//            return new ImageViewState(5, new PointF(), layout.getOrientation());
//        }
        return imageViewState;
    }


    public void displayFile(@Nullable File file) {
        if (!UIUtils.checkNull(layout) && !UIUtils.checkNull(file)) {
            ImageViewState state = state();
            if (state == null) {
                float scale;
                int widthPixels = layout.getContext().getResources().getDisplayMetrics().widthPixels;
                float heightPixels = layout.getContext().getResources().getDisplayMetrics().heightPixels - UIUtils.getDimension(R.dimen.dp_70);
                int[] imageWidthHeight = BitmapUtils.getImageWidthHeight(file.getAbsolutePath());
                int width = imageWidthHeight[0];
                int height = imageWidthHeight[1];
                if (width < height) {
                    scale = (float) widthPixels / width;
                } else {
                    scale = heightPixels / height;
                }
                state = new ImageViewState(scale, new PointF(), layout.getOrientation());
            }
            layout.setImage(ImageSource.uri(file.getAbsolutePath()).tilingDisabled(), state);
        }
    }

    public void quiteEdit() {
        text();
        if (!UIUtils.checkNull(layout)) {
            layout.setEditType(EditType.NONE);
        }
    }

    public void text() {
        if (!UIUtils.checkNull(layout)) {
            layout.saveText().setEditTextType(EditTextType.NONE);
        }
    }

    public void clearImage() {
        if (!UIUtils.checkNull(layout)) {
            layout.clearImage();
        }
    }

    public void lastImage() {
        if (!UIUtils.checkNull(layout)) {
            layout.lastImage();
        }
    }

    public boolean cacheEmpty() {
        if (UIUtils.checkNull(layout)) {
            return true;
        }
        return layout.getCacheArrayList().isEmpty();
    }

    public void removeCache() {
        if (UIUtils.checkNull(layout)) {
            return;
        }
        LinkedList<EditImageCache> cacheArrayList = layout.getCacheArrayList();
        for (EditImageCache editImageCache : cacheArrayList) {
            editImageCache.reset();
        }
        layout.getCacheArrayList().clear();
    }

    public void setText(@NonNull String text) {
        text();
        if (!UIUtils.checkNull(layout)) {
            DisplayMetrics displayMetrics = layout.getContext().getResources().getDisplayMetrics();
            int widthPixels = UIUtils.isLandscape(layout.getContext()) ? displayMetrics.heightPixels : displayMetrics.widthPixels;
            PointF pointF = new PointF((float) (widthPixels / 2), (float) (widthPixels / 2));
            EditImageText editImageText = new EditImageText(pointF, 1, 0, text, layout.getTextPaint().getColor(), layout.getTextPaint().getTextSize());
            layout.setText(editImageText).setEditType(EditType.TEXT);
        }
    }

    public void paint() {
        text();
        if (!UIUtils.checkNull(layout)) {
            layout.setEditType(EditType.PAINT);
        }
    }

    @Nullable
    public Bitmap getBitmap() {
        if (UIUtils.checkNull(layout)) {
            return null;
        }
        return layout.getDrawBitmap();
    }

    @Nullable
    public Bitmap getNewBitmap() {
        if (UIUtils.checkNull(layout)) {
            return null;
        }
        return layout.getNewBitmap();
    }

    public boolean noScroll() {
        if (UIUtils.checkNull(layout)) {
            return false;
        }
        return layout.getEditType() == EditType.PAINT || layout.getEditType() == EditType.ERASER || !layout.getEditTextType().equals(EditTextType.NONE);
    }
}
