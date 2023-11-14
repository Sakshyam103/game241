package Game241.Project;

public class Child extends NPC {
    private double childScareLevel = 0;
    private Child temp;
    private Boolean hasLeftHouse = false;

    public Child(String name, String description) {
        super(name, description);
    }

//    public void scared(double scarenesslevel) {
//
//    }

    public Boolean childScare(int incr) {
        hasLeftHouse = false;
        if (childScareLevel < 50) {
            childScareLevel = childScareLevel + (1.5 * incr);
            System.out.println(getCharacterName()+"'s scareness level is:" + childScareLevel);
            if (childScareLevel >= 50) {
                temp = this;
                getRoom().changeRoom(temp);
                getRoom().removePlayer(this);
                getRoom().clearItem();

            }
        } else if (childScareLevel > 50 && childScareLevel < 100) {
            childScareLevel = childScareLevel + incr;
            System.out.println(getCharacterName()+"'s scareness level is:" + childScareLevel);
            if (childScareLevel > 100) {
            getRoom().removePlayer(this);
                hasLeftHouse = true;
            }
        }
        return hasLeftHouse;

//        } else if (adultScareLevel >= 50 && adultScareLevel < 100) {
//            adultScareLevel = adultScareLevel + incr;
//            System.out.println("The adult scareness level is:" + adultScareLevel);
//            if (adultScareLevel > 100) {
//                getRoom().removePlayer(getCharacterName());
//            }
//        }

        }
    }

