package com.quaice.hackathonapp.dto.Post;

public class PostRequest {
    private String postId;
    public PostRequest(String postId) {
        this.postId = postId;
    }
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
}
