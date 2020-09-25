package zhw.impl.spi;

import zhw.SPI.Subscribe;

public class OtherSubscribe implements Subscribe {
    @Override
    public void follow() {
        System.out.println("关注了其他");
    }
}
