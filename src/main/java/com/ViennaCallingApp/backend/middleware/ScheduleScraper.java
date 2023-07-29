package com.ViennaCallingApp.backend.middleware;

import com.ViennaCallingApp.backend.model.StepQuery;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleScraper {

    public ScheduleScraper() {
    }

    public static List<StepQuery> getSchedule(String startStation, String endStation) throws IOException {
        String url = "https://efa10.vor.at/bcl/XSLT_TRIP_REQUEST2?sessionID=0&requestID=0&language=de&command=&execInst=normal&ptOptionsActive=0&itOptionsActive=0&itdLPxx_options=0&itdDateDay=29&itdDateMonth=07&itdDateYear=23&itdTripDateTimeDepArr=dep&itdTimeHour=19&itdTimeMinute=19&placeState_origin=empty&place_origin=Wien&type_origin=stop&nameState_origin=empty&name_origin=" + startStation + "&placeState_destination=empty&place_destination=Wien&type_destination=stop&nameState_destination=empty&name_destination=" + endStation;
        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.getElementsByClass("request_data").first().nextElementSibling().nextElementSiblings();

        List<String> journeys = new ArrayList<>();

        for (Element element : elements) {

            if (element.text().equals("Zweite Fahrt")) {
                break;
            }

            journeys.add(element.text());
        }

        journeys.remove(0);

        elements.first();

        String regex1 = "Abfahrt ab (?<from>.*) um (?<startTime>\\d\\d:\\d\\d) mit (?<mode>.*) (?<lineNumber>\\w*) Richtung (?<direction>.*)\\.";
        String regex2 = "Ankunft an (?<to>.*) um (?<endTime>\\d\\d:\\d\\d)\\..*";

        Pattern pattern = Pattern.compile(regex1 + " " + regex2);

        List<StepQuery> queries = new ArrayList<>();

        for (String journey : journeys) {
            Matcher matcher = pattern.matcher(journey);

            if (matcher.matches()) {
                String from = matcher.group("from");
                String startTime = matcher.group("startTime");
                String mode = matcher.group("mode");
                String lineNumber = matcher.group("lineNumber");
                String direction = matcher.group("direction");
                String to = matcher.group("to");
                String endTime = matcher.group("endTime");

                queries.add(new StepQuery(from, startTime, mode, lineNumber, direction, to, endTime));
            }
        }

        return queries;
    }
}
