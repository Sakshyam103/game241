package Game241.Project;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {
    String roomName, roomDescription;
    String itemName, itemDescription;
    String itemAction;  // Itemactions
    String adultName, adultDescription;
    String playerName, playerDescription;
    String childName, childDescription;
    private int index = 0;
    private final Item[] item = new Item[15];
    public Character character;
    public Room[] room = new Room[8];
    public Room presentRoom;
    public Player player;
    private BinarySearchTree<String, Room> BST = new BinarySearchTree<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes att) {
        if (qName.equals("room")) {
            roomName = att.getValue("name");
            roomDescription = att.getValue("description");
            presentRoom = new Room(roomName, roomDescription);
            if (att.getValue("north") != null) {
                Room north = new Room(att.getValue("north"), null);
                presentRoom.addNorth(north);
            }
            if (att.getValue("east") != null) {
                Room east = new Room(att.getValue("east"), null);
                presentRoom.addEast(east);
            }
            if (att.getValue("south") != null) {
                Room south = new Room(att.getValue("south"), null);
                presentRoom.addSouth(south);
            }
            if (att.getValue("west") != null) {
                Room west = new Room(att.getValue("west"), null);
                presentRoom.addWest(west);
            }
        }

        if (qName.equals("item")) {
            itemName = att.getValue("name");
            itemDescription = att.getValue("description");
            itemAction = att.getValue("action");
            Item i = new Item(itemName, itemDescription);
            presentRoom.addItems(i);

        }
        if (qName.equals("adult")) {
            adultName = att.getValue("name");
            adultDescription = att.getValue("description");
            Adult a = new Adult(adultName, adultDescription); //SAB MA CORRECT GRNE. ADD ADULT OR ADD CHILD GRBne
//            if(a.scarenesslevel<50){
            presentRoom.addCharacters(a);
            a.setRoom(presentRoom);
//           else if(a.scarenesslevel>50 && a.scarenesslevel<100){
//                a.setRoom(presentRoom.changeRoom());
//            }

        }
        if (qName.equals("player")) {
            playerName = att.getValue("name");
            playerDescription = att.getValue("description");
            Player b = new Player(playerName, playerDescription);
            presentRoom.addCharacters(b);
            b.setRoom(presentRoom);
            player = b;

        }
        if (qName.equals("child")) {
            childName = att.getValue("name");
            childDescription = att.getValue("description");
            Child c = new Child(childName, childDescription);
            presentRoom.addCharacters(c);
            c.setRoom(presentRoom);
        }


    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("room")) {
            room[index] = presentRoom;
            index++;
       //     BST.add(presentRoom.getName(),presentRoom);
        }
    }

//    public Room[] getRoom() {
//
//        return room;
//    }

    public void setNeighborVals(){
        for(Room x: room){
            if(x.getNorth()!= null) {
                String roomNameNorth = x.getNorth().getName();
                for (Room y:room) {
                    if(roomNameNorth.equalsIgnoreCase(y.getName())){
                        x.addNorth(y);
                    }
                }
            }

            if(x.getSouth()!=null){
                String roomNameSouth=x.getSouth().getName();
                for (Room y:room) {
                    if(roomNameSouth.equalsIgnoreCase(y.getName())){
                        x.addSouth(y);
                    }
                }
            }
            if(x.getEast()!= null) {
                String roomNameEast = x.getEast().getName();
                for (Room y:room) {
                    if(roomNameEast.equalsIgnoreCase(y.getName())){
                        x.addEast(y);
                    }
                }
            }
            if(x.getWest()!= null) {
                String roomNameWest = x.getWest().getName();
                for (Room y:room) {
                    if(roomNameWest.equalsIgnoreCase(y.getName())){
                        x.addWest(y);
                    }
                }
            }
        }



    }

    public void fillBST(){
        for (Room x:room) {
            BST.add(x.getName(),x);
        }
    }



    public Player getPlayer() {
//        for (int i=0;i<room.length;i++) {
//            presentRoom=room[i];
//            if(player!=null){
//                presentRoom=room[i+1];
//            }
//        }
        return player;
    }

    public BinarySearchTree getBST(){
        return BST;
    }

}







