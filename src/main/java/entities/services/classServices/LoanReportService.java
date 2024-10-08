package entities.services.classServices;

import entities.Loan;
import entities.services.Reports;

import java.util.List;

public class LoanReportService implements Reports {

    private List<Loan> loan;

    @Override
    public void newReports() {
        System.out.println("Loan Report");
        for (Loan loan : loan) {
            System.out.println(loan);
        }

    }
}
