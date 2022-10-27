import utils.Input;

public class ContactsApp {
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
        input.getInt(1, 5);

    }

}
