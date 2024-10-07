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
    public void newLoan(){
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
                System.out.println("Book not found / not available! ");
            }
        } else {
            System.out.println("Member with unpaid / late loan / Missed Member!");
        }
    }


    public void consultLoanStatus(){

        System.out.println("Enter Loan ID: ");
        int loanId = sc.nextInt();
        sc.nextLine();

        Loan loan = em.find(Loan.class, loanId);

        LocalDate endLoan = loan.getDateLoanFinishes();
        LocalDate nowLoan = LocalDate.now();
        Long calcDaysLoan = ChronoUnit.DAYS.between(endLoan, nowLoan);

        if (calcDaysLoan > 0) {
            System.out.println("Outstanding debts! Days Late: " + calcDaysLoan);
            System.out.println("System returns: " + LoanState.LATE);

            BigDecimal fine = new BigDecimal(5.00);
            BigDecimal calcFine = fine.multiply(new BigDecimal(calcDaysLoan));
            loan.setFineValue(calcFine);

            System.out.println("Total debit R$: " + calcFine);

            em.getTransaction().begin();
            em.merge(loan);
            em.getTransaction().commit();
        }else{
            System.out.println("Loan on time, no fines to pay");
        }
    }

    public void closeLoan(){

        System.out.println("Enter Loan ID: ");
        int loanId = sc.nextInt();
        sc.nextLine();
        System.out.println("status of book:");
        String status = sc.nextLine();

        Loan loan = em.find(Loan.class, loanId);

        LocalDate endLoan = loan.getDateLoanFinishes();
        LocalDate nowLoan = LocalDate.now();
        Long calcDaysLoan = ChronoUnit.DAYS.between(endLoan, nowLoan);

        if (calcDaysLoan == 0) {

            loan.setBookState(status);
            loan.setFineValue(new BigDecimal(0.0 ));

            em.getTransaction().begin();
            Book book = loan.getBook(); // Livro associado ao empréstimo

            book.setQty(book.getQty() + 1);

            em.merge(book);
            loan.setDateLoanFinishes(LocalDate.now());
            em.getTransaction().commit();
            System.out.println("Loan Closed Successful");

        } else if (calcDaysLoan > 0) {

            System.out.println("Outstanding debts! Days Late: " + calcDaysLoan);
            System.out.println("System returns: " + LoanState.LATE);

            BigDecimal fine = new BigDecimal(5.00);
            BigDecimal calcFine = fine.multiply(new BigDecimal(calcDaysLoan));

            System.out.println("Total debts R$: " + calcFine);
            System.out.println("System returns: " + LoanState.COMPLETED);

            loan.setBookState(status);
            loan.setFineValue(calcFine);

            em.getTransaction().begin();
            Book book = loan.getBook();

            book.setQty(book.getQty() + 1);

            em.merge(book);
            loan.setDateLoanFinishes(LocalDate.now());
            em.getTransaction().commit();
            System.out.println("Loan Closed Successful");

        }else{
            System.out.println("Loan not found");
        }
    }
}