import java.util.ArrayList;
import java.util.List;

public class Contact {
    private final String name;
    private final String phoneNo;
    private int priority = 0;     // 0 means not a favourite
    Contact(String name,String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return getName()+"-"+getPhoneNo();
    }
}
