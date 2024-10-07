package entities.services.classServices;

import entities.Author;
import entities.Book;
import entities.Person;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AuthorBookService {

    private Scanner sc;
    private DateTimeFormatter dTF;
    private EntityManager em;

    public AuthorBookService() {
    }

    public AuthorBookService(Scanner sc, DateTimeFormatter dTF, EntityManager em) {
        this.sc = sc;
        this.dTF = dTF;
        this.em = em;
    }

    //-------------------------------------------------------------------------------------------------------AUTHOR/BOOK
    public void addAuthorBookY() {

        try {
            sc.nextLine();
            System.out.println("Enter ISBN: (13-digits) "); //author-book
            long isbn = sc.nextLong();
            sc.nextLine();

            Book consultISBN = em.find(Book.class, isbn);
            String isbnLimit = String.valueOf(isbn);

            if (isbnLimit.length() != 13) {
                System.out.println("Error! ISBN is not a valid ISBN number!");
                return;
            }
            if (consultISBN != null) {
                System.out.println("Error! ISBN already exists!");
                return;
            }

            System.out.println("Enter title: ");
            String titleBook = sc.nextLine();
            System.out.println("Enter publish date: (dd/MM/yyyy) ");
            LocalDate publishDate = LocalDate.parse(sc.next(), dTF);
            sc.nextLine();
            System.out.println("Enter genre: ");
            String genreBook = sc.nextLine();
            System.out.println("Enter quantity: ");
            int qty = sc.nextInt();

            System.out.print("Enter the ID of the existing author: ");
            int authorId = sc.nextInt();
            Author author = em.find(Author.class, authorId);

            // Console related Problem #68
            if (author == null) {
                System.out.println("Error! Author not found!");
            } else {
                Book book = new Book(titleBook, publishDate, isbn, author, genreBook, qty);

                em.getTransaction().begin();
                em.persist(book);
                em.getTransaction().commit();

                System.out.println("Added! " + titleBook + " book");

            }
        } catch (Exception e) {
            System.out.println("Error! Datem example insert: 05/02/1997 " +
                    "/ Only number in Quantity / Only numbers in cadastred Author ID space insert! Try again!");
        }
    }

    public void addAuthorBookN() {
        try {
            sc.nextLine();
            System.out.print("Enter name of author: ");
            String nameAuthor = sc.nextLine();

            System.out.print("Enter author's birth date (dd/MM/yyyy): ");
            LocalDate birthDate = LocalDate.parse(sc.next(), dTF);
            sc.nextLine();
            System.out.print("Enter author's nationality: ");
            String nationality = sc.nextLine();
            System.out.println("Enter author's biography: ");
            String biography = sc.nextLine();

            Author author = new Author(nameAuthor, birthDate, nationality, biography);

            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();

            System.out.println("Enter ISBN: (13-digits) "); //author-book
            long isbn = sc.nextLong();
            sc.nextLine();
            //isbnPolicy
            Book consultISBN = em.find(Book.class, isbn);
            String isbnLimit = String.valueOf(isbn);

            if (isbnLimit.length() != 13) {
                System.out.println("Error! ISBN is not a valid ISBN number!");
                return;
            }
            if (consultISBN != null) {
                System.out.println("Error! ISBN already exists!");
                return;
            }

            System.out.println("Enter title: ");
            String titleBook = sc.nextLine();
            System.out.println("Enter publish date: (dd/MM/yyyy) ");
            LocalDate publishDate = LocalDate.parse(sc.next(), dTF);
            sc.nextLine();
            System.out.println("Enter genre: ");
            String genreBook = sc.nextLine();
            System.out.println("Enter quantity: ");
            int qty = sc.nextInt();


            Book book = new Book(titleBook, publishDate, isbn, author, genreBook, qty);

            em.getTransaction().begin();
            em.merge(book); //insert book database
            em.getTransaction().commit();

            System.out.println("Added! " + titleBook + " book");
        } catch (Exception e) {
            System.out.println("Error! Observes date model (dd/MM/yyyy) / Quantity only accepts integer " +
                    "numbers" + e.getMessage());
        }
    }

    public void consultBook() {
        try {
            System.out.println("Consult Book");
            System.out.println("Enter ID: ");
            Long id = sc.nextLong();

            Book findId = em.find(Book.class, id);
            System.out.println(findId);
        } catch (Exception e) {
            System.out.println("Error! Book not found / ID accepts only numbers!" + e.getMessage());
        }
    }

    public void removeBook() {
        try {
            System.out.println("Remove Book");
            System.out.println("Enter ISBN: ");
            long isbn = sc.nextLong();
            Book findID = em.find(Book.class, isbn);

            em.getTransaction().begin();
            em.remove(findID);
            em.getTransaction().commit();

            System.out.println("Removed Book");
        } catch (Exception e) {
            System.out.println("Error! Book not found / ID accepts only numbers!" + e.getMessage());
        }
    }
}