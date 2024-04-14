package com.quaice.hackathonapp.dto.Post;

import java.util.List;

public class CreatePostRequest {

    private String userId;
    private String creatorFullName;
    private Long dateOfCreation;
    private String description;
    private String fundraisingId;
    private List<String> attachedPhotos;

    public CreatePostRequest(String userId, String creatorFullName, Long dateOfCreation, String description, String fundraisingId, List<String> attachedPhotos) {
        this.userId = userId;
        this.creatorFullName = creatorFullName;
        this.dateOfCreation = dateOfCreation;
        this.description = description;
        this.fundraisingId = fundraisingId;
        this.attachedPhotos = attachedPhotos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatorFullName() {
        return creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    public Long getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFundraisingId() {
        return fundraisingId;
    }

    public void setFundraisingId(String fundraisingId) {
        this.fundraisingId = fundraisingId;
    }

    public List<String> getAttachedPhotos() {
        return attachedPhotos;
    }

    public void setAttachedPhotos(List<String> attachedPhotos) {
        this.attachedPhotos = attachedPhotos;
    }
}
