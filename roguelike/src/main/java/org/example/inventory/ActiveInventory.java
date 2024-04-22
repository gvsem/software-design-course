package org.example.inventory;

import org.example.inventory.item.Item;
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

}
