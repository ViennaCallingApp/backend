package com.ViennaCallingApp.backend.middleware;

import com.ViennaCallingApp.backend.model.Box;
import com.ViennaCallingApp.backend.model.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PoptisScraper {

    public PoptisScraper() {
    }

    public Document getDetailPage(String station, String entrance, String exit) throws IOException {
        String url = "http://poptis.wl-barrierefrei.at/stationen/" + station + "/trip/Wegbeschreibung_" + station + "___von___" + entrance + "___nach___" + exit + ".html";
        Document doc = Jsoup.connect(url).get();

        Step step = new Step();

        step.setTitle(doc.title());

        step.setOverview(new Box(doc.getElementsByClass("overview")));
        step.setRoute(new Box(doc.getElementsByClass("route")));
        step.setAlternative(new Box(doc.getElementsByClass("alternativ")));

        return doc;
    }


}
