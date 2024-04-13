package com.quaice.hackathonapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.quaice.hackathonapp.AuthenticationActivity;
import com.quaice.hackathonapp.MainActivity;
import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.Review;
import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;

import java.util.ArrayList;
import java.util.List;

public class FundraisingAdapter extends RecyclerView.Adapter<FundraisingAdapter.MyViewHolder> {
    private List<FundraisingResponse> mData;
    private Context context;

    public FundraisingAdapter(List<FundraisingResponse> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.request_item, parent, false);

        // Return a new holder instance
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Get the data model based on position
        FundraisingResponse temp_item = mData.get(position);

        // Set item views based on your views and data model
        holder.title.setText(temp_item.getTitle());
        holder.description.setText(temp_item.getDescription());
        holder.type.setText(temp_item.getFundraisingType().toString());
        holder.fond_name.setText(temp_item.getFundraisingCompany());
        holder.open_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Review.current = temp_item;
                context.startActivity(new Intent(context, Review.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, type, fond_name, time_text;
        public CardView open_review;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_name);
            type = itemView.findViewById(R.id.type);
            fond_name = itemView.findViewById(R.id.fond_name);
            time_text = itemView.findViewById(R.id.time_text);
            description = itemView.findViewById(R.id.description);
        }
    }
}