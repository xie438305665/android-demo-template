package com.demo.template.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;

import com.android.library.bridge.core.listener.SimpleXMultiAdapterListener;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.bridge.util.TvDrawableUtils;
import com.android.library.net.entity.UserEntity;
import com.demo.template.R;
import com.demo.template.entity.MultiTemplateEntity;
import com.xadapter.adapter.multi.MultiCallBack;
import com.xadapter.holder.XViewHolder;

/**
 * @author xcl
 */
public class UserMultiAdapterListener extends SimpleXMultiAdapterListener<MultiTemplateEntity> {

    public static final int RECYCLER_HEADER = 0;
    public static final int RECYCLER_LINE = 2;
    public static final int RECYCLER_FOOTER = 3;

    private Activity activity;
    private UserEntity userEntity;

    public UserMultiAdapterListener(Activity mActivity, UserEntity userEntity) {
        this.activity = mActivity;
        this.userEntity = userEntity;
    }

    @Override
    public int multiLayoutId(int viewItemType) {
        switch (viewItemType) {
            case RECYCLER_HEADER:
                return R.layout.item_user_header;
            case RECYCLER_FOOTER:
                return R.layout.item_user_footer;
            case RECYCLER_LINE:
                return R.layout.layout_item_decoration_line;
            default:
                return R.layout.item_user_navigation;
        }
    }

    @Override
    public void onXMultiBind(XViewHolder holder, MultiTemplateEntity multiTemplateEntity, int itemViewType, int position) {
        switch (itemViewType) {
            case RECYCLER_HEADER:
                holder.setTextView(R.id.tv_item_user_name, multiTemplateEntity.title);
                ImageLoaderUtils.display(holder.getImageView(R.id.iv_item_header), multiTemplateEntity.icon);
                break;
            case MultiCallBack.TYPE_ITEM:
                holder.itemView.setEnabled(position != 0);
                AppCompatTextView sign = holder.getView(R.id.tv_user_sign);
                AppCompatTextView title = holder.getView(R.id.tv_user_title);
                sign.setText("");
                title.setText(multiTemplateEntity.title);
                TvDrawableUtils.setLeftCompoundDrawables(title, multiTemplateEntity.icon);
                TvDrawableUtils.setLeftCompoundDrawables(sign, R.drawable.ic_arrow_forward_light_detail);
                break;
            default:
                break;
        }
    }
}
