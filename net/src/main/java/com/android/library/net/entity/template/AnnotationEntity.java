package com.android.library.net.entity.template;

public class AnnotationEntity {

    private int labelId;
    private String markingLabel;
    private String teacherId;
    private boolean isChecked;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getMarkingLabel() {
        return markingLabel;
    }

    public void setMarkingLabel(String markingLabel) {
        this.markingLabel = markingLabel;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
