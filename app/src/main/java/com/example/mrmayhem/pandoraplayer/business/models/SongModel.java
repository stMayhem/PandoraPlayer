package com.example.mrmayhem.pandoraplayer.business.models;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public class SongModel {
    private int id;
    private String artist;
    private String name;
    private String imageUrl;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
