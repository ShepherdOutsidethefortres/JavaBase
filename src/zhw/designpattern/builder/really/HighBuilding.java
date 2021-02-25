package zhw.designpattern.builder.really;

public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("高楼大厦打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼大厦砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("高楼大厦封顶");
    }
}
