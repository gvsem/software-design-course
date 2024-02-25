package org.example.substitution;

import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.execution.context.Context;
import org.example.interfaces.ISubstitutor;

public class Substitutor implements ISubstitutor {

    @Override
    public ResolvedCommandExpression resolve(UnresolvedCommandExpression expression, Context context) {

        throw new UnsupportedOperationException();

//        StringBuilder resolvedExpression = new StringBuilder();
//        String originalExpression = expression.getCommand();
//
//        boolean inSingleQuote = false;
//        boolean inDoubleQuote = false;
//        // points on the last symbol or the last opened quote
//        int startIndex = 0;
//
//        for (int i = 0; i < originalExpression.length(); i++) {
//            char currentChar = originalExpression.charAt(i);
//            if (currentChar == '\'' && !inDoubleQuote) {
//                // Handle single-quoted string:
//                if (inSingleQuote) {
//                    // We want to keep the single quote sign
//                    String resolvedQuote = originalExpression.substring(startIndex, i + 1);
//                    // Here single quotes are counted
//                    if (resolvedQuote.length() > 2) {
//                        resolvedExpression.append(originalExpression, startIndex, i + 1);
//                    }
//                    startIndex = i + 1;
//                }
//                inSingleQuote = !inSingleQuote;
//            } else if (currentChar == '"' && !inSingleQuote) {
//                // Handle double-quoted string:
//                if (inDoubleQuote) {
//                    // Perform substitution
//                    String substituted = substitute(originalExpression.substring(startIndex + 1, i), context);
//                    if (substituted.length() > 0) {
//                        // Cover into single quotes
//                        resolvedExpression.append('\'').append(substituted).append('\'');
//                    }
//                    startIndex = i + 1;
//                }
//                inDoubleQuote = !inDoubleQuote;
//            } else if (!inSingleQuote && !inDoubleQuote) {
//                // Not in quotes, append character
//                resolvedExpression.append(currentChar);
//                startIndex = i + 1;
//            }
//            // TODO: add cases for substitution outside the quotations
//
//        }
//        return new ResolvedCommandExpression(resolvedExpression.toString().trim());

    }

    private static String substitute(String token, Context context) {
        // Identify all potential substitution places
        return token;
    }

}
