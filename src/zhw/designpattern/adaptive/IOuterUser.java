package zhw.designpattern.adaptive;

import java.util.Map;

@SuppressWarnings("all")
public interface IOuterUser {
    Map getUserBaseInfo();
    Map getUserOfficeInfo();
    Map getUserHomeInfo();
}
