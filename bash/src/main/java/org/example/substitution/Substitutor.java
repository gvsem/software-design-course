package org.example.substitution;

import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.ast.concrete.token.EnvVariableToken;
import org.example.ast.concrete.token.StringToken;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.interfaces.ISubstitutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Substitutor implements ISubstitutor {

    private static EnvVariableToken parseEnvironmentToken(String token) {
        List<String> parts = List.of(token.split("="));
        if (parts.size() < 2) {
            throw new IllegalArgumentException("parser tried to interpret it as a env token");
        }
        String varName = token.substring(0, parts.get(0).length());
        String expression = token.substring(parts.get(0).length() + 1);

        StringBuilder t = new StringBuilder();
        boolean openDoubleQuote = false;
        boolean openSingleQuote = false;

        for (int i = 0; i < expression.length(); i++) {
            char x = expression.charAt(i);
            if (x == ' ' && !openDoubleQuote && !openSingleQuote) {
                break;
            } else if (x == '\'' && !openDoubleQuote) {
                openSingleQuote ^= true;
            } else if (x == '"' && !openSingleQuote) {
                openDoubleQuote ^= true;
            } else {
                t.append(x);
            }
        }

        return new EnvVariableToken(varName, t.toString());
    }

    @Override
    public ResolvedCommandExpression resolve(UnresolvedCommandExpression expression, Context context) {

        List<AbstractToken> tokens = new ArrayList<>();

        StringBuilder token = new StringBuilder();
        int startPos = 0;

        boolean openDoubleQuote = false;
        boolean openSingleQuote = false;

        boolean canBeEnvironmentToken = true;

        for (int i = 0; i < expression.getCommand().length(); i++) {
            char x = expression.getCommand().charAt(i);
            if (x == ' ' && !openDoubleQuote && !openSingleQuote) {
                if (token.length() > 0) {
                    String s = token.toString();
                    if (canBeEnvironmentToken && s.matches("^[A-Za-z0-9]*=.*")) {
                        tokens.add(parseEnvironmentToken(expression.getCommand().substring(startPos, i)));
                    } else {
                        canBeEnvironmentToken = false;
                        tokens.add(new StringToken(s));
                    }
                    token.setLength(0);
                    startPos = i + 1;
                }
            } else if (x == '\'' && !openDoubleQuote) {
                openSingleQuote ^= true;
            } else if (x == '"' && !openSingleQuote) {
                openDoubleQuote ^= true;
            } else {
                token.append(x);
            }
        }

        if (token.length() > 0) {
            String s = token.toString();
            if (canBeEnvironmentToken && s.matches("^[A-Za-z0-9]*=.*")) {
                tokens.add(parseEnvironmentToken(expression.getCommand().substring(startPos)));
            } else {
                tokens.add(new StringToken(s));
            }
        }

        return new ResolvedCommandExpression(tokens);

    }

}
