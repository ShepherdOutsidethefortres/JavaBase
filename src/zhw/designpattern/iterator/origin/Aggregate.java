package zhw.designpattern.iterator.origin;

/**
 * 抽象聚合角色
 * 定义存储、添加、 删除聚合对象以及创建迭代器对象的接口。
 */
public interface Aggregate {
    void add(Object obj);
    void remove(Object obj);
    Iterator getIterator();
}
