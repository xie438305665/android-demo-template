package com.demo.template.ui.template.oldx.rx;

import com.android.library.bridge.util.UIUtils;
import com.android.library.net.NetException;
import com.android.library.net.entity.BaseEntity;
import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.ScoreEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;
import com.demo.template.R;

import java.util.ArrayList;

import io.reactivex.functions.Function3;

/**
 * @author y
 * @create 2019/3/22
 */
@Deprecated
public class OldProblemFun3 implements Function3<BaseEntity<GroupQuotaEntity>, BaseEntity<ScoreEntity>, BaseEntity<TopicAnswerEntity>, ScoreZipEntity> {

    @Override
    public ScoreZipEntity apply(BaseEntity<GroupQuotaEntity> groupQuotaEntityBaseEntity, BaseEntity<ScoreEntity> data, BaseEntity<TopicAnswerEntity> topicAnswerEntityBaseEntity) {
        ScoreZipEntity scoreZipEntity = new ScoreZipEntity();
        if (data.getData() == null || data.getData().getStudentPaperTopic() == null) {
            throw new NetException(UIUtils.getString(R.string.grade_null_data));
        }
        ArrayList<ScoreEntity> arrayList = new ArrayList<>();
        arrayList.add(data.getData());
        scoreZipEntity.setGroupQuota(groupQuotaEntityBaseEntity.getData());
        scoreZipEntity.setStudentFirst(arrayList);
        scoreZipEntity.setTopicAnswerEntity(topicAnswerEntityBaseEntity.getData());
        return scoreZipEntity;
    }
}
