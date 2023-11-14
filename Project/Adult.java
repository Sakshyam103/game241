package Game241.Project;
public class Adult extends NPC {
    private double adultScareLevel = 0;
    private Adult temp;
    private Boolean hasLeftHouse = false;
    public Adult(String Name, String Description) {
        super(Name, Description);
    }

    public Boolean adultScare(int incr) {
        hasLeftHouse  = false;
        if (adultScareLevel < 50) {
            adultScareLevel = adultScareLevel + incr;
            System.out.println(getCharacterName()+"'s scareness level is:" + adultScareLevel);
            if(adultScareLevel >= 50){
                temp = this;
                getRoom().removePlayer(this);
                getRoom().changeRoom(temp);
                getRoom().clearItem();
            }
        }
        else if (adultScareLevel >= 50 && adultScareLevel < 100) {
            adultScareLevel = adultScareLevel + incr;
            System.out.println(getCharacterName()+"'s scareness level is:" + adultScareLevel);
            if (adultScareLevel >= 100) {
                System.out.println(getCharacterName() + "has left the house");
                getRoom().removePlayer(this);
                hasLeftHouse = true;
                System.out.println(getRoom());
            }
        }
        return hasLeftHouse;
    }
}
