package com.android.library.net.entity.template;

import java.util.List;


/**
 * @author y
 */
public class TopicDetailEntity {

    private String answer;
    private String difficultyDegreeName;
    private String title;
    private String topicId;
    private List<MethodsBean> methods;
    private List<OptionsBean> options;
    private int typeId;
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDifficultyDegreeName() {
        return difficultyDegreeName;
    }

    public void setDifficultyDegreeName(String difficultyDegreeName) {
        this.difficultyDegreeName = difficultyDegreeName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }


    public List<MethodsBean> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodsBean> methods) {
        this.methods = methods;
    }

    public List<OptionsBean> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsBean> options) {
        this.options = options;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public static class MethodsBean {

        private String methodName;

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }
    }

    public static class OptionsBean {

        private String content;
        private boolean correct;
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }
    }
}
