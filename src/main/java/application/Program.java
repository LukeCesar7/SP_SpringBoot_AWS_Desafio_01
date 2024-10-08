package application;

import entities.services.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_test");
        EntityManager em = emf.createEntityManager();

        Menu menu = new Menu(sc, dTF, em);

        boolean forever = true;

        while (forever) {
            menu.menu();

             {
                forever = true;
            }
        }
        sc.close();
        em.close();
        emf.close();
    }

}
