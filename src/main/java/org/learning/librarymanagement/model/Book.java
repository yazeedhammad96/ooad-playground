package org.learning.librarymanagement.model;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int publicationYear;
    private boolean available;
    private Member currentBorrower;


    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    public void borrow(Member member) {
        this.available = false;
        this.currentBorrower = member;
    }

    public void returnBook() {
        this.available = true;
        this.currentBorrower = null;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Member getCurrentBorrower() {
        return currentBorrower;
    }
}
