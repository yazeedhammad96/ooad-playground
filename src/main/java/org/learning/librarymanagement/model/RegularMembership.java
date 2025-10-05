package org.learning.librarymanagement.model;

public class RegularMembership implements MembershipType {
    @Override
    public int getBorrowLimit() {
        return 3;
    }

    @Override
    public String getTypeName() {
        return "Regular";
    }
}
