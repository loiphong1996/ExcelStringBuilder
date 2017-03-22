package ui;

import parser.GUIPackage;
import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by Phong on 3/21/2017.
 */
public class LayoutSetup {
    private JPanel jpanel;
    public LayoutSetup(JPanel panel) {
        if(panel == null){
            throw new NullPointerException();
        }
        this.jpanel = panel;
        GridBagLayout layout = new GridBagLayout();
        jpanel.setLayout(layout);
    }

    public LinkedList<JComponent> bootstrap(File path) throws Exception {
        GUIPackage guiPackage = Parser.parse(path);
        LinkedList<JComponent> components = new LinkedList<>();
        jpanel.removeAll();
        GridBagConstraintsBuilder builder = new GridBagConstraintsBuilder();

        for (int i = 0; i < guiPackage.getLines().size(); i++) {
            GUIPackage.Line line = guiPackage.getLines().get(i);
            builder.setDefault();
            builder.setGridY(i);
            int gridX = 0;
            for (JComponent jComponent : line.getLine()) {
                builder.setGridX(gridX);
                gridX++;
                if (jComponent instanceof JLabel) {
                    builder.setWeightX(0);
                } else if (jComponent instanceof JTextField){
                    builder.setWeightX(2);
                } else if (jComponent instanceof JTextArea){
                    builder.setWeightX(3);
                    builder.setWeightY(2);
                }
                jpanel.add(jComponent,builder.getGridBagConstraints());
                components.add(jComponent);
            }
        }

        return components;
    }

    private class GridBagConstraintsBuilder{
        private GridBagConstraints gbc;

        GridBagConstraintsBuilder() {
            gbc = new GridBagConstraints();
            this.setDefault();
        }

        GridBagConstraints getGridBagConstraints() {
            return gbc;
        }

        GridBagConstraintsBuilder setGridX(int gridX){
            gbc.gridx = gridX;
            return this;
        }

        GridBagConstraintsBuilder setGridY(int gridY){
            gbc.gridy = gridY;
            return this;
        }

        GridBagConstraintsBuilder setWeightX(int weightx){
            gbc.weightx = weightx;
            return this;
        }

        GridBagConstraintsBuilder setWeightY(int weightY){
            gbc.weighty = weightY;
            return this;
        }

        GridBagConstraintsBuilder setFill(int fill){
            gbc.fill = fill;
            return this;
        }

        GridBagConstraintsBuilder setInsets(int top,int left,int bottom,int right){
            gbc.insets = new Insets(top, left, bottom, right);
            return this;
        }

        GridBagConstraintsBuilder setDefault() {
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(3, 3, 3, 3);
            return this;
        }
    }

}
