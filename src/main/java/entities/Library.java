package entities;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members= new ArrayList<>();
    }

    public void addBook(Book book) {

        books.add(book);
    }
    public void addMember(Member member) {
        members.add(member);
    }
}
