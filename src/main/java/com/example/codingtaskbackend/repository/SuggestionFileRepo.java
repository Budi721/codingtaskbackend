package com.example.codingtaskbackend.repository;

import com.example.codingtaskbackend.model.Location;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class SuggestionFileRepo implements SuggestionRepository {
    @Override
    public List<Location> getLocations() {
        File file = new File("src/main/resources/static/cities_canada-usa.tsv");
        ArrayList<String[]> data = tsvr(file);
        Stream<Location> locations = data.stream().map(this::mapLocationFromArray);
        return locations.collect(Collectors.toList());
    }

    private Location mapLocationFromArray(String[] list) {
        return new Location(
                list[0],
                list[1],
                list[2],
                list[3],
                list[4],
                list[5]
        );
    }

    private ArrayList<String[]> tsvr(File filepath) {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int count = 0;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                if (count > 0) data.add(lineItems);
                count++;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        return data;
    }
}
