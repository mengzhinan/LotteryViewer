package com.lotteryviewer.base.util;

import android.text.Spanned;

import androidx.annotation.NonNull;

import java.util.Iterator;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:41:12
 * @Description: 功能描述：
 */
public class TextUtils {



    public static final long KB = 1024;
    public static final long MB = KB * 1024;
    public static final long GB = MB * 1024;

    /**
     * 对比两个版本号，如果 version1 大于 version2，就返回正
     *
     * @return version1 - version2
     */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null && version2 == null) {
            return 0;
        } else if (version1 != null && version2 == null) {
            return 1;
        } else if (version1 == null) {
            return -1;
        } else {
            String[] versions1 = version1.split("\\.");
            String[] versions2 = version2.split("\\.");

            try {
                int index;
                for (index = 0; index < versions1.length && index < versions2.length; ++index) {
                    int segment1 = Integer.parseInt(versions1[index]);
                    int segment2 = Integer.parseInt(versions2[index]);
                    if (segment1 < segment2) {
                        return -1;
                    }
                    if (segment1 > segment2) {
                        return 1;
                    }
                }
                return versions1.length > index ? 1 : (versions2.length > index ? -1 : 0);
            } catch (NumberFormatException e) {
                return version1.compareTo(version2);
            }
        }
    }


    /**
     * 判断是否为英文名字
     */
    public static boolean isEnglishName(String name) {
        int codePoint = name.charAt(name.length() - 1);
        return ('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z') || ('0' <= codePoint
                && codePoint <= '9');
    }

    /**
     * 如果是英文名字加个空格在后面
     */
    public static String formatName(String name) {
        if (isEnglishName(name)) {
            return name + " ";
        }
        return name;
    }

    public static String removeWhitespace(@NonNull final String content) {
        return content.trim().replaceAll("['\n']", "");
    }

    public static String trimWhitespaceAndLineFeed(@NonNull String content) {
        // 去除首尾空格
        content = content.trim();

        // 去除首换行
        int start = 0;
        for (char c : content.toCharArray()) {
            if (c == '\n') {
                start++;
            } else {
                break;
            }
        }
        content = content.substring(start);

        // 去除尾换行
        int end = content.length();
        for (int i = content.length() - 1; i >= 0; i--) {
            if (content.charAt(i) == '\n') {
                end--;
            } else {
                break;
            }
        }
        content = content.substring(0, end);

        return content;
    }

    /**
     * 计算文字长度，汉字*2。
     */
    public static int length(@NonNull String content) {
        if (isEmpty(content)) {
            return 0;
        }
        return content.replaceAll("[^\\x00-\\xff]", "__").length(); // 将双字节字符替换成两个_
    }


    /**
     * 去除飘红标签
     *
     * @param stringRes 飘红文字
     * @return 无飘红文本
     */
    public static String clearColourLabel(final String stringRes) {
        if (isEmpty(stringRes))
            return null;
        return stringRes.replace("<em>", "").replace("</em>", "");
    }

    public static int parseInt(String text, int defaultValue) {
        if (text == null)
            return defaultValue;
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private static final int MAX_COUNT = 99;

    public static final String getPrettyCount(long count) {
        if (count > MAX_COUNT) {
            return MAX_COUNT + "+";
        }
        return String.valueOf(count);
    }

    public static String join(Iterable<?> iterable, char separator) {
        return iterable == null ? null : join(iterable.iterator(), separator);
    }

    public static String join(Iterable<?> iterable, String separator) {
        return iterable == null ? null : join(iterable.iterator(), separator);
    }


    public static String join(Iterator<?> iterator, char separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return first == null ? "" : first.toString();
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }
                while (iterator.hasNext()) {
                    buf.append(separator);
                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }
                return buf.toString();
            }
        }
    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return first == null ? "" : first.toString();
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                while (iterator.hasNext()) {
                    if (separator != null) {
                        buf.append(separator);
                    }

                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }
                return buf.toString();
            }
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
