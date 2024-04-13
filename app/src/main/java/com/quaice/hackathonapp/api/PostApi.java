package com.quaice.hackathonapp.api;

import com.quaice.hackathonapp.dto.Post.AllPostResponse;
import com.quaice.hackathonapp.dto.Post.CreatePostRequest;
import com.quaice.hackathonapp.dto.Post.CreatePostResponse;
import com.quaice.hackathonapp.dto.Post.PostRequest;
import com.quaice.hackathonapp.dto.Post.PostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApi {
    @POST("api/Post")
    Call<CreatePostResponse> uploadUserPost(@Body CreatePostRequest post);

    @GET("api/Post/GetPostById/{postId}")
    Call<PostResponse> getPostById(@Body PostRequest postRequest);

    @GET("api/Post/GetAllPosts")
    Call<AllPostResponse> getAllPosts();
}
