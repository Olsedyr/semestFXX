package com.example.semestfxx;

import java.util.Set;
import java.util.HashMap;

public class Room{

    private String description;
    private HashMap<String, Item> roomItems;

    public Room(String description) {
        this.description = description;
        roomItems = new HashMap<String, Item>();
    }

    public void setRoomItems(String itemName, Item item){
        roomItems.put(itemName,item);
    }

    public String getShortDescription() {
        return description;
    }
    public String getLongDescription() {
        return "Du er " + description + ".";
    }

    public String getRoomItemList() {
        return "Du ser " + roomItems.size() + " objekter af interesse:" + getItemString();
    }
    private String getItemString(){
        String returnString = "";
        Set<String> keys = roomItems.keySet();
        for(String roomItem : keys) {
            returnString += "\n" + "- " + roomItem;
        }
        return returnString;
    }

    public void removeItem(String itemName)
    {
        roomItems.remove(itemName);
    }

    public Item getItem(String itemName) { return roomItems.get(itemName);}
}

