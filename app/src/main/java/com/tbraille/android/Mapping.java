package com.tbraille.android;

import java.util.HashMap;

public class Mapping {

    private enum State {
        Normal, Capital, Number,
    }

    public final class MapResult {
        public final String text;
        public final String voice;
        public MapResult(String text, String voice) {
            this.text = text;
            this.voice = voice;
        }
    }

    private static final int CAPITAL_SIGN = 4;
    private static final int NUMBER_SIGN = 39;

    private static final String CAPITAL_PREFIX = "Capital ";
    private static final String NORMAL_VOICE = "Normal follows";
    private static final String CAPITAL_VOICE = "Capital follows";
    private static final String NUMBER_VOICE = "Number follows";
    private static final String NULL_VOICE = "Wrong input";

    private  State state;

    private HashMap<Integer, String> normalMap;
    private HashMap<Integer, String> capitalMap;
    private HashMap<Integer, String> numberMap;

    public Mapping() {
        state = State.Normal;
        createMaps();
    }

    private void createMaps() {
        //Integer.valueOf("100000",2).toString() capital follows
        //Integer.valueOf("111100",2).toString() number follows
        normalMap = new HashMap<Integer, String>();

        normalMap.put(Integer.valueOf("000001",2).toString(), "a");
        normalMap.put(Integer.valueOf("000011",2).toString(), "b");
        normalMap.put(Integer.valueOf("001001",2).toString(), "c");
        normalMap.put(Integer.valueOf("011001",2).toString(), "d");
        normalMap.put(Integer.valueOf("010001",2).toString(), "e");
        normalMap.put(Integer.valueOf("001011",2).toString(), "f");
        normalMap.put(Integer.valueOf("011011",2).toString(), "g");
        normalMap.put(Integer.valueOf("010011",2).toString(), "h");
        normalMap.put(Integer.valueOf("001010",2).toString(), "i");
        normalMap.put(Integer.valueOf("011010",2).toString(), "j");
        normalMap.put(Integer.valueOf("000101",2).toString(), "k");
        normalMap.put(Integer.valueOf("000111",2).toString(), "l");
        normalMap.put(Integer.valueOf("001101",2).toString(), "m");
        normalMap.put(Integer.valueOf("011101",2).toString(), "n");
        normalMap.put(Integer.valueOf("010101",2).toString(), "o");
        normalMap.put(Integer.valueOf("001111",2).toString(), "p");
        normalMap.put(Integer.valueOf("011111",2).toString(), "q");
        normalMap.put(Integer.valueOf("010111",2).toString(), "r");
        normalMap.put(Integer.valueOf("001110",2).toString(), "s");
        normalMap.put(Integer.valueOf("011110",2).toString(), "t");
        normalMap.put(Integer.valueOf("100101",2).toString(), "u");
        normalMap.put(Integer.valueOf("100111",2).toString(), "v");
        normalMap.put(Integer.valueOf("101101",2).toString(), "x");
        normalMap.put(Integer.valueOf("111101",2).toString(), "y");
        normalMap.put(Integer.valueOf("110101",2).toString(), "z");
        normalMap.put(Integer.valueOf("111010",2).toString(), "w");
        normalMap.put(Integer.valueOf("100001",2).toString(), "ch");
        normalMap.put(Integer.valueOf("101001",2).toString(), "sh");
        normalMap.put(Integer.valueOf("111001",2).toString(), "th");
        normalMap.put(Integer.valueOf("000010",2).toString(), ",");
        normalMap.put(Integer.valueOf("000110",2).toString(), ";");
        normalMap.put(Integer.valueOf("000100",2).toString(), "'");
        normalMap.put(Integer.valueOf("010010",2).toString(), ":");
        normalMap.put(Integer.valueOf("100100",2).toString(), "-");
        normalMap.put(Integer.valueOf("101000",2).toString(), ".");
        normalMap.put(Integer.valueOf("110010",2).toString(), ".");
        normalMap.put(Integer.valueOf("010110",2).toString(), "!");
        normalMap.put(Integer.valueOf("100110",2).toString(), "?");
        normalMap.put(Integer.valueOf("001100",2).toString(), "\\");

        capitalMap = new HashMap<Integer, String>();

        capitalMap.put(Integer.valueOf("000001",2).toString(), "A");
        capitalMap.put(Integer.valueOf("000011",2).toString(), "B");
        capitalMap.put(Integer.valueOf("001001",2).toString(), "C");
        capitalMap.put(Integer.valueOf("011001",2).toString(), "D");
        capitalMap.put(Integer.valueOf("010001",2).toString(), "E");
        capitalMap.put(Integer.valueOf("001011",2).toString(), "F");
        capitalMap.put(Integer.valueOf("011011",2).toString(), "G");
        capitalMap.put(Integer.valueOf("010011",2).toString(), "H");
        capitalMap.put(Integer.valueOf("001010",2).toString(), "I");
        capitalMap.put(Integer.valueOf("011010",2).toString(), "J");
        capitalMap.put(Integer.valueOf("000101",2).toString(), "K");
        capitalMap.put(Integer.valueOf("000111",2).toString(), "L");
        capitalMap.put(Integer.valueOf("001101",2).toString(), "M");
        capitalMap.put(Integer.valueOf("011101",2).toString(), "N");
        capitalMap.put(Integer.valueOf("010101",2).toString(), "O");
        capitalMap.put(Integer.valueOf("001111",2).toString(), "P");
        capitalMap.put(Integer.valueOf("011111",2).toString(), "Q");
        capitalMap.put(Integer.valueOf("010111",2).toString(), "R");
        capitalMap.put(Integer.valueOf("001110",2).toString(), "S");
        capitalMap.put(Integer.valueOf("011110",2).toString(), "T");
        capitalMap.put(Integer.valueOf("100101",2).toString(), "U");
        capitalMap.put(Integer.valueOf("100111",2).toString(), "V");
        capitalMap.put(Integer.valueOf("101101",2).toString(), "X");
        capitalMap.put(Integer.valueOf("111101",2).toString(), "Y");
        capitalMap.put(Integer.valueOf("110101",2).toString(), "Z");
        capitalMap.put(Integer.valueOf("111010",2).toString(), "W");
        capitalMap.put(Integer.valueOf("100001",2).toString(), "CH");
        capitalMap.put(Integer.valueOf("101001",2).toString(), "SH");
        capitalMap.put(Integer.valueOf("111001",2).toString(), "TH");
        capitalMap.put(Integer.valueOf("000010",2).toString(), ",");
        capitalMap.put(Integer.valueOf("000110",2).toString(), ";");
        capitalMap.put(Integer.valueOf("000100",2).toString(), "'");
        capitalMap.put(Integer.valueOf("010010",2).toString(), ":");
        capitalMap.put(Integer.valueOf("100100",2).toString(), "-");
        capitalMap.put(Integer.valueOf("101000",2).toString(), ".");
        capitalMap.put(Integer.valueOf("110010",2).toString(), ".");
        capitalMap.put(Integer.valueOf("010110",2).toString(), "!");
        capitalMap.put(Integer.valueOf("100110",2).toString(), "?");
        capitalMap.put(Integer.valueOf("001100",2).toString(), "\\");


        numberMap = new HashMap<Integer, String>();

        numberMap.put(Integer.valueOf("000001",2).toString(), "1");
        numberMap.put(Integer.valueOf("000011",2).toString(), "2");
        numberMap.put(Integer.valueOf("001001",2).toString(), "3");
        numberMap.put(Integer.valueOf("011001",2).toString(), "4");
        numberMap.put(Integer.valueOf("010001",2).toString(), "5");
        numberMap.put(Integer.valueOf("001011",2).toString(), "6");
        numberMap.put(Integer.valueOf("011011",2).toString(), "7");
        numberMap.put(Integer.valueOf("010011",2).toString(), "8");
        numberMap.put(Integer.valueOf("001010",2).toString(), "9");
        numberMap.put(Integer.valueOf("011010",2).toString(), "0");
        numberMap.put(16, ",");
        numberMap.put(48, ";");
        numberMap.put(32, "'");
        numberMap.put(18, ":");
        numberMap.put(36, "-");
        numberMap.put(5, ".");
        numberMap.put(22, ".");
        numberMap.put(50, "!");
        numberMap.put(52, "?");
        numberMap.put(33, "\\");
    }

    public MapResult getMapping(int braille) {
        if (braille == CAPITAL_SIGN) {
            if (state == State.Capital) {
                state = State.Normal;
                return new MapResult("", NORMAL_VOICE);
            } else {
                state = State.Capital;
                return new MapResult("", CAPITAL_VOICE);
            }
        } else if (braille == NUMBER_SIGN) {
            if (state == State.Number) {
                state = State.Normal;
                return new MapResult("", NORMAL_VOICE);
            } else {
                state = State.Number;
                return new MapResult("", NUMBER_VOICE);
            }
        } else {
            String text;
            switch (state) {
                case Normal:
                    if (normalMap.containsKey(braille)) {
                        text = normalMap.get(braille);
                        return new MapResult(text, text);
                    }
                    break;
                case Capital:
                    if (capitalMap.containsKey(braille)) {
                        text = capitalMap.get(braille);
                        return new MapResult(text, CAPITAL_PREFIX + text);
                    }
                    break;
                case Number:
                    if (numberMap.containsKey(braille)) {
                        text = numberMap.get(braille);
                        return new MapResult(text, text);
                    }
                    break;
            }
            return new MapResult("", NULL_VOICE);
        }
    }
}
