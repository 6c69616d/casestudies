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
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String title;
    private Member borrower = null;
    private static int bookCount = 0;
    private final int accessionNumber;
    private long iSBNNumber;
    private String author;

    public Book(String name, String author, long iSBNNumber) {
        title = name;
        accessionNumber = bookCount++;
        this.iSBNNumber = iSBNNumber;
        this.author = author;

    }

    void setCurrentBorrower(Member theBorrower) {
        borrower = theBorrower;
    }

    Member getBorrower() {
        return borrower;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the accessionNumber
     */
    public int getAccessionNumber() {
        return accessionNumber;
    }

    /**
     * @return the iSBNNumber
     */
    public long getiSBNNumber() {
        return iSBNNumber;
    }

    /**
     * @param iSBNNumber the iSBNNumber to set
     */
    public void setiSBNNumber(int iSBNNumber) {
        this.iSBNNumber = iSBNNumber;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return Integer.toString(accessionNumber) + ", " + title + ", " + author + ", " + iSBNNumber;

    }

    public boolean isOnLoan() {
        if (borrower != null) {
            return true;
        } else {
            return false;
        }

    }

    public void setBookCount(int bookCount) {
        Book.bookCount = bookCount;
    }
}
