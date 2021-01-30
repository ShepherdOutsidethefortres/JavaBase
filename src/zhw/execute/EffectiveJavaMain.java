package zhw.execute;

import beans.effectivejava.builderdesign.Calzone;
import beans.effectivejava.builderdesign.NutritionFacts;
import beans.effectivejava.builderdesign.NyPizza;
import beans.effectivejava.builderdesign.Pizza;

public class EffectiveJavaMain {
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();

        NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();

        Calzone calzone = new Calzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside().build();
    }
}
