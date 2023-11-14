package Game241.Project;


import java.util.ArrayList;
import java.util.Random;

public class Room {
    private final String name;
    private final String description;
    private final Item[] items = new Item[10];
    private Room north, south, east, west;
    public Character[] character = new Character[10];
    int count = 0;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addCharacters(Character charac) {  //add the character to this room
        int i = 0;
        int ch = 0;
        while (i < character.length && ch == 0) {
            if (character[i] == null) {
                character[i] = charac;
                character[i].assignRoom(this);
                ch = 1;
            }
            count++;
            i++;
        }
    }

    public void addItems(Item newItem) {
        int i = 0;
        int ch = 1;
        while (i < items.length && ch == 1) {
            if (items[i] == null) {
                items[i] = newItem;
                ch = 0;

            }
            i++;
        }
    }

    public void addNorth(Room north) {
        this.north = north;
    }

    public void addEast(Room east) {
        this.east = east;
    }

    public void addSouth(Room south) {
        this.south = south;
    }

    public void addWest(Room west) {
        this.west = west;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        String result = "";
        result = "Name:" + name + " Description:" + description;
        if (north != null) {
            result = result + "\nNorth: " + north.getName();
        }
        if (south != null) {
            result = result + "\nSouth: " + south.getName();
        }
        if (east != null) {
            result = result + "\nEast: " + east.getName();
        }
        if (west != null) {
            result = result + "\n West: " + west.getName();
        }
        for (int i = 0; i < character.length; i++) {
            if (character[i] != null) {

                String charac = character[i].toString();
                if (charac != null) {
                    result = result + "\nCharacter: " + charac;
                }
            }
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {

                String item = items[i].toString();
                if (item != null) {
                    result = result + " \nItem: " + item;
                }
            }
        }
        return result;
    }


    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public Item[] getItems() {
        return items;
    }

    public ArrayList<Adult> getAdults() {
        ArrayList<Adult> a = new ArrayList<>();
        for (Character c : character) {
            if (c instanceof Adult) {
                a.add((Adult) c);
            }
        }
        return a;
    }

    public ArrayList<Child> getChildren() {
        ArrayList<Child> child = new ArrayList<>();
        for (Character c : character) {
            if (c instanceof Child) {
                child.add((Child) c);
            }
        }
        return child;
    }


    public void changeRoom(Character c) {

        Random r = new Random();
        int x = r.nextInt(4);
        if (x == 0 && getNorth() != null) {
            getNorth().character[count] = c;
            c.setRoom(getNorth());
            System.out.println(c.getCharacterName() + " is scared and moved to north -"+c.getRoom().getName());
        }
        else if (x == 1 && getSouth() != null) {
            getSouth().character[count] = c;
            c.setRoom(getSouth());
            System.out.println(c.getCharacterName() + " is scared and moved to south-"+c.getRoom().getName());
        }
       else if (x == 2 && getEast() != null) {
            getEast().character[count] = c;
            c.setRoom(getEast());
            System.out.println(c.getCharacterName() + " is scared and moved to east -" +c.getRoom().getName());
}
        else if(x == 3 && getWest() != null) {
            getWest().character[count] = c;
            c.setRoom(getWest());
            System.out.println(c.getCharacterName() + " is scared and moved to west -" +c.getRoom().getName());

        }
    }

    public void removePlayer(Character c){
        for(int i = 0; i< character.length;i++){
            if (character[i] != null && (character[i] == c)){
                character[i] = null;
            }
        }
    }
    public Player getPlayer(){
        for(Character c : character){
            if( c instanceof Player){
                return (Player) c;
            }
        }
        return null;
    }


    public void clearItem() {
        int index = 0;
        for(Item i : getItems()){
            if(i != null && i.getItemStatus()){
                getItems()[index] = null;
                index++;
            }
        }
    }
    public Character[] getCharacter(){
        return character;
    }
}



















































































































































































































































































































































































































































































































































































































































































































