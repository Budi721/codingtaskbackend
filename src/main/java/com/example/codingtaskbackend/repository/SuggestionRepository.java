package com.example.codingtaskbackend.repository;

import com.example.codingtaskbackend.model.Location;
import java.util.List;

public interface SuggestionRepository {
    List<Location> getLocations();
}
