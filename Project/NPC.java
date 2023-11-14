package Game241.Project;

import java.util.Random;

public abstract class NPC extends Character {
    public int scarenesslevel;
    public NPC(String name, String description) {
        super(name, description);
    }

    public void changeRoom() {
            Random r = new Random();
            int x = r.nextInt(4);
            if (x == 1 && getRoom().getNorth() != null) {
                System.out.println("moved to north");
                setRoom(getRoom().getNorth());
            }
            if (x == 2 && getRoom().getSouth() != null) {
                System.out.println("moved to south");
                setRoom(getRoom().getSouth());
            }
            if (x == 3 && getRoom().getEast() != null) {
                System.out.println("moved to east ");
                setRoom(getRoom().getEast());
            }
            if (x == 0 && getRoom().getWest() != null) {
                System.out.println("moved to west");
                setRoom(getRoom().getWest());
            }
    }




}
