package main;

import ui.LayoutSetup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by Phong on 3/20/2017.
 */
public class ExcelStringBuilder {
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JTextArea resultTextarea;
    private JCheckBox alwayTopCheckbox;
    private JScrollPane resultScrollPane;
    private JScrollPane inputScrollPane;
    private JPanel mainPane;
    private JPanel inputsPanel;
    private JFrame frame;
    private LinkedList<JComponent> inputComponents;

    private void createUIComponents() {
        inputsPanel = new JPanel();
        LayoutSetup layoutSetup = new LayoutSetup(inputsPanel);
        inputComponents = layoutSetup.bootstrap(new File("random path"));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ExcelStringBuilder");
        ExcelStringBuilder excelStringBuilder = new ExcelStringBuilder();
        frame.setContentPane(excelStringBuilder.mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        excelStringBuilder.frame = frame;
        excelStringBuilder.afterInit();
        frame.setVisible(true);
    }


    private void afterInit(){
        ExcelStringBuilder parent = this;
        ActionListener alwayTopCheckboxListener = e -> parent.handleAlwayTopCheckboxOnCheck(e);
        FocusListener resultTextFocusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                parent.handleResultTextFocusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                parent.handleResultTextFocusLost(e);
            }
        };

        this.alwayTopCheckbox.addActionListener(alwayTopCheckboxListener);
        this.resultTextarea.addFocusListener(resultTextFocusListener);
    }

    private void handleAlwayTopCheckboxOnCheck(ActionEvent e){
        frame.setAlwaysOnTop(alwayTopCheckbox.isSelected());
    }

    private void handleResultTextFocusGained(FocusEvent e){
        resultTextarea.setText(this.GatherInputs(inputComponents));
        resultTextarea.selectAll();
    }

    private void handleResultTextFocusLost(FocusEvent e){

    }

    private String GatherInputs(LinkedList<JComponent> jComponentLinkedList){
        StringBuilder stringBuilder = new StringBuilder();
        for (JComponent jComponent : jComponentLinkedList) {
            if(jComponent instanceof JTextField){
                JTextField jTextField = (JTextField)jComponent;
                String textValue = wrapExcel(jTextField.getText());
                stringBuilder.append(textValue);
            }
        }
        return stringBuilder.toString();
    }

    private String wrapExcel(String content){
        String prefix = "**";
        if(content.startsWith(prefix)){
            String type = content.substring(prefix.length(),content.length());
            switch (type){
                case "newline":
                    return Character.toString((char)13) + Character.toString((char)10);
            }
        }
        return content;
    }



}
