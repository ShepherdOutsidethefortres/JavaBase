package zhw.designpattern.decorate.silicon;

public class Soy extends Decorator {

    public Soy(Drink drink) {
        super(drink);
        setDes("豆浆");
        setPrice(1.0f);
    }
}
