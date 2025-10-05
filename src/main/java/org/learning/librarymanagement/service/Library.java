package org.learning.librarymanagement.service;

import org.learning.librarymanagement.model.Book;
import org.learning.librarymanagement.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private final Map<String, Book> books;
    private final Map<String, Member> members;

    public Library() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public boolean borrowBook(String memberId, String isbn) {
        Member member = members.get(memberId);
        Book book = books.get(isbn);

        if (member == null || book == null || !book.isAvailable() || !member.canBorrow()) {
            return false;
        }

        book.borrow(member);
        member.addBorrowedBook(book);
        return true;
    }

    public boolean returnBook(String memberId, String isbn) {
        Member member = members.get(memberId);
        Book book = books.get(isbn);

        if (member == null || book == null || book.isAvailable()) {
            return false;
        }

        book.returnBook();
        member.returnBook(book);
        return true;
    }

    public List<Book> searchByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getMemberBorrowedBooks(String memberId) {
        Member member = members.get(memberId);
        return member != null ? member.getBorrowedBooks() : new ArrayList<>();
    }
}
