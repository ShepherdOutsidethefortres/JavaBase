package zhw.designpattern.iterator.touristmap;

/**
 * 抽象迭代器
 */
public interface ViewSpotIterator {
    boolean hasNext();
    WyViewSpot first();
    WyViewSpot next();
    WyViewSpot previous();
    WyViewSpot last();
}
