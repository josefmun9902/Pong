import java.awt.*;
import javax.swing.*;
public class PongFrame extends JFrame
{
    private int panelWidth=600;
    private int panelHeight=300;
    public PongFrame(String title)
    {
        super(title);
        setDefaultCloseOperation(PongFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        PongPanel o=new PongPanel(panelWidth, panelHeight);
        Insets insets=getInsets();
        int width=o.getWidth()+insets.left+insets.right;
        int height=o.getHeight()+insets.top+insets.bottom;
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        add(o);
        pack();
        setVisible(true);
    }
}

