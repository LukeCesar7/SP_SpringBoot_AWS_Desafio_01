package entities.services.classServices;
import entities.Book;
import entities.Loan;
import entities.Member;
import entities.services.LoanState;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LoanService {

    private Scanner sc;
    private DateTimeFormatter dTF;
    private EntityManager em;

    public LoanService() {

    }

    public LoanService(Scanner sc, DateTimeFormatter dTF, EntityManager em) {
        this.sc = sc;
        this.dTF = dTF;
        this.em = em;
    }

    //--------------------------------------------------------------------------------------------------------------LOAN

    public void newLoan() {
        System.out.println("Enter member ID: ");
        int memberId = sc.nextInt();
        sc.nextLine();

        Member member = em.find(Member.class, memberId);
        Loan loan = em.find(Loan.class, memberId);

        //BigDecimal fineValue = loan.getFineValue();
        // && fineValue.compareTo(BigDecimal.ZERO) < 5

        if (member != null) {

            System.out.println("Enter ISBN Book: ");
            Long isbn = sc.nextLong();
            Book book = em.find(Book.class, isbn);
            System.out.println("status of book:");
            String status = sc.nextLine();

            if (book != null && book.getQty() > 0) {

                System.out.println("Book available for loan. Qty: " + book.getQty());
                System.out.println("Enter date of start loan: ");
                LocalDate dateLoanStarts = LocalDate.parse(sc.next(), dTF);
                System.out.println("Enter date of end loan: ");
                LocalDate dateLoanEnds = LocalDate.parse(sc.next(), dTF);

                Loan newloan = new Loan(dateLoanStarts, member, book, dateLoanEnds);
                newloan.setBookState(status);


                book.setQty(book.getQty() - 1); //Qty associado ao Book, pelo loan

                em.getTransaction().begin();
                em.persist(newloan);//
                em.merge(book);
                em.getTransaction().commit();
                //ISBN, volta, loan.getBook()

                System.out.println("Loan Successful!");
            } else {
                System.out.println("Book not found / available! ");
            }
        } else {
            System.out.println("Member with unpaid / late loan / Missed Member!");
        }
    }
}