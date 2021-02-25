package zhw.designpattern.visitor.origin;

/***
 * 抽象元素类
 */
public interface Element {
    void accept(Visitor visitor);
}
