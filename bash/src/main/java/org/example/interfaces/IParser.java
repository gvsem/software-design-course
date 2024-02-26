package org.example.interfaces;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.command.Command;
import org.example.parsing.exception.ParseException;

/**
 * Interface of Parser module
 */
public interface IParser {

    /**
     * Parse string into abstract syntax tree
     * @param expression arbitrary string treated as bash expression
     * @return root of abstract syntax tree
     * @throws ParseException if expression is malformed
     */
    AbstractExpression parse(String expression) throws ParseException;

    /**
     * Convert ResolvedCommandExpression into executable Command <br><br>
     * For example, expression [Env("LOL", "1"), Env("KEK", 2), "echo", "25", "8 8 8"] <br>
     * should be translated into Command( <br>
     *      executable = "echo" - first not-Env token <br>
     *      environmentVariables = [Env("LOL", "1"), Env("KEK", 2)] = all Env tokens <br>
     *      commandLineArguments = all non-first not-Env tokens <br>
     * ) <br>
     * @param expression - expression to be translated into Command
     */
    Command parse(ResolvedCommandExpression expression) throws ParseException;
}
