package com.ViennaCallingApp.backend.middleware;

import com.ViennaCallingApp.backend.model.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class PoptisScraper {

    public PoptisScraper() {
    }

    public Document getDetailPage(String station, String entrance, String exit) throws IOException {
        String url = "http://poptis.wl-barrierefrei.at/stationen/" + station + "/trip/Wegbeschreibung_" + station + "___von___" + entrance + "___nach___" + exit + ".html";
        Document doc = Jsoup.connect(url).get();

        Step step = new Step();

        step.setTitle(doc.title());

        List<Node> nodes = doc.getElementsByClass("overview").first().childNodes();

        String output = "";

        for ( Node node : nodes ){
            if(node instanceof TextNode){
                output = output + node.toString();
            }
        }



        step.setOverview(doc.getElementsByClass("overview").toString());
        step.setRoute(doc.getElementsByClass("route").toString());
        step.setAlternative(doc.getElementsByClass("alternativ").toString());

        return doc;
    }

}
