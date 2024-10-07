package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Member extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String address;
    private String phone;
    private LocalDate joinDate;

    public Member() {

    }

    public Member( String name, String email, String address, String phone, LocalDate joinDate) {
        super(name);
        this.email = email;
        this.address= address;
        this.phone = phone;
        this.joinDate = joinDate;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }



    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getAddress() {

        return address ;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public LocalDate getJoinDate() {

        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {

        this.joinDate = joinDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return  "Name: " + getName()  +
                ", Email: " + email +
                ", Address: " + address +
                ", Phone: " + phone  +
                ", Register Date: " + joinDate;
    }
}