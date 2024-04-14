package com.quaice.hackathonapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.quaice.hackathonapp.dto.Post.CreatePostResponse;
import com.quaice.hackathonapp.service.PostService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final int REQUEST_IMAGE_CAPTURE = 102;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private static final int REQUEST_SELECT_IMAGE = 100;

    private Bitmap imageBitmap, screenshotBitmap;
    private ImageView imageView, screenShotImage;

    private CardView publish;
    private TextInputEditText edittext;

    private PostService postService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        edittext = findViewById(R.id.editText);
        publish = findViewById(R.id.publish);

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> bitmapList = new ArrayList<>();
                bitmapList.add(postService.encodeToBase64(imageBitmap)); bitmapList.add(postService.encodeToBase64(screenshotBitmap));
                uploadUserPost(getSharedPreferences("AunthPref", Context.MODE_PRIVATE).getString("userID", ""),
                        edittext.getText().toString(), bitmapList);
            }
        });

        postService = new PostService(this);

        imageView = findViewById(R.id.big_img);
        screenShotImage = findViewById(R.id.screenShotImage);

        screenShotImage.setOnClickListener(v -> openGallery());

        if (allPermissionsGranted()) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                openCamera();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                imageBitmap = (Bitmap) extras.get("data");
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageBitmap(imageBitmap);
            }
        }

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    screenshotBitmap = getBitmapFromUri(selectedImageUri);
                    screenShotImage.setImageBitmap(screenshotBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmap;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_SELECT_IMAGE);
    }

    public void uploadUserPost(String userId, String description, List<String> attachedPhotos){
        postService.uploadUserPost(userId, description, attachedPhotos, new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                if (response.isSuccessful()) {
                    CreatePostResponse createPostResponse = response.body();
                    if (createPostResponse != null) {
                        // Handle successful post upload here
                        Toast.makeText(CreatePostActivity.this, "Post successfully published", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    //Toast.makeText(Cre.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //Log.d("Error", t.getMessage());
            }
        });
    }
}