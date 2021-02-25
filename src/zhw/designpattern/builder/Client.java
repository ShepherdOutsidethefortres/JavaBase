package zhw.designpattern.builder;

import zhw.designpattern.builder.origin.CommonHouseOrigin;
import zhw.designpattern.builder.really.CommonHouse;
import zhw.designpattern.builder.really.HighBuilding;
import zhw.designpattern.builder.really.HouseDirector;

public class Client {
    public static void main(String[] args) {
        CommonHouseOrigin commonHouseOrigin = new CommonHouseOrigin();
        commonHouseOrigin.build();

        //改造后
        CommonHouse commonHouse = new CommonHouse();
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        houseDirector.concreteHouse();

        HighBuilding highBuilding = new HighBuilding();
        houseDirector.setHouseBuilder(highBuilding);
        houseDirector.concreteHouse();
    }
}
