package entities;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public Person() {}


    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: " + name;

    }
}


