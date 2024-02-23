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
        if (this.variables.containsKey(variable)) {
            return this.variables.getOrDefault(variable, "");
        } else if (System.getenv().containsKey(variable)) {
            return System.getenv(variable);
        } else {
            return "";
        }
    }

}
