package com.dst.dbparser.locarus.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties({ "referenceID", "navigationID", "objectType", "dist", "mileage", "voltage",
        "message", "extra", "nativeFlags", "generalListIn", "Coords", "Filter", "Flags", "Satellites",
        "framePacket"})
public class LocarusDataField {

    @JsonProperty("time")
    private String time;
    @JsonProperty("digitalIn")
    private String digitalIn;
    @JsonProperty("analogIn")
    private Map<String, Double> analogIn;
    @JsonProperty("Coords")
    private Coordinates coords;

//    private String referenceID;
//    private String navigationID;
//    private String objectID;
//    private String objectType;
//    private String dist;
//    private String mileage;
//    private String voltage;
//    private String message;
//    private List<String> extra;
//    private String nativeFlags;
//    private List<String> generalListIn;
//    private List<String> Filter;
//    private List<String> Flags;
//    private List<String> Satellites;
//    private boolean framePacket;

    public String getTime() {
        return time;
    }

    public Map<String, Double> getAnalogIn() {
        return analogIn;
    }

    public Coordinates getCoords() {
        return coords;
    }
}
