package com.quaice.hackathonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.dto.Post.PostResponse;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<PostResponse> mData;
    private Context context;

    public PostAdapter(List<PostResponse> data, Context context) {
        this.mData = data;
        this.context = context;
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
        //String item = mData.get(position);

        // Set item views based on your views and data model
        //holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView creator_nickname, post_desc, winsteek;
        private ImageView big_img, small_img;


        public MyViewHolder(View itemView) {
            super(itemView);
            creator_nickname = itemView.findViewById(R.id.creator_nickname);
            post_desc = itemView.findViewById(R.id.post_desc);
            winsteek = itemView.findViewById(R.id.winsteek);
            big_img = itemView.findViewById(R.id.big_img);
            small_img = itemView.findViewById(R.id.small_img);

        }
    }
}
