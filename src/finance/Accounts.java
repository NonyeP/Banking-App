package finance;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


/*
also known as a Basic account which serves as the base of all accounts type

 */
public class Accounts {

    private String fullName;
    private long accountNumber;
    private double balance;
    private double overDraftAmount;
    private String accountType;
    private static Map<String, Long> allAccounts = new HashMap<>() ;

    public static void main(String[] args) {
        Accounts obj1 = new Accounts("Jane Doe", 7100.0);
        long accountNumber =  obj1.generateAccountsNumber();
        obj1.setAccountType("Savings");
        obj1.getAccountNumber();
        System.out.println("Account Number :" + accountNumber);
        System.out.println("Account Name :" +obj1.getFullName());
        System.out.println("Account Balance :" +obj1.getBalance());
        System.out.println("Account Type :" +obj1.getAccountType());
        System.out.println();

        Accounts obj2 = new Accounts("John Doe", 7300.0);
        long accountNumber2 =  obj2.generateAccountsNumber();
        obj2.setAccountType("Savings");
        obj2.getAccountNumber();
        System.out.println("Account Number :" + accountNumber2);
        System.out.println("Account Name :" +obj2.getFullName());
        System.out.println("Account Balance :" +obj2.getBalance());
        System.out.println("Account Type :" +obj2.getAccountType());

        System.out.println();
        obj1.addToMapUser("John Doe",accountNumber);
        obj2.addToMapUser("Jane Doe",accountNumber2);

        System.out.println(allAccounts);

        obj1.deposit(1100);

        obj1.withdraw(500);






    }

    public Accounts(String fullName, long accountNumber, String accountType) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.overDraftAmount = 0;
        this.accountType = accountType;
        this.allAccounts = new HashMap<>();
    }


//        public Accounts(String fullName, long accountNumber) {
//            this.fullName = fullName;
//            this.accountNumber = accountNumber;
//        }

    public Accounts(String fullName, double balance) {//for account opening
        this.fullName = fullName;
        this.balance = balance;
        this.allAccounts = new HashMap<>();
        //this.accountNumber = generateAccountsNumber();

        //this.allAccounts.put(this.fullName,this.accountNumber);
        //System.out.println(this.allAccounts);
    }

    public void openAccount(String typeOfAccount){
        this.accountType = typeOfAccount;

    }

    public void addToMapUser(String name,long accountNumber){
        allAccounts.put(name,accountNumber);
        // System.out.println(allAccounts);
    }


    public long generateAccountsNumber(){
        AtomicLong uniqueId = new AtomicLong(System.currentTimeMillis());
        long id = uniqueId.incrementAndGet();
        System.out.println("Unique ID generated: " + id);

        return id;

    }

    public BigDecimal deposit(double moneyIn){
        Date date = new Date();
        System.out.println(date);

        System.out.println("*****Deposit Statement Begins***** ");
        if(moneyIn >= 0){
            this.balance += moneyIn;
            System.out.println("Your Account Balance = £" + this.balance);
            BigDecimal convertTo = BigDecimal.valueOf(this.balance);
            System.out.println("*****Deposit Statement Ends***** ");
            return convertTo;
        }else{
            System.out.println("Invalid input: Please input positive integers.");
            //overDraft();
            System.out.println("****Deposit Statement Ends***** ");
            return null;
        }

    }

    //    public BigDecimal overDraft(double overDraftAmount) {
    //        //this.overDraftAmount = overDraftAmount;
    //        if(overDraftAmount == 0) {
    //            System.out.println("Your Account have no overdraft set");
    //            this.overDraftAmount = 0;
    //            BigDecimal convertTo = BigDecimal.valueOf(this.overDraftAmount);
    //            return null;
    //        }else if(overDraftAmount < 0) {
    //            this.overDraftAmount = overDraftAmount;
    //            System.out.println("Your Account have an overdraft set ");
    //            BigDecimal convertTo = BigDecimal.valueOf(this.overDraftAmount);
    //            return convertTo;
    //        }else {
    //            System.out.println("Invalid input. Please ");
    //            BigDecimal convertTo = BigDecimal.valueOf(0);
    //            return convertTo;
    //        }
    //    }

    public BigDecimal withdraw(double moneyOut){
        Date date = new Date();
        System.out.println(date);
        System.out.println("*****Withdrawal Statement Begins***** ");
        if(moneyOut < 0 ){
            System.out.println("Invalid Input: Please input positive integers.");
            return null;
        }else if((moneyOut >= 0)&&(moneyOut <= this.balance)){
            this.balance -= moneyOut;
            System.out.println("Your Account Balance = £" + this.balance);
            BigDecimal convertTo = BigDecimal.valueOf(this.balance);
            System.out.println("*****Withdrawal Statement Ends***** ");
            return convertTo;
        }else{
            System.out.println("Invalid input: You need an overdraft");
            //overDraft();
            System.out.println("*****Withdrawal Statement Ends***** ");
            return null;
        }

    }

    public String getAccountType() {

        return this.accountType;
    }

    public void setAccountType(String accountType) {

        this.accountType = accountType;
    }

    public long getAccountNumber() {

        return this.accountNumber;
    }

    public void setAccountNumber(long accountNumber) {

        this.accountNumber = accountNumber;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String name) {

        this.fullName = name;
    }

    public double getBalance() {

        return balance;
    }

    @Override
    public String toString() {
        System.out.println("Welcome to Private Bank");
        return "[Accounts name = " + fullName + ", accountNumber = " + accountNumber + "]";
    }


}



