package com.teamdev.demo;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaHighlighter {

    private static String JAVA_KEYWORDS_REGEX;
    private static String FUNCTIONS_NAMES_REGEX;
    private static String TEXT_FIELDS_REGEX;
    private static String NUMBERS_COLOR_REGEX;
    private static String CONSTANTS_COLOR_REGEX;
    private static String COMMENTS_COLOR_REGEX;

    private static final String[] JAVA_KEYWORDS = new String[]{"abstract",
            "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float", "for", "goto",
            "if", "implements", "import", "instanceof", "int", "long",
            "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try",
            "void", "volatile", "while", "false", "null", "true"};

    static {
        StringBuilder keyWordsBuff = new StringBuilder("");
        StringBuilder functionsBuff = new StringBuilder("");
        StringBuilder textFieldsBuff = new StringBuilder("");
        StringBuilder numbersBuff = new StringBuilder("");
        StringBuilder constantsBuff = new StringBuilder("");
        StringBuilder commentsBuff = new StringBuilder("");
        for (String keyword : JAVA_KEYWORDS) {
            keyWordsBuff.append(keyword).append("\\W").append("|");
            functionsBuff.append(" [a-z]+\\(|");
        }
        keyWordsBuff.deleteCharAt(keyWordsBuff.length() - 1);
        functionsBuff.deleteCharAt(functionsBuff.length() - 1);
        textFieldsBuff.append("\".+\"");
        numbersBuff.append("[0-9]");
//        constantsBuff.append("([^a-z]+[A-Z]{3,}[^a-z]+)|_[A-Z]+_");
        constantsBuff.append("(_*[A-Z]{3,}_*)|_[A-Z]+_");
        commentsBuff.append("//.+");
        JAVA_KEYWORDS_REGEX = keyWordsBuff.toString();
        FUNCTIONS_NAMES_REGEX = functionsBuff.toString();
        TEXT_FIELDS_REGEX = textFieldsBuff.toString();
        NUMBERS_COLOR_REGEX = numbersBuff.toString();
        CONSTANTS_COLOR_REGEX = constantsBuff.toString();
        COMMENTS_COLOR_REGEX = commentsBuff.toString();
    }

    private final JTextPane jTextPane;
    private final JScrollPane jScrollPane;
    private final JTextPane lines;

    public JavaHighlighter(JTextPane jTextPane, JScrollPane jScrollPane) {
        this.lines = new JTextPane();
        this.jTextPane = jTextPane;
        this.jScrollPane = jScrollPane;
        this.jTextPane.setEditable(false);
        lines.setBackground(Color.lightGray);
        lines.setEditable(false);
        lines.setFont(jTextPane.getFont());
    }

    public void highlightCode() {
        setLineNumbering();
        highlight(JAVA_KEYWORDS_REGEX, Color.blue, 0, 0);
        highlight(NUMBERS_COLOR_REGEX, Color.cyan, 0, 0);
        highlight(FUNCTIONS_NAMES_REGEX, Color.orange, 0, -1);
        highlight(CONSTANTS_COLOR_REGEX, Color.pink, 0, 0);
        highlight(COMMENTS_COLOR_REGEX, Color.lightGray, 0, 0);
        highlight(TEXT_FIELDS_REGEX, Color.green, 0, 0);
    }

    private void setLineNumbering() {
        int caretPosition = jTextPane.getCaretPosition() + 1;
        Element root = jTextPane.getDocument().getDefaultRootElement();
        String text = "";
        for (int i = 1; i <= root.getElementIndex(caretPosition); i++) {
            text += (i + System.getProperty("line.separator"));
        }
        lines.setText(text);
        jScrollPane.getViewport().add(jTextPane);
        jScrollPane.setRowHeaderView(lines);
    }

    private void updateTextColor(int offset, int length, Color color) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);
        jTextPane.getStyledDocument().setCharacterAttributes(offset, length, attributeSet, true);
    }


    private void highlight(String regex, Color color, int offsetStart, int offsetEnd) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start() + offsetStart, match.end() - match.start() + offsetEnd, color);
    }
}


