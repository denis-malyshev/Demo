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
    private static String NUMBERS_REGEX;
    private static String CONSTANTS_REGEX;
    private static String COMMENTS_REGEX;
    private static String OVERRIDE_REGEX;

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
        for (String keyword : JAVA_KEYWORDS) {
            keyWordsBuff.append(keyword).append("\\W").append("|");
            functionsBuff.append(keyword).append(" [a-z]+[A-Za-z]+\\(|");
        }
        keyWordsBuff.deleteCharAt(keyWordsBuff.length() - 1);
        functionsBuff.deleteCharAt(functionsBuff.length() - 1);
        JAVA_KEYWORDS_REGEX = keyWordsBuff.toString();
        FUNCTIONS_NAMES_REGEX = functionsBuff.toString();
        TEXT_FIELDS_REGEX = "\".+\"";
        NUMBERS_REGEX = "[0-9]";
        CONSTANTS_REGEX = "( [A-Z]{2,} =)|(.[A-Z]{2,}_[A-Z]{2,}[,;\\)]{1})|(\\.[A-Z]{2,}[,;\\)]{1})";
        COMMENTS_REGEX = "//.+";
        OVERRIDE_REGEX = "@Override";
    }

    private final JTextPane jTextPane;
    private final JScrollPane jScrollPane;
    private final JTextPane lines;

    public JavaHighlighter(JTextPane jTextPane, JScrollPane jScrollPane) {
        this.lines = new JTextPane();
        this.jTextPane = jTextPane;
        this.jScrollPane = jScrollPane;
        this.jTextPane.setEditable(false);
        this.jTextPane.setFont(new Font("Consolas", Font.PLAIN, 12));
        lines.setBackground(Color.lightGray);
        lines.setEditable(false);
        lines.setFont(jTextPane.getFont());
    }

    public void highlightCode() {
        setLineNumbering();
        highlight(FUNCTIONS_NAMES_REGEX, Color.decode("#D2691E"), 0, -1);
        highlight(JAVA_KEYWORDS_REGEX, Color.blue, 0, 0);
        highlight(NUMBERS_REGEX, Color.decode("#1E90FF"), 0, 0);
        highlight(CONSTANTS_REGEX, Color.decode("#C71585"), 1, -2);
        highlight(COMMENTS_REGEX, Color.decode("#A9A9A9"), 0, 0);
        highlight(TEXT_FIELDS_REGEX, Color.decode("#228B22"), 0, 0);
        highlight(OVERRIDE_REGEX, Color.decode("#FFA500"), 0, 0);
    }

    private void setLineNumbering() {
        int caretPosition = jTextPane.getCaretPosition() + 1;
        Element root = jTextPane.getDocument().getDefaultRootElement();
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= root.getElementIndex(caretPosition); i++) {
            text.append(i).append(System.getProperty("line.separator"));
        }
        lines.setText(text.toString());
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


