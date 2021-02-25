package zhw.designpattern.visitor.origin;

public class ConcreteVisitorB implements Visitor{
    @Override
    public void visit(ConcreteElementA elementA) {
        System.out.println("具体访问者B访问-->"+elementA.operationA());
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        System.out.println("具体访问者B访问-->"+elementB.operationB());
    }
}
