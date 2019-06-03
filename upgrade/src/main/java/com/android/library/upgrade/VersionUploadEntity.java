package com.android.library.upgrade;

import com.google.gson.annotations.SerializedName;

/**
 * @author xcl
 */

public class VersionUploadEntity {

    @SerializedName("success")
    private int success;
    @SerializedName("message")
    private String message;
    @SerializedName("object")
    private ObjectBean object;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }


    public static class ObjectBean {

        @SerializedName("id")
        private String id;
        @SerializedName("description")
        private String description;
        @SerializedName("software_id")
        private String softwareId;
        @SerializedName("forceUpdate")
        private String forceUpdate;
        @SerializedName("file_url")
        private String fileUrl;
        @SerializedName("file_size")
        private String fileSize;
        @SerializedName("version_number")
        private String versionNumber;
        @SerializedName("change_log")
        private String changeLog;
        @SerializedName("releaseTime")
        private String release_time;
        @SerializedName("release_date_string")
        private String releaseDateString;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSoftwareId() {
            return softwareId;
        }

        public void setSoftwareId(String softwareId) {
            this.softwareId = softwareId;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getChangeLog() {
            return changeLog;
        }

        public void setChangeLog(String changeLog) {
            this.changeLog = changeLog;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }

        public String getReleaseDateString() {
            return releaseDateString;
        }

        public void setReleaseDateString(String releaseDateString) {
            this.releaseDateString = releaseDateString;
        }

        public String getForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(String forceUpdate) {
            this.forceUpdate = forceUpdate;
        }
    }
}
