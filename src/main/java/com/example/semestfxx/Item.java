package com.example.semestfxx;

public class Item{

    protected String itemDescription;       //Text for the item itself
    protected String itemName;
    private final int points;
    public boolean toggleState;             //On or off for the ToggleItem class
    public boolean used;                    //Check if the ChoiceItem class item have been used or not
    public int correctChoice;               //Correct int choice
    protected String choiceDescription;     //Text for the choices
    public String choice1Text;              //Text shown when choosing one of the options
    public String choice2Text;
    public String choice3Text;
    public String choice4Text;
    public boolean pickedUp;                //Check if the TrashItem class item have been picked up or not

    public Item(String itemDescription, String itemName, int points){
        this.itemDescription = itemDescription;
        this.itemName = itemName;
        this.points = points;
    }

    public String getItemLongDescription(){
        return getItemDescription();
    }

    public String getItemDescription(){
        if(this instanceof ToggleItem) {
            return ((ToggleItem) this).changeItemDescription();
        }
        return itemDescription;
    }

    public  String getChoiceDescription() {return choiceDescription;}
    public int getItemPoints(){
        return points;
    }
    public boolean getItemState(){
        return toggleState;
    }
    public boolean getItemUsed(){
        return used;
    }
    public boolean getPickedUp(){
        return pickedUp;
    }


    public static class ToggleItem extends Item{
        private final String itemDescriptionTrue;
        private final String itemDescriptionFalse;
        public ToggleItem(String itemDescription, String itemName, String itemDescriptionTrue, String itemDescriptionFalse, int points, boolean toggleState) {
            super(itemDescription, itemName, points);
            this.itemDescriptionTrue = itemDescriptionTrue;
            this.itemDescriptionFalse = itemDescriptionFalse;
            this.toggleState = toggleState;
        }

        public String changeItemDescription() {             //Changes item description based on toggle state
            if (this.getItemState()) {
                this.itemDescription = itemDescriptionTrue;
                return itemDescription;
            } else {
                this.itemDescription = itemDescriptionFalse;
                return itemDescription;
            }
        }
    }

    public static class ChoiceItem extends Item{

        public ChoiceItem(String itemDescirption, String itemName, int points, String choiceDescription, String choice1Text, String choice2Text, boolean used, int correctChoice) {
            super(itemDescirption, itemName, points);
            this.choiceDescription = choiceDescription;
            this.choice1Text = choice1Text;
            this.choice2Text = choice2Text;
            this.used = used;
            this.correctChoice = correctChoice;
        }
    }

    public static class MultipleChoice extends Item{
        public MultipleChoice (String itemDescirption, String itemName, int points, String choiceDescription, String choice1Text, String choice2Text, String choice3Text, String choice4Text) {
            super(itemDescirption, itemName, points);
            this.choiceDescription = choiceDescription;
            this.choice1Text = choice1Text;
            this.choice2Text = choice2Text;
            this.choice3Text = choice3Text;
            this.choice4Text = choice4Text;
        }
    }

    public static class TrashItem extends Item{
        public TrashItem(String itemDescirption, String itemName, int points, boolean pickedUp) {
            super(itemDescirption, itemName, points);
            this.pickedUp = pickedUp;
        }
    }
}
