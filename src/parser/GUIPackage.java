package parser;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by Phong on 3/21/2017.
 */
public class GUIPackage {
    private LinkedList<Line> lines;

    public GUIPackage() {
        lines = new LinkedList<>();
    }

    public Line addLine(){
        Line line = new Line();
        this.addLine(line);
        return line;
    }

    public void addLine(Line line){
        lines.add(line);
    }

    public LinkedList<Line> getLines() {
        return lines;
    }

    public void setLines(LinkedList<Line> lines) {
        this.lines = lines;
    }

    public class Line{
        private LinkedList<JComponent> line;

        public Line() {
            line = new LinkedList<>();
        }

        public void add(JComponent jComponent){
            line.add(jComponent);
        }

        public LinkedList<JComponent> getLine() {
            return line;
        }

        public void setLine(LinkedList<JComponent> line) {
            this.line = line;
        }
    }
}
