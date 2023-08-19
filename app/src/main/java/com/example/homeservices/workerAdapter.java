package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.homeservices.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class workerAdapter extends RecyclerView.Adapter<workerAdapter.MyViewHolder> {

    private Context mContext;
    private List<worker_model> mData;
    RequestOptions option;


    public workerAdapter(Context mContext, List<worker_model> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.defualt).error(R.drawable.defualt);
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.worker_item,parent,false);




        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        holder.field.setText(mData.get(position).getField());
        holder.rating.setText(mData.get(position).getRating());
        holder.price.setText(mData.get(position).getPrice());

        // load image

        Glide.with(mContext).load(mData.get(position).getImage()).apply(option).into(holder.image);

        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent priceButton = new Intent(mContext,AppointmentActivity.class);
                priceButton.putExtra("name",mData.get(holder.getAdapterPosition()).getName());
                priceButton.putExtra("field",mData.get(holder.getAdapterPosition()).getField());
                priceButton.putExtra("rating",mData.get(holder.getAdapterPosition()).getRating());
                priceButton.putExtra("image",mData.get(holder.getAdapterPosition()).getImage());

                mContext.startActivity(priceButton);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView field;
        TextView rating;
        ImageView image;
        Button price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            field = itemView.findViewById(R.id.field);
            rating = itemView.findViewById(R.id.rating);
            image = itemView.findViewById(R.id.worker_image);
            price = itemView.findViewById(R.id.price);




        }
    }
}