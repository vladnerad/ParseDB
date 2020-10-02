package com.dst.dbparser.fillers;

import com.dst.dbparser.CleanValues;

public interface DataFiller {
    void fillDate(CleanValues cleanValues);
    void fillAnalogIn(CleanValues cleanValues);
    void fillErrors(CleanValues cleanValues);
    void fillDigitalIn(CleanValues cleanValues);
    void fillAll(CleanValues cleanValues);
}
