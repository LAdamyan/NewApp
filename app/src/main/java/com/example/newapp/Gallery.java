package com.example.newapp;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    private int image;

    public Gallery(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static List<Gallery> getGalleryItems(){
        ArrayList<Gallery> galleryArrayList = new ArrayList<>();
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));
        galleryArrayList.add(new Gallery(R.drawable.image1));

         return  galleryArrayList;
    }
}
