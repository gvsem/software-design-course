package org.example.execution.context;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private final Map<String, String> variables = new HashMap<>();

    public Environment() {}

    public void put(String variable, String value) {
        this.variables.put(variable, value);
    }

    public String get(String variable) {
        return this.variables.getOrDefault(variable, "");
    }

}
