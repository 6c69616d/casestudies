/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.ArrayList;

/**
 *
 * @author Kutoma
 */
public class SetOfMembers extends ArrayList<Member> {

    public void addMember(Member aMember) {
        super.add(aMember);
    }

    public Member getMemberFromName(String name) {
        return super.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public Member getMemberFromNumber(int number) {
        return super.stream().filter(x -> x.getNumber() == number).findFirst().orElse(null);
    }

    public void removeMember(Member memberToRemove) {
        super.remove(memberToRemove);
    }

}
