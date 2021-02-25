package zhw.designpattern.visitor.papercuprum;

public class Paper implements Material {
    @Override
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}
