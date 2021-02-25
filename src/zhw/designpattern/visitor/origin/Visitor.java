package zhw.designpattern.visitor.origin;

/***
 * 抽象访问者
 */
public interface Visitor {
    void visit(ConcreteElementA elementA);
    void visit(ConcreteElementB elementB);
}
