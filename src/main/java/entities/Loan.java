package entities;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateLoanStarts;
    private LocalDate dateLoanFinishes;
    private String bookState;
    private BigDecimal fineValue;
    //-->

    @ManyToOne
    private Member member; // Member in loans

    @ManyToOne
    private Book book; // Book in loans

    public Loan() {

    }

    public Loan(LocalDate dateLoanStarts, Member member, Book book, LocalDate dateLoanFinishes) {
        this.dateLoanStarts = dateLoanStarts;
        this.member = member;
        this.book = book;
        this.dateLoanFinishes = dateLoanFinishes;
    }

    public LocalDate getDateLoanStarts() {
        return dateLoanStarts;
    }

    public void setDateLoanStarts(LocalDate dateLoanStarts) {
        this.dateLoanStarts = dateLoanStarts;
    }

    public LocalDate getDateLoanFinishes() {
        return dateLoanFinishes;
    }

    public void setDateLoanFinishes(LocalDate dateLoanFinishes) {
        this.dateLoanFinishes = dateLoanFinishes;
    }

    public String getBookState(String status) {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }

    public BigDecimal getFineValue() {
        return fineValue;
    }

    public void setFineValue(BigDecimal fineValue) {
        this.fineValue = fineValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    @Override
    public String toString() {
        return " Date Loan Starts: " + dateLoanStarts +
                ", Date Loan finishes: " + dateLoanFinishes +
                ", Book state: " + bookState +
                ", Fine Value: " + fineValue;
    }
}
