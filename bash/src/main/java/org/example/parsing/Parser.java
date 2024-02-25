package org.example.parsing;


import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.command.Command;
import org.example.interfaces.IParser;
import org.example.parsing.exception.ParseException;
import org.example.parsing.preprocessing.PreprocessingState;


public class Parser implements IParser {
    @Override
    public AbstractExpression parse(String expression) throws ParseException {
        return new UnresolvedCommandExpression(preprocess(expression));
    }
    
    
    @Override
    public Command parse(ResolvedCommandExpression expression) throws ParseException {
        throw new UnsupportedOperationException();
    }
    
    
    /**
     * Checks quoting and bracing & removes extra spaces.
     */
    private String preprocess(String expression) throws ParseException {
        StringBuilder preprocessed = new StringBuilder();
        final String exMsg = "Mismatched parentheses / braces / quotes in \"" + expression + "\"";
        
        char c = '\0';
        PreprocessingState state = new PreprocessingState();
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            
            if (Character.isWhitespace(c) && state.isSpace())
                continue;
            
            else {
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
