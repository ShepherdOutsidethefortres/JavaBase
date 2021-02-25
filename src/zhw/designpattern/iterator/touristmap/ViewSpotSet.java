package zhw.designpattern.iterator.touristmap;

/**
 * 抽象聚合：景点集接口
 */
public interface ViewSpotSet {
    void add(WyViewSpot obj);
    void remove(WyViewSpot obj);
    ViewSpotIterator getIterator();
}
