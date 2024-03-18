package org.example.ast.concrete.token;

import lombok.Getter;

/**
 * Token of resolved command corresponding to one command line argument
 */
public class StringToken extends AbstractToken {
    @Getter
    protected final String value;

    public StringToken(String value) {
        this.value = value;
    }
}
