package component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Phong on 3/23/2017.
 */
public class BorderedLabel extends JLabel {
    public BorderedLabel(String text) throws HeadlessException {
        super(text);
        this.setBorder(new LineBorder(Color.black, 1));
    }

    public BorderedLabel(String text, int alignment) throws HeadlessException {
        super(text, alignment);
        this.setBorder(new LineBorder(Color.black, 1));
    }
}
