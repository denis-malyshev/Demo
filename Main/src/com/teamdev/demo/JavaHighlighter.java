package com.teamdev.demo;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaHighlighter {
    private static final Color DEFAULT_KEYWORD_COLOR = Color.blue;
    private static final Color FUNCTIONS_NAME_COLOR = Color.orange;
    private JTextPane jTextPane;
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
    private static String FUNCTIONS_NAMES_REGEX;

    static {
        StringBuilder buff = new StringBuilder("");
        StringBuilder buff1 = new StringBuilder("");
        buff1.append("(");
        for (String keyword : JAVA_KEYWORDS) {
            buff.append(keyword).append("|");
            buff1.append(keyword).append(" ?(|");
        }
        buff.deleteCharAt(buff.length() - 1);
        buff1.deleteCharAt(buff1.length() - 1);
        buff1.append(")");
        JAVA_KEYWORDS_REGEX = buff.toString();
        FUNCTIONS_NAMES_REGEX = buff1.toString();
    }

    public JavaHighlighter(JTextPane jTextPane) {
        this.jTextPane = jTextPane;
    }

    private void updateTextColor(int offset, int length, Color color) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);
        jTextPane.getStyledDocument().setCharacterAttributes(offset, length, attributeSet, true);
    }

    public void highlightKeyWords() {
        Pattern pattern = Pattern.compile(JAVA_KEYWORDS_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start(), DEFAULT_KEYWORD_COLOR);
    }

    public void highlightFunctions() {
        Pattern pattern = Pattern.compile(FUNCTIONS_NAMES_REGEX);
        System.out.println(pattern.pattern());
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start(), FUNCTIONS_NAME_COLOR);
    }
}


