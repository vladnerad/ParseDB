package com.dst.dbparser.parsed;

import java.util.TreeMap;

public class ParsedEntity {

    private String time;
    private TreeMap<String, Double> params;
    private TreeMap<String, Boolean> flags;

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
}