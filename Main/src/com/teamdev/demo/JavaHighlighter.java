package com.teamdev.demo;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaHighlighter {
    private static final Color DEFAULT_KEYWORD_COLOR = Color.BLUE;
    private JTextPane jTextPane;
    private StyledDocument styledDocument;
    private static final String[] JAVA_KEYWORDS = new String[]{"abstract",
            "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float", "for", "goto",
            "if", "implements", "import", "instanceof", "int", "long",
            "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try",
            "void", "volatile", "while", "false", "null", "true"};

    private static String JAVA_KEYWORDS_REGEX;

    static {
        StringBuilder buff = new StringBuilder("");
        for (String keyword : JAVA_KEYWORDS) {
            buff.append(keyword).append("|");
        }
        buff.deleteCharAt(buff.length() - 1);
        JAVA_KEYWORDS_REGEX = buff.toString();
    }

    public JavaHighlighter(JTextPane jTextPane) {
        this.jTextPane = jTextPane;
        styledDocument = jTextPane.getStyledDocument();
    }

    private void updateTextColor(int offset, int length, Color color) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);
        styledDocument.setCharacterAttributes(offset, length, attributeSet, true);
    }

    private void updateTextColor(int offset, int length) {
        updateTextColor(offset, length, DEFAULT_KEYWORD_COLOR);
    }

    public void changeColor() {
        Pattern pattern = Pattern.compile(JAVA_KEYWORDS_REGEX);
        Matcher match = pattern.matcher(jTextPane.getText());
        while (match.find()) {
            updateTextColor(match.start(), match.end() - match.start());
        }
    }

}
