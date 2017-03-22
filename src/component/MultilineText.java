package component;

import javax.swing.*;

/**
 * Created by Phong on 3/22/2017.
 */
public class MultilineText extends JTextArea{
    private String prefix;
    private String postfix;

    public MultilineText() {
        prefix = "";
        postfix = "";
    }

    public MultilineText(String text, String prefix, String postfix) {
        super(text);
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @Override
    public String getText() {
        String textData = super.getText();
        String[] lines = convertToStringArray(textData);
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(prefix);
            stringBuilder.append(line);
            stringBuilder.append(postfix);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private String[] convertToStringArray(String data){
        return data.split("\n");
    }
}
