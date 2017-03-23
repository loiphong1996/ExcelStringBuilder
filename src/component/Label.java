package component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Phong on 3/23/2017.
 */
public class Label extends JLabel {
    public Label(String text) throws HeadlessException {
        super(text);
        this.setBorder(new LineBorder(Color.black, 1));
    }

    public Label(String text, int alignment) throws HeadlessException {
        super(text, alignment);
        this.setBorder(new LineBorder(Color.black, 1));
    }
}
