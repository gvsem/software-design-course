package org.example.game.inventory;

import org.example.game.inventory.item.Item;
import org.example.game.inventory.item.WearableItem;
import org.example.game.inventory.item.wearable.Boots;
import org.example.game.inventory.item.wearable.Helmet;
import org.example.game.inventory.item.wearable.Leggings;
import org.example.game.inventory.item.wearable.Plate;
import org.example.game.inventory.item.wearable.Poison;
import org.example.game.inventory.item.wearable.Sword;

import lombok.Getter;
import lombok.Setter;

/**
 * Active inventory is a set of currently equipped items by player
 */
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

   /**
    * Get equipped item by its type
    * @param type of equipped item
    * @return item, if it is present, otherwise - null
    */
   public WearableItem get(WearableItem.WearableType type) {
      return switch (type) {
         case Boots -> boots;
         case Plate -> plate;
         case Helmet -> helmet;
         case Sword -> sword;
         case Leggings -> leggings;
         default -> null;
      };
   }

   /**
    * Unequip selected item type
    * @param type of equipped item
    */
   public void remove(WearableItem.WearableType type) {
      switch (type) {
         case Boots -> boots = null;
         case Plate -> plate = null;
         case Helmet -> helmet = null;
         case Sword -> sword = null;
         case Leggings -> leggings = null;
      }
   }

   /**
    * Emplace new item and return previously equipped item
    * @param item new item
    * @return old one
    */
   public Item swap(Item item) {
      Item swapped = null;
      
      if (item instanceof Helmet) {
         swapped = helmet;
         helmet = (Helmet) item;
      } else if (item instanceof Plate) {
         swapped = plate;
         plate = (Plate) item;
      } else if (item instanceof Leggings) {
         swapped = leggings;
         leggings = (Leggings) item;
      } else if (item instanceof Boots) {
         swapped = boots;
         boots = (Boots) item;
      } else if (item instanceof Sword) {
         swapped = sword;
         sword = (Sword) item;
      }

      return swapped;
   }

   /**
    * Get hp additional points brought by current equipment
    * @return hp sum
    */
   public int getHpImprovement() {
      return (helmet == null ? 0 : helmet.getHpImprovement())
              + (plate == null ? 0 : plate.getHpImprovement())
              + (leggings == null ? 0 : leggings.getHpImprovement())
              + (boots == null ? 0 : boots.getHpImprovement())
              + (sword == null ? 0 : sword.getHpImprovement());
   }

   /**
    * Get strength additional points brought by current equipment
    * @return strength sum
    */
   public int getStrengthImprovement() {
      return (helmet == null ? 0 : helmet.getStrengthImprovement())
              + (plate == null ? 0 : plate.getStrengthImprovement())
              + (leggings == null ? 0 : leggings.getStrengthImprovement())
              + (boots == null ? 0 : boots.getStrengthImprovement())
              + (sword == null ? 0 : sword.getStrengthImprovement());
   }
}
