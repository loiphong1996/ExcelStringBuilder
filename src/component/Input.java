package component;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Phong on 3/23/2017.
 */
public class Input extends JTextField implements FocusListener{
    public Input(String text) {
        super(text);
        this.addFocusListener(this);
    }


    @Override
    public void focusGained(FocusEvent e) {
        this.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
