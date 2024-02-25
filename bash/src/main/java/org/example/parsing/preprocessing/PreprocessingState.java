package org.example.parsing.preprocessing;


import lombok.Getter;
import org.example.parsing.exception.ParseException;

import java.util.ArrayDeque;
import java.util.Deque;


public class PreprocessingState {
    @Getter
    private boolean space = false;
    private final Deque<Character> stack = new ArrayDeque<>();
    
    
    public void add(char c) throws ParseException {
        if (Character.isWhitespace(c))
            space = stack.isEmpty() || stack.peek() != '\'' && stack.peek() != '\"';
        
        else {
            space = false;
            
            if (c == '\'') {
                if (!stack.isEmpty() && stack.peek() == '\'')
                    stack.pop();
                else if (stack.isEmpty() || stack.peek() != '\"')
                    stack.push(c);
            }
            
            else if (c == '\"') {
                if (!stack.isEmpty() && stack.peek() == '\"')
                    stack.pop();
                else if (stack.isEmpty() || stack.peek() != '\'')
                    stack.push(c);
            }
            
            else if ((c == '(' || c == '{') && (stack.isEmpty() || stack.peek() != '\'' || stack.peek() != '\"')) {
                stack.add(c);
            }
            
            else if (c == ')' || c == '}') {
                if (stack.isEmpty())
                    throw new ParseException("Mismatch");
                
                if (stack.peek() != '\'' || stack.peek() != '\"')
                    stack.pop();
            }
        }
    }
    
    
    public char peek() {
        return stack.isEmpty()? '\0' : stack.peek();
    }
    
    
    public boolean isMismatch() {
        return !stack.isEmpty();
    }
}
