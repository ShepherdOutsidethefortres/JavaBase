package zhw.designpattern.iterator.touristmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体迭代器
 */
public class WyViewSpotIterator implements ViewSpotIterator {
    private List<WyViewSpot> list = new ArrayList<>();
    private int index = -1;

    WyViewSpot obj = null;

    public WyViewSpotIterator(List<WyViewSpot> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (index<list.size()-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public WyViewSpot first() {
        index=0;
        obj=list.get(index);
        return obj;
    }

    @Override
    public WyViewSpot next() {
        if (this.hasNext()){
            obj=list.get(++index);
        }
        return obj;
    }

    @Override
    public WyViewSpot previous() {
        if (index>0){
            obj=list.get(--index);
        }
        return obj;
    }

    @Override
    public WyViewSpot last() {
        index=list.size()-1;
        obj=list.get(index);
        return obj;
    }
}
