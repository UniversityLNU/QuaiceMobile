package com.quaice.hackathonapp.dto.Post;

import java.util.List;

public class PostResponse {
    private String postId;
    private String CreatorFullName;
    private String description;
    private Long dateOfCreation;
    private String fundraisingId;

    private List<String> photoLinks;

    public PostResponse(String postId, String CreatorFullName, String description, Long dateOfCreation, String fundraisingId, List<String> photoLinks) {
        this.postId = postId;
        this.CreatorFullName = CreatorFullName;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.fundraisingId = fundraisingId;
        this.photoLinks = photoLinks;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCreatorFullName() {
        return CreatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        CreatorFullName = creatorFullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getFundraisingId() {
        return fundraisingId;
    }

    public void setFundraisingId(String fundraisingId) {
        this.fundraisingId = fundraisingId;
    }

    public List<String> getPhotoLinks() {
        return photoLinks;
    }

    public void setPhotoLinks(List<String> photoLinks) {
        this.photoLinks = photoLinks;
    }
}
