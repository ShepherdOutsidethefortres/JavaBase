package zhw.designpattern.visitor.papercuprum;

/**
 * 元素抽象类
 */
public interface Material {
    String accept(Company visitor);
}
