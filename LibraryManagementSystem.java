import java.util.Scanner;

// Book class
class Book {
    String title;
    boolean isAvailable;

    public Book(String title) {
        this.title = title;
        this.isAvailable = true; // Initially, the book is available
    }
}

// Member class
class Member {
    String name;

    public Member(String name) {
        this.name = name;
    }
}

// LibraryManagementSystem class
public class LibraryManagementSystem {
    Book[] books = new Book[10];   // Array to store up to 10 books
    Member[] members = new Member[10]; // Array to store up to 10 members

    // Method to add a new book to the system
    public void addBook(String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = new Book(title);
                System.out.println("Book"+(i+1)+": " + title);
                break;
            }
        }
    }

    // Method to add a new member to the system
    public void addMember(String name) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                members[i] = new Member(name);
                System.out.println("Member "+(i+1)+": " + name);
                break;
            }
        }
    }

    // Method to issue a book
    public void issueBook(String title, String memberName) {
        Book book = findBook(title);
        if (book != null && book.isAvailable) {
            book.isAvailable = false;
            System.out.println("Book issued to: " + memberName);
        } else {
            System.out.println("Book is not available.");
        }
    }

    // Method to return a book
    public void returnBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            book.isAvailable = true;
            System.out.println("Book returned: " + title);
        }
    }

    // Helper method to find a book by title
    private Book findBook(String title) {
        for (Book book : books) {
            if (book != null && book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.println("Book not found.");
        return null;
    }

    // Main method to run the system
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("Books available:");
        library.addBook("Harry Potter");
        library.addBook("The Lord of the Rings");

        library.addMember("Alice");
        library.addMember("Bob");
        library.addMember("Suraj");
        library.addMember("Aarush ");
        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Issue a book");
            System.out.println("2. Return a book");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    library.issueBook(bookTitle, memberName);
                    break;
                case 2:                    System.out.print("Enter book title to return: ");
                    String returnBookTitle = scanner.nextLine();
                    library.returnBook(returnBookTitle);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
          }
