import utils.Input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ContactsApp {
    private static final Path contactsPath = Paths.get("src",  "contacts.txt");
//Added New UpdateContacts method
    private static void updateContacts(List<String> lines){
        try{
            Files.write(contactsPath, lines);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void addPeople(String contactName, String contactNumber){
        List<String> lines = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        try {
            lines = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String name : lines) {
            if(name.contains(contactName)){
                System.out.println("This contact already exists, would you like to overwrite it? [Yes/No]");
                String userChoice = myScanner.nextLine();
                if(userChoice.equalsIgnoreCase("yes")){
                    deletePerson(contactName);
                    List<String> names = new ArrayList<>();
                    names.add(contactName + " | " + contactNumber);
                    try {
                        Files.write(contactsPath, names, StandardOpenOption.APPEND);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    List<String> names = new ArrayList<>();
                    names.add(contactName + " | " + contactNumber);
                    try {
                        Files.write(contactsPath, names, StandardOpenOption.APPEND);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        //We want to Grab the users New name
        //I want to search for the person, and if it is a match, do not
        //Allow the user to add, otherwise overwrite






    }//End of addPeople Method
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
    //Added a new DeletePerson Method
    private static void deletePerson(String nameToDelete){
        List<String> updatedContacts = new ArrayList<>();
        for (String name : readFile()){
            if (!name.contains(nameToDelete)){
                updatedContacts.add(name);
            }
        }
        updateContacts(updatedContacts);
    }//End of DeletePerson method
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
                ----------------------------------------
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
                //If we added the Search for People method here
                //And if it matches
                System.out.println("Enter " + usersContact +"'s number: " );
                String userNumber =  input.getString();
                addPeople(usersContact, userNumber);
            } else if (usersInput == 3){
                System.out.println("Enter the name you would like to search for: ");
                String userSearch = input.getString();
                title();
                searchForPeople(userSearch);
            } else if (usersInput == 4){
                System.out.println("Enter the name to delete: ");
                String usersDeletedName = input.getString();
//                System.out.printf("""
//                        The following name has now been deleted:
//                        %s
//                        """, usersDeletedName);
                System.out.println("""
                            The following Contact has now been deleted:
                            ----------------------------------------""");
                title();
                searchForPeople(usersDeletedName);
                deletePerson(usersDeletedName);
            }
        }//End of while loop












    }//End of ContactApp

}//End of Main
