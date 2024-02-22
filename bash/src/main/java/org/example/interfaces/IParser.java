package org.example.interfaces;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.command.Command;
import org.example.parsing.exception.ParseException;

public interface IParser {
    AbstractExpression parse(String expression) throws ParseException;
    Command parse(ResolvedCommandExpression expression) throws ParseException;
}
