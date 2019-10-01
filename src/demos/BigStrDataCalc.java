package demos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 有两个字符串类型的数字，实现一个方法将它们进行相加，并返回相加后的数值
 * 大数据！！！
 * Created by huiwei.zhao on 2019/6/24.
 */
public class BigStrDataCalc {
    public static void main(String[] args) {
        String str1 = "99999";
        String str2 = "123";

        System.out.println(strAdd(str1, str2));

        System.out.println(subtract(str1, str2));

        System.out.println(divideEnhanced(str1, str2));

        System.out.println(multiply(str1, str2));


        System.out.println(System.currentTimeMillis());
        String newPreEarliT = BigStrDataCalc.add(String.valueOf(System.currentTimeMillis()), "30000");
        System.out.println(newPreEarliT);
        String divide = BigStrDataCalc.divideEnhanced(newPreEarliT, "1000");
        String substring = divide.substring(0, divide.indexOf("."));
        System.out.println(substring);
    }

    /**
     * 相加
     * @param str1
     * @param str2
     * @return
     */
    public static String strAdd(String str1, String str2) {
//        1.反转
        StringBuilder stb1 = new StringBuilder(str1).reverse();
        StringBuilder stb2 = new StringBuilder(str2).reverse();

        int len1 = stb1.length();
        int len2 = stb2.length();
        int len = len1 > len2 ? len1 : len2;

//        2.补位
        if (len1 < len2) {
            int count = len2 - len1;
            while (count-- > 0){
                stb1.append('0');
            }
        } else {
            int count = len1 - len2;
            while (count-- > 0) {
                stb2.append('0');
            }
        }

        StringBuilder ret = new StringBuilder();
        int overFlow = 0;

//        3.进位，相加
        for (int i = 0; i < len; i++) {
            int num = stb1.charAt(i) - '0' + stb2.charAt(i) - '0' + overFlow;
            if (num >= 10) {
                overFlow = 1;
                ret.append(num - 10);
            } else {
                ret.append(num);
            }
        }

        if (overFlow == 1) {
            ret.append(1);
        }


        return ret.reverse().toString();
    }

    /**
     * 两数相加
     * @param numStr1 数1
     * @param numStr2 数2
     * @return 结果
     */
    public static String add(String numStr1, String numStr2){

        int numLen1 = numStr1.length();
        int numLen2 = numStr2.length();

        int[] numArray1 = new int[numLen1]; //数字数组
        int[] numArray2 = new int[numLen2];



        // "12345"-> [5,4,3,2,1]
        for(int i=0;i<numLen1;i++){
            String c = numStr1.substring(i,i+1);
            numArray1[numLen1-i-1] = Integer.parseInt(c); //低位存字符串尾部数字
        }
        for(int i=0;i<numLen2;i++){
            String c = numStr2.substring(i,i+1);
            numArray2[numLen2-i-1] = Integer.parseInt(c); //低位存字符串尾部数字
        }


        int minLen = 0; //取长度小的数位数
        int maxLen = 0; //取长度大的数位数
        int[] maxArray = null; //长度大的数
        if(numLen1<numLen2){
            minLen = numLen1;
            maxLen = numLen2;
            maxArray = numArray2;
        }else{
            minLen = numLen2;
            maxLen = numLen1;
            maxArray = numArray1;
        }

        int[] resultArray = new int[maxLen+1]; //考虑到可能会进位，多给一个元素空间

        //两数长度相同的部分，同位相加，超出9进1
        int added = 0;
        int i=0;
        for(;i<minLen;i++){
            int t = numArray1[i]+numArray2[i]+added; //两数相加，再加进位
            if(t>9){
                added = 1; //进1
                resultArray[i] = t-10; //当前位计算结果
            }else{
                added = 0; //不进位
                resultArray[i] = t; //当前位计算结果
            }
        }
        //长度超出部分累加
        for(;i<maxLen;i++){
            int t = maxArray[i]+added; //多余位数加上进位
            if(t>9){
                added = 1; //进1
                resultArray[i] = t-10; //当前位计算结果
            }else{
                added = 0; //不进位
                resultArray[i] = t; //当前位计算结果
            }
        }
        resultArray[i] = added; //最高位

        //拼接结果 [1,4,8,2,0] -> 2841
        StringBuilder builder = new StringBuilder();
        for(int n=resultArray.length-1;n>=0;n--){
            //如果最高位为0,移除
            if(n==resultArray.length-1 && resultArray[resultArray.length-1]==0){
                continue; //跳过
            }else{
                builder.append(resultArray[n]);
            }
        }

        return builder.toString();
    }

    /**
     * 两数相减
     *
     * @param numStr1 数1
     * @param numStr2 数2
     * @return 结果
     */
    public static String subtract(String numStr1, String numStr2) {
        int numLen1 = numStr1.length();
        int numLen2 = numStr2.length();

        int[] numArray1 = new int[numLen1]; //数字数组
        int[] numArray2 = new int[numLen2];


        // "12345"-> [5,4,3,2,1]
        for (int i = 0; i < numLen1; i++) {
            String c = numStr1.substring(i, i + 1);
            numArray1[numLen1 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }
        for (int i = 0; i < numLen2; i++) {
            String c = numStr2.substring(i, i + 1);
            numArray2[numLen2 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }

        int minLen = 0; //取长度小的数位数
        int maxLen = 0; //取长度大的数位数
        int[] maxArray = null; //数值大的数
        if (numLen1 < numLen2) {
            minLen = numLen1;
            maxLen = numLen2;
            maxArray = numArray2;
        } else {
            minLen = numLen2;
            maxLen = numLen1;
            maxArray = numArray1;
            if (numLen1 == numLen2) { //等于
                maxArray = getMaxNumber(numArray1, numArray2);
            }
        }
        int[] minArray = maxArray == numArray1 ? numArray2 : numArray1; //数值小的数

        int[] resultArray = new int[maxLen];
//大数-小数，同位相减，小于0借位
        int subtracted = 0;
        int i = 0;
        for (; i < minLen; i++) {
            int t = maxArray[i] - minArray[i] - subtracted; //两数相减，再减借位
            if (t < 0) {
                subtracted = 1; //向高位借1，暂存起来
                resultArray[i] = t + 10; //当前位计算结果（借1相当于借了10）
            } else {
                subtracted = 0; //不借位
                resultArray[i] = t; //当前位计算结果
            }
        }
        //大数超出部分减掉借位
        for (; i < maxLen; i++) {
            int t = maxArray[i] - subtracted; //多余位数减掉借位
            if (t < 0) {
                subtracted = 1; //进1
                resultArray[i] = t + 10; //当前位计算结果
            } else {
                subtracted = 0; //不借位
                resultArray[i] = t; //当前位计算结果
            }
        }

        //拼接结果 [1,4,8,2,0] -> 2841
        StringBuilder builder = new StringBuilder();
        boolean highBitNotEqualZero = false; //存在高位不为0的情况，低位0保留
        for (int n = resultArray.length - 1; n >= 0; n--) {
            //如果高位为0,移除
            if (resultArray[n] == 0 && !highBitNotEqualZero && n != 0) { //高位无用的0去除
                continue; //跳过
            } else {
                highBitNotEqualZero = true; //找到不为0的位
                builder.append(resultArray[n]);
            }
        }

        if (maxArray == numArray1) { //第一个数大或相等

        } else {  //第一个数小于第二个数，相减为负数
            builder.insert(0, "-");
        }

        return builder.toString();
    }

    /**
     * 两数相乘
     *
     * @param numStr1 数1
     * @param numStr2 数2
     * @return 结果
     */
    public static String multiply(String numStr1, String numStr2) {
        int numLen1 = numStr1.length();
        int numLen2 = numStr2.length();

        int[] numArray1 = new int[numLen1]; //数字数组
        int[] numArray2 = new int[numLen2];


        // "12345"-> [5,4,3,2,1]
        for (int i = 0; i < numLen1; i++) {
            String c = numStr1.substring(i, i + 1);
            numArray1[numLen1 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }
        for (int i = 0; i < numLen2; i++) {
            String c = numStr2.substring(i, i + 1);
            numArray2[numLen2 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }


        int minLen = 0; //取长度小的数位数
        int maxLen = 0; //取长度大的数位数
        int[] maxArray = null; //长度大的数
        int[] minArray = null; //长度小的数
        if (numLen1 < numLen2) {
            minLen = numLen1;
            maxLen = numLen2;
            minArray = numArray1;
            maxArray = numArray2;
        } else {
            minLen = numLen2;
            maxLen = numLen1;
            minArray = numArray2;
            maxArray = numArray1;
        }

        //二维数组存储结果，例如：23*23 ->[[6,9],[4,6]] ,内部括号（低维）存某位的相乘结果，高维低位存个位,十位...
        int[][] resultArray = new int[minLen][maxLen + 1];

        //长度大的数*长度小的数的每一位，分别存到相应数组中，然后累加
        for (int h = 0; h < minLen; h++) { //高维
            int l = 0;
            int added = 0;
            for (; l < maxLen; l++) { //低维
                int t = maxArray[l] * minArray[h] + added; //长度大的数的每一位*长度小的数的个位、十位...
                if (t > 9) {
                    added = t / 10; //进位
                    resultArray[h][l] = t % 10; //当前位计算结果
                } else {
                    added = 0; //不进位
                    resultArray[h][l] = t; //当前位计算结果
                }
            }
            resultArray[h][l] = added; //个位、十位...的计算结果的最高位
        }

        //对结果补位（左移），个位不动，十位补0，百位补00...，然后累加
        int[] sum = null; //最终累加结果
        int[] lowBitResult = null; //低位补0结果（前一位）
        for (int h = 0; h < minLen; h++) {
            int[] bitResult = resultArray[h];
            int[] r;  //个位、十位...的补0结果
            if (h == 0) { //个位
                r = bitResult;
                sum = r;
                lowBitResult = r; //记录下来，待下次循环累加
            } else { //十位...的计算结果
                r = new int[resultArray[h].length + h]; //初始化默认就是0的
                int rLen = r.length - 1;
                for (int i = bitResult.length - 1; i >= 0; i--) { //从高位开始复制到新数组
                    r[rLen--] = bitResult[i];
                }
                //累加之前的数
                sum = new int[r.length + 1]; //取高位长度+1，可能进位

                //================加法核心算法====================
                //两数长度相同的部分，同位相加，超出9进1
                int added = 0;
                int i = 0;
                for (; i < lowBitResult.length; i++) {
                    int t = lowBitResult[i] + r[i] + added; //两数相加，再加进位
                    if (t > 9) {
                        added = 1; //进1
                        sum[i] = t - 10; //当前位计算结果
                    } else {
                        added = 0; //不进位
                        sum[i] = t; //当前位计算结果
                    }
                }
                //长度超出部分累加
                for (; i < r.length; i++) {
                    int t = r[i] + added; //多余位数加上进位
                    if (t > 9) {
                        added = 1; //进1
                        sum[i] = t - 10; //当前位计算结果
                    } else {
                        added = 0; //不进位
                        sum[i] = t; //当前位计算结果
                    }
                }
                sum[i] = added; //最高位
                //===============================================

                lowBitResult = sum; //记录下来，待下次循环累加
            }
        }

        //拼接结果 [1,4,8,2,0] -> 2841
        StringBuilder builder = new StringBuilder();
        boolean existHighNotZero = false; //高位存在不为0的，这个0就不能移除
        for (int n = sum.length - 1; n >= 0; n--) {
            //移除高位无效的0，保留最后一个0
            if (sum[n] == 0 && !existHighNotZero && n != 0) {
                continue; //跳过
            } else {
                existHighNotZero = true;
                builder.append(sum[n]);
            }
        }

        return builder.toString();
    }

    /**
     * 两数相除
     *
     * @param numStr1 数1(被除数)
     * @param numStr2 数2(除数,不能超过long型)
     * @return 结果
     */
    public static String divide(String numStr1, String numStr2) {
        int numLen1 = numStr1.length();
        int numLen2 = numStr2.length();

        int[] numArray1 = new int[numLen1]; //数字数组
        int[] numArray2 = new int[numLen2];


        // "12345"-> [5,4,3,2,1]
        for (int i = 0; i < numLen1; i++) {
            String c = numStr1.substring(i, i + 1);
            numArray1[numLen1 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }
        for (int i = 0; i < numLen2; i++) {
            String c = numStr2.substring(i, i + 1);
            numArray2[numLen2 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }

        int effectiveNum = (numLen1 >= numLen2 ? numLen1 : numLen2) + 16; //有效位数: 默认大数长度+16
        int[] resultArray = new int[effectiveNum]; //高位存高位

        //将被除数的每一位除以除数，取整为该位结果，取余暂存借给低位(除数不能大过long型，除非除法转换为减法)
        long yu = 0;
        int resultIndex = effectiveNum - 1;
        for (int i = numArray1.length - 1; i >= 0; i--) {
            long num = yu * 10 + numArray1[i]; //被除数该位为：余数*10+自己
            int r = (int) (num / Long.parseLong(numStr2)); //取整
            yu = num % Long.parseLong(numStr2); //取余
            resultArray[resultIndex--] = r;
        }
        int decimalPoint = effectiveNum - numArray1.length - 1; //小数点位置
        if (yu != 0) {
            int decimal = decimalPoint; //小数
            for (int i = 0; i < effectiveNum - numArray1.length; i++) {
                long num = yu * 10 + 0; //小数部分被除数补0
                int r = (int) (num / Long.parseLong(numStr2)); //取整
                yu = num % Long.parseLong(numStr2); //取余
                resultArray[decimal--] = r;
                if (yu == 0) {
                    break; //余数为0，提前退出
                }
            }
        }

        //拼接结果
        StringBuilder builder = new StringBuilder();
        boolean existHighNotZero = false;
        for (int i = effectiveNum - 1; i >= 0; i--) {
            if (i == decimalPoint) {
                builder.append(".");
            }
            if (resultArray[i] == 0) {
                if (!existHighNotZero && i > decimalPoint + 1) { //跳过高位无用的0
                    continue;
                }
            } else {
                existHighNotZero = true;
            }
            builder.append(resultArray[i]);
        }
        String result = builder.toString();
        //去除尾部无用的0
        int endIndex = result.length();
        for (int i = result.length() - 1; i >= 0; i--) {
            char c = result.charAt(i);
            if (c != '0') {
                endIndex = i + 1;
                break;
            }
        }
        //去除多余的小数点
        if (result.charAt(endIndex - 1) == '.') {
            endIndex = endIndex - 1;
        }
        result = result.substring(0, endIndex);
        return result;
    }


    /**
     * 两数相除（增强版）
     *
     * @param numStr1 数1(被除数)
     * @param numStr2 数2(除数)
     * @return 结果
     */
    public static String divideEnhanced(String numStr1, String numStr2) {
        int numLen1 = numStr1.length();
        int numLen2 = numStr2.length();

        int[] numArray1 = new int[numLen1]; //数字数组
        int[] numArray2 = new int[numLen2];


        // "12345"-> [5,4,3,2,1]
        for (int i = 0; i < numLen1; i++) {
            String c = numStr1.substring(i, i + 1);
            numArray1[numLen1 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }
        for (int i = 0; i < numLen2; i++) {
            String c = numStr2.substring(i, i + 1);
            numArray2[numLen2 - i - 1] = Integer.parseInt(c); //低位存字符串尾部数字
        }

        int effectiveNum = (numLen1 >= numLen2 ? numLen1 : numLen2) + 16; //有效位数: 默认大数长度+16
        int[] resultArray = new int[effectiveNum]; //高位存高位

        //将被除数的每一位除以除数，取整为该位结果，取余暂存借给低位(除数不能大过long型，除非除法转换为减法)
        String yu = "0";
        int resultIndex = effectiveNum - 1;
        for (int i = numArray1.length - 1; i >= 0; i--) {
            String num = "0".equals(yu) ? numArray1[i] + "" : strAdd(yu + "0", numArray1[i] + ""); //被除数该位为：余数*10+自己
            DivideResult result = getDivideResult(num, numStr2);
            String r = result.getR(); //取整
            yu = result.getYu(); //取余
            resultArray[resultIndex--] = Integer.parseInt(r); //某位上的结果肯定小于10
        }
        int decimalPoint = effectiveNum - numArray1.length - 1; //小数点位置
        if (!"0".equals(yu)) {
            int decimal = decimalPoint; //小数
            for (int i = 0; i < effectiveNum - numArray1.length; i++) {
                String num = yu + "0"; //小数部分被除数补0
                DivideResult result = getDivideResult(num, numStr2);
                String r = result.getR(); //取整
                yu = result.getYu(); //取余
                resultArray[decimal--] = Integer.parseInt(r);
                if ("0".equals(yu)) {
                    break; //余数为0，提前退出
                }
            }
        }

        //拼接结果
        StringBuilder builder = new StringBuilder();
        boolean existHighNotZero = false;
        for (int i = effectiveNum - 1; i >= 0; i--) {
            if (i == decimalPoint) {
                builder.append(".");
            }
            if (resultArray[i] == 0) {
                if (!existHighNotZero && i > decimalPoint + 1) { //跳过高位无用的0
                    continue;
                }
            } else {
                existHighNotZero = true;
            }
            builder.append(resultArray[i]);
        }
        String result = builder.toString();
        //去除尾部无用的0
        int endIndex = result.length();
        for (int i = result.length() - 1; i >= 0; i--) {
            char c = result.charAt(i);
            if (c != '0') {
                endIndex = i + 1;
                break;
            }
        }
        //去除多余的小数点
        if (result.charAt(endIndex - 1) == '.') {
            endIndex = endIndex - 1;
        }
        result = result.substring(0, endIndex);
        return result;
    }

    /**
     * 校验数字是否合法
     *
     * @param numStr 数字字符串
     * @return 是否合法
     */
    public static boolean numberValid(String numStr) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$|0");
        Matcher matcher = pattern.matcher(numStr);
        return matcher.matches();
    }

    /**
     * 计算大数
     *
     * @param numArray1 数1
     * @param numArray2 数2
     * @return 大数
     */
    public static int[] getMaxNumber(int[] numArray1, int[] numArray2) {
        for (int i = numArray1.length - 1; i >= 0; i--) {
            if (numArray1[i] > numArray2[i]) {
                return numArray1;
            } else {
                if (numArray1[i] == numArray2[i]) {
                    continue; //待继续比较
                } else {
                    return numArray2;
                }
            }
        }
        return numArray1; //全部相等，返回第一个
    }

    /**
     * 除法转换为减法
     *
     * @param numStr1 数1（被除数）
     * @param numStr2 数2（除数）
     * @return 除的结果
     */
    public static DivideResult getDivideResult(String numStr1, String numStr2) {
        DivideResult result = new DivideResult();
        String r = "";
        // String times = "0";
        int times = 0; //取整不会大于9的（被除数（余数+某位）/除数(肯定大于余数)这个过程是，被除数逐渐增大到可以除以除数为止，此时被除数>=除数，刚刚好，所以被除数最多比除数多1位，两数相差肯定小于10倍）
        while (true) {
            r = subtract(numStr1, numStr2);
            // times = add(times,"1"); //次数递增
            times++;
            if ("0".equals(r)) { //除尽了
                result.setYu("0");
                result.setR(times + "");
                break;
            } else if (r.startsWith("-")) { //负数，多减了一次
                result.setYu(numStr1); //上次减下来多余的数值，就是余数
                // result.setR(subtract(times,"1"));
                result.setR((times - 1) + "");
                break;
            }
            numStr1 = r; //被减数重置为剩余的数值
        }
        return result;
    }

}
