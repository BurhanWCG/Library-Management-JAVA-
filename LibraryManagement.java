import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrow() { isBorrowed = true; }
    public void returnBook() { isBorrowed = false; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Borrowed: " + (isBorrowed ? "Yes" : "No");
    }
}

// User class
class User implements Serializable {
    private int userId;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name +
               ", Borrowed Books: " + borrowedBooks.size();
    }
}

// Library class
class Library implements Serializable {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void addUser(User user) { users.add(user); }

    public ArrayList<Book> getBooks() { return books; }
    public ArrayList<User> getUsers() { return users; }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) return user;
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void saveLibrary(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        }
    }

    public static Library loadLibrary(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Library) ois.readObject();
        }
    }
}

// Main class
public class LibraryManagement {
    private static final String LIBRARY_FILE = "library.dat";

    public static void main(String[] args) {
        Library library;
        try {
            library = Library.loadLibrary(LIBRARY_FILE);
        } catch (Exception e) {
            library = new Library();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Add Book");
            System.out.println("3. Register User");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display Users");
            System.out.println("7. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    System.out.println("Book added successfully!");
                    break;
                case 3:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    library.addUser(new User(userId, name));
                    System.out.println("User registered successfully!");
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    userId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    User user = library.findUserById(userId);
                    Book book = library.findBookById(bookId);
                    if (user != null && book != null && !book.isBorrowed()) {
                        book.borrow();
                        user.borrowBook(book);
                        System.out.println("Book borrowed successfully!");
                    } else {
                        System.out.println("Invalid user/book or book is already borrowed.");
                    }
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    userId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    user = library.findUserById(userId);
                    book = library.findBookById(bookId);
                    if (user != null && book != null && book.isBorrowed()) {
                        book.returnBook();
                        user.returnBook(book);
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Invalid user/book or book is not borrowed.");
                    }
                    break;
                case 6:
                    library.displayUsers();
                    break;
                case 7:
                    try {
                        library.saveLibrary(LIBRARY_FILE);
                        System.out.println("Library saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving library.");
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
