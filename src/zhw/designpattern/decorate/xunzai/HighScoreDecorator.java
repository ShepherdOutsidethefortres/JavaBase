package zhw.designpattern.decorate.xunzai;

public class HighScoreDecorator extends Decorator {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private  void reportHighScore(){
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    @Override
    public void report(){
        this.reportHighScore();
        super.report();
    }
}
