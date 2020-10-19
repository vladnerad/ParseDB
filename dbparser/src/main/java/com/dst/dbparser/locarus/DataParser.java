package com.dst.dbparser.locarus;

import com.dst.dbparser.CleanValues;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.parsed.ParsedEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DataParser {
    public static HashMap<String, Handler> handlers;
    static {
        handlers = new HandlersStorage().getHandlers();
    }

    public static int getNumberFromByte(int number, int startBit, int length) {
        boolean[] bits = new boolean[32];
        for (int i = 0; i < 32; i++) {
            bits[31 - i] = (number & (1 << i)) != 0;
        }
        int result = bits[bits.length - startBit - 1] ? 1 : 0;
        for (int z = 1; z < length; z++) {
            int o = (bits[bits.length - startBit - 1 - z] ? 1 : 0) << z;
            result = result | o;
        }
        return result;
    }

    public static int getNumberFromByte(int number, Handler handler) {
        boolean[] bits = new boolean[32];
        for (int i = 0; i < 32; i++) {
            bits[31 - i] = (number & (1 << i)) != 0;
        }
        int result = bits[bits.length - handler.getStartBit() - 1] ? 1 : 0;
        for (int z = 1; z < handler.getLength(); z++) {
            int o = (bits[bits.length - handler.getStartBit() - 1 - z] ? 1 : 0) << z;
            result = result | o;
        }
        return (int) (result * handler.getMultiply()) + handler.getShift();
    }

//    private static boolean[] getDigitalInputs(String digIn){
//        boolean[] result = new boolean[8];
//        long digInLong = Long.parseLong(digIn);
//        if(digInLong > 0){
//            System.out.println("error getDigitalInputs");
//            return null;
//        }
//        else {
//            int n = (int) (4294967295L + digInLong + 1) >> 7;
//            for (int i = 7; i >= 0; i--) {
//                result[i] = (n & (1 << i)) != 0;
//            }
//        }
//        return result;
//    }

//    public static boolean[][] getDigitalInData(List<LocarusDataField> data){
//        boolean[][] result = new boolean[data.size()][8];
//            for (int i = 0; i < result.length; i++) {
//                result[i] = getDigitalInputs(data.get(i).getDigitalIn());
//            }
//            return result;
//    }

    public static ParsedEntity parseLocarusDataField (LocarusDataField ldf){
        ParsedEntity result = new ParsedEntity();
        result.setTime(ldf.getTime().getValue());
        TreeMap<String, Double> resParams = new TreeMap<>();
        TreeMap<String, Boolean> resFlags = new TreeMap<>();
//        System.out.println(ldf.getAnalogIn());
//        System.out.println(ldf.getAnalogIn().keySet());
        for (Map.Entry<String, Double> anal_n: ldf.getAnalogIn().entrySet()){
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P1.ordinal() + 1))){
                resParams.put(CleanValues.joyY, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.joyY)));
                resParams.put(CleanValues.joyX, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.joyX)));
                resParams.put(CleanValues.tempHydOil, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.tempHydOil)));
                resParams.put(CleanValues.fuelLevel, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.fuelLevel)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P2.ordinal() + 1))){
                resParams.put(CleanValues.engineSpeed, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.engineSpeed)));
                resParams.put(CleanValues.engSpeedTsc1, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.engSpeedTsc1)));
                resParams.put(CleanValues.maxJoy, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.maxJoy)));
                resParams.put(CleanValues.accelPedal, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.accelPedal)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P3.ordinal() + 1))){
                resParams.put(CleanValues.tempCoolant, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.tempCoolant)));
                resParams.put(CleanValues.tempGearbox, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.tempGearbox)));
                resParams.put(CleanValues.tempTurbo, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.tempTurbo)));
                resParams.put(CleanValues.tempEnv, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.tempEnv)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P4.ordinal() + 1))){
                resParams.put(CleanValues.actualVelocity, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.actualVelocity)) / 10);
                resParams.put(CleanValues.velLimit, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.velLimit)) / 10);
                resParams.put(CleanValues.inchPedal, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.inchPedal)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P5.ordinal() + 1))){
                resParams.put(CleanValues.driveMode, new DriveModeHandler(anal_n.getValue()).getMode());
                resFlags.put(CleanValues.velocityLimiter, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.velocityLimiter)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P6.ordinal() + 1))){
                resParams.put(CleanValues.pressA, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressA)));
                resParams.put(CleanValues.pressB, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressB)));
                resParams.put(CleanValues.pressAttach, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressAttach)));
                resParams.put(CleanValues.pressFanDrive, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressFanDrive)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P7.ordinal() + 1))){
                resParams.put(CleanValues.errQuan, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.errQuan)));
                resFlags.put(CleanValues.swtGear1, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtGear1)));
                resFlags.put(CleanValues.swtGear2, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtGear2)));
                resFlags.put(CleanValues.isHydSysBlock, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isHydSysBlock)));
                resFlags.put(CleanValues.isSwimMode, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isSwimMode)));
                resFlags.put(CleanValues.isStartPossible, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isStartPossible)));
                resFlags.put(CleanValues.isControlForb, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isControlForb)));
                resFlags.put(CleanValues.isContForbSpeed, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isContForbSpeed)));
                resFlags.put(CleanValues.isContForbOpAbsent, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isContForbOpAbsent)));
                resFlags.put(CleanValues.isContForbUnintentionall, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isContForbUnintentionall)));
                resFlags.put(CleanValues.isMoveBackward, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isMoveBackward)));
                resFlags.put(CleanValues.isEmerDir, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isEmerDir)));
                resFlags.put(CleanValues.isOperMode, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isOperMode)));
                resFlags.put(CleanValues.isEcoMode, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.isEcoMode)));
                resFlags.put(CleanValues.swtParkingSens, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtParkingSens)));
                resFlags.put(CleanValues.swtOperAbsentSens, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtOperAbsentSens)));
                resFlags.put(CleanValues.swtParkingButton, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtParkingButton)));
                resFlags.put(CleanValues.swtAirFilter, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtAirFilter)));
                resFlags.put(CleanValues.swtHydFiltCloggVac, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtHydFiltCloggVac)));
                resFlags.put(CleanValues.swtHydFiltCloggPress, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtHydFiltCloggPress)));
                resFlags.put(CleanValues.swtHydFiltCloggSuction, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.swtHydFiltCloggSuction)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P10.ordinal() + 1))){
                resParams.put(CleanValues.bucketTilt, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.bucketTilt)));
                resParams.put(CleanValues.jibTilt, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.jibTilt)));
                resParams.put(CleanValues.chassisPitch, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.chassisPitch)));
                resParams.put(CleanValues.chassisTilt, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.chassisTilt)));
                continue;
            }
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P11.ordinal() + 1))){
                resFlags.put(CleanValues.sigGear1, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.sigGear1)));
                resFlags.put(CleanValues.sigGear2, 1 == DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.sigGear2)));
//                continue;
            }
        }
        result.setParams(resParams);
        result.setFlags(resFlags);
        return result;
    }
}
