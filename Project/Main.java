package Game241.Project;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {

            InputStream input = new FileInputStream("C:\\Users\\DELL\\Documents\\Oswego 2nd Semester\\CSC 241\\CSC241Fall22_StarterProject\\CSC241Fall22\\src\\main\\java\\Game241\\Project\\xmlFiles.xml");
            //Creates a SAX parser factory and parser
            SAXParser saxParser = spf.newSAXParser();
            //parse the file
            XmlHandler sxp = new XmlHandler();
            saxParser.parse(input, sxp);
            sxp.setNeighborVals();
            sxp.fillBST();
            Player player = sxp.getPlayer();
            //set the player's bst equal to sxp's bst
            player.setBst(sxp.getBST());
            player.startTimer();

            //print the stuffs
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter the name of the room:");
//            String final_room = scanner.nextLine();
//            for (Room room1 : sxp.getRoom()) {
//                if (final_room.equalsIgnoreCase(room1.getName())) {
//                    System.out.println(room1);
//            System.out.println("File name:");
//            String string=scanner.nextLine();
//            if(string.equalsIgnoreCase("Project")) {
                System.out.println("Enter cheatmode or nocheatmode!");
                Scanner scan=new Scanner(System.in);
                String S=scan.nextLine();
                player.play(S);
//            }




                } catch(ParserConfigurationException | SAXException |
                        IOException e){
                    throw new RuntimeException(e);
                }


            }

    }




