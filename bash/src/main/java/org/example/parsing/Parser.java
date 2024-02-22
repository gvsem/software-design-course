package org.example.parsing;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.command.Command;
import org.example.interfaces.IParser;
import org.example.parsing.exception.ParseException;

public class Parser implements IParser {
    @Override
    public AbstractExpression parse(String expression) throws ParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command parse(ResolvedCommandExpression expression) throws ParseException {
        throw new UnsupportedOperationException();
    }
}
