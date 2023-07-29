package com.ViennaCallingApp.backend.controller;

import com.ViennaCallingApp.backend.middleware.PoptisScraper;
import com.ViennaCallingApp.backend.middleware.ScheduleScraper;
import com.ViennaCallingApp.backend.model.Path;
import com.ViennaCallingApp.backend.model.StepQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(value = {"*"},
        allowedHeaders = {"GET, POST"}
)
@RestController
@RequestMapping(path = "/api")
public class RESTController {

    private static final String errorResponse = "No data available.";

    public RESTController() {
    }

    @GetMapping(path = "/path/{startStation}/{endStation}", produces = MediaType.APPLICATION_JSON_VALUE)
    String respondWithPath(@PathVariable("startStation") String startStation, @PathVariable("endStation") String endStation) throws IOException {

        List<StepQuery> queries = ScheduleScraper.getSchedule(startStation, endStation);
        Path path = PoptisScraper.createFullPath(queries);

        ObjectMapper mapper = new ObjectMapper();
        try {
            /*return mapper.writeValueAsString(
                    scraper.getDetailPage("Alte_Donau", "U1_Richtung_Leopoldau", "U1_Richtung_Oberlaa"));*/
            return mapper.writeValueAsString(path);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return errorResponse;
    }

}
