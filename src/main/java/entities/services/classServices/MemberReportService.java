package entities.services.classServices;

import entities.Member;
import entities.services.Reports;

import java.util.List;

public class MemberReportService implements Reports {

    private List<Member> member;

    @Override
    public void newReports() {
        System.out.println("Members information");
        for (Member member : member) {
            System.out.println(member);
        }

    }
}
