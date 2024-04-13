package com.quaice.hackathonapp.dto.Post;

import java.util.List;

public class AllPostResponse {
    private List<PostResponse> postList;

    public AllPostResponse(List<PostResponse> postResponses) {
        this.postList = postResponses;
    }
    public List<PostResponse> getPostList() {
        return postList;
    }
    public void setPostList(List<PostResponse> postList) {
        this.postList = postList;
    }
}
