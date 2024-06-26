package com.quaice.hackathonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.dto.Post.PostRequest;
import com.quaice.hackathonapp.dto.Post.PostResponse;
import com.quaice.hackathonapp.service.PostService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<PostResponse> mData;
    private Context context;

    private PostService postService;

    public PostAdapter(List<PostResponse> data, Context context) {
        this.mData = data;
        this.context = context;
        postService = new PostService(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.post_layout, parent, false);

        // Return a new holder instance
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Get the data model based on position
        PostResponse item = mData.get(position);

        holder.creator_nickname.setText(item.getCreatorFullName());
        holder.post_desc.setText(item.getDescription());

        Date date = new Date(item.getDateOfCreation());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String formattedDate = sdf.format(date);

        holder.date_of_pub.setText(formattedDate);

        holder.big_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.small_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.big_img.setImageBitmap(postService.decodeBase64(item.getPhotoLinks().get(0)));
        holder.small_img.setImageBitmap(postService.decodeBase64(item.getPhotoLinks().get(1)));
        // Set item views based on your views and data model
        //holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView creator_nickname, post_desc, date_of_pub;
        private ImageView big_img, small_img;


        public MyViewHolder(View itemView) {
            super(itemView);
            creator_nickname = itemView.findViewById(R.id.creator_nickname);
            post_desc = itemView.findViewById(R.id.post_desc);
            big_img = itemView.findViewById(R.id.big_img);
            small_img = itemView.findViewById(R.id.small_img);
            date_of_pub = itemView.findViewById(R.id.date_of_pub);

        }
    }
}
