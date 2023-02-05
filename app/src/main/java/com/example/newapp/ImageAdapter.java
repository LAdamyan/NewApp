package com.example.newapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newapp.dto.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Image> imageArrayList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.initData(imageArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageArrayList == null ? 0 : imageArrayList.size();
    }

    public void setImages(List<Image> images) {
        this.imageArrayList.clear();
        this.imageArrayList.addAll(images);
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void initData(Image image) {

        AppCompatImageView imageView = itemView.findViewById(R.id.image);
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error))
                .load(image.url)
                .into(imageView);
    }
}
