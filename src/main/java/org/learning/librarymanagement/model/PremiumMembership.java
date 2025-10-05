package org.learning.librarymanagement.model;

public class PremiumMembership implements MembershipType {
    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public String getTypeName() {
        return "Premium";
    }
}
