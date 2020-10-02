package com.dst.dbparser.locarus;

import com.dst.dbparser.CleanValues;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.parsed.ParsedEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {
    public static HashMap<String, Handler> handlers;
    static {
        handlers = new HandlersStorage().getHandlers();
    }

//    public static int[] getIntegerArray(Message message, int analogInNumber) {
////        if (message.getDescription() == null) {
//            Object[] pressL = message.getResult().getData().stream().map(data -> data.getAnalogIn().get(String.valueOf(analogInNumber))).toArray();
//            int[] arr = new int[pressL.length];
//            for (int i = 0; i < pressL.length; i++) {
////                arr[i] = Integer.parseInt(String.valueOf(pressL[i]));
//                if (pressL[i] != null) {
//                    arr[i] = (int) ((double) pressL[i] * 1/*0.05*/);
//                }
//            }
//            return arr;
////        } else {
////            System.out.println(message.getDescription());
////            return null;
////        }
//    }

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


//    public static String[] getTimeArr(Message message) {
////        if (message.getDescription() == null) {
//            return message.getResult().getData().stream().map(LocarusDataField::getTime).map(Time::toString).map(s -> s.replaceAll("T", " ")).map(s -> s.replaceAll("\\.000Z", "")).toArray(String[]::new);
////        } else {
////            System.out.println(message.getDescription());
////            return null;
////        }
//    }



//    public static String errorsToString(Set<Integer> errors) {
//        if (errors != null) {
//            StringBuilder sb = new StringBuilder();
//            for (Integer i : errors) {
//                sb.append(i).append(":");
//            }
//            if (sb.length() > 0) {
//                sb.setLength(sb.length() - 1);
//            } else {
//                sb.append("no errors");
//            }
//            return sb.toString();
//        }
//        return "set of errors is null";
//    }

    private static boolean[] getDigitalInputs(String digIn){
        boolean[] result = new boolean[8];
        long digInLong = Long.parseLong(digIn);
        if(digInLong > 0){
            System.out.println("error getDigitalInputs");
            return null;
        }
        else {
            int n = (int) (4294967295L + digInLong + 1) >> 7;
            for (int i = 7; i >= 0; i--) {
                result[i] = (n & (1 << i)) != 0;
            }
        }
        return result;
    }

    public static boolean[][] getDigitalInData(List<LocarusDataField> data){
        boolean[][] result = new boolean[data.size()][8];
            for (int i = 0; i < result.length; i++) {
                result[i] = getDigitalInputs(data.get(i).getDigitalIn());
            }
            return result;
    }

    public static ParsedEntity parseLocarusDataField (LocarusDataField ldf){
        ParsedEntity result = new ParsedEntity();
        result.setTime(ldf.getTime());
        Map<String, Double> resParams = new HashMap<>();
//        System.out.println(ldf.getAnalogIn());
//        System.out.println(ldf.getAnalogIn().keySet());
        for (Map.Entry<String, Double> anal_n: ldf.getAnalogIn().entrySet()){
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P1.ordinal() + 1))){
                resParams.put(CleanValues.tempHydOil, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.tempHydOil)));
//                System.out.println(resParams.get(CleanValues.tempHydOil));
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
            if (anal_n.getKey().equals(String.valueOf(LocarusChannels.P6.ordinal() + 1))){
                resParams.put(CleanValues.pressA, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressA)));
                resParams.put(CleanValues.pressB, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressB)));
                resParams.put(CleanValues.pressAttach, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressAttach)));
                resParams.put(CleanValues.pressFanDrive, (double)DataParser.getNumberFromByte((int)(double)(anal_n.getValue()), handlers.get(CleanValues.pressFanDrive)));
//                continue;
            }
        }
        result.setParams(resParams);
        return result;
    }
}
