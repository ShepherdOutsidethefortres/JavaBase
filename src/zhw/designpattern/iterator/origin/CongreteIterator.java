package zhw.designpattern.iterator.origin;

import java.util.List;

/**
 * 具体迭代器角色
 * 实现抽象迭代器接口 中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
 */
public class CongreteIterator implements Iterator {

    private List<Object> list = null;

    private int index = 0;

    public CongreteIterator(List<Object> list) {
        this.list = list;
    }


    @Override
    public Object first() {
        index = 0;
        Object obj = list.get(index);
        return obj;
    }

    @Override
    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(index++);
        }
        return obj;
    }

    @Override
    public boolean hasNext() {
        if (index >= list.size()) {
            return false;
        } else {
            return true;
        }

    }
}
