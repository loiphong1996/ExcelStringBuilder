package main;

import ui.LayoutSetup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.LinkedList;

interface ErrorMessageShowListener {
    void showErrorDialog(String message);
}

/**
 * Created by Phong on 3/20/2017.
 */
public class ExcelStringBuilder implements ErrorMessageShowListener {
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JTextArea resultTextarea;
    private JCheckBox alwayTopCheckbox;
    private JScrollPane resultScrollPane;
    private JScrollPane inputScrollPane;
    private JPanel mainPane;
    private JPanel inputsPanel;
    private JComboBox patternComboBox;
    private JFrame frame;
    private LinkedList<JComponent> inputComponents;
    private LayoutSetup layoutSetup;
    private File[] patterns;

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("ExcelStringBuilder");
        ExcelStringBuilder excelStringBuilder = new ExcelStringBuilder();
        frame.setContentPane(excelStringBuilder.mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        excelStringBuilder.frame = frame;
        excelStringBuilder.afterInit();
    }

    private void createUIComponents() {
        inputsPanel = new JPanel();
    }

    private void afterInit() {
        try {
            initApp();
            //------------
            initPatternComboBox();
        } catch (Exception ex) {
            showErrorDialog(ex.getMessage());
        }
    }


    private void initApp() {
        ExcelStringBuilder parent = this;
        FocusListener resultTextFocusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                parent.handleResultTextFocusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        };
        layoutSetup = new LayoutSetup(inputsPanel);
        this.alwayTopCheckbox.addActionListener(this::handleAlwayTopCheckboxOnCheck);
        this.resultTextarea.addFocusListener(resultTextFocusListener);
        this.patternComboBox.addActionListener(this::handleComboBoxChoose);
    }

    private void initPatternComboBox() throws Exception {
        File patternDir = new File("pattern");
        if (patternDir.exists() && patternDir.isDirectory()) {
            patterns = patternDir.listFiles();
            for (File patternFile : patterns) {
                patternComboBox.addItem(patternFile.getName());
            }
        } else {
            showErrorDialog("Invalid pattern folder");
        }
    }


    private void buildInputPanel(File xmlFile) throws Exception {
        inputComponents = layoutSetup.bootstrap(xmlFile);
        frame.pack();
        frame.repaint();
    }

    private void handleAlwayTopCheckboxOnCheck(ActionEvent e) {
        frame.setAlwaysOnTop(alwayTopCheckbox.isSelected());
    }

    private void handleResultTextFocusGained(FocusEvent e) {
        resultTextarea.setText(this.GatherInputs(inputComponents));
        resultTextarea.selectAll();
    }


    private void handleComboBoxChoose(ActionEvent e) {
        if (e.getSource() == this.patternComboBox) {
            String fileName = (String) patternComboBox.getSelectedItem();
            for (File pattern : this.patterns) {
                if (pattern.getName().equals(fileName)) {
                    try {
                        buildInputPanel(pattern);
                    } catch (Exception ex) {
                        this.showErrorDialog(ex.getMessage());
                    }
                }
            }
        }
    }

    private String GatherInputs(LinkedList<JComponent> jComponentLinkedList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (JComponent jComponent : jComponentLinkedList) {
            if (jComponent instanceof JTextField) {
                JTextField jTextField = (JTextField) jComponent;
                String textValue = wrapText(jTextField.getText());
                stringBuilder.append(textValue);
            } else if (jComponent instanceof JLabel) {
                JLabel jLabel = (JLabel) jComponent;
                String textValue = wrapText(jLabel.getText());
                stringBuilder.append(textValue);
            } else if (jComponent instanceof JTextArea){
                JTextArea jTextArea = (JTextArea) jComponent;
                String textValue = wrapText(jTextArea.getText());
                stringBuilder.append(textValue);
            }
        }
        return stringBuilder.toString();
    }

    private String wrapText(String content) {
        content = content.replace("**Newline","\r\n");
        content = content.replace("**Tab","    ");
        return content;
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}