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
    //================================METHODS============================================
    private static final Path contactsPath = Paths.get("src",  "contacts.txt");
    //Added New UpdateContacts method
    private static void updateContacts(List<String> lines){
        try{
            Files.write(contactsPath, lines);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static String formatNumber(String contactNumber){
        String[] breakNumber = contactNumber.split("");
        String parenthesis = "";
        String first3Nums = "";
        String last4Nums = "";
        for (int i= 0; i <= 2; i++ ){
            parenthesis += breakNumber[i];
        }
        for (int i= 3; i <= 5; i++ ){
            first3Nums += breakNumber[i];
        }
        for (int i= 6; i <= 9; i++ ){
            last4Nums += breakNumber[i];
        }
        return String.format("(%s)%s-%s",parenthesis,first3Nums,last4Nums);
    }
    private static void addPeople(String contactName, String contactNumber){
        List<String> contactsArray = new ArrayList<>();
        String contactsFullInfo = contactName + " | " + formatNumber(contactNumber);
        contactsArray.add(contactsFullInfo);
        try {
            Files.write(contactsPath, contactsArray, StandardOpenOption.APPEND);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    private static void addPeople(){
        List<String> contactsArray = new ArrayList<>();
        try {
            contactsArray = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the Contact's Name: ");
        String userContactName = myScanner.nextLine();
        for(String person : contactsArray) {
            if (person.contains(userContactName)) {
                System.out.println("This person already exists! Do you want to continue? [Yes/No]");
                String userChoice = myScanner.nextLine();
                if (userChoice.equalsIgnoreCase("no")) {
                    addPeople();
                    return;
                }
            }
        }
        System.out.println("What is " + userContactName + "'s number?");
        String userNumber = myScanner.nextLine();
        addPeople(userContactName, userNumber);

    }//End of add people method
    private static void searchForPeople(String userSearch){
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String name : lines) {
            if(name.toLowerCase().contains(userSearch.toLowerCase())){
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
    //==========================END OF METHODS==============================================
    public static void main(String[] args) {
        Input input = new Input();
        int usersInput = 0;
        while(usersInput != 5) {
            prompt();
            usersInput = input.getInt(1, 5);
            if (usersInput == 1) {
                title();
                for (String contact : readFile()) {
                    System.out.printf("%s%n", contact);
                }
            } else if(usersInput == 2){
                addPeople();
            } else if (usersInput == 3){
                System.out.println("Enter the name you would like to search for: ");
                String userSearch = input.getString();
                title();
                searchForPeople(userSearch);
            } else if (usersInput == 4){
                System.out.println("Enter the name to delete: ");
                String usersDeletedName = input.getString();
                System.out.println("""
                            The following Contact has now been deleted:
                            ----------------------------------------""");
                title();
                searchForPeople(usersDeletedName);
                deletePerson(usersDeletedName);
            }
        }//End of while loop

    }//End of Main

}//End of ContactApp
