package com.demo.template.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;

import com.demo.template.R;
import com.xadapter.adapter.multi.MultiCallBack;
import com.xadapter.holder.XViewHolder;
import com.demo.template.entity.UserMultiEntity;
import com.android.library.bridge.User;
import com.android.library.bridge.core.listener.SimpleXMultiAdapterListener;
import com.android.library.bridge.util.DrawableUtils;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.bridge.util.MDialogUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.UserEntity;

/**
 * @author xcl
 */
public class UserAdapterListener extends SimpleXMultiAdapterListener<UserMultiEntity> {

    public static final int RECYCLER_HEADER = 0;
    public static final int RECYCLER_LINE = 2;
    public static final int RECYCLER_FOOTER = 3;

    private Activity activity;
    private UserEntity userEntity;

    public UserAdapterListener(Activity mActivity, UserEntity userEntity) {
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
    public void onXMultiBind(XViewHolder holder, UserMultiEntity userMultiEntity, int itemViewType, int position) {
        switch (itemViewType) {
            case RECYCLER_HEADER:
                holder.setTextView(R.id.tv_item_user_name, userMultiEntity.title);
                ImageLoaderUtils.display(holder.getImageView(R.id.iv_item_header), userMultiEntity.icon);
                break;
            case MultiCallBack.TYPE_ITEM:
                holder.itemView.setEnabled(position != 0);
                AppCompatTextView sign = holder.getView(R.id.tv_user_sign);
                AppCompatTextView title = holder.getView(R.id.tv_user_title);
                sign.setText("");
                title.setText(userMultiEntity.title);
                DrawableUtils.setUserItemIcon(title, userMultiEntity.icon);
                DrawableUtils.setUserSignItemIcon(sign, UIUtils.getDrawable(R.drawable.ic_arrow_forward_light_detail));
                break;
            default:
                break;
        }
    }
}
