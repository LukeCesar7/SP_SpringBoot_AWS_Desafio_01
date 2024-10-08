package entities.services.classServices;

import entities.Member;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MemberService {

    private Scanner sc;
    private DateTimeFormatter dTF;
    private EntityManager em;

    public MemberService() {
    }

    public MemberService(Scanner sc, DateTimeFormatter dTF, EntityManager em) {
        this.sc = sc;
        this.dTF = dTF;
        this.em = em;
    }

    //------------------------------------------------------------------------------------------------------------MEMBER
    public void addMember() {

        try {
            System.out.println("Enter member's name: ");
            String name = sc.nextLine();
            System.out.println("Enter member's email: ");
            String email = sc.nextLine();
            System.out.println("Enter member's address:  *Postal Code / Home Number  ");
            String address = sc.nextLine();
            System.out.println("Enter member's phone number: *Code area and number");
            String phone = sc.nextLine();
            System.out.println("Enter a date of registration : *(dd/MM/yyyy)");
            LocalDate joinDate = LocalDate.parse(sc.next(), dTF);

            Member member = new Member(name, email, address, phone, joinDate);

            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();

            System.out.println("Added! " + member.getName() + " member");

        }catch (Exception e) {
            System.out.println("Error! Enter Date as correct format: (dd/MM/yyyy) or Email already exists" +
                    " " +e.getMessage());}
    }

    public void consultMember() {
        try{
            System.out.println("Consult member");
            System.out.println("Enter ID: ");
            int id = sc.nextInt();

            Member findId = em.find(Member.class, id);
            if(findId == null) {
                System.out.println("Member not found");
            }
        }catch (Exception e) {
            System.out.println("Error! Enter ID with only number(s)"+e.getMessage());}
    }

    public void removeMember() {
        try{
            System.out.println("Remove member action");
            System.out.println("Enter ID: ");
            int id = sc.nextInt();
            Member findID = em.find(Member.class, id);

            em.getTransaction().begin();
            em.remove(findID);
            em.getTransaction().commit();

            System.out.println("Deleted Member");
        }catch (Exception e) {
            System.out.println("Error! Enter ID with only number(s)"+e.getMessage());}
    }

}
