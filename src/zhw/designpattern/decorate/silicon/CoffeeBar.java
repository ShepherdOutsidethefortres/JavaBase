package zhw.designpattern.decorate.silicon;

public class CoffeeBar {
    public static void main(String[] args) {
        Drink drink = new LongBlack();
        System.out.println("费用："+drink.cost());
        System.out.println("描述："+drink.getDes());

        drink = new Milk(drink);
        System.out.println("费用："+drink.cost());
        System.out.println("描述："+drink.getDes());

        drink=new Chocolate(drink);
        System.out.println("费用："+drink.cost());
        System.out.println("描述："+drink.getDes());
    }
}
