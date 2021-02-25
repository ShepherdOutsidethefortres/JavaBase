package zhw.designpattern.visitor.papercuprum;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MaterialWin extends JFrame implements ItemListener {
    private static final long serialVersionUID = 1L;
    private SetMaterial material;
    private Company visitor1, visitor2;
    private JPanel centerJP;
    private String[] select;

    MaterialWin() {
        super("利用访问者模式设计艺术公司和造币公司");
        JRadioButton art;
        JRadioButton mint;
        material = new SetMaterial();
        material.add(new Paper());
        material.add(new Cuprum());

        visitor1 = new ArtCompany();
        visitor2 = new Mint();

        this.setBounds(10, 10, 750, 350);
        this.setResizable(false);

        centerJP = new JPanel();

        this.add("Center", centerJP);

        JPanel southJP = new JPanel();
        JLabel yl = new JLabel("原材料有：铜和纸，请选择生产公司：");

        art = new JRadioButton("艺术公司", true);
        mint = new JRadioButton("造币公司");

        art.addItemListener(this);
        mint.addItemListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(art);
        group.add(mint);

        southJP.add(yl);
        southJP.add(art);
        southJP.add(mint);

        this.add("South", southJP);

        //产品名
        select = material.accept(visitor1).split(" ");

        showPicture(select[0], select[1]);

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
        if (jc.getText() == "造币公司") {
            select = material.accept(visitor2).split(" ");
        } else {
            select = material.accept(visitor1).split(" ");
        }

        showPicture(select[0], select[1]);

    }

    private void showPicture(String cuprum, String paper) {
        centerJP.removeAll();
        centerJP.repaint();

        String filename1 = "src/zhw/designpattern/visitor/papercuprum/" + cuprum + ".jpg";
        String filename2 = "src/zhw/designpattern/visitor/papercuprum/" + paper + ".jpg";

        JLabel lb = new JLabel(new ImageIcon(filename1), JLabel.CENTER);
        JLabel rb = new JLabel(new ImageIcon(filename2), JLabel.CENTER);

        centerJP.add(lb);
        centerJP.add(rb);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
