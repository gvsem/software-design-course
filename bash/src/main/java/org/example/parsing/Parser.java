package org.example.parsing;


import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.ast.concrete.token.EnvVariableToken;
import org.example.ast.concrete.token.StringToken;
import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.interfaces.IParser;
import org.example.parsing.exception.ParseException;
import org.example.parsing.preprocessing.PreprocessingState;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Standard implementation of IParser
 */
public class Parser implements IParser {

    @Override
    public AbstractExpression parse(String expression) throws ParseException {
        return new UnresolvedCommandExpression(preprocess(expression));
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
    private String preprocess(String expression) throws ParseException {
        StringBuilder preprocessed = new StringBuilder();
        final String exMsg = "Mismatched parentheses / braces / quotes in \"" + expression + "\"";
        
        char c;
        PreprocessingState state = new PreprocessingState();
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            
            if (!Character.isWhitespace(c) || !state.isSpace()) {
                preprocessed.append(Character.isWhitespace(c) && state.peek() != '\'' && state.peek() != '\"'? ' ' : c);
                
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
}
