package com.dst.dbparser.locarus;

import com.dst.dbparser.CleanValues;

import java.util.HashMap;

public class HandlersStorage {

    private HashMap<String, Handler> handlers;

    public HandlersStorage() {
        handlers = new HashMap<>();
        handlers.put(CleanValues.tempHydOil,         new Handler(16, 8, 1, -40));
        handlers.put(CleanValues.tempCoolant,        new Handler(0, 8, 1, -40));
        handlers.put(CleanValues.tempGearbox,        new Handler(8, 8, 1, -40));
        handlers.put(CleanValues.tempTurbo,          new Handler(16, 8, 1, -40));
        handlers.put(CleanValues.tempEnv,            new Handler(24, 8, 1, -40));
        handlers.put(CleanValues.pressA,             new Handler(0, 8, 2.5, 0));
        handlers.put(CleanValues.pressB,             new Handler(8, 8, 2.5, 0));
        handlers.put(CleanValues.pressAttach,        new Handler(16, 8, 2.5, 0));
        handlers.put(CleanValues.pressFanDrive,      new Handler(24, 8, 2.5, 0));

        handlers.put(CleanValues.engineSpeed,        new Handler(0, 8, 10, 0));
        handlers.put(CleanValues.engSpeedTsc1,       new Handler(8, 8, 10, 0));
        handlers.put(CleanValues.maxJoy,             new Handler(16, 7, 1, 0));
        handlers.put(CleanValues.accelPedal,         new Handler(24, 7, 1, 0));

        handlers.put(CleanValues.joyY,               new Handler(0, 8, 1, -100));
        handlers.put(CleanValues.joyX,               new Handler(8, 8, 1, -100));
        handlers.put(CleanValues.fuelLevel,          new Handler(24, 8, 0.4, 0));

        handlers.put(CleanValues.actualVelocity,     new Handler(0, 12, 1, -1250));
        handlers.put(CleanValues.velLimit,           new Handler(12, 12, 1, -1250));
        handlers.put(CleanValues.inchPedal,          new Handler(24, 7, 1, 0));

        handlers.put(CleanValues.bucketTilt,        new Handler(0, 8, 1, 0));
        handlers.put(CleanValues.jibTilt,           new Handler(8, 8, 1, 0));
        handlers.put(CleanValues.chassisPitch,      new Handler(16, 8, 1, 0));
        handlers.put(CleanValues.chassisTilt,       new Handler(24, 8, 1, 0));
    }

    public HashMap<String, Handler> getHandlers() {
        return handlers;
    }
}