package demos;

/**
 * 100层楼梯，走一步、走两步、走三步，这三种走法组合，走到100层共有多少种走法
 * 时间复杂度O(n^2)
 * Created by huiwei.zhao on 2019/7/4.
 */
public class Stairs {
    public static void main(String[] args) {
        System.out.println("1层楼梯走法：" + getCount(1));
        System.out.println("2层楼梯走法：" + getCount(2));
        System.out.println("3层楼梯走法：" + getCount(3));
        System.out.println("20层楼梯走法：" + getCount(20));

        System.out.println("斐波那契数列，第10个数是：" + fetBoNaQi(10));
    }

    public static int getCount(int x) {
        if (x == 1) {
            return 1;
        } else if (x == 2) {
            return 2;
        } else if (x == 3) {
            return 4;
        } else {
            return getCount(x - 3) + getCount(x - 2) + getCount(x - 1);
        }
    }

    /**
     * 斐波那契数列
     *
     * @return
     */
    public static int fetBoNaQi(int x) {
        if (x == 1 || x==2) {
            return 1;
        }else {
            return fetBoNaQi(x - 1) + fetBoNaQi(x - 2);
        }
    }
}
