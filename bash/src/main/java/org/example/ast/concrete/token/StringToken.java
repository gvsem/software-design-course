package org.example.ast.concrete.token;

import lombok.Getter;

public class StringToken extends AbstractToken{
    @Getter
    protected final String value;

    public StringToken(String value) {
        this.value = value;
    }
}
