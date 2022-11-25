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

    public String name() {
        return name;
    }

    public String latitude() {
        return latitude;
    }

    public String longitude() {
        return longitude;
    }

    public Double score() {
        return score;
    }
}