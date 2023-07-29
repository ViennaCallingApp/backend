package com.ViennaCallingApp.backend.middleware;

import com.ViennaCallingApp.backend.model.Box;
import com.ViennaCallingApp.backend.model.Path;
import com.ViennaCallingApp.backend.model.Step;
import com.ViennaCallingApp.backend.model.StepQuery;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class PoptisScraper {

    public PoptisScraper() {
    }

    public static Step getStep(StepQuery stepQueryFrom, StepQuery stepQueryTo) throws IOException {

        String station = stepQueryFrom.getTo().split(" ")[1];
        String entrance = stepQueryFrom.getLineNumber() + "_Richtung_" + stepQueryFrom.getDirection().split(" ")[1];
        String exit = stepQueryTo.getLineNumber() + "_Richtung_" + stepQueryTo.getDirection().split(" ")[1];

        String url = "http://poptis.wl-barrierefrei.at/stationen/" + station + "/trip/Wegbeschreibung_" + station + "___von___" + entrance + "___nach___" + exit + ".html";
        Document doc = Jsoup.connect(url).get();


        Step step = new Step();

        step.setStepQueryFrom(stepQueryFrom);
        step.setStepQueryTo(stepQueryTo);

        step.setTitle(doc.title());
        step.setOverview(new Box(doc.getElementsByClass("overview")));
        step.setRoute(new Box(doc.getElementsByClass("route")));
        step.setAlternative(new Box(doc.getElementsByClass("alternativ")));

        return step;
    }

    public static Step getStartStep(StepQuery stepQuery) throws IOException {

        String station = stepQuery.getFrom().split(" ")[1];
        String url = "http://poptis.wl-barrierefrei.at/index.php?id=" + stepQuery.getLineNumber() + "&station=" + station;
        Document doc = Jsoup.connect(url).get();
        String link = doc.getElementsByClass("displayflex").first().childNodes().get(0).attr("href");

        String entrance = link.replace("index.php?id=" + stepQuery.getLineNumber() + "&station=" + station + "&von=", "");
        String exit = stepQuery.getLineNumber() + "_Richtung_" + stepQuery.getDirection().split(" ")[1];

        String stepUrl = "http://poptis.wl-barrierefrei.at/stationen/" + station + "/trip/Wegbeschreibung_" + station + "___von___" + entrance + "___nach___" + exit + ".html";
        Document stepDoc = Jsoup.connect(stepUrl).get();

        Step step = new Step();
        step.setStepQueryFrom(stepQuery);
        step.setStepQueryTo(new StepQuery());

        step.setTitle(stepDoc.title());
        step.setOverview(new Box(stepDoc.getElementsByClass("overview")));
        step.setRoute(new Box(stepDoc.getElementsByClass("route")));
        step.setAlternative(new Box(stepDoc.getElementsByClass("alternativ")));

        return step;
    }

    public static Step getEndStep(StepQuery stepQuery) throws IOException {
        String station = stepQuery.getTo().split(" ")[1];
        String url = "http://poptis.wl-barrierefrei.at/index.php?id=" + stepQuery.getLineNumber() + "&station=" + station;
        Document doc = Jsoup.connect(url).get();
        String link = doc.getElementsByClass("displayflex").first().childNodes().get(0).attr("href");

        String exit = link.replace("index.php?id=" + stepQuery.getLineNumber() + "&station=" + station + "&von=", "");
        String entrance = stepQuery.getLineNumber() + "_Richtung_" + stepQuery.getDirection().split(" ")[1];

        String stepUrl = "http://poptis.wl-barrierefrei.at/stationen/" + station + "/trip/Wegbeschreibung_" + station + "___von___" + entrance + "___nach___" + exit + ".html";
        Document stepDoc = Jsoup.connect(stepUrl).get();

        Step step = new Step();
        step.setStepQueryFrom(new StepQuery());
        step.setStepQueryTo(stepQuery);

        step.setTitle(stepDoc.title());
        step.setOverview(new Box(stepDoc.getElementsByClass("overview")));
        step.setRoute(new Box(stepDoc.getElementsByClass("route")));
        step.setAlternative(new Box(stepDoc.getElementsByClass("alternativ")));

        return step;

    }

    public static Path createFullPath(List<StepQuery> queries) throws IOException {
        Path path = new Path();

        Step startStep = getStartStep(queries.get(0));
        path.addStep(startStep);

        for (int i = 1; i < queries.size(); i++) {
            path.addStep(getStep(queries.get(i - 1), queries.get(i)));
        }

        path.addStep(getEndStep(queries.get(queries.size() - 1)));

        return path;
    }

}
