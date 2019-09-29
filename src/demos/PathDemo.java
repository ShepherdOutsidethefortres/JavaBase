package demos;

import java.io.File;

public class PathDemo {
    public static void main(String[] args) {
        File file = new File("/");
        System.out.println("/代表的绝对路径为：" + file.getAbsolutePath());

        File file1 = new File(".");
        System.out.println(".代表的绝对路径为：" + file1.getAbsolutePath());

    }
}
