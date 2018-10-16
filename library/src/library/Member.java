/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;

/**
 *
 * @author Kutoma
 */
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String name;
    private final int memberNumber;
    private final SetOfBooks currentLoans = new SetOfBooks();
    private static int memberCount = 0;

    public Member(String aName) {
        name = aName;
        memberNumber = memberCount++;

    }

    @Override
    public String toString() {
        return Integer.toString(memberNumber) + " " + name;

    }

    public void borrowBook(Book aBook) {
        currentLoans.addBook(aBook);
        aBook.setCurrentBorrower(this);
    }

    public void returnBook(Book aBook) {
        currentLoans.removeBook(aBook);
        aBook.setCurrentBorrower(null);
    }

    public SetOfBooks getBooksOnLoan() {
        return currentLoans;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return memberNumber;
    }

    public void setMemberCount(int memberCount) {
    Member.memberCount = memberCount;
    }
    
}
