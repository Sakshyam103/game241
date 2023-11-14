package Game241.Project;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;



public class Player extends Character {
    private Timer timer;
    private AtomicInteger timeLeft;
    public int incr;    public BinarySearchTree<String, Room> bst;



    public Player(String Name, String Description) {
        super(Name, Description);

    }

    public void setBst(BinarySearchTree<String,Room> bst){
        this.bst=bst;
    }

    public BinarySearchTree<String,Room> getBst(){
        if(bst!=null){
        return bst;}
        return null;
    }

    public void initTimer(int secs) {
        timeLeft = new AtomicInteger(secs);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int tl = timeLeft.decrementAndGet();
                if (tl == 0) {
                    System.out.println("GoodBYE, You LOST!!");
                    System.exit(3);
                }
            }
        };

        timer = new Timer();
        timer.schedule(task, 0, 1000);
    }
    public void startTimer() {
        initTimer(60000);
    }
    public AtomicInteger getTimeLeft() {
        return timeLeft;
    }

    public ArrayList<String> commandSplit(String command) {
        if (command.contains(":")) {
            int index = command.indexOf(':');

            ArrayList<String> splCMD = new ArrayList<>();
            splCMD.add(command.substring(0, index));
            splCMD.add(command.substring(index + 1, command.length()));
            return splCMD;
        }
        return null;


    }




    public void playWithCheatMode(Scanner S) {

        System.out.println("Cheat mode is activated");
        String userInput = S.nextLine();

        while (userInput != "exit") {
            ArrayList<String> spltCMD = commandSplit(userInput);
            if (userInput.equals("help")) {
                System.out.println("look – this command immediately outputs to the user the complete information pertaining to the presentroom the Player is in");
                System.out.println("north– the Player leaves the current presentroom and enters its north presentroom. Be careful about special cases");
                System.out.println("south– the Player leaves the current presentroom and enters its south presentroom. Be careful about special cases");
                System.out.println("east– the Player leaves the current presentroom and enters its east presentroom. Be careful about special cases");
                System.out.println("west– the Player leaves the current presentroom and enters its west presentroom. Be careful about special cases");
                System.out.println("shake:[itemname]- this command allows to shake the objects in the presentroom, causing a reaction from the other Characters. Allow the user to type the actual item name, e.g., shake:white lamp.");
                System.out.println("posses:[itemname]- this command allows to posses the objects in the presentroom, causing a reaction from the other Characters. Allow the user to type the actual item name, e.g., posses:glass.");
                System.out.println("throw:[itemname]- this command allows to throw the objects in the presentroom, causing a reaction from the other Characters. Allow the user to type the actual item name, e.g., throw:vessel.");
                System.out.println("exit, quit – these commands print a 'Goodbye' message and quit the program.");
                System.out.println("You have " + timeLeft + "seconds left");
            }
            else if (userInput.startsWith("look:")){
                String[] command = userInput.split(":");
                if(command[1].equals("all")){
                    bst.Traverse();
                }else{
                    Room foundRoom = bst.find(command[1]);
                    if(foundRoom == null){
                        System.out.println("No such room exists");
                    }else{
                        System.out.println(foundRoom);
                    }
                }
            }else if(userInput.equalsIgnoreCase("nocheatmode")){
                Scanner scan=new Scanner(System.in);
                playWithoutCheatMode(scan);
            }else if (userInput.equals("look")) {
                System.out.println(getRoom());
                System.out.println("You have " + timeLeft + "seconds left");
            } else if (userInput.equals("north")) {
                if (getRoom().getNorth() != null) {
                    setRoom(getRoom().getNorth());
                    getRoom().getSouth().removePlayer(this);
                    getRoom().clearItem();
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no north");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equals("south")) {
                if (getRoom().getSouth() != null) {
                    setRoom(getRoom().getSouth());
                    getRoom().getNorth().removePlayer(this);
                    getRoom().clearItem();
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no south");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equals("east")) {
                if (getRoom().getEast() != null) {
                    setRoom(getRoom().getEast());
                    getRoom().getWest().removePlayer(this);
                    getRoom().clearItem();
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no east");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equals("west")) {
                if (getRoom().getWest() != null) {
                    setRoom(getRoom().getWest());
                    getRoom().getEast().removePlayer(this);
                    getRoom().clearItem();
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no west");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("GoodBye");
                System.exit(0);
            } else if (spltCMD != null && spltCMD.get(0).equalsIgnoreCase("shake")) {
                shakeItem(spltCMD.get(1));
                System.out.println("You have " + timeLeft + "seconds left");
            } else if (spltCMD != null && spltCMD.get(0).equalsIgnoreCase("possess")) {
                possesItem(spltCMD.get(1));
                System.out.println("You have " + timeLeft + "seconds left");
            } else if (spltCMD != null && spltCMD.get(0).equalsIgnoreCase("throw")) {
                throwItem(spltCMD.get(1));
                System.out.println("You have " + timeLeft + "seconds left");
            }else{
                System.out.println("Enter correct command!  (HINT: Type 'help' for list of commands.)");
                System.out.println("You have " + timeLeft + "seconds left");
            }
            userInput = S.nextLine();
        }
        }
    public void playWithoutCheatMode(Scanner S) {
        System.out.println("Cheat mode is deactivated!");

        String userInput = S.nextLine();

        while (userInput != "exit") {
            ArrayList<String> spltCMD = commandSplit(userInput);
            if (userInput.equals("help")) {
                System.out.println("look – this command immediately outputs to the user the complete information pertaining to the presentroom the Player is in");
                System.out.println("north– the Player leaves the current presentroom and enters its north presentroom. Be careful about special cases");
                System.out.println("south– the Player leaves the current presentroom and enters its south presentroom. Be careful about special cases");
                System.out.println("east– the Player leaves the current presentroom and enters its east presentroom. Be careful about special cases");
                System.out.println("west– the Player leaves the current presentroom and enters its west presentroom. Be careful about special cases");
                System.out.println("shake:[itemname]- this command allows to shake the objects in the presentroom, causing a reaction from the other Characters. Allow the user to type the actual item name, e.g., shake:white lamp.");
                System.out.println("posses:[itemname]- this command allows to posses the objects in the presentroom, causing a reaction from the other Characters. Allow the user to type the actual item name, e.g., posses:glass.");
                System.out.println("throw:[itemname]- this command allows to throw the objects in the presentroom, causing a reaction from the other Characters. Allow the user to type the actual item name, e.g., throw:vessel.");
                System.out.println("exit, quit – these commands print a 'Goodbye' message and quit the program.");
                System.out.println("You have " + timeLeft + "seconds left");
            }else if(userInput.equalsIgnoreCase("cheatmode")){
                Scanner scan=new Scanner(System.in);
                playWithCheatMode(scan);
            }
            else if (userInput.equals("look")) {
                System.out.println(getRoom());
                System.out.println("You have " + timeLeft + "seconds left");
            } else if (userInput.equals("north")) {
                if (getRoom().getNorth() != null) {
                    setRoom(getRoom().getNorth());
                    getRoom().getSouth().removePlayer(this);
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no north");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equals("south")) {
                if (getRoom().getSouth() != null) {
                    setRoom(getRoom().getSouth());
                    getRoom().getNorth().removePlayer(this);
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no south");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equals("east")) {
                if (getRoom().getEast() != null) {
                    setRoom(getRoom().getEast());
                    getRoom().getWest().removePlayer(this);
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no east");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equals("west")) {
                if (getRoom().getWest() != null) {
                    setRoom(getRoom().getWest());
                    getRoom().getEast().removePlayer(this);
                    System.out.println("You have " + timeLeft + "seconds left");
                } else {
                    System.out.println("no west");
                    System.out.println("You have " + timeLeft + "seconds left");
                }
            } else if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("GoodBye");
                System.exit(0);
            } else if (spltCMD != null && spltCMD.get(0).equalsIgnoreCase("shake")) {
                shakeItem(spltCMD.get(1));
                System.out.println("You have " + timeLeft + "seconds left");
            } else if (spltCMD != null && spltCMD.get(0).equalsIgnoreCase("possess")) {
                possesItem(spltCMD.get(1));
                System.out.println("You have " + timeLeft + "seconds left");
            } else if (spltCMD != null && spltCMD.get(0).equalsIgnoreCase("throw")) {
                throwItem(spltCMD.get(1));
                System.out.println("You have " + timeLeft + "seconds left");
            }else{
                System.out.println("Enter correct command!  (HINT: Type 'help' for list of commands.)");
                System.out.println("You have " + timeLeft + "seconds left");
            }
            userInput = S.nextLine();
        }
    }




    public void shakeItem(String s) {
                Boolean status = false;
                Item[] items = getRoom().getItems();
                for (int j = 0; j < items.length; j++) {
                    if (items[j] != null) {
                        if (items[j].getItemName().equalsIgnoreCase(s) && (!items[j].getItemStatus())) {
                                    for (Adult a : getRoom().getAdults()) {
                                        Random random = new Random();
                                        incr = random.nextInt(10) + 5;
                                       status =   a.adultScare(incr);
                                       if(status){
                                           timeLeft.addAndGet(30);
                                           System.out.println("30 Seconds Added");
                                       }
                                    }for (Child c : getRoom().getChildren()) {
                                Random random = new Random();
                                incr = random.nextInt(10) + 5;
                                        status =  c.childScare(incr);
                                        if(status){
                                            timeLeft.addAndGet(30);
                                            System.out.println("30 Seconds Added");
                                        }
                                    }
                      }
                    }
                }
    }

    public void possesItem(String s) {

                Boolean status=false;

                Item[] items = getRoom().getItems();
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null) {
                        if (items[i].getItemName().equalsIgnoreCase(s) && !items[i].getItemStatus()) {
                            for (Adult a : getRoom().getAdults()) {
                                Random random = new Random();
                                incr = random.nextInt(15) + 10;
                                status=a.adultScare(incr);
                                if (status){
                                    timeLeft.addAndGet(30);
                                }
                            }
                            for (Child c : getRoom().getChildren()) {
                                Random random = new Random();
                                incr = random.nextInt(15) + 10;
                                status=c.childScare(incr);
                                if (status){
                                    timeLeft.addAndGet(30);
                                }
                            }
                        }
                    }
                }
    }

    public void throwItem(String s) {

        Boolean status=false;

        int index = 0;
          for ( Item i:getRoom().getItems()) {
              if (i != null) {
                  if (i.getItemName().equalsIgnoreCase(s) && !i.getItemStatus()) {
                      getRoom().getItems()[index].breakItem();
                      for (Adult a : getRoom().getAdults()) {
                          Random random = new Random();
                          incr = random.nextInt(20) + 20;
                          status=a.adultScare(incr);
                          if(status){
                              timeLeft.addAndGet(30);
                          }
                      }
                      for (Child c : getRoom().getChildren()) {
                          Random random = new Random();
                          incr = random.nextInt(20) + 20;
                         status= c.childScare(incr);
                         ;if (status){
                             timeLeft.addAndGet(30);
                          }
                      }
                  } else if (i.getItemName().equalsIgnoreCase(s) && i.getItemStatus()) {
                      System.out.println("The Item is broken ");
                  }

                  index++;
              }
          }
                }

    public void play(String s) {
            Scanner scanner=new Scanner(System.in);
            playWithCheatMode(scanner);
            playWithoutCheatMode(scanner);

    }
}





