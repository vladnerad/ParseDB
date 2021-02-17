package com.dst.dbparser.parsed;

import com.dst.dbparser.locarus.response.Coordinates;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.TreeMap;

@Document
public class ParsedEntity {

    private String time;
    private TreeMap<String, Double> params;
    private TreeMap<String, Boolean> flags;
    private ArrayList<Double> hstErrors;
    private Coordinates coordinates;

    public void setParams(TreeMap<String, Double> params) {
        this.params = params;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setFlags(TreeMap<String, Boolean> flags) {
        this.flags = flags;
    }

    public ArrayList<Double> getHstErrors() {
        return hstErrors;
    }

    public void setHstErrors(ArrayList<Double> hstErrors) {
        this.hstErrors = hstErrors;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}