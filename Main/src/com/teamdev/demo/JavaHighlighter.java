package com.teamdev.demo;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaHighlighter {
    private static final Color DEFAULT_KEYWORD_COLOR = Color.blue;
    private static final Color FUNCTIONS_NAME_COLOR = Color.orange;
    private static final Color PARAMS_COLOR =Color.magenta;
    private static final Color TEXT_FIELDS_COLOR =Color.green;
    private static final Color NUMBERS_COLOR =Color.cyan;
    private static final Color CONSTANTS_COLOR=Color.pink;
    private static final Color CHARACTERS_COLOR =Color.orange;
    private JTextPane jTextPane;
    private JScrollPane jScrollPane;
    private JTextPane lines=new JTextPane();
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
    private static String PARAMS_REGEX;
    private static String TEXT_FIELDS_REGEX;
    private static String NUMBERS_COLOR_REGEX;
    private static String CONSTANTS_COLOR_REGEX;
    private static String CHARACTERS_COLOR_REGEX;

    static {
        StringBuilder keyWordsBuff = new StringBuilder("");
        StringBuilder functionsBuff = new StringBuilder("");
        StringBuilder paramsBuff = new StringBuilder("");
        StringBuilder textFieldsBuff = new StringBuilder("");
        StringBuilder numbersBuff = new StringBuilder("");
        StringBuilder constantsBuff = new StringBuilder("");
        StringBuilder charactersBuff = new StringBuilder("");
        for (String keyword : JAVA_KEYWORDS) {
            keyWordsBuff.append(keyword+"\\W").append("|");
            functionsBuff.append(" [a-z]+\\(|");
        }
        keyWordsBuff.deleteCharAt(keyWordsBuff.length() - 1);
        functionsBuff.deleteCharAt(functionsBuff.length() - 1);
        paramsBuff.append("\\([A-Za-z]+\\)");
        textFieldsBuff.append("\\(\".+\"\\)");
        numbersBuff.append("[0-9]");
        constantsBuff.append("[^a-z]+[A-Z]{3,}|_[A-Z]+_");
        charactersBuff.append(";|,");
        JAVA_KEYWORDS_REGEX = keyWordsBuff.toString();
        FUNCTIONS_NAMES_REGEX = functionsBuff.toString();
        PARAMS_REGEX = paramsBuff.toString();
        TEXT_FIELDS_REGEX =textFieldsBuff.toString();
        NUMBERS_COLOR_REGEX =numbersBuff.toString();
        CONSTANTS_COLOR_REGEX=constantsBuff.toString();
        CHARACTERS_COLOR_REGEX = charactersBuff.toString();
    }

    public JavaHighlighter(JTextPane jTextPane, JScrollPane jScrollPane) {
        this.jTextPane = jTextPane;
        this.jScrollPane=jScrollPane;
        lines.setBackground(Color.lightGray);
        lines.setEditable(false);
        lines.setFont(jTextPane.getFont());
    }

    private void setLineNumbering() {
        int caretPosition=jTextPane.getCaretPosition()+1;
        Element root=jTextPane.getDocument().getDefaultRootElement();
        String text="";
        for(int i=1;i<=root.getElementIndex(caretPosition);i++) {
            text+=(i + System.getProperty("line.separator"));
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

    public void highlightCode() {
        setLineNumbering();
        highlightFields();
        //highlightParams();
        highlightFunctions();
        highlightConstants();
        highlightKeyWords();
        highlightNumbers();
        //highlightCharacters();
    }

    private void highlightKeyWords() {
        Pattern pattern = Pattern.compile(JAVA_KEYWORDS_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start(), DEFAULT_KEYWORD_COLOR);
    }

    private void highlightFunctions() {
        Pattern pattern = Pattern.compile(FUNCTIONS_NAMES_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start()-1, FUNCTIONS_NAME_COLOR);
    }

    private void highlightParams() {
        Pattern pattern = Pattern.compile(PARAMS_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start()+1, match.end() - match.start()-2, PARAMS_COLOR);
    }

    private void highlightFields() {
        Pattern pattern = Pattern.compile(TEXT_FIELDS_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start()+1, match.end() - match.start()-2, TEXT_FIELDS_COLOR);
    }

    private void highlightNumbers() {
        Pattern pattern = Pattern.compile(NUMBERS_COLOR_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start(), NUMBERS_COLOR);
    }
    private void highlightConstants() {
        Pattern pattern = Pattern.compile(CONSTANTS_COLOR_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start(), CONSTANTS_COLOR);
    }
    private void highlightCharacters() {
        Pattern pattern = Pattern.compile(CHARACTERS_COLOR_REGEX);
        Matcher match = pattern.matcher(this.jTextPane.getText());
        while (match.find())
            updateTextColor(match.start(), match.end() - match.start(), CHARACTERS_COLOR);
    }
}


