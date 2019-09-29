package Utils;

import java.io.*;

public class TextUtil {

    private static String readString2(String filename) {

        StringBuffer str = new StringBuffer("");

        File file = new File(filename);

        try {

            FileReader fr = new FileReader(file);

            int ch = 0;

            while ((ch = fr.read()) != -1) {

                System.out.print((char) ch + " ");

            }

            fr.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

            System.out.println("File reader出错");

        }

        return str.toString();

    }

    /*按字节读取字符串 一次读完*/
    public static String readString3(String filename) {
        String str = "";

        try {
            FileInputStream in = new FileInputStream(filename);

            // size  为字串的长度 ，这里一次性读完
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);

            in.close();

            str = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;

    }

    /*InputStreamReader+BufferedReader读取字符串  ， InputStreamReader类是从字节流到字符流的桥梁*/

    /* 按行读对于要处理的格式化数据是一种读取的好方式 */

    private static String readString4(String filename) {
        int len = 0;
        StringBuffer str = new StringBuffer("");
        File file = new File(filename);

        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader in = new BufferedReader(isr);

            String line = null;
            while ((line = in.readLine()) != null) {

                if (len != 0){

                    str.append("\r\n" + line);
                } else {
                    str.append(line);
                }
                len++;
            }
            in.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.toString();
    }

    public static void WriteStringToFile(String filePath, String str) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.append(str);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void WriteStringToFile2(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("在已有的基础上添加字符串");
            bw.write("abc\r\n ");// 往已有的文件上添加字符串
            bw.write("def\r\n ");
            bw.write("hijk ");
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WriteStringToFile3(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.println("abc ");
            pw.println("def ");
            pw.println("hef ");
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void WriteStringToFile4(String filePath) {
        try {
            RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
            rf.writeBytes("op\r\n");
            rf.writeBytes("app\r\n");
            rf.writeBytes("hijklllll");
            rf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteStringToFile5(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            String s = "http://www.jb51.netl";
            fos.write(s.getBytes());
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
