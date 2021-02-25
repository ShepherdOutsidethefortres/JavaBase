package zhw.designpattern.strategy.demos.crabcook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CrabCookingStrategy implements ItemListener {

    private JFrame f;
    private JPanel southJP, centerJP;
    private JRadioButton qz, hs;
    private Kitchen cf;
    private CrabCooking qzx, hsx;

    CrabCookingStrategy() {
        f = new JFrame("策略模式在大闸蟹做菜中的应用");
        f.setBounds(100, 100, 500, 400);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        southJP = new JPanel();
        centerJP = new JPanel();
        f.add("South", southJP);
        f.add("Center", centerJP);

        qz = new JRadioButton("清蒸大闸蟹");
        hs = new JRadioButton("红烧大闸蟹");

        qz.addItemListener(this);
        hs.addItemListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(qz);
        group.add(hs);

        southJP.add(qz);
        southJP.add(hs);

        cf = new Kitchen();

        qzx = new SteamedCrabs();
        hsx = new BraisedCrabs();

        f.setVisible(true);

    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        JRadioButton jc = (JRadioButton) e.getSource();
        if (jc == qz) {
            cf.setCrabCooking(qzx);
            cf.cookingMethod();
        } else if (jc == hs) {
            cf.setCrabCooking(hsx);
            cf.cookingMethod();
        }

        centerJP.removeAll();
        centerJP.repaint();
        centerJP.add((Component) cf.getCrabCooking());
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new CrabCookingStrategy();
    }
}
