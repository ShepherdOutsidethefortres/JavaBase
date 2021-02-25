package zhw.designpattern.decorate.silicon;

public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
