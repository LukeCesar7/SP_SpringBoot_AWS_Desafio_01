package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate birthDate;
    public String nationality;
    public String biography;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();


    public Author() {

    }

    public Author( String name, LocalDate birthDate, String nationality, String biography) {
        super(name);
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.biography = biography;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getName(), author.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + getName() + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", biography='" + biography;
    }

}
