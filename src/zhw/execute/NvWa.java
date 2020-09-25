package zhw.execute;

import zhw.container.HumanFactory;
import zhw.impl.factory.*;
import zhw.service.Human;

/**
 * 女娲类
 */
public class NvWa {
    public static void main(String[] args) {
        //指定造人
//        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
//        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
//        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
//        Human blackHuman2 = HumanFactory.createHuman(BlackHuman.class);
//
//        whiteHuman.laugh();
//        yellowHuman.laugh();
//        blackHuman.laugh();
//        blackHuman2.laugh();

//        for (int i = 0; i < 100; i++) {
//            System.out.println("随机造人");
//            Human human = HumanFactory.createHuman();
//            human.laugh();
//        }


        //男性生产线
        MaleHumanFactory maleHumanFactory = new MaleHumanFactory();
        //女性生产线
        FemaleHumanFactory femaleHumanFactory = new FemaleHumanFactory();

        //生产男性黄种人
        Human maleYellowHuman = maleHumanFactory.createYellowHuman();
        //生产女性黄种人
        Human femaleYellowHuman = femaleHumanFactory.createYellowHuman();

        maleYellowHuman.laugh();
        maleYellowHuman.sex();

        femaleYellowHuman.laugh();
        femaleYellowHuman.sex();
    }
}
