package zhw.container;

import java.util.ArrayList;
import java.util.Random;

public class Emperor {
    private static int maxNumOfEmperor = 2;
    private static ArrayList emperorInfoList = new ArrayList(maxNumOfEmperor);
    private static ArrayList emperorList = new ArrayList(maxNumOfEmperor);
    private static int countNumOfEmperor = 0;

    /***
     * 生产2个皇帝
     */
    static {
        for (int i = 0; i < maxNumOfEmperor; i++) {
            emperorList.add(new Emperor("皇" + (i + 1) + "帝"));
        }
    }

    /***
     * 不允许随意生产皇帝
     */
    private Emperor() {
    }

    private Emperor(String info) {
        emperorInfoList.add(info);
    }

    /***
     * 多例模式随便拉出一个皇帝
     * @return
     */
    public static Emperor getInstance() {
        Random random = new Random();
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return (Emperor) emperorList.get(countNumOfEmperor);
    }

    /***
     * 皇帝的名字
     */
    public void emperorInfo() {
        System.out.println(emperorInfoList.get(countNumOfEmperor));
    }
}
