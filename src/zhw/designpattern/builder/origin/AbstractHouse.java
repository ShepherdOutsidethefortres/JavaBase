package zhw.designpattern.builder.origin;

/***
 * 造房子
 */
public abstract class AbstractHouse {
    //打地基
    protected abstract void buildBasic();
    //砌墙
    protected abstract void buildWalls();
    //封顶
    protected abstract void roofed();

    public void build(){
        buildBasic();
        buildWalls();
        roofed();
    }
}
