package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public Long isbn;
    private String title;
    private LocalDate publishDate;
    private String genre;
    private int qty;
    //-->
    @ManyToOne
    @JoinColumn(name = "author_id", nullable= false)
    private Author author;

    public Book() {
    }

    public Book(String title, LocalDate publishDate, Long isbn, Author author, String genre, int qty) {
        this.title = title;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.qty = qty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQty() {//
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return ", Title: " + title+
                ", publishDate: " + publishDate +
                ", ISBN: " + isbn +
                ", Author: " + author +
                ", Genre: " + genre +
                ", Qty: " + qty;
    }
}