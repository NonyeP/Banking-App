package finance;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.*;

public class Banking {
    /**
     * The Bank class represents a bank that can create and manage accounts.
     * It provides methods to open new accounts, close existing accounts,check if an account exists,
     * search for account,verifies an account details, update an account,
     * and perform various other banking operations.
     */

    private static List<Accounts> acctObj = new ArrayList<>();
    private Map<String, AbstractMap.SimpleEntry<Long, Double>> transactionDetails;//transaction->unique ID and amount
    private Map<String,Long> transaction;
    private long acctNumber;

    private String name;


    public static void main(String[] args) {
        Accounts obj3 = new Accounts("Jane Doe", 300);
        obj3.openAccount("Savings Account");

        System.out.println("Account Number :" + obj3.getAccountNumber());
        System.out.println("Account Name :" +obj3.getFullName());
        System.out.println("Account Balance :" +obj3.getBalance());
        System.out.println("Account Type :" +obj3.getAccountType());

        acctObj.add(obj3);
        System.out.println(acctObj);

        Banking obj = new Banking(1698877883750L,"Henry JJ"); //warningMessage();

        //obj.openAccount("Juliet Phelan","Current account",450);





//        Accounts obj1 = new Accounts("John Doe ", 100.0);
//        long accountNumber =  obj1.generateAccountsNumber();
//        obj1.setAccountType("Savings");
//        System.out.println("Account Number :" + accountNumber);
//        System.out.println("Account Name :" +obj1.getFullName());
//        System.out.println("Account Balance :" +obj1.getBalance());
//        System.out.println("Account Type :" +obj1.getAccountType());

        //       obj1.deposit(1100);

        //       obj1.withdraw(500);



    }

    public Banking() {
    }

    public Banking(long acctNumber, String name) {
        this.acctObj = new ArrayList<>();
        this.acctNumber = acctNumber;
        this.name = name;
        this.transaction = new HashMap<>();
        this.transactionDetails = new HashMap<>();
    }

    public List<Accounts> getAcctObj() {
        //this.getAcctObj().add(this.acctNumber);
        return this.acctObj;
    }

    public void addNewAccount(Accounts acctNum){  //adds created account generated if none exists
        if(findAccount(acctNum)){
            System.out.println("Account is Already on list");
        }else {
            this.acctObj.add(acctNum);
        }

    }
    public boolean deleteAccount(Accounts acctNum) {
        if (findAccount(acctNum)) {
            acctObj.remove(acctNum);
            return true; // Account deleted successfully
        } else {
            System.out.println("Account does not exist");
            return false; // Account not found
        }
    }

    private boolean findAccount(Accounts acctNum) {    //finds account with complete details
        boolean flag = true;
        for(Accounts s : this.acctObj) {
            if (s == acctNum) {
                System.out.println("Account Already Exists");
                System.out.println(acctNum.getAccountType() + " " + acctNum.getFullName()
                        + " " + acctNum.getAccountNumber());
                return flag;
            } else {
                System.out.println("Account does not exist. Please generate an Account number to open an account");
                flag = false;
                return flag;

            }
        }
        return flag;

    }
    public void InstructionList(){
        System.out.println("""
                Please supply your full name and the type of account you will like to open here at \s
                PrivateBank when prompted to. We have three types of account\s
                 1. Basic \s
                 2. Savings \s
                 3. Current\s
                 Thank you""");
    }
    public Accounts openAccount(String fullName,String acctType,long deposit){  // opening of an account
        long acctNum = generateNewAccountNumber();
        Accounts acct = null;
        switch (acctType.trim().toLowerCase()) {
            case "savings" -> {
                acct = new SavingsAccount(fullName, acctNum);
                acct.deposit(deposit);
                System.out.println("Your Savings Account have been opened");
                this.acctObj.add(acct);
                this.transaction.put("saving", deposit);
            }
            case "basic" -> {
                acct = new Accounts(fullName, acctNum);
                acct.deposit(deposit);
                System.out.println("Your Basic Account have been opened");
                this.acctObj.add(acct);
                this.transaction.put("saving", deposit);
            }
            case "current" -> {
                acct = new CurrentAccount(fullName, acctNum);
                acct.deposit(deposit);
                System.out.println("Your Current Account have been opened");
                this.acctObj.add(acct);
                this.transaction.put("saving", deposit);
            }
            default -> System.out.println("Invalid Account Type entry Please try Again");
        }

        return acct;
    }
    private int findAccount(long acctNum) { //used to update the selected account by getting the index position on the list
        // overloaded findAccount finds account with only account number and returns the bearer of the account
        for(int i = 0; i < this.acctObj.size(); i++) {
            Accounts acct = this.acctObj.get(i);
            System.out.println(acct.getFullName() + acct.getAccountNumber());
            if (acct.getAccountNumber() == acctNum) {
                return i;
            }
        }
        System.out.println("Contact not on list");
        return -1;

    }

    private String VerifyAccountName(long acctNum) {
        String name = "";// verifies an account holder details using account number
        for(int i = 0; i < this.acctObj.size(); i++) {
            Accounts acct = this.acctObj.get(i);
            System.out.println(acct.getFullName() + acct.getAccountNumber());
            if (acct.getAccountNumber() == acctNum) {
                name = acct.getFullName();
                System.out.println(name + acct.getAccountNumber());

            }
            name = "contact not found";

        }
        return name;
    }

    public void transaction(long acctnum, String transac, long transAcct, double amount){
        // performs transactions of paying bills and transfer of funds for account holders
        switch (transac.trim().toLowerCase()) {
            case "pay bills" -> payBills(acctnum,transAcct, amount);
            case "transfer funds" -> transfer(acctnum, transAcct, amount);
            default -> System.out.println("You can only pay bills and transfer funds");
        }
    }

    private void transfer(long acctnum, long transAcct, double amount) {
        long uniqueID = System.currentTimeMillis();

        for (Accounts account : this.acctObj) {
            if (account.getAccountNumber() == acctnum) {
                double balance = account.getBalance();
                if(balance >= amount){
                    balance -= amount;
                    informationMessage();
                }
                warningMessage();
            }
            System.out.println("Account does not exist");
        }
        this.transactionDetails.put("transfer",new AbstractMap.SimpleEntry<>(uniqueID,amount));
    }

    public void payBills(long acctnum, long transAcct, double amount) {
        long uniqueID = System.currentTimeMillis();
        for (Accounts account : this.acctObj) {
            if (account.getAccountNumber() == acctnum) {
                double balance = account.getBalance();
                if (balance >= amount) {
                    balance -= amount;
                    informationMessage();

                }
                warningMessage();
            }
            System.out.println("Account does not exist");
        }
        this.transactionDetails.put("Paybills",new AbstractMap.SimpleEntry<>(uniqueID,amount));
    }

    public void update(Accounts oldInfo, Accounts newInfo){
        if (findAccount(oldInfo)) {
            int index = acctObj.indexOf(oldInfo);
            acctObj.set(index , newInfo);

        } else {
            System.out.println("Account does not exist");
        }


    }

    private long generateNewAccountNumber() {
        // Finding the maximum accountNumber using a loop
        long maxAccountNumber = 10000000L;

        for (Accounts account : this.acctObj) {
            long currentAccountNumber = account.getAccountNumber();
            if (currentAccountNumber > maxAccountNumber) {
                maxAccountNumber = currentAccountNumber;
            }
        }

        return maxAccountNumber + 1;
    }
    public long getAcctNumber()
    {
        return this.acctNumber;
    }

    public void viewAllUsers() {
        System.out.println("List of Users:");
        Map<Long, String> map = new HashMap<>();

        // put every value list to Map
        for (Accounts acct : this.acctObj) {
            map.put(acct.getAccountNumber(), acct.getFullName());
        }

        // print map
        System.out.println("Map  : " + map);
    }

    public void warningMessage(){
        JOptionPane.showMessageDialog(null, "This is a warning message\n" +
                        "insufficient balance in account",
                "Warning", JOptionPane.WARNING_MESSAGE);
    }


    public void informationMessage(){
        JOptionPane.showMessageDialog(null, "This is an information message. Your transaction was successful",
                "Information", JOptionPane.INFORMATION_MESSAGE);
    }


    public void viewAllTransaction(){

    }

    public void generateMailForOverDraftNonPayment(){

    }

    public void payDividend(double dividend) {
        for (Accounts account : acctObj) {
            account.deposit(dividend);
        }
    }

    public void updateAccountActivity() {
        for (Accounts account : acctObj) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).addInterest();
            } else if (account instanceof CurrentAccount) {
                getAcctNumber();
                if (account.getBalance() < 0) {
                    new EmailTask().sendEmail();
                    letter();
                    //sendOverdraftLetter(account.getAccountNumber());
                }
            }
        }
    }

    public void letter(){
        System.out.println("You have defaulted in your repayment of Loan");
    }

    private void sendOverdraftLetter(long accountId) {
        System.out.println("Overdraft letter sent to holder of account no.: " + accountId);
    }



}




