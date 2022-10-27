public class Contact {
    private String name;

    private long number;

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    //Constructors

    public Contact(){

    }
    public Contact(String name, long number){
        this.name = name;
        this.number = number;
    }


}
