package com.ViennaCallingApp.backend.model;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Box {

    private List<String> contents;

    public Box(Elements elements){
        contents = new ArrayList<>();
        List<Node> nodes = elements.first().childNodes();

        for ( Node node : nodes ){
            if(node instanceof TextNode && !node.toString().equals("")){
               contents.add(node.toString());
            }
        }
    }
}
