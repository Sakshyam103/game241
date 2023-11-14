package Game241.Project;

 public abstract class Character {
    private Room presentroom;///
    private final String name;
    private final String description;
    private Character character;
   // public Item item;

    public Character(String name,String description){
        this.name=name;
        this.description=description;
    }

    @Override
    public String toString(){

        return "Name:"+name+" "+"Description:"+description;
    }

    public void assignRoom(Room actualRoom){

        presentroom=actualRoom;
    }

    public Room getRoom(){
        return  presentroom;
    }

    public void setRoom(Room room){
        this.presentroom = room;
    }

    public String getCharacterName(){
        return name;
    }

//    public Character getCharacter(){
//        return character;
//    }

//    public Room getActualRoom(){
//
//        return presentroom;
//    }

    }

    /*public void enterRoom(){
    }
    public void leaveRoom(){
    }
     */
