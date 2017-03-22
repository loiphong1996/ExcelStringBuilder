package component;

import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Phong on 3/22/2017.
 */
public class TextArea extends JTextArea {
    private LinkedList<LineElement> lineStructure;

    public TextArea(LinkedList<LineElement> lineStructure) {
        super();
        this.setBorder(new LineBorder(Color.black));
        this.lineStructure = lineStructure;
    }

    @Override
    public String getText() {
        String textData = super.getText();
        String[] lines = convertToStringArray(textData);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.length;i++) {
            for (LineElement lineElement : lineStructure) {
                switch (lineElement.type){
                    case Sub:
                        stringBuilder.append(lineElement.values[0]);
                        break;
                    case Counter:
                        float initNumber = Float.parseFloat(lineElement.values[0]);
                        float step = Float.parseFloat(lineElement.values[1]);
                        String format = lineElement.values[2];
                        stringBuilder.append(String.format(format,initNumber+step*i));
                        break;
                    case LineContent:
                        stringBuilder.append(lines[i]);
                        break;
                }
            }
        }

        return stringBuilder.toString();
    }

    private String[] convertToStringArray(String data) {
        return data.split("\n");
    }

    public static class LineElement {
        public LineElementType type;
        public String[] values;

        public LineElement(LineElementType type, String[] values) {
            this.type = type;
            this.values = values;
        }
    }

    public enum LineElementType {
        Sub, Counter, LineContent
    }

    public static LineElementType generateLineElementType(Node node) {
        LineElementType type;
        switch (node.getNodeName()) {
            case "Sub":
                type = LineElementType.Sub;
                break;
            case "Counter":
                type = LineElementType.Counter;
                break;
            case "LineContent":
                type = LineElementType.LineContent;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return type;
    }

}
