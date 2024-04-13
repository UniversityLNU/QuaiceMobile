package com.quaice.hackathonapp.dto.Post;

import java.util.List;

public class CreatePostRequest {

    private String userId;
    private String description;
    private List<String> attachedPhotos;

    public CreatePostRequest(String userId, String description, List<String> attachedPhotos) {
        this.userId = userId;
        this.description = description;
        this.attachedPhotos = attachedPhotos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAttachedPhotos() {
        return attachedPhotos;
    }

    public void setAttachedPhotos(List<String> attachedPhotos) {
        this.attachedPhotos = attachedPhotos;
    }
}
