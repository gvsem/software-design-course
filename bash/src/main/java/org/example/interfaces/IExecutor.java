package org.example.interfaces;

public interface IExecutor extends IEmbeddedExecutor {

    ISubstitutor getSubstitutor();

    IParser getParser();

}
