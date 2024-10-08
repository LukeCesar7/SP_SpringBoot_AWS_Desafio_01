package entities.services;

import entities.services.classServices.AuthorBookService;
import entities.services.classServices.LoanService;
import entities.services.classServices.MemberService;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private DateTimeFormatter dTF;
    private EntityManager em;

    private MemberService memberService;
    private AuthorBookService authorBookService;
    private LoanService loanService;

    public Menu(Scanner sc, DateTimeFormatter dTF, EntityManager em) {
        this.sc = sc;
        this.dTF = dTF;
        this.em = em;

        this.memberService = new MemberService(sc, dTF, em);
        this.authorBookService = new AuthorBookService(sc, dTF, em);
        this.loanService = new LoanService(sc, dTF, em);
    }

    //--------------------------------------------------------------------------------------------------------------MENU
    public void menu() {


        System.out.println("2024 - UOL.Compass - Registration System Services of the Library");
        System.out.println();
        System.out.print(
                "1 - Manager Member \n" +
                        "2 - Manager Authors / Books \n" +
                        "3 - Manage Loans \n" +
                        "Insert Option number: ");
        int choice = sc.nextInt();


        if (choice == 1) {//---------------------------------------------------------------------------------SUB(MEMBER)
            System.out.print(
                    "1 - Add new member \n" +
                            "2 - Consult member \n" +
                            "3 - Remove member \n" +
                            "Insert Option number: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                memberService.addMember();
                return;
            } else if (option == 2) {
                memberService.consultMember();

            } else if (option == 3) {
                memberService.removeMember();
                return;
            }

        } else if (choice == 2) {//-------------------------------------------------------------------SUB(AUTHORS/BOOKS)
            System.out.print(
                    "1 - Add new Author / Book \n" +
                            "2 - Consult registered Author / Book \n" +
                            "3 - Remove Book" + "\n" +
                            "Insert Option number: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                System.out.println("Does the new book to be added already have a registered author? y/n");
                char response = sc.next().charAt(0);

                if (response == 'y') {
                    authorBookService.addAuthorBookY();

                } else if (response == 'n') {
                    authorBookService.addAuthorBookN();

                }

            } else if (option == 2) {
                authorBookService.consultBook();

            } else if (option == 3) {
                authorBookService.removeBook();

            }

        } else if (choice == 3) {//---------------------------------------------------------------------------SUB(LOANS)
            System.out.print(
                    "1 - Realizes a new Loan \n" +
                            "2 - Consult Loan status \n" +
                            "3 - Close loan \n" +
                            "Insert Option number: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                loanService.newLoan();

            } else if (option == 2) {
                loanService.consultLoanStatus();

            } else if (choice == 3) {
                loanService.closeLoan();

            }

        } else if (choice == 4) {//-------------------------------------------------------------------------SUB(REPORTS)
            System.out.print("1 - Member Report \n" +
                    "2 - Books Report \n" +
                    "3 - Authors Report \n" +
                    "4 - Loans Report" +
                    "Insert Option number: ");
            int option = sc.nextInt();
        } else {
            System.out.println("Invalid Choice");
        }
    }

}
