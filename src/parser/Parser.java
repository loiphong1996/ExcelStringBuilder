package parser;


import com.sun.corba.se.impl.io.TypeMismatchException;
import component.BorderedLabel;
import component.TextArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Phong on 3/21/2017.
 */
public class Parser {
    private static Parser parser;

    private Parser() {
    }

    public static Parser getInstance() {
        if (parser == null) {
            parser = new Parser();
        }
        return parser;
    }

    public static GUIPackage parse(File xmlFile) throws Exception {
        Parser parser = Parser.getInstance();
        GUIPackage guiPackage = new GUIPackage();
        Document document = parser.readXMLFile(xmlFile);
        Element root = document.getDocumentElement();

        for (int i = 0; i < root.getElementsByTagName("Line").getLength(); i++) {
            Node lineNode = root.getElementsByTagName("Line").item(i);
            if (lineNode.getNodeType() == Node.ELEMENT_NODE) {
                GUIPackage.Line line = guiPackage.addLine();
                for (int ii = 0; ii < lineNode.getChildNodes().getLength(); ii++) {
                    Node componentNode = lineNode.getChildNodes().item(ii);
                    if (componentNode.getNodeType() == Node.ELEMENT_NODE) {
                        JComponent jComponent = parser.parseComponent(componentNode);
                        line.add(jComponent);
                    }
                }
            }
        }

        return guiPackage;
    }

    private Document readXMLFile(File xmlFile) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.normalize();
        return doc;
    }

    private JComponent parseComponent(Node node) throws Exception {
        String type = node.getNodeName();
        JComponent jComponent;

        switch (type) {
            case "Label":
                jComponent = parseLabel(node);
                break;
            case "Input":
                jComponent = parseInput(node);
                break;
            case "TextArea":
                jComponent = parseTextArea(node);
                break;
            default:
                throw new TypeMismatchException();

        }

        return jComponent;
    }

    private JComponent parseLabel(Node node) {
        String labelText = node.getTextContent();
        BorderedLabel jLabel = new BorderedLabel(labelText, SwingConstants.CENTER);
        return jLabel;
    }

    private JComponent parseInput(Node node) {
        String defaultValue = node.getTextContent();
        return new JTextField(defaultValue);
    }

    private JComponent parseTextArea(Node node) {
        LinkedList<TextArea.LineElement> lineElements = new LinkedList<>();

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node subNode = node.getChildNodes().item(i);
            if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                TextArea.LineElementType lineElementType = TextArea.generateLineElementType(subNode);
                String[] values;
                switch (lineElementType) {
                    case Sub:
                        values = new String[]{subNode.getTextContent()};
                        lineElements.add(new TextArea.LineElement(TextArea.LineElementType.Sub, values));
                        break;
                    case Counter:
                        values = new String[]{((Element) subNode).getAttribute("start"), ((Element) subNode).getAttribute("step"), ((Element) subNode).getAttribute("format")};
                        lineElements.add(new TextArea.LineElement(TextArea.LineElementType.Counter, values));
                        break;
                    case LineContent:
                        lineElements.add(new TextArea.LineElement(TextArea.LineElementType.LineContent, new String[]{}));
                        break;
                }
            }
        }

        return new TextArea(lineElements);
    }


}
