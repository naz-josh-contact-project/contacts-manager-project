import utils.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ContactsApp {
    private static final Path contactsPath = Paths.get("src",  "contacts.txt");
    private static void addPeople(String contactName, String contactNumber){
        //We want to Grab the users New name
        List<String> names = new ArrayList<>();
        names.add(contactName + " | " + contactNumber);
        try {
            Files.write(contactsPath, names, StandardOpenOption.APPEND);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchForPeople(String userSearch){
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String name : lines) {
            if(name.contains(userSearch)){
                System.out.println(name);
            }
        }
    }//End of SearchForPeople Method

    private static List<String> readFile() {
        List<String> names = null;
        try {
            names = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
    public static void title(){
        System.out.println("""
                Name | Phone Number
                -------------------""");
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

        int usersInput = 0;

//        for (String name: lines){
//            System.out.println("Hello, " + name + "!");
//        }
//
        while(usersInput != 5) {
            prompt();
            usersInput = input.getInt(1, 5);
            if (usersInput == 1) {
                title();
                for (String contact : readFile()) {
                    System.out.printf("%s%n", contact);

                }
            } else if(usersInput == 2){

                System.out.println("Enter the contact name: ");
                String usersContact = input.getString();
                System.out.println("Enter " + usersContact +"'s number: " );
                String userNumber =  input.getString();
                addPeople(usersContact, userNumber);
            } else if (usersInput == 3){
                System.out.println("Enter the name you would like to search for: ");
                String userSearch = input.getString();
                title();
                searchForPeople(userSearch);
            }
        }//End of while loop












    }//End of ContactApp

}//End of Main
