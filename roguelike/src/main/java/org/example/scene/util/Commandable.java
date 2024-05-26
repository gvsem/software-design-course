package org.example.scene.util;

import org.example.scene.command.Command;

public interface Commandable {
    void submit(Command command);
}
