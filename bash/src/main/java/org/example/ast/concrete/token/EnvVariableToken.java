package org.example.ast.concrete.token;

import lombok.Getter;

public class EnvVariableToken extends AbstractToken {
    @Getter
    private final String variableName;
    @Getter
    private final String value;

    public EnvVariableToken(String variableName, String value) {
        this.variableName = variableName;
        this.value = value;
    }
}
