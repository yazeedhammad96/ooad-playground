package org.learning.librarymanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String memberId;
    private final String name;
    private final String email;
    private final MembershipType membershipType;
    private final List<Book> borrowedBooks;
    private final List<Book> borrowingHistory;

    public Member(String memberId, String name, String email, MembershipType membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.borrowedBooks = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < membershipType.getBorrowLimit();
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
        borrowingHistory.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public List<Book> getBorrowingHistory() {
        return new ArrayList<>(borrowingHistory);
    }
}
