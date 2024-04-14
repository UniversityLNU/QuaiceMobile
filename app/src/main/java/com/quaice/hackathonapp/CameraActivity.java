package com.quaice.hackathonapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.quaice.hackathonapp.adapters.FundraisingAdapter;
import com.quaice.hackathonapp.adapters.PostAdapter;
import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.dto.Post.AllPostResponse;
import com.quaice.hackathonapp.service.FundraisingService;
import com.quaice.hackathonapp.service.PostService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CameraActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int SELECT_PHOTO = 2;
    private String imageBitmap;
    private String selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Open the camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

        // Open the gallery
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = encodeToBase64( (Bitmap) extras.get("data"));
        } else if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                selectedImage = encodeToBase64( MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri));
            } catch (IOException e) {
                Log.e("CameraActivity", "Error while creating bitmap from selected image", e);
            }
        }
    }
    public String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}