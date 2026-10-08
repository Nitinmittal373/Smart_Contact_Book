public class Action {
    private final String type;
    private final Contact contact;

    Action(String type,Contact contact){
        this.type=type;
        this.contact=contact;
    }

    public String getType(){
        return type;
    }

    public Contact getContact() {
        return contact;
    }

}
