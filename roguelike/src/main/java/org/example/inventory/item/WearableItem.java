package org.example.inventory.item;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public abstract class WearableItem extends Item {
    private final int hpImprovement;
    private final int strengthImprovement;
}
