package com.example.codingtaskbackend.model;



public class Suggestion {
    private final String name;
    private final String latitude;
    private final String longitude;
    private final Double score;

    public Suggestion(String name, String latitude, String longitude, Double score) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Double getScore() {
        return score;
    }
}