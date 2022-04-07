package com.lotteryviewer.twocolorball.util;

import android.os.Build;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: duke
 * @DateTime: 2022-03-14 13:55:36
 * @Description: 功能描述：
 */
public class DoubleColorUtil {

    private static final int[] RED_ARR = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};
    private static final int[] BLUE_ARR = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    // 篮球奇偶性
    // 无
    public static final int BLUE_TYPE_NO = 0;
    // 奇数
    public static final int BLUE_TYPE_ODD = 1;
    // 偶数
    public static final int BLUE_TYPE_EVEN = 2;

    // 分隔符
    public static final String SEPARATOR = "_";

    private static ArrayList<Integer> redSorted(int redCount) {
        int[] redArrCopy = new int[RED_ARR.length];
        System.arraycopy(RED_ARR, 0, redArrCopy, 0, RED_ARR.length);
        if (redCount < 6) {
            // 最小 6
            redCount = 6;
        }
        if (redCount > RED_ARR.length) {
            // 最大 33
            redCount = RED_ARR.length;
        }
        ArrayList<Integer> list = new ArrayList<>(redCount * 2);
        Random random = new Random();
        while (redCount > 0) {
            // 0 ~ 32
            int index = random.nextInt(RED_ARR.length);
            if (redArrCopy[index] <= 0) {
                continue;
            }
            list.add(redArrCopy[index]);
            redArrCopy[index] = 0;
            redCount--;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.sort(Integer::compareTo);
        }
        return list;
    }

    private static int blue(int blueFromInt, int blueToInt, int blueOddEven) {
        if (blueFromInt > blueToInt) {
            // 交换
            blueFromInt = blueFromInt + blueToInt;
            blueToInt = blueFromInt - blueToInt;
            blueFromInt = blueFromInt - blueToInt;
        }
        if (blueFromInt == blueToInt) {
            blueFromInt = 1;
            blueToInt = BLUE_ARR.length;
        }
        if (blueFromInt < 1) {
            blueFromInt = 1;
        }
        if (blueToInt > BLUE_ARR.length) {
            blueToInt = BLUE_ARR.length;
        }
        int[] blueArrCopy = new int[blueToInt - blueFromInt + 1];
        System.arraycopy(BLUE_ARR, blueFromInt - 1, blueArrCopy, 0, blueArrCopy.length);
        Random random = new Random();
        boolean isFind = false;
        int blue = 0;
        while (!isFind) {
            int index = random.nextInt(blueArrCopy.length);
            blue = blueArrCopy[index];
            if (blueOddEven == BLUE_TYPE_ODD) {
                // 寻找奇数
                if (blue % 2 == 0) {
                    // 未找到，再来
                    continue;
                }
            } else if (blueOddEven == BLUE_TYPE_EVEN) {
                // 寻找偶数
                if (blue % 2 == 1) {
                    // 未找到，再来
                    continue;
                }
            }
            isFind = true;
        }
        return blue;
    }

    /**
     * 拼接红球和篮球
     *
     * @param redCount         红球个数
     * @param blueFromInt      篮球开始范围
     * @param blueToInt        篮球结束范围
     * @param blueOddEven      篮球奇偶性: 0 无，1 奇数，2 偶数
     * @param isSupplementZero 小于 10 的数，是否在前面补充 0
     * @return [red, red, red, ???, red, red, red, blue]
     */
    private static ArrayList<String> produceRedBlueBall(int redCount, int blueFromInt, int blueToInt, int blueOddEven, boolean isSupplementZero) {
        ArrayList<Integer> intList = redSorted(redCount);
        int blue = blue(blueFromInt, blueToInt, blueOddEven);
        intList.add(blue);
        ArrayList<String> resultList = new ArrayList<>(intList.size() * 2);
        for (int value : intList) {
            if (isSupplementZero && value < 10) {
                resultList.add("0" + value);
            } else {
                resultList.add(String.valueOf(value));
            }
        }
        return resultList;
    }

    /**
     * 单式
     *
     * @param blueFromInt      篮球开始范围
     * @param blueToInt        篮球结束范围
     * @param blueOddEven      篮球奇偶性: 0 无，1 奇数，2 偶数
     * @param isSupplementZero 小于 10 的数，是否在前面补充 0
     * @return [red, red, red, red, red, red, blue]
     */
    private static ArrayList<String> getSimplex(int blueFromInt, int blueToInt, int blueOddEven, boolean isSupplementZero) {
        return produceRedBlueBall(6, blueFromInt, blueToInt, blueOddEven, isSupplementZero);
    }

    private static ArrayList<String> getSimplex(boolean isSupplementZero) {
        return produceRedBlueBall(6, 1, BLUE_ARR.length, BLUE_TYPE_NO, isSupplementZero);
    }

    private static ArrayList<String> getSimplex() {
        return produceRedBlueBall(6, 1, BLUE_ARR.length, BLUE_TYPE_NO, true);
    }

    /**
     * 复式
     *
     * @param redCount         红球个数
     * @param blueFromInt      篮球开始范围
     * @param blueToInt        篮球结束范围
     * @param blueOddEven      篮球奇偶性: 0 无，1 奇数，2 偶数
     * @param isSupplementZero 小于 10 的数，是否在前面补充 0
     * @return [red, red, red, ..., red, red, red, blue]
     */
    public static ArrayList<String> getCompound(int redCount, int blueFromInt, int blueToInt, int blueOddEven, boolean isSupplementZero) {
        return produceRedBlueBall(redCount, blueFromInt, blueToInt, blueOddEven, isSupplementZero);
    }

    public static ArrayList<String> getCompound(int redCount, boolean isSupplementZero) {
        return produceRedBlueBall(redCount, 1, BLUE_ARR.length, BLUE_TYPE_NO, isSupplementZero);
    }

    public static ArrayList<String> getCompound(int redCount) {
        return produceRedBlueBall(redCount, 1, BLUE_ARR.length, BLUE_TYPE_NO, true);
    }

    public static String listToString(ArrayList<String> list, String separator) {
        if (list == null || list.size() == 0) {
            return "";
        }
        if (separator == null || separator.trim().length() == 0) {
            separator = SEPARATOR;
        }
        StringBuffer sb = new StringBuffer();
        int size = list.size();
        for (String s : list) {
            sb.append(s);
            sb.append(separator);
        }
        int start = 0;
        int end = sb.length() - separator.length();
        String result = sb.substring(start, end);
        return result;
    }

    public static String listToString(ArrayList<String> list) {
        return listToString(list, SEPARATOR);
    }

    public static int parseToInt(String s) {
        if (s == null || s.trim().length() == 0) {
            return -1;
        }
        int r = -1;
        try {
            r = Integer.parseInt(s);
        } catch (NumberFormatException e) {

        }
        return r;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            ArrayList<String> list = getSimplex();
            System.out.println("红色: " + listToString(list));
        }
    }
}
