package com.dst.dbparser.parsed;

import java.util.Map;

public class ParsedEntity {

    private String time;
    private Map<String, Double> params;

    public void setParams(Map<String, Double> params) {
        this.params = params;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}