package zhw.SPI;

import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args){
        ServiceLoader<Subscribe> services = ServiceLoader.load(Subscribe.class);
        for (Subscribe sub:services){
            sub.follow();
        }
    }
}
