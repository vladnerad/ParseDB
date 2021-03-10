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

        handlers.put(CleanValues.velocityLimiter,   new Handler(29, 1, 1, 0));
        handlers.put(CleanValues.swtGear1,          new Handler(29, 1, 1, 0));
        handlers.put(CleanValues.swtGear2,          new Handler(28, 1, 1, 0));
        handlers.put(CleanValues.sigGear1,          new Handler(5, 1, 1, 0));
        handlers.put(CleanValues.sigGear2,          new Handler(4, 1, 1, 0));

        handlers.put(CleanValues.errQuan,           new Handler(0, 6, 1, 0));
        handlers.put(CleanValues.isHydSysBlock,     new Handler(15, 1, 1, 0));
        handlers.put(CleanValues.isSwimMode,        new Handler(14, 1, 1, 0));
        handlers.put(CleanValues.isStartPossible,   new Handler(13, 1, 1, 0));
        handlers.put(CleanValues.isControlForb,     new Handler(12, 1, 1, 0));
        handlers.put(CleanValues.isContForbSpeed,   new Handler(11, 1, 1, 0));
        handlers.put(CleanValues.isContForbOpAbsent, new Handler(10, 1, 1, 0));
        handlers.put(CleanValues.isContForbUnintentionall, new Handler(9, 1, 1, 0));
        handlers.put(CleanValues.isMoveBackward,    new Handler(8, 1, 1, 0));
        handlers.put(CleanValues.isEmerDir,         new Handler(22, 1, 1, 0));
        handlers.put(CleanValues.isOperMode,        new Handler(21, 1, 1, 0));
        handlers.put(CleanValues.isEcoMode,         new Handler(20, 1, 1, 0));
        handlers.put(CleanValues.swtParkingSens,    new Handler(23, 1, 1, 0));
        handlers.put(CleanValues.swtOperAbsentSens, new Handler(31, 1, 1, 0));
        handlers.put(CleanValues.swtParkingButton,  new Handler(30, 1, 1, 0));
        handlers.put(CleanValues.swtAirFilter,      new Handler(27, 1, 1, 0));
        handlers.put(CleanValues.swtHydFiltCloggVac, new Handler(26, 1, 1, 0));
        handlers.put(CleanValues.swtHydFiltCloggPress, new Handler(25, 1, 1, 0));
        handlers.put(CleanValues.swtHydFiltCloggSuction, new Handler(24, 1, 1, 0));

        handlers.put(CleanValues.tempMotSpeed,      new Handler(0, 10, 16, -8000));
        handlers.put(CleanValues.permMotSpeed,      new Handler(10, 10, 16, -8000));
        handlers.put(CleanValues.motoHours,         new Handler(0, 16, 1, 0));
        handlers.put(CleanValues.motoMinutes,       new Handler(20, 4, 1, 1));
        //Ощибки ГСТ spn & fmi
        handlers.put(CleanValues.spn1p1,            new Handler(16, 8, 1, 0));
        handlers.put(CleanValues.spn1p2,            new Handler(24, 8, 1, 0));
        handlers.put(CleanValues.spn1p3,            new Handler(5, 3, 1, 0));
        handlers.put(CleanValues.fmi1,              new Handler(0, 5, 1, 0));

        handlers.put(CleanValues.spn2p1,            new Handler(8, 8, 1, 0));
        handlers.put(CleanValues.spn2p2,            new Handler(16, 8, 1, 0));
        handlers.put(CleanValues.spn2p3,            new Handler(29, 3, 1, 0));
        handlers.put(CleanValues.fmi2,              new Handler(24, 5, 1, 0));

        handlers.put(CleanValues.spn3p1,            new Handler(0, 8, 1, 0));
        handlers.put(CleanValues.spn3p2,            new Handler(8, 8, 1, 0));
        handlers.put(CleanValues.spn3p3,            new Handler(21, 3, 1, 0));
        handlers.put(CleanValues.fmi3,              new Handler(16, 5, 1, 0));

        handlers.put(CleanValues.spn4p1,            new Handler(24, 8, 1, 0));
        handlers.put(CleanValues.spn4p2,            new Handler(0, 8, 1, 0));
        handlers.put(CleanValues.spn4p3,            new Handler(13, 3, 1, 0));
        handlers.put(CleanValues.fmi4,              new Handler(8, 5, 1, 0));

        handlers.put(CleanValues.spn5p1,            new Handler(16, 8, 1, 0));
        handlers.put(CleanValues.spn5p2,            new Handler(24, 8, 1, 0));
        handlers.put(CleanValues.spn5p3,            new Handler(5, 3, 1, 0));
        handlers.put(CleanValues.fmi5,              new Handler(0, 5, 1, 0));

        handlers.put(CleanValues.spn6p1,            new Handler(8, 8, 1, 0));
        handlers.put(CleanValues.spn6p2,            new Handler(16, 8, 1, 0));
        handlers.put(CleanValues.spn6p3,            new Handler(29, 3, 1, 0));
        handlers.put(CleanValues.fmi6,              new Handler(24, 5, 1, 0));

        handlers.put(CleanValues.torqueLimit,       new Handler(0, 7, 1, 0));
        handlers.put(CleanValues.velocLimitPct,     new Handler(8, 7, 1, 0));
        handlers.put(CleanValues.virtualAccel,      new Handler(16, 7, 1, 0));
        handlers.put(CleanValues.emerSteerRelay,    new Handler(24, 1, 1, 0));
        handlers.put(CleanValues.steerRelay,        new Handler(25, 1, 1, 0));
        handlers.put(CleanValues.cruiseContMinus,   new Handler(26, 1, 1, 0));
        handlers.put(CleanValues.cruiseContPlus,    new Handler(27, 1, 1, 0));
        handlers.put(CleanValues.cruiseContOn,      new Handler(28, 1, 1, 0));
        handlers.put(CleanValues.driveDirection,    new Handler(30, 2, 1, -1));

        handlers.put(CleanValues.engCpuConLost,     new Handler(31, 1, 1, 0));
        handlers.put(CleanValues.hstCpuConLost,     new Handler(30, 1, 1, 0));

        handlers.put(CleanValues.btnBSU,            new Handler(17, 1, 1, 0));
        handlers.put(CleanValues.btnRoad,           new Handler(18, 1, 1, 0));
        handlers.put(CleanValues.btnA1_3,           new Handler(19, 1, 1, 0));
        handlers.put(CleanValues.btnF1,             new Handler(20, 1, 1, 0));
        handlers.put(CleanValues.btnFuelHeat,       new Handler(21, 1, 1, 0));
        handlers.put(CleanValues.btnDumping,        new Handler(22, 1, 1, 0));
        handlers.put(CleanValues.btnSwim,           new Handler(23, 1, 1, 0));
        handlers.put(CleanValues.btnBack,           new Handler(30, 1, 1, 0));
        handlers.put(CleanValues.btnFront,          new Handler(31, 1, 1, 0));
    }

    public HashMap<String, Handler> getHandlers() {
        return handlers;
    }
}