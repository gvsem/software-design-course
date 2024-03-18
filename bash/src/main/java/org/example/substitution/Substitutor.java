package org.example.substitution;

import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.ast.concrete.token.EnvVariableToken;
import org.example.ast.concrete.token.StringToken;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;
import org.example.interfaces.ISubstitutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Substitutor performs splitting string stream into meaningful tokens 
 * and substituting variables with their values
 */
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

    /*
     * Resolves CommandExpression by splitting the expression into StringEnvironment
     * and EnvironmentTokens, performing substitution for the variables within the
     * context
     */
    @Override
    public ResolvedCommandExpression resolve(UnresolvedCommandExpression expression, IExecutor executor, Context context) {

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
            } else if (x == '$' && !openSingleQuote && i + 1 < expression.getCommand().length()) {
                StringBuilder substitution = new StringBuilder();
                if (expression.getCommand().charAt(i + 1) == '(') {
                    // TODO: убрать костыль с тем, что читаем до закрывающей скобки. или это и не костыль?
                    i += 2;
                    while (i < expression.getCommand().length() && (expression.getCommand().charAt(i) != ')')) {
                        substitution.append(expression.getCommand().charAt(i));
                        i++;
                    }
                    try {
                        token.append(new UnresolvedCommandExpression(substitution.toString()).executeAsExpression(executor, context));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException ignored) {}
                } else {
                    i++;
                    while (i < expression.getCommand().length() && Character.isLetterOrDigit(expression.getCommand().charAt(i))) {
                        substitution.append(expression.getCommand().charAt(i));
                        i++;
                    }
                    i--;
                    token.append(context.getEnvironment().get(substitution.toString()));
                }
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
