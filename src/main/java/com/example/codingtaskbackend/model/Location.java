package com.example.codingtaskbackend.model;

public class Location {
    private final String id;
    private final String name;
    private final String ascii;
    private final String alt_name;
    private final String latitude;
    private final String longitude;

    public Location(String id, String name, String ascii, String alt_name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.ascii = ascii;
        this.alt_name = alt_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAscii() {
        return ascii;
    }

    public String getAlt_name() {
        return alt_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}