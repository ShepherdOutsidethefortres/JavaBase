package zhw.designpattern.iterator.origin;

public class IteratorPattern {
    public static void main(String[] args) {
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");

        System.out.println("聚合的内容有：");
        Iterator it = ag.getIterator();
        while (it.hasNext()) {
            Object ob = it.next();
            System.out.println(ob.toString() + "\t");
        }

        Object ob = it.first();
        System.out.println("\nFirst:" + ob.toString());
    }
}
