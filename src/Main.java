import java.util.List;
import java.util.Scanner;

public class Main {

    private static void printList(List<Contact> list, String emptyMessage) {
        if (list.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Contact c : list) {
            System.out.println(c);
        }
    }

    private static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactBook book = new ContactBook();
        for (Contact c : FileHelper.load()) {      // right after creating the book
            book.addContact(c);
        }
        book.clearUndo();
        while (true) {
            System.out.println("\n1 Add  2 Find  3 Delete  4 Search prefix  5 Undo  6 Recent  7 All sorted  8 Favourite  9 Top favourites  0 Exit");
            System.out.print("Enter the choice: ");
            int choice = readInt(sc);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter the Phone-No: ");
                    String phoneNo = sc.nextLine();
                    if (book.addContact(new Contact(name, phoneNo))) {
                        System.out.println("Added");
                    } else {
                        System.out.println("Name already exists");
                    }
                }
                case 2 -> {
                    System.out.print("Enter the Name: ");
                    String name = sc.nextLine();
                    Contact c = book.findContact(name);
                    if (c == null) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(c);
                    }
                }
                case 3 -> {
                    System.out.print("Enter the Name: ");
                    String name = sc.nextLine();
                    if (book.deleteContact(name)) {
                        System.out.println("Deleted");
                    } else {
                        System.out.println("Not found");
                    }
                }
                case 4 -> {
                    System.out.print("Enter the Prefix: ");
                    String prefix = sc.nextLine();
                    printList(book.searchByPrefix(prefix), "No match");
                }
                case 5 -> {
                    if (book.undo()) {
                        System.out.println("Undone");
                    } else {
                        System.out.println("Nothing to undo");
                    }
                }
                case 6 -> printList(book.getRecentSearches(), "No recent searches");
                case 7 -> printList(book.getAllSorted(), "Contact book is empty");
                case 8 -> {
                    System.out.print("Enter the Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter the priority (1 or more, higher is more important): ");
                    int priority = readInt(sc);
                    if (priority < 1) {
                        System.out.println("Priority must be a number 1 or more");
                    } else if (book.markFavourite(name, priority)) {
                        System.out.println("Marked as favourite");
                    } else {
                        System.out.println("Not found");
                    }
                }
                case 9 -> {
                    System.out.print("How many top favourites? ");
                    int k = readInt(sc);
                    if (k < 1) {
                        System.out.println("Enter a number 1 or more");
                    } else {
                        printList(book.getTopFavourites(k), "No favourites yet");
                    }
                }
                case 0 -> {
                    FileHelper.save(book.getAllSorted());
                    System.out.println("Bye");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}