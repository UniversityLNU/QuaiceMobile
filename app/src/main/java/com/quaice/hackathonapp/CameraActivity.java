package com.quaice.hackathonapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
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
    private PostService postService;
    private static final int SELECT_PHOTO = 2;
    private String imageBitmap;
    private String selectedImage;
    private static final int PERMISSIONS_REQUEST_CODE = 10;

    private static final String[] PERMISSIONS_REQUIRED = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        postService = new PostService(this);
        Button captureButton = findViewById(R.id.button_capture);
        Button selectButton = findViewById(R.id.button_select);

        // Listener for capture button
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermissions(CameraActivity.this, PERMISSIONS_REQUIRED)) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                } else {
                    ActivityCompat.requestPermissions(CameraActivity.this, PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE);
                }
            }
        });

        // Listener for select button
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermissions(CameraActivity.this, PERMISSIONS_REQUIRED)) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                } else {
                    ActivityCompat.requestPermissions(CameraActivity.this, PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE);
                }
            }
        });
    }
    // Check if all permissions are granted
    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions not granted.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // Permissions granted. You can now open the camera or the gallery.
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap =postService.encodeToBase64((Bitmap) extras.get("data"));
        } else if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                selectedImage = postService.encodeToBase64(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri));
            } catch (IOException e) {
                Log.e("CameraActivity", "Error while creating bitmap from selected image", e);
            }
        }
    }

}