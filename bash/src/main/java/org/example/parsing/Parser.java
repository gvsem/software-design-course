package org.example.parsing;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.ast.concrete.token.EnvVariableToken;
import org.example.ast.concrete.token.StringToken;
import org.example.ast.sequential.PipeExpression;
import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.interfaces.IParser;
import org.example.parsing.exception.ParseException;
import org.example.parsing.preprocessing.PreprocessingState;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Standard implementation of IParser
 */
public class Parser implements IParser {

    @Override
    public AbstractExpression parse(String expression) throws ParseException {

        List<String> stringPipeExpressionsList = pipeSplit(expression);
        if (stringPipeExpressionsList.size() == 1) {
            return new UnresolvedCommandExpression(preprocess(expression));
        }
        // there is at least a pair of expressions,
        // accompanied by pipe

        int exprSize = stringPipeExpressionsList.size();
        // if the last character is a pipe, strip it
        if (stringPipeExpressionsList.get(exprSize - 1).strip().length() == 0) {
            exprSize -= 1;
        }
        // last two
        PipeExpression currPipeExpr = new PipeExpression(
                new UnresolvedCommandExpression(preprocess(stringPipeExpressionsList.get(exprSize - 2))),
                new UnresolvedCommandExpression(preprocess(stringPipeExpressionsList.get(exprSize - 1))));
        for (int i = exprSize - 3; i >= 0; i--) {
            currPipeExpr = new PipeExpression(
                    new UnresolvedCommandExpression(preprocess(stringPipeExpressionsList.get(i))),
                    currPipeExpr);
        }
        return currPipeExpr;
    }

    @Override
    public Command parse(ResolvedCommandExpression expression) throws ParseException {
        List<AbstractToken> tokens = expression.getTokens();
        List<EnvironmentVariable> vars = tokens.stream()
                .takeWhile(token -> token instanceof EnvVariableToken)
                .map(EnvVariableToken.class::cast)
                .map(evt -> new EnvironmentVariable(evt.getVariableName(), evt.getValue()))
                .toList();

        List<String> strings;
        try {
            strings = tokens.subList(vars.size(), tokens.size()).stream()
                    .map(token -> ((StringToken) token).getValue())
                    .toList();
        } catch (ClassCastException e) {
            throw new ParseException("Unexpected non-string token", e);
        }

        String executable = strings.isEmpty() ? "" : strings.get(0);
        List<String> args = strings.isEmpty() ? Collections.emptyList() : strings.subList(1, strings.size());

        if (EmbeddedCommand.isEmbeddedCommandName(executable)) {
            return EmbeddedCommand.createEmbeddedCommand(executable, args, vars);
        }

        return new Command(Path.of(executable), args, vars);
    }

    /**
     * Checks quoting and bracing & removes extra spaces.
     */
    private static String preprocess(String expression) throws ParseException {
        StringBuilder preprocessed = new StringBuilder();
        final String exMsg = "Mismatched parentheses / braces / quotes in \"" + expression + "\"";

        char c;
        PreprocessingState state = new PreprocessingState();
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);

            if (!Character.isWhitespace(c) || !state.isSpace()) {
                preprocessed
                        .append(Character.isWhitespace(c) && state.peek() != '\'' && state.peek() != '\"' ? ' ' : c);

                try {
                    state.add(c);
                } catch (ParseException e) {
                    throw new ParseException(exMsg, e);
                }
            }
        }

        if (state.isMismatch())
            throw new ParseException(exMsg);

        return preprocessed.toString().trim();
    }

    /**
     * Helper function that correctly splits string by pipes
     */
    private static List<String> pipeSplit(String expression) {
        // splits expression by pipes that are not surrounded by quotes
        boolean openDoubleQuote = false;
        boolean openSingleQuote = false;
        List<String> stringsPipeExpressionsList = new ArrayList<>();

        int prevPipeIdx = -1;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '|' && !openDoubleQuote && !openSingleQuote) {
                stringsPipeExpressionsList.add(expression.substring(prevPipeIdx + 1, i));
                prevPipeIdx = i;
            } else if (c == '\'' && !openDoubleQuote) {
                openSingleQuote ^= true;
            } else if (c == '"' && !openSingleQuote) {
                openDoubleQuote ^= true;
            }
        }
        stringsPipeExpressionsList.add(expression.substring(prevPipeIdx + 1, expression.length()));
        return stringsPipeExpressionsList;
    }
}
