package finance;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SavingsAccount extends Accounts {
    private double interest;
    private Calendar lastInterestDate;


    public static void main(String[] args) {

        // System.out.println(new SavingsAccount("J.N","9876543").getInterest());
        //SavingsAccount acct1 = new SavingsAccount("W>E", 1376543);
        SavingsAccount acct2 = new SavingsAccount("J.N", 1376543);
        //acct1.deposit(3000);
        acct2.deposit(12000);
        acct2.calculateDelay(27,5,32 , 0);
        acct2.monthlyInterest();

        acct2.getBalance();


        //SavingsAccount acct = new SavingsAccount("123456", 500);
        //System.out.println(acct.addInterest());
        //System.out.println(acct.addInterest());
        //System.out.println(acct.deposit(500));
        //System.out.println(acct.deposit(3000));
    }


    public SavingsAccount(String fullName, long accountNumber) {
        super(fullName, accountNumber, "SavingsAccount");
        this.interest = 0.05;// % per month
        this.lastInterestDate = Calendar.getInstance();
    }


    public BigDecimal addInterest() {
        Date date = new Date();
        System.out.println(date);
        System.out.println("*******Calculating your interest");
        System.out.println("Previous Balance = £" + super.getBalance());
        double amount = super.getBalance() * this.interest / 100;
        System.out.println("Interest on £" + super.getBalance() + " is: £" + amount);
        double newBalance = amount + super.getBalance();
        System.out.println("New Balance = £" + newBalance);
        return super.deposit(amount);

    }

    public double getInterest() {
        return interest;
    }


    //An option is to add interest everytime money is added to the account when to add interest only at day 28
    public void TimeToAddInterest() {
        int the1st = 1;
        int at16hrs = 16;

        MonthlyTimer t = MonthlyTimer.schedule(new Runnable() {
            public void run() {
                System.out.println("You are due for Monthly interest ");
                addInterest();
            }
        }, the1st, at16hrs);

        // will print "You are due for Monthly interest" every 1st at 16:00 hrs.
        t.cancelCurrent();

    }

    public void monthlyInterest() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Define the task you want to run
        Runnable task = () -> {
            // Your task logic goes here
            System.out.println("You are due for Monthly interest ");
            addInterest();
            System.out.println("Task executed at " + System.currentTimeMillis());
        };
// Simulating a loan default after 2 mins (for example purposes)
        executor.scheduleAtFixedRate(
                task, // Task to run
                0, // Initial delay (start immediately)
                2, // Repeat every 2 minutes
                TimeUnit.MINUTES // Time unit
        );
        System.out.println();
    }





//        // Schedule the task to run on a specific day of the month (e.g., the 1st day)
//        int dayOfMonth = 27;
//
//        // Set the time (hours, minutes, seconds)
//        int hour = 0;
//        int minute = 0;
//        int second = 0;
//
//        // Schedule the task to run on the specified day and time every month
//        executor.scheduleAtFixedRate(
//                task,
//                calculateDelay(dayOfMonth, hour, minute, second),
//                30, // Repeat every 30 days (1 month)
//                TimeUnit.DAYS
//        );//   }

    // Calculate the delay until the first execution
    private long calculateDelay(int dayOfMonth, int hour, int minute, int second) {//sets when it will run
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.util.Calendar future = java.util.Calendar.getInstance();
        future.set(today.get(java.util.Calendar.YEAR), today.get(java.util.Calendar.MONTH), dayOfMonth, hour, minute, second);

        if (today.after(future)) {
            future.add(java.util.Calendar.MONTH, 1);
        }

        return future.getTimeInMillis() - today.getTimeInMillis();
    }
}

