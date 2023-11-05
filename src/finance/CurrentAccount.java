package finance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class CurrentAccount extends Accounts {
    private double overDraftLimit;
    private double overDraftAmount;


    public CurrentAccount(String fullName, long accountNumber) {
        super(fullName, accountNumber, "CurrentAccount");
        this.overDraftLimit = -1000;
        this.overDraftAmount = overDraftAmount;
    }

    public BigDecimal setOverDraft(double overDraftAmount) {//sets overdraft
        //this.overDraftAmount = overDraftAmount;
        BigDecimal convertTo = BigDecimal.valueOf(0);
        if (overDraftAmount == 0) {
            System.out.println("Your Account have no overdraft set");
            this.overDraftAmount = 0;
            convertTo = BigDecimal.valueOf(this.overDraftAmount);
            return null;
        } else if (overDraftAmount < 0 && overDraftAmount >= -1000) {
            this.overDraftAmount = overDraftAmount;
            System.out.println("Your Account have an overdraft set ");
            convertTo = BigDecimal.valueOf(this.overDraftAmount);
            return convertTo;
        } else {
            System.out.println("Invalid input. Please put in negative values between -0.1 and -1000");
            //convertTo = BigDecimal.valueOf(0);
            return convertTo;
        }
    }

    @Override
    public double getBalance() {
        if (super.getBalance() > 0) {
            System.out.println("Your Account Balance = £" + super.getBalance());
            return super.getBalance();
        } else if (super.getBalance() < 0) {
            System.out.println("Invalid input: Please input positive integers.");
            return 0;
        } else {
            System.out.println("Request for OverDraft");
            return 0;
        }
    }

    public void setTimeToRepayOverDraft(LocalDate loanIssuedDate, int repaymentDurationInDays) {

        // Calculate the due date for repayment
        LocalDate dueDate = loanIssuedDate.plusDays(repaymentDurationInDays);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDueDate = dueDate.format(formatter);

        System.out.println("Loan Issued Date: " + loanIssuedDate);
        System.out.println("Due Date for Repayment: " + formattedDueDate);
    }

}





