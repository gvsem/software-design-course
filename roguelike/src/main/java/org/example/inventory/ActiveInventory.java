package org.example.inventory;

import org.example.inventory.item.wearable.Boots;
import org.example.inventory.item.wearable.Helmet;
import org.example.inventory.item.wearable.Leggings;
import org.example.inventory.item.wearable.Plate;
import org.example.inventory.item.wearable.Sword;

import lombok.Getter;
import lombok.Setter;

public class ActiveInventory {

   @Getter @Setter
   private Helmet helmet = null;

   @Getter @Setter
   private Plate plate = null;

   @Getter @Setter
   private Leggings leggings = null;

   @Getter @Setter
   private Boots boots = null;

   @Getter @Setter
   private Sword sword = null;

}
