package zhw.execute;

import java.util.ArrayList;
import java.util.List;

public class DateOperaMain {

    public static void main(String[] args) {
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(14);
        list1.add(2);

        list2.retainAll(list1);

//        for (int i = 0; i < list1.size(); i++) {
//            System.out.println(list1.get(i));
//        }


        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2);
        }


    }
}
