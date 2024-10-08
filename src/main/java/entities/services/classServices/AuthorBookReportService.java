package entities.services.classServices;
import entities.Author;
import entities.services.Reports;
import java.util.List;

public class AuthorBookReportService implements Reports {

    List<Author> authors;

    @Override
    public void newReports() {
        System.out.println("Author and Book information");
        for (Author author : authors) {
            System.out.println(author);
        }
    }
}
