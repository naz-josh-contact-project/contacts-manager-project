import utils.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContactsApp {
    private static final Path contactsPath = Paths.get("src",  "contacts.txt");
    private static List<String> readFile() {
        List<String> names = null;
        try {
            names = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
    public static void prompt(){
        System.out.println("""
                1 - View Contacts
                2 - Add a new contact
                3 - Search a contact by name
                4 - Delete an existing contact
                5 - Exit""");
    }
    public static void main(String[] args) {
        Input input = new Input();

        prompt();
        int usersInput = input.getInt(1, 5);

//        for (String name: lines){
//            System.out.println("Hello, " + name + "!");
//        }
//

        if(usersInput == 1) {
            System.out.println("""
                     Name | Phone Number
                     -------------------""");
            for (String contact : readFile()) {
                System.out.printf("%s%n", contact);

            }
        }












    }//End of ContactApp

}//End of Main
