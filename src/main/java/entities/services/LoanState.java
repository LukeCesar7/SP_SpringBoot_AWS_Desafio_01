package entities.services;

public enum LoanState {
    ACTIVE {
        public String getMessage() {

            return "Active Loan";
        }
    },
    COMPLETED {
        public String getMessage() {

            return "Completed Loan";
        }
    },
    LATE {
        public String getMessage() {
            return "Late Loan, necessary payment of fine";
        }
    }
}