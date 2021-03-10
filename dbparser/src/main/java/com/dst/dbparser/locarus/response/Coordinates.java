package com.dst.dbparser.locarus.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "alt", "dir", "acceleration", "valid"})
public class Coordinates {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;
    @JsonProperty("speed")
    private Double speed;
}