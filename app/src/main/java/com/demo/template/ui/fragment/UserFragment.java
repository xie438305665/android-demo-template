package com.demo.template.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.library.bridge.core.MVPFragment;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.UserEntity;
import com.demo.template.R;
import com.demo.template.entity.UserMultiEntity;
import com.demo.template.ui.activity.AboutActivity;
import com.demo.template.ui.activity.ChangePWActivity;
import com.demo.template.ui.adapter.UserAdapterListener;
import com.xadapter.adapter.multi.MultiAdapter;
import com.xadapter.adapter.multi.MultiCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;

import static com.demo.template.ui.adapter.UserAdapterListener.RECYCLER_FOOTER;
import static com.demo.template.ui.adapter.UserAdapterListener.RECYCLER_HEADER;
import static com.demo.template.ui.adapter.UserAdapterListener.RECYCLER_LINE;

/**
 * @author xcl
 */
public class UserFragment extends MVPFragment {
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
    private UserEntity userEntity;

    public static UserFragment newInstance() {
        return new UserFragment();
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
        MultiAdapter<UserMultiEntity> multiAdapter = new MultiAdapter<>(getData())
                .setXMultiAdapterListener(new UserAdapterListener(mActivity, userEntity))
                .setOnItemClickListener((view, position, info) -> {
                    switch (position) {
                        case CHANGE_PW:
                            UIUtils.startActivity(ChangePWActivity.class);
                            break;
                        case ABOUT_APPLICATION:
                            UIUtils.startActivity(AboutActivity.class);
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

    private List<UserMultiEntity> getData() {
        List<UserMultiEntity> data = new ArrayList<>();
        data.add(new UserMultiEntity(passwordDrawable, changePassword, RECYCLER_HEADER, 0));
        data.add(new UserMultiEntity(passwordDrawable, changePassword, MultiCallBack.TYPE_ITEM, 1));
        data.add(new UserMultiEntity(null, null, RECYCLER_LINE, 2));
        data.add(new UserMultiEntity(abutDrawable, abutApplication, MultiCallBack.TYPE_ITEM, 3));
        data.add(new UserMultiEntity(null, null, RECYCLER_FOOTER, 4));
        return data;
    }
}
