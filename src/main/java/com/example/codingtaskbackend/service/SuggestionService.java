package com.example.codingtaskbackend.service;

import com.example.codingtaskbackend.model.Suggestion;
import com.example.codingtaskbackend.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SuggestionService {

    private final SuggestionRepository repository;

    @Autowired
    public SuggestionService(SuggestionRepository repository) {
        this.repository = repository;
    }

    public List<Suggestion> getSuggestions(String query, Double lat, Double lon) {
        var locations = repository.getLocations();
        Supplier<Stream<Double>> arrayOfDistanceSupplier = () -> locations.stream()
                .filter(location -> location.name().toLowerCase().contains(query.toLowerCase()))
                .map(
                        (s) -> distance(
                                lat,
                                lon,
                                Double.parseDouble(s.latitude()),
                                Double.parseDouble(s.longitude())
                        )
                );
        var sumOfDistance = arrayOfDistanceSupplier.get().reduce(Double::sum).orElse(1D);

        AtomicInteger index = new AtomicInteger();
        var suggestions = locations.stream()
                .filter(location -> location.name().toLowerCase().contains(query.toLowerCase()))
                .map(location -> {
                    var suggestion = new Suggestion(
                            location.name(),
                            location.latitude(),
                            location.longitude(),
                            (Math.round(arrayOfDistanceSupplier.get().toList().get(index.get()) / sumOfDistance) == 1) ?
                                    1 :
                                    1 - (arrayOfDistanceSupplier.get().toList().get(index.get()) / sumOfDistance)
                    );
                    index.getAndIncrement();
                    return suggestion;
                });


        if (lat == null && lon == null) return suggestions.collect(Collectors.toList());

        var sorted = suggestions.sorted(
                (o1, o2) -> distance(lat, lon, Double.parseDouble(o1.latitude()), Double.parseDouble(o1.longitude()))
                        .compareTo(distance(lat, lon, Double.parseDouble(o2.latitude()), Double.parseDouble(o2.longitude()))));
        return sorted.collect(Collectors.toList());
    }

    private Double distance(double lat1, double lon1, double lat2, double lon2) {
        // haversine great circle distance approximation, returns meters
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // 60 nautical miles per degree of seperation
        dist = dist * 1852; // 1852 meters per nautical mile
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
