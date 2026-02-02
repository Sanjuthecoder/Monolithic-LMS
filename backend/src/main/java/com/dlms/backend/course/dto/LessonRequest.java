package com.dlms.backend.course.dto;

import java.util.List;

public class LessonRequest {
    private String title;
    private String type;
    private String mediaId;

    public LessonRequest() {
    }

    public LessonRequest(String title, String type, String mediaId) {
        this.title = title;
        this.type = type;
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
