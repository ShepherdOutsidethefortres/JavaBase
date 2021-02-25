package zhw.designpattern.visitor.papercuprum;

/**
 * 纸币公司
 */
public class Mint implements Company {
    @Override
    public String create(Paper element) {
        return "纸币";
    }

    @Override
    public String create(Cuprum element) {
        return "铜币";
    }
}
