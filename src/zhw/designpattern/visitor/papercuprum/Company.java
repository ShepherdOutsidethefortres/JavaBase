package zhw.designpattern.visitor.papercuprum;

/**
 * 抽象访问者
 */
public interface Company {
    String create(Paper element);
    String create(Cuprum element);
}
