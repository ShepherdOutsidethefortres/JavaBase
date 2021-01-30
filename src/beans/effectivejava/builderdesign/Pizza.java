package beans.effectivejava.builderdesign;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/***
 * builder模式适用于类层次结构
 * 一般只有在很多参数的时候才使用：4个或者更多个参数
 * 如果类的构造器或者静态工厂中具有多个参数，设计这种类时，Builder模式就是一种不错的选择
 */
public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
