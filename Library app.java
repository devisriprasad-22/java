import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + " (Available: " + isAvailable + ")";
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

       
        library.addBook(new Book(1, "Harry Potter", "J.K.Rowling"));
        library.addBook(new Book(2, "One Piece", "ODA"));
        library.addBook(new Book(3, "Naruto", "Kishimoto"));

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. List Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("\nList of Books:");
                    library.listBooks();
                    break;
                case 2:
                    System.out.print("Enter the book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Book borrowBook = library.findBookById(borrowId);
                    if (borrowBook != null && borrowBook.isAvailable()) {
                        borrowBook.borrow();
                        System.out.println("You have successfully borrowed " + borrowBook.getTitle());
                    } else {
                        System.out.println("The book is unavailable or does not exist.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the book ID to return: ");
                    int returnId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Book returnBook = library.findBookById(returnId);
                    if (returnBook != null && !returnBook.isAvailable()) {
                        returnBook.returnBook();
                        System.out.println("You have successfully returned " + returnBook.getTitle());
                    } else {
                        System.out.println("The book is not borrowed or does not exist.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the Library Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
