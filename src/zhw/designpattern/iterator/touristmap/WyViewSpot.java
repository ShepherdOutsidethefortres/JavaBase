package zhw.designpattern.iterator.touristmap;

/**
 * 景点类
 */
public class WyViewSpot {
    private String name;
    private String introduce;

    public WyViewSpot(String name, String introduce) {
        this.name = name;
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }
}
