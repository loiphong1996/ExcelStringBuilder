package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by Phong on 3/21/2017.
 */
public class LayoutSetup {
    private JPanel jpanel;
    private LinkedList<JComponent> jComponentLinkedList;
    public LayoutSetup(JPanel panel) {
        if(panel == null){
            throw new NullPointerException();
        }
        jComponentLinkedList = new LinkedList<>();
        this.jpanel = panel;
        GridBagLayout layout = new GridBagLayout();
        jpanel.setLayout(layout);
    }

    public LinkedList<JComponent> bootstrap(File path){
        GridBagConstraintsBuilder builder = new GridBagConstraintsBuilder();

        builder.setGridY(0);
        JTextField leftFieldCol1 = new JTextField();
        leftFieldCol1.setText("Label: ");
        leftFieldCol1.setEditable(true);
        jpanel.add(leftFieldCol1,builder.setGridX(0).setWeightX(0).getGridBagConstraints());

        JTextField centerFieldCol1 = new JTextField();
        centerFieldCol1.setEditable(true);
        jpanel.add(centerFieldCol1,builder.setGridX(1).setWeightX(2).getGridBagConstraints());

        JTextField rightFieldCol1 = new JTextField();
        rightFieldCol1.setText("**newline");
        rightFieldCol1.setEditable(true);
        jpanel.add(rightFieldCol1,builder.setGridX(2).setWeightX(0).getGridBagConstraints());

        builder.setGridY(1);
        JTextField leftFieldCol2 = new JTextField();
        leftFieldCol2.setText("Default status: ");
        leftFieldCol2.setEditable(true);
        jpanel.add(leftFieldCol2,builder.setGridX(0).setWeightX(0).getGridBagConstraints());


        JTextField centerFieldCol2 = new JTextField();
        centerFieldCol2.setEditable(true);
        jpanel.add(centerFieldCol2,builder.setGridX(1).setWeightX(2).getGridBagConstraints());

        JTextField rightFieldCol2 = new JTextField();
        rightFieldCol2.setText("**newline");
        rightFieldCol2.setEditable(true);
        jpanel.add(rightFieldCol2,builder.setGridX(2).setWeightX(0).getGridBagConstraints());

        builder.setGridY(2);
        JTextField leftFieldCol3 = new JTextField();
        leftFieldCol3.setText("Default value: ");
        leftFieldCol3.setEditable(true);
        jpanel.add(leftFieldCol3,builder.setGridX(0).setWeightX(0).getGridBagConstraints());


        JTextField centerFieldCol3 = new JTextField();
        centerFieldCol3.setEditable(true);
        jpanel.add(centerFieldCol3,builder.setGridX(1).setWeightX(2).getGridBagConstraints());

        JTextField rightFieldCol3 = new JTextField();
        rightFieldCol3.setText("**newline");
        rightFieldCol3.setEditable(true);
        jpanel.add(rightFieldCol3,builder.setGridX(2).setWeightX(0).getGridBagConstraints());

        builder.setGridY(3);
        JTextField leftFieldCol4 = new JTextField();
        leftFieldCol4.setText("Required field: ");
        leftFieldCol4.setEditable(true);
        jpanel.add(leftFieldCol4,builder.setGridX(0).setWeightX(0).getGridBagConstraints());


        JTextField centerFieldCol4 = new JTextField();
        centerFieldCol4.setEditable(true);
        jpanel.add(centerFieldCol4,builder.setGridX(1).setWeightX(2).getGridBagConstraints());

//        JTextField rightFieldCol4 = new JTextField();
//        rightFieldCol4.setText("**newline");
//        rightFieldCol4.setEditable(false);
//        jpanel.add(rightFieldCol4,builder.setGridX(2).setWeightX(0).getGridBagConstraints());

        jComponentLinkedList.add(leftFieldCol1);
        jComponentLinkedList.add(centerFieldCol1);
        jComponentLinkedList.add(rightFieldCol1);

        jComponentLinkedList.add(leftFieldCol2);
        jComponentLinkedList.add(centerFieldCol2);
        jComponentLinkedList.add(rightFieldCol2);

        jComponentLinkedList.add(leftFieldCol3);
        jComponentLinkedList.add(centerFieldCol3);
        jComponentLinkedList.add(rightFieldCol3);

        jComponentLinkedList.add(leftFieldCol4);
        jComponentLinkedList.add(centerFieldCol4);
//        jComponentLinkedList.add(rightFieldCol4);

        return jComponentLinkedList;
    }

    private class GridBagConstraintsBuilder{
        private GridBagConstraints gbc;

        public GridBagConstraintsBuilder() {
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(3,3,3,3);
        }

        public GridBagConstraints getGridBagConstraints() {
            return gbc;
        }

        public GridBagConstraintsBuilder setGridX(int gridX){
            gbc.gridx = gridX;
            return this;
        }

        public GridBagConstraintsBuilder setGridY(int gridY){
            gbc.gridy = gridY;
            return this;
        }

        public GridBagConstraintsBuilder setWeightX(int weightx){
            gbc.weightx = weightx;
            return this;
        }

        public GridBagConstraintsBuilder setWeightY(int weightY){
            gbc.weighty = weightY;
            return this;
        }

        public GridBagConstraintsBuilder setFill(int fill){
            gbc.fill = fill;
            return this;
        }

        public GridBagConstraintsBuilder setInsets(int top,int left,int bottom,int right){
            gbc.insets = new Insets(top, left, bottom, right);
            return this;
        }
    }

}
