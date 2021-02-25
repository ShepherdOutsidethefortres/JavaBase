package zhw.designpattern.strategy.demos.crabcook;

import javax.swing.*;

public class SteamedCrabs extends JLabel implements CrabCooking {
    private static final long serialVersionUID = 1L;

    @Override
    public void cookingMethod() {
        this.setIcon(new ImageIcon("src/zhw/designpattern/strategy/qingzheng.jpg"));
        this.setHorizontalAlignment(CENTER);
    }
}
