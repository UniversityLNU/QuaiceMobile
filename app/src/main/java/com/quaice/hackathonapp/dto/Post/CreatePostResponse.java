package com.quaice.hackathonapp.dto.Post;

import java.util.List;

public class CreatePostResponse {
    private String postId;
    private List<String> photoLinks;

    public CreatePostResponse(String postId, List<String> photoLinks) {
        this.postId = postId;
        this.photoLinks = photoLinks;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<String> getPhotoLinks() {
        return photoLinks;
    }

    public void setPhotoLinks(List<String> photoLinks) {
        this.photoLinks = photoLinks;
    }
}
