package zhw.designpattern.builder.really;

public class HouseDirector {
    private HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House concreteHouse() {
        this.houseBuilder.buildBasic();
        this.houseBuilder.buildWalls();
        this.houseBuilder.roofed();
        return this.houseBuilder.buidHouse();
    }
}
