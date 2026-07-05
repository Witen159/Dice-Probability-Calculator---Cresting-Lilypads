package parser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String input;
    private int pos = 0;

    public Tokenizer(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {

            char c = input.charAt(pos);

            // Number or Dice
            if (Character.isDigit(c)) {

                int start = pos;

                while (pos < input.length() &&
                        Character.isDigit(input.charAt(pos))) {
                    pos++;
                }

                // Dice?
                if (pos < input.length()
                        && (input.charAt(pos) == 'd'
                        || input.charAt(pos) == 'D')) {

                    pos++;

                    while (pos < input.length()
                            && Character.isDigit(input.charAt(pos))) {
                        pos++;
                    }

                    tokens.add(new Token(
                            TokenType.DICE,
                            input.substring(start, pos)
                    ));
                }
                else {

                    tokens.add(new Token(
                            TokenType.NUMBER,
                            input.substring(start, pos)
                    ));
                }

                continue;
            }

            switch (c) {

                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    pos++;
                    break;

                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-"));
                    pos++;
                    break;

                case '>':

                    if (peek('=')) {
                        tokens.add(new Token(TokenType.GREATER_EQUAL, ">="));
                        pos += 2;
                    } else {
                        tokens.add(new Token(TokenType.GREATER, ">"));
                        pos++;
                    }

                    break;

                case '<':

                    if (peek('=')) {
                        tokens.add(new Token(TokenType.LESS_EQUAL, "<="));
                        pos += 2;
                    } else {
                        tokens.add(new Token(TokenType.LESS, "<"));
                        pos++;
                    }

                    break;

                case '=':

                    if (peek('=')) {
                        tokens.add(new Token(TokenType.EQUAL, "=="));
                        pos += 2;
                    } else {
                        throw new RuntimeException("Expected ==");
                    }

                    break;

                case '!':

                    if (peek('=')) {
                        tokens.add(new Token(TokenType.NOT_EQUAL, "!="));
                        pos += 2;
                    } else {
                        throw new RuntimeException("Expected !=");
                    }

                    break;

                case '(':
                    tokens.add(new Token(TokenType.LEFT_PAREN, "("));
                    pos++;
                    break;

                case ')':
                    tokens.add(new Token(TokenType.RIGHT_PAREN, ")"));
                    pos++;
                    break;

                case '*':
                    tokens.add(new Token(TokenType.STAR, "*"));
                    pos++;
                    break;

                default:
                    throw new RuntimeException(
                            "Unexpected character: " + c
                    );
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }

    private boolean peek(char expected) {

        return pos + 1 < input.length()
                && input.charAt(pos + 1) == expected;

    }
}
