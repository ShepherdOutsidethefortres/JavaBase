package zhw.impl.spi;

import zhw.SPI.Subscribe;

public class MySubscribe implements Subscribe {
    @Override
    public void follow() {
        System.out.println("关注了公众号：hello world！");
    }
}
