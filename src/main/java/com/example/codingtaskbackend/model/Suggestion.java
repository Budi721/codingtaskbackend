package com.example.codingtaskbackend.model;

public record Suggestion(
        String name,
        String latitude,
        String longitude,
        Double score
) {
}
