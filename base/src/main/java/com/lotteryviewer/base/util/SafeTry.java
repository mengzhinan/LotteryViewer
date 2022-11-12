package com.lotteryviewer.base.util;

/**
 * @Author: duke
 * @DateTime: 2022-11-01 11:21:33
 * @Description: 功能描述：
 */
public class SafeTry {

    public static String getString(StringProvider stringProvider) {
        String DEFAULT_STRING = "";
        String result = DEFAULT_STRING;
        try {
            result = stringProvider.get();
            if (result == null) {
                result = DEFAULT_STRING;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getInt(IntProvider intProvider) {
        try {
            return intProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getLong(LongProvider longProvider) {
        try {
            return longProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static float getFloat(FloatProvider floatProvider) {
        try {
            return floatProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0F;
        }
    }

    public static double getDouble(DoubleProvider doubleProvider) {
        try {
            return doubleProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0D;
        }
    }

    public static boolean getBoolean(BooleanProvider booleanProvider) {
        try {
            return booleanProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public interface StringProvider {
        String get();
    }

    public interface IntProvider {
        int get();
    }

    public interface LongProvider {
        long get();
    }

    public interface FloatProvider {
        float get();
    }

    public interface DoubleProvider {
        double get();
    }

    public interface BooleanProvider {
        boolean get();
    }
}
