package com.demo.template.entity;

import com.android.library.net.entity.template.NewListEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.android.library.net.entity.template.TopicAnswerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y
 */
public class ScoreZipV2Entity {

    public static ScoreZipV2Entity createProblem(ScoreTaskEntity taskEntity, TopicAnswerEntity topicAnswerEntity) {
        NewListEntity<ScoreTaskEntity> scoreTaskEntities = new NewListEntity<>();
        ArrayList<ScoreTaskEntity> list = new ArrayList<>();
        list.add(taskEntity);
        scoreTaskEntities.setList(list);
        return new ScoreZipV2Entity().setTaskList(scoreTaskEntities).setTopicAnswerEntity(topicAnswerEntity);
    }

    public static ScoreZipV2Entity createProblemPrev(ScoreTaskEntity taskEntity) {
        NewListEntity<ScoreTaskEntity> scoreTaskEntities = new NewListEntity<>();
        ArrayList<ScoreTaskEntity> list = new ArrayList<>();
        list.add(taskEntity);
        scoreTaskEntities.setList(list);
        return new ScoreZipV2Entity().setTaskList(scoreTaskEntities);
    }

    public static ScoreZipV2Entity createProblemNext(ScoreTaskEntity taskEntity) {
        NewListEntity<ScoreTaskEntity> scoreTaskEntities = new NewListEntity<>();
        ArrayList<ScoreTaskEntity> list = new ArrayList<>();
        list.add(taskEntity);
        scoreTaskEntities.setList(list);
        return new ScoreZipV2Entity().setTaskList(scoreTaskEntities);
    }

    public static ScoreZipV2Entity createFirst(NewListEntity<ScoreTaskEntity> taskList, TopicAnswerEntity topicAnswerEntity) {
        return new ScoreZipV2Entity().setTaskList(taskList).setTopicAnswerEntity(topicAnswerEntity);
    }

    public static ScoreZipV2Entity createNext(NewListEntity<ScoreTaskEntity> taskList) {
        return new ScoreZipV2Entity().setTaskList(taskList);
    }

    public static ScoreZipV2Entity createPrev(NewListEntity<ScoreTaskEntity> taskList) {
        return new ScoreZipV2Entity().setTaskList(taskList);
    }

    public static ScoreZipV2Entity createArbitrationFirst(NewListEntity<ScoreTaskEntity> taskList, TopicAnswerEntity topicAnswerEntity) {
        return new ScoreZipV2Entity().setTaskList(taskList).setTopicAnswerEntity(topicAnswerEntity);
    }

    public static ScoreZipV2Entity createArbitrationNext(NewListEntity<ScoreTaskEntity> taskList) {
        return new ScoreZipV2Entity().setTaskList(taskList);
    }

    public static ScoreZipV2Entity createArbitrationPrev(NewListEntity<ScoreTaskEntity> taskList) {
        return new ScoreZipV2Entity().setTaskList(taskList);
    }

    public static ScoreZipV2Entity createUn(List<ScoreTaskEntity> taskList) {
        NewListEntity<ScoreTaskEntity> scoreTaskEntityNewListEntity = new NewListEntity<>();
        scoreTaskEntityNewListEntity.setList(taskList);
        return new ScoreZipV2Entity().setTaskList(scoreTaskEntityNewListEntity);
    }

    @Deprecated
    public static ScoreZipV2Entity createTemp() {
        return new ScoreZipV2Entity();
    }

    private NewListEntity<ScoreTaskEntity> taskList;
    private TopicAnswerEntity topicAnswerEntity;

    private ScoreZipV2Entity() {
    }

    public TopicAnswerEntity getTopicAnswerEntity() {
        return topicAnswerEntity;
    }

    public ScoreZipV2Entity setTopicAnswerEntity(TopicAnswerEntity topicAnswerEntity) {
        this.topicAnswerEntity = topicAnswerEntity;
        return this;
    }

    public NewListEntity<ScoreTaskEntity> getTaskList() {
        return taskList;
    }

    public ScoreZipV2Entity setTaskList(NewListEntity<ScoreTaskEntity> taskList) {
        this.taskList = taskList;
        return this;
    }

}
