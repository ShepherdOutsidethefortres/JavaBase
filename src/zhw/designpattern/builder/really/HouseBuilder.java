package zhw.designpattern.builder.really;

/**
 * Builder
 */
public abstract class HouseBuilder {
    protected House house = new House();

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public House buidHouse() {
        return house;
    }
}
