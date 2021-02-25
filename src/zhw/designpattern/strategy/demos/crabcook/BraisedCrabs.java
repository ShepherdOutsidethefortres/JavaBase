package zhw.designpattern.strategy.demos.crabcook;

import javax.swing.*;

public class BraisedCrabs extends JLabel implements CrabCooking {
    private static final long serialVersionUID = 1L;

    @Override
    public void cookingMethod() {
        this.setIcon(new ImageIcon("src/zhw/designpattern/strategy/hongshao.jpg"));
        this.setHorizontalAlignment(CENTER);
    }
}
