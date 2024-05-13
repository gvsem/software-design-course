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

   public int getHpImprovement() {
      return (helmet == null ? 0 : helmet.getHpImprovement())
              + (plate == null ? 0 : plate.getHpImprovement())
              + (leggings == null ? 0 : leggings.getHpImprovement())
              + (boots == null ? 0 : boots.getHpImprovement())
              + (sword == null ? 0 : sword.getHpImprovement());
   }
   
   public int getStrengthImprovement() {
      return (helmet == null ? 0 : helmet.getStrengthImprovement())
              + (plate == null ? 0 : plate.getStrengthImprovement())
              + (leggings == null ? 0 : leggings.getStrengthImprovement())
              + (boots == null ? 0 : boots.getStrengthImprovement())
              + (sword == null ? 0 : sword.getStrengthImprovement());
   }
}
