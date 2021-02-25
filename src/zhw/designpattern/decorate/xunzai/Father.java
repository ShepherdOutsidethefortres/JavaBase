package zhw.designpattern.decorate.xunzai;

public class Father {
    public static void main(String[] args) {
        SchoolReport sr = new FouthGradeShcoolReport();

        sr=new HighScoreDecorator(sr);

        sr=new SortDecorator(sr);

        sr.report();

        sr.sign("老三");
    }
}
