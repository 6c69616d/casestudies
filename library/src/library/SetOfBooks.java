/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Kutoma
 */
public class SetOfBooks extends ArrayList<Book> {

    public SetOfBooks() {

        super();
    }

    public SetOfBooks(SetOfBooks holdings) {
        super(holdings);
    }

    public void addBook(Book aBook) {
        super.add(aBook);
    }

    public void removeBook(Book aBook) {
        super.remove(aBook);
    }

    public SetOfBooks findBookByAuthor(String author) {
        return (SetOfBooks) super.stream().filter(x -> x.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public SetOfBooks findBookFromTitle(String title) {
        return (SetOfBooks) super.stream().filter(x -> x.getTitle().equals(title)).collect(Collectors.toList());
    }

    public Book findBookFromAccNumber(int accNumber) {
        return super.stream().filter(x -> x.getAccessionNumber() == accNumber).findFirst().orElse(null);
    }

    public SetOfBooks findBookFromISBN(int ISBN) {
        return (SetOfBooks) super.stream().filter(x -> x.getiSBNNumber() == ISBN).collect(Collectors.toList());
    }

}
