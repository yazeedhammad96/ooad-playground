package org.learning.librarymanagement;

import org.learning.librarymanagement.model.*;
import org.learning.librarymanagement.service.Library;

/**
 * OOAD Problem: Library Management System
 * 
 * FUNCTIONAL REQUIREMENTS:
 * 
 * 1. Books:
 *    - Each book has: ISBN, title, author, publication year, and availability status
 *    - Books can be borrowed and returned
 *    - Track which member currently has the book
 * 
 * 2. Members:
 *    - Each member has: member ID, name, email, and membership type (Regular/Premium)
 *    - Regular members can borrow up to 3 books
 *    - Premium members can borrow up to 5 books
 *    - Track borrowing history
 * 
 * 3. Library Operations:
 *    - Add new books to the library
 *    - Register new members
 *    - Process book borrowing (check availability and member limits)
 *    - Process book returns
 *    - Search books by title or author
 *    - View member's current borrowed books
 * 
 * CONSTRAINTS:
 *    - A book can only be borrowed by one member at a time
 *    - Members cannot borrow more books than their limit
 *    - The system should be extensible for new membership types
 *    - Consider late fees for future extension (don't implement, just design for it)
 * 
 * TASK:
 * Design the class structure with appropriate:
 *    - Classes and interfaces
 *    - Relationships (inheritance, composition, etc.)
 *    - Key methods (you can use method signatures without full implementation)
 *    - Proper encapsulation and access modifiers
 * 
 * EVALUATION CRITERIA:
 *    - Encapsulation (0-10)
 *    - Inheritance (0-10)
 *    - Polymorphism (0-10)
 *    - Abstraction (0-10)
 *    - SOLID Principles (0-10)
 *    - Clean Code & Readability (0-10)
 *    - Design Patterns (bonus)
 */
public class Question {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2017));
        library.addBook(new Book("978-0132350884", "Clean Code", "Robert Martin", 2008));

        // Register members
        library.registerMember(new Member("M001", "Alice", "alice@example.com", new RegularMembership()));
        library.registerMember(new Member("M002", "Bob", "bob@example.com", new PremiumMembership()));

        // Borrow and return operations
        library.borrowBook("M001", "978-0134685991");
        library.returnBook("M001", "978-0134685991");
    }
}
