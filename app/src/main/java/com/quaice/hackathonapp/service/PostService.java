package com.quaice.hackathonapp.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.api.PostApi;
import com.quaice.hackathonapp.dto.Post.AllPostResponse;
import com.quaice.hackathonapp.dto.Post.CreatePostRequest;
import com.quaice.hackathonapp.dto.Post.CreatePostResponse;
import com.quaice.hackathonapp.dto.Post.PostRequest;
import com.quaice.hackathonapp.dto.Post.PostResponse;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostService {
    private PostApi postApi;

    public PostService(Context context) {
        String url = context.getResources().getString(R.string.api_url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postApi = retrofit.create(PostApi.class);
    }

    public void uploadUserPost(String userId, String description, List<String> attachedPhotos, Callback<CreatePostResponse> callback) {
        long epochTime = System.currentTimeMillis();
        CreatePostRequest post = new CreatePostRequest(userId,"CreatorFullName", epochTime, description,"fundrising" , attachedPhotos);
        Call<CreatePostResponse> call = postApi.uploadUserPost(post);
        call.enqueue(callback);
    }

    public void getPostById(String postId, Callback<PostResponse> callback) {
        PostRequest postRequest = new PostRequest(postId);
        Call<PostResponse> call = postApi.getPostById(postRequest);
        call.enqueue(callback);
    }

    public void getAllPosts(Callback<AllPostResponse> callback) {
        Call<AllPostResponse> call = postApi.getAllPosts();
        call.enqueue(callback);
    }
    public String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public Bitmap decodeBase64(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return Bitmap.createBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
    }
}