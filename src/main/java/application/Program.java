package application;
import entities.services.MenuService;
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

        MenuService menuService = new MenuService(sc, dTF, em);

        menuService.menu();

        sc.close();
        em.close();
        emf.close();
    }
}
