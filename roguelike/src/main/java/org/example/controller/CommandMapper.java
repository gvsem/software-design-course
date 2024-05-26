package org.example.controller;


import com.googlecode.lanterna.input.KeyStroke;
import org.example.game.entity.MoveDirection;
import org.example.game.inventory.item.WearableItem;
import org.example.scene.command.ApplyCommand;
import org.example.scene.command.Command;
import org.example.scene.command.MoveCommand;
import org.example.scene.command.MoveSelectInventoryCommand;
import org.example.scene.command.QuitCommand;
import org.example.scene.command.TakeOffCommand;

/**
 * Class to translate KeyStroke to game commands
 */
public class CommandMapper {
    public static Command map(KeyStroke stroke) {
        return switch (stroke.getKeyType()) {
            case ArrowUp -> new MoveCommand(MoveDirection.UP);
            case ArrowDown -> new MoveCommand(MoveDirection.DOWN);
            case ArrowLeft -> new MoveCommand(MoveDirection.LEFT);
            case ArrowRight -> new MoveCommand(MoveDirection.RIGHT);
            case Character -> switch (stroke.getCharacter()) {
                case 'A', 'a' -> new MoveSelectInventoryCommand(MoveSelectInventoryCommand.MoveSelectType.Left);
                case 'D', 'd' -> new MoveSelectInventoryCommand(MoveSelectInventoryCommand.MoveSelectType.Right);
                case 'E', 'e' -> new ApplyCommand();
                case '1' -> new TakeOffCommand(WearableItem.WearableType.Helmet);
                case '2' -> new TakeOffCommand(WearableItem.WearableType.Plate);
                case '3' -> new TakeOffCommand(WearableItem.WearableType.Leggings);
                case '4' -> new TakeOffCommand(WearableItem.WearableType.Boots);
                case '5' -> new TakeOffCommand(WearableItem.WearableType.Sword);
                default -> null;
            };
            case Enter -> new ApplyCommand();
            case Escape -> new QuitCommand();
            default -> null;
        };
    }
}
