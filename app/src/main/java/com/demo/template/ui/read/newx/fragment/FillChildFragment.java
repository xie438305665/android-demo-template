package com.demo.template.ui.read.newx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.library.bridge.core.LazyFragment;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.demo.template.R;
import com.demo.template.utils.HtmlUtils;
import com.demo.template.utils.ScoreUtils;
import com.demo.template.widget.ScoreFractionEditTextLayout;
import com.status.layout.Status;
import com.xadapter.adapter.XRecyclerViewAdapter;
import com.xadapter.holder.XViewHolder;
import com.xadapter.listener.OnXBindListener;

import java.util.List;

import butterknife.BindView;

/**
 * @author y
 */
public class FillChildFragment extends LazyFragment implements OnXBindListener<ScoreTaskEntity> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private XRecyclerViewAdapter<ScoreTaskEntity> mAdapter;
    private boolean mixing;
    private boolean problem;
    private boolean arbitration;

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initActivityCreated() {
        FillScoreFragment parentFragment = (FillScoreFragment) getParentFragment();
        mixing = parentFragment != null && parentFragment.isMixing();
        problem = parentFragment != null && parentFragment.isProblem();
        arbitration = parentFragment != null && parentFragment.isArbitration();
        if (parentFragment == null || parentFragment.getScoreActivity() == null || parentFragment.getScoreActivity().getFillEntity() == null) {
            onChangeRootUI(Status.EMPTY);
            return;
        }
        List<ScoreTaskEntity> fillEntity = parentFragment.getScoreActivity().getFillEntity();
        mAdapter = new XRecyclerViewAdapter<>();
        mAdapter.setLayoutId(R.layout.grade_item_fill_score_detail).onXBind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(UIUtils.getDrawable(R.drawable.grade_shape_fill_score_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);
        updateUI(fillEntity);
    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_fragment_fill_child_score;
    }

    @Override
    public void onXBind(XViewHolder holder, int position, ScoreTaskEntity scoreEntity) {
        TextView textView = holder.getTextView(R.id.fill_score_teacher_name);
        if (arbitration) {
            textView.setText(Html.fromHtml(HtmlUtils.getNameHtml(scoreEntity.getTeacherMarkingScores())));
            ViewUtils.visibleView(textView);
        } else {
            textView.setText("");
            ViewUtils.goneView(textView);
        }

        ImageView imageView = holder.getImageView(R.id.fill_score_iv);
        ImageLoaderUtils.display(imageView, scoreEntity.getFile());
        holder.setTextView(R.id.fill_score_student_name, mixing ? String.valueOf(mAdapter.getData().size() - position) : scoreEntity.getStudentName());
        ScoreFractionEditTextLayout editTextLayout = holder.getView(R.id.fill_score_et);
        editTextLayout.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                scoreEntity.setSelect(false);
                v.setSelected(false);
            } else {
                for (ScoreTaskEntity datum : mAdapter.getData()) {
                    datum.setSelect(false);
                }
                scoreEntity.setSelect(true);
                v.setSelected(true);
            }
        });
        if (scoreEntity.isSelect() && !editTextLayout.hasFocus()) {
            editTextLayout.requestFocus();
        } else if (!scoreEntity.isSelect() && editTextLayout.hasFocus()) {
            editTextLayout.clearFocus();
        }
        editTextLayout.setFractionText(ScoreUtils.getCurrentFraction(scoreEntity));
        editTextLayout.setSelected(scoreEntity.isSelect());
        UIUtils.disableShowSoftInput(editTextLayout);
    }

    public void updateUI(List<ScoreTaskEntity> data) {
        if (data == null || data.isEmpty()) {
            onChangeRootUI(Status.EMPTY);
            return;
        }
        mAdapter.removeAll();
        mAdapter.addAllData(data);
        if (problem) {
            nextProblemSelect();
        } else {
            nextSelect();
        }
    }

    private void nextProblemSelect() {
    }

    private void nextSelect() {
        List<ScoreTaskEntity> data = mAdapter.getData();
        for (ScoreTaskEntity datum : data) {
            if (datum.getStatus() == 0) {
                datum.setSelect(true);
                return;
            }
        }
    }

    public void notifyDataSetChanged() {
    }

    public void refresh() {
        if (problem) {
            nextProblemSelect();
        } else {
            nextSelect();
        }
        mAdapter.notifyDataSetChanged();
    }

    public boolean hasSubmit() {
        List<ScoreTaskEntity> data = mAdapter.getData();
        for (ScoreTaskEntity datum : data) {
            if (datum.getStatus() == 0) {
                return true;
            }
        }
        return false;
    }

    public void smoothScrollToPosition(int position) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.smoothScrollToPosition(position);
    }

    @Nullable
    public List<ScoreTaskEntity> getData() {
        if (mAdapter == null) {
            return null;
        }
        return mAdapter.getData();
    }

    @Nullable
    public ScoreTaskEntity getSubmitEntity() {
        List<ScoreTaskEntity> data = mAdapter.getData();
        for (ScoreTaskEntity datum : data) {
            if (datum.isSelect()) {
                return datum;
            }
        }
        return null;
    }

    public int getSubmitEntityPosition() {
        List<ScoreTaskEntity> data = mAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                return i;
            }
        }
        return -1;
    }
}
