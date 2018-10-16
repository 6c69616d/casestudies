/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * LibraryGUI.java
 *
 * Created on 28-Sep-2009, 11:55:39
 */
package library;

import javax.swing.JOptionPane;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Kutoma
 */
public class LibraryGUI extends javax.swing.JFrame {

    private static LibraryGUI libraryGUI;
    private final SetOfMembers theMembers = new SetOfMembers();
    private final SetOfBooks holdings = new SetOfBooks();
    private Book selectedBook;
    private Member selectedMember;

    public final static String FILE = "system_data.ser";

    /**
     * Creates new form LibraryGUI
     */
    public LibraryGUI() {

        initComponents();

        try {
            loadData();
        } catch (IOException e) {
            //
        } catch (ClassNotFoundException e) {
            Logger.getLogger(LibraryGUI.class.getName()).log(Level.SEVERE, null, e);
        }

//        Member member1 = new Member("Jane");
//        Member member2 = new Member("Amir");
//        Member member3 = new Member("Astrid");
//        Member member4 = new Member("Andy");
//
//        theMembers.addMember(member1);
//        theMembers.addMember(member2);
//        theMembers.addMember(member3);
//        theMembers.addMember(member4);
//
//        Book book1 = new Book("book1");
//        Book book2 = new Book("book2");
//        Book book3 = new Book("book3");
//        Book book4 = new Book("book4");
//
//        holdings.addBook(book1);
//        holdings.addBook(book2);
//        holdings.addBook(book3);
//        holdings.addBook(book4);
        showCurrentLoans();

        memberList.setListData(theMembers.toArray());
//        bookList.setListData(holdings.toArray());
//        memberOnLoanList.setListData(new SetOfBooks().toArray());

    }

    private void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectIn = null;
        try (FileInputStream fileIn = new FileInputStream(FILE)) {
            objectIn = new ObjectInputStream(fileIn);
            Object o;
            try {
                while (true) {
                    o = objectIn.readObject();

                    if (o instanceof Book) {
                        Book newBook = (Book) o;
                        holdings.addBook(newBook);
                        ((Book) o).setBookCount(newBook.getAccessionNumber() + 1);
                    } else if (o instanceof Member) {
                        Member newMember = (Member) o;
                        theMembers.addMember(newMember);
                        ((Member) o).setMemberCount(newMember.getNumber() + 1);
                    } else {
                        String className = "";
                        if (o != null) {
                            className = o.getClass().getCanonicalName();
                        }
                        throw new UnsupportedOperationException("Can not deserialize the class" + className);
                    }
                }
            } catch (EOFException e) {
                return;
            }
        } finally {
            if (objectIn != null) {
                objectIn.close();
            }
        }
    }

    public void save() throws FileNotFoundException, IOException {
        ObjectOutputStream objectOut = null;

        try (FileOutputStream outputFile = new FileOutputStream(FILE)) {
            objectOut = new ObjectOutputStream(outputFile);
            for (Book x : holdings) {
                objectOut.writeObject(x);
                objectOut.flush();
            }

            for (Member x : theMembers) {
                objectOut.writeObject(x);
                objectOut.flush();
            }

        } finally {
            if (objectOut != null) {
                objectOut.close();
            }
        }
    }

    /**
     * @return the selectedBook
     */
    public Book getSelectedBook() {
        return selectedBook;
    }

    /**
     * @param selectedBook the selectedBook to set
     */
    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    /**
     * @return the selectedMember
     */
    public Member getSelectedMember() {
        return selectedMember;
    }

    /**
     * @param selectedMember the selectedMember to set
     */
    public void setSelectedMember(Member selectedMember) {
        this.selectedMember = selectedMember;
    }

    public void loanBook() {
        if (memberOnLoanList.getModel().getSize() < 3 && selectedBook != null) {
            selectedMember.borrowBook(selectedBook);
            System.out.println(selectedMember + "has borrowed" + selectedBook);
            showCurrentLoans();
            selectedBook = null;
        }
    }

    public void acceptReturn() {
        if (selectedBook != null) {
            selectedMember.returnBook(selectedBook);
            showCurrentLoans();
            selectedBook = null;
        }
    }

    private void showCurrentLoans() {
        SetOfBooks notLoanedBooks = new SetOfBooks(holdings);
        notLoanedBooks.removeIf(x -> x.isOnLoan());
        if (selectedMember != null) {
            memberOnLoanList.setListData(selectedMember.getBooksOnLoan().toArray());
        } else {
            memberOnLoanList.setListData(new SetOfBooks().toArray());
        }
        bookList.setListData(notLoanedBooks.toArray());
    }

    public void selectBook(boolean bookAvailable) {
        Object x;
        String bookListSelectedValue;
        if (bookAvailable) {
            x = bookList.getSelectedValue();
        } else {
            x = memberOnLoanList.getSelectedValue();
        }
        if (x == null) {
            bookListSelectedValue = "";
        } else {
            bookListSelectedValue = x.toString();
        }
        if (!bookListSelectedValue.isEmpty()) {
            selectedBook = holdings.findBookFromAccNumber(Integer.valueOf(
                    bookListSelectedValue.substring(0, bookListSelectedValue.indexOf(","))));
        }
    }

    public void selectMember() {
        Object x = memberList.getSelectedValue();
        String memberListSelectedValue = "";
        if (x != null){
            memberListSelectedValue = x.toString();
        }
        if(!memberListSelectedValue.isEmpty()){
        selectedMember = theMembers.getMemberFromNumber(Integer.valueOf(
                memberListSelectedValue.substring(0, memberListSelectedValue.indexOf(" "))));
        }
        showCurrentLoans();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loanBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookList = new javax.swing.JList();
        returnBtn = new javax.swing.JButton();
        addBookBtn = new javax.swing.JButton();
        addMemberBtn = new javax.swing.JButton();
        selectMemberBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        memberOnLoanList = new javax.swing.JList();
        removeMemberBtn = new javax.swing.JButton();
        removeBookBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loanBtn.setText("Loan");
        loanBtn.setMaximumSize(new java.awt.Dimension(135, 29));
        loanBtn.setMinimumSize(new java.awt.Dimension(135, 29));
        loanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanBtnActionPerformed(evt);
            }
        });

        memberList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(memberList);

        bookList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(bookList);

        returnBtn.setText("Return");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        addBookBtn.setText("Add Book");
        addBookBtn.setMaximumSize(new java.awt.Dimension(135, 29));
        addBookBtn.setMinimumSize(new java.awt.Dimension(135, 29));
        addBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookBtnActionPerformed(evt);
            }
        });

        addMemberBtn.setText("Add Member");
        addMemberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberBtnActionPerformed(evt);
            }
        });

        selectMemberBtn.setText("Select Member");
        selectMemberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMemberBtnActionPerformed(evt);
            }
        });

        memberOnLoanList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(memberOnLoanList);

        removeMemberBtn.setText("Remove Member");
        removeMemberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberBtnActionPerformed(evt);
            }
        });

        removeBookBtn.setText("Remove Book");
        removeBookBtn.setMaximumSize(new java.awt.Dimension(135, 29));
        removeBookBtn.setMinimumSize(new java.awt.Dimension(135, 29));
        removeBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBookBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeMemberBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectMemberBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addMemberBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(removeBookBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(addBookBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(loanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectMemberBtn)
                    .addComponent(addBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeMemberBtn)
                    .addComponent(removeBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addMemberBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnBtn))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanBtnActionPerformed
        if (selectedMember != null) {
            selectBook(true);
            loanBook();
        }

    }//GEN-LAST:event_loanBtnActionPerformed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        if (selectedMember != null) {
            selectBook(false);
            acceptReturn();
        }
    }//GEN-LAST:event_returnBtnActionPerformed

    private void addBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookBtnActionPerformed
        addBook();
    }//GEN-LAST:event_addBookBtnActionPerformed

    private void addMemberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberBtnActionPerformed
        addMember();
    }//GEN-LAST:event_addMemberBtnActionPerformed

    private void selectMemberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMemberBtnActionPerformed
        selectMember();
    }//GEN-LAST:event_selectMemberBtnActionPerformed

    private void removeMemberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberBtnActionPerformed
        removeMember();
    }//GEN-LAST:event_removeMemberBtnActionPerformed

    private void removeBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBookBtnActionPerformed
        selectBook(true);
        removeBook();
        showCurrentLoans();
    }//GEN-LAST:event_removeBookBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                libraryGUI = new LibraryGUI();
                libraryGUI.setVisible(true);
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        libraryGUI.save();
                    } catch (IOException e) {

                    }
                }));
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookBtn;
    private javax.swing.JButton addMemberBtn;
    private javax.swing.JList bookList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loanBtn;
    private javax.swing.JList memberList;
    private javax.swing.JList memberOnLoanList;
    private javax.swing.JButton removeBookBtn;
    private javax.swing.JButton removeMemberBtn;
    private javax.swing.JButton returnBtn;
    private javax.swing.JButton selectMemberBtn;
    // End of variables declaration//GEN-END:variables

    private void addBook() {
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField isbn = new JTextField();

        Object[] message = {
            "Title: ", title, "Author: ", author, "ISBN: ", isbn
        };
        int option = JOptionPane.showConfirmDialog(
                libraryGUI, message, "Add Book",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {

            if ((title.getText() != null) && (author.getText() != null) && (!isbn.getText().isEmpty())) {
                Long isbnNumber = null;
                try {
                    isbnNumber = Long.valueOf(isbn.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(libraryGUI, "ISBN can only include numbers");

                }
                if (isbnNumber != null) {
                    Book newBook = new Book(title.getText(), author.getText(), (isbnNumber));
                    holdings.addBook(newBook);
                    showCurrentLoans();
                }
            }

        }

    }

    private void addMember() {
        String memberName = (String) JOptionPane.showInputDialog(
                libraryGUI, "Enter New Member Name", "Add Member",
                JOptionPane.PLAIN_MESSAGE, null, null, null);
        if ((memberName != null) && (memberName.length() > 0)) {
            Member newMember = new Member(memberName);
            theMembers.addMember(newMember);
            memberList.setListData(theMembers.toArray());
        }

    }

    private void removeMember() {

        if (selectedMember != null && selectedMember.getBooksOnLoan().isEmpty()) {

            theMembers.remove(selectedMember);
            memberList.setListData(theMembers.toArray());
        }

    }

    private void removeBook() {
        if (selectedBook != null && selectedBook.getBorrower() == null) {
            holdings.remove(selectedBook);
        }

    }

}
