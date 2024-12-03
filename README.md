Library Management System 📚
A Java-based Library Management System designed to manage books and users efficiently, demonstrating core Object-Oriented Programming (OOP) principles like encapsulation, inheritance, and file handling. This project enables seamless book borrowing, user registration, and persistent data storage using file serialization.

Features 🚀
Display Books and Users
View detailed information about all available books and registered users.

Add Books and Register Users
Dynamically add new books and register new users.

Borrow and Return Books

Allow users to borrow books, marking them as borrowed.
Enable users to return books, updating their availability.
Persistent Data Storage

Store and load library data using file serialization.
Ensures that all data (books and users) is saved between sessions.
How It Works ⚙️
Book Class
Represents a book with attributes like ID, title, author, and borrowed status.

User Class
Represents a user with attributes like user ID, name, and a list of borrowed books.

Library Class

Manages books and users.
Provides functionalities to add books/users, borrow/return books, and display information.
Handles data persistence with file serialization.
Main Class (LibraryManagement)

Provides a menu-driven interface for interacting with the system.
Features options to display books/users, add books/users, borrow/return books, and save the system state.


Menu Options 📋
Display Books: Lists all books in the library.
Add Book: Allows adding new books with ID, title, and author.
Register User: Registers a new user with a unique ID and name.
Borrow Book: Enables a user to borrow an available book.
Return Book: Lets a user return a borrowed book.
Display Users: Lists all registered users with their borrowed books.
Save & Exit: Saves the library data to a file and exits the program.


File Handling 📂
The library data is stored in a file named library.dat using Java's ObjectOutputStream and ObjectInputStream.
When the program starts, it attempts to load existing data from this file.
Upon exit, the current state of the library is saved back to the file.


Technologies Used 💻
Programming Language: Java
Concepts: Object-Oriented Programming (OOP), File Handling, Serialization


Example 📖
Here’s a simple interaction:

Add a book: ID: 101, Title: Java Programming, Author: John Doe.
Register a user: User ID: 1, Name: Alice.
Borrow the book: User 1 borrows book 101.
Save the library state and exit.
When the program is restarted, the saved data is reloaded automatically.

