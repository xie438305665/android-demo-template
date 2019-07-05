package com.demo.template.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.library.bridge.User;
import com.android.library.bridge.core.MVPFragment;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.util.MDialogUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.UserEntity;
import com.demo.template.R;
import com.demo.template.entity.MultiTemplateEntity;
import com.demo.template.ui.activity.AboutTemplateActivity;
import com.demo.template.ui.activity.ChangePWTemplateActivity;
import com.demo.template.ui.adapter.UserMultiAdapterListener;
import com.xadapter.adapter.multi.MultiAdapter;
import com.xadapter.adapter.multi.MultiCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;

import static com.demo.template.ui.adapter.UserMultiAdapterListener.RECYCLER_FOOTER;
import static com.demo.template.ui.adapter.UserMultiAdapterListener.RECYCLER_HEADER;
import static com.demo.template.ui.adapter.UserMultiAdapterListener.RECYCLER_LINE;

/**
 * @author xcl
 */
public class UserTemplateFragment extends MVPFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindDrawable(R.drawable.ic_close)
    Drawable passwordDrawable;
    @BindDrawable(R.drawable.ic_close)
    Drawable abutDrawable;

    @BindString(R.string.change_password)
    String changePassword;
    @BindString(R.string.about_application)
    String abutApplication;

    private static final int CHANGE_PW = 1;
    private static final int ABOUT_APPLICATION = 3;
    private static final int LOGOUT = 4;
    private UserEntity userEntity;

    public static UserTemplateFragment newInstance() {
        return new UserTemplateFragment();
    }

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        userEntity = User.getUserInfo();
    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        MultiAdapter<MultiTemplateEntity> multiAdapter = new MultiAdapter<>(getData())
                .setXMultiAdapterListener(new UserMultiAdapterListener(mActivity, userEntity))
                .setOnItemClickListener((view, position, info) -> {
                    switch (position) {
                        case CHANGE_PW:
                            UIUtils.startActivity(ChangePWTemplateActivity.class);
                            break;
                        case ABOUT_APPLICATION:
                            UIUtils.startActivity(AboutTemplateActivity.class);
                            break;
                        case LOGOUT:
                            MDialogUtils.logout(mActivity, (dialog, which) -> User.quit());
                            break;
                        default:
                            break;
                    }
                });
        mRecyclerView.setAdapter(multiAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    private List<MultiTemplateEntity> getData() {
        List<MultiTemplateEntity> data = new ArrayList<>();
        data.add(new MultiTemplateEntity(passwordDrawable, changePassword, RECYCLER_HEADER, 0));
        data.add(new MultiTemplateEntity(passwordDrawable, changePassword, MultiCallBack.TYPE_ITEM, 1));
        data.add(new MultiTemplateEntity(null, null, RECYCLER_LINE, 2));
        data.add(new MultiTemplateEntity(abutDrawable, abutApplication, MultiCallBack.TYPE_ITEM, 3));
        data.add(new MultiTemplateEntity(null, null, RECYCLER_FOOTER, 4));
        return data;
    }
}
