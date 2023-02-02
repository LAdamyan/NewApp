package com.example.newapp;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    private String image;

    public Gallery(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }



    public static List<Gallery> getGalleryItems(){
        ArrayList<Gallery> galleryArrayList = new ArrayList<>();

         return  galleryArrayList;
    }
}
