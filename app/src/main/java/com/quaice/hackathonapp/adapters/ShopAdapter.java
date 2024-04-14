package com.quaice.hackathonapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.dto.Post.PostResponse;
import com.quaice.hackathonapp.dto.Shop.ShopItemResponse;
import com.quaice.hackathonapp.service.PostService;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
        private List<ShopItemResponse> mData;
        private Context context;

        private PostService postService;

        public ShopAdapter(List<ShopItemResponse> data, Context context) {
            this.mData = data;
            this.context = context;
            postService = new PostService(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View itemView = inflater.inflate(R.layout.shop_item, parent, false);

            // Return a new holder instance
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // Get the data model based on position
            ShopItemResponse item = mData.get(position);

            holder.title.setText(item.getTitle());
            holder.description.setText(item.getDescription());
            holder.price.setText(item.getPrice().toString());
            holder.image.setImageURI(Uri.parse(item.getItemImage()));

            // Set item views based on your views and data model
            //holder.textView.setText(item);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, description, price;
            private ImageView image;


            public MyViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                description = itemView.findViewById(R.id.description);
                price = itemView.findViewById(R.id.price);
                image = itemView.findViewById(R.id.image);

            }
        }
}
