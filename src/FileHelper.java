import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private static final String FILE_NAME = "contacts.txt";

    public static void save(List<Contact> contacts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contacts) {
                bw.write(c.getName() + "," + c.getPhoneNo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    public static List<Contact> load() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return contacts;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                int comma = line.lastIndexOf(',');
                if (comma == -1) continue;
                String name = line.substring(0, comma);
                String phone = line.substring(comma + 1);
                contacts.add(new Contact(name, phone));
            }
        } catch (IOException e) {
            System.out.println("Could not load: " + e.getMessage());
        }
        return contacts;
    }
}