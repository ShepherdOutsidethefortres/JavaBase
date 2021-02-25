package zhw.designpattern.builder.origin;

public class CommonHouseOrigin extends AbstractHouse {
    @Override
    protected void buildBasic() {
        System.out.println("普通房子打地基——origin");
    }

    @Override
    protected void buildWalls() {
        System.out.println("普通房子砌墙——origin");
    }

    @Override
    protected void roofed() {
        System.out.println("普通房子封顶——origin");
    }
}
