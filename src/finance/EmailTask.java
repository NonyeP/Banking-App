package finance;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class EmailTask extends TimerTask {
    public void run() {
        // This is where you'd put the code to send the email
        System.out.println("Sending email: Customer has defaulted on the loan.");
        // Include email sending logic here using JavaMail API or an email service provider's API
    }

    public void sendEmail(){
        Timer timer = new Timer();

        // Simulating a loan default after 7 days (for example purposes)
        //int daysTillDefault = 7;
        int daysTillDefault = 1;// for 1 min

        // Scheduling the task to send an email after the specified time
        //timer.schedule(new EmailTask(), daysTillDefault * 24 * 60 * 60 * 1000);
        timer.schedule(new EmailTask(), daysTillDefault * 60 * 1000); //for 1 min

        // To simulate the passage of time for demonstration purposes
        System.out.println("Timer scheduled. Current time: " + LocalDateTime.now());
        try {
            Thread.sleep((daysTillDefault + 1) * 60 * 1000); // Simulating passage of time for 1 min + delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel(); // Cancel the timer (in a real application, you might handle cancellation differently)
    }





    public static void main(String[] args) {
        new EmailTask().sendEmail();

    }
}

