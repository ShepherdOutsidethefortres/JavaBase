package com.jdjr.knowledge.test.frame.internal.util;

public class ChineseUtil {
    public ChineseUtil() {
    }

    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        char[] var2 = ch;
        int var3 = ch.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            char c = var2[var4];
            if (isChinese(c)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}
