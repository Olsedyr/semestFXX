package com.example.semestfxx;

import java.util.HashMap;
import java.util.Set;

public class Inventory {
    ///Inventory that stores the trash player picked up in the game
    public HashMap<String, Item> trash;

    public Inventory() {
        trash = new HashMap<String, Item>();
    }

    public void addTrash(String string, Item item) {
        trash.put( string, item);
    }

    public String getInventoryString() {
        String returnString = "Din affaldspose:";
        Set<String> keys = trash.keySet();
        for (String trash : keys) {
            returnString += "\n" + "- " + trash;
        }
        return returnString;
    }
}
