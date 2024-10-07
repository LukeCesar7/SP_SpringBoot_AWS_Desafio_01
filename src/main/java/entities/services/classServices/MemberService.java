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

    }
}
