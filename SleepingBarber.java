// Sleeping Barber Problem

import java.lang.Thread;

class Sem {
    static int barber = 1;
    static int FreeSeats = 3;

    static void enter_Barber() {
        while (barber <= 0) {
            if (FreeSeats > 0) {
                FreeSeats--;
                System.out.println("Barber Chair Occupied. Going to Free Seats.");
            } else
                System.out.println("No Free Seats.");
        }
        barber--;
    }

    static void exit_Barber() {
        barber++;
    }
}

class Customer implements Runnable {
    String name;
    Thread c;
    int a;

    Customer(String threadname, int a) {
        name = threadname;
        c = new Thread(this, name);
        System.out.println("Process: " + c);
        c.start();
    }

    public void run() {
        Sem.enter_Barber();
        System.out.println(name + " In the barber chair.");
        Sem.exit_Barber();
    }

    void Completion() {
        System.out.println(name + ": Process Complete");
    }
}

class SleepingBarber {
    public static void main(String args[]) {
        System.out.println("Sajanjit Singh Brar\n20124087\nSleeping Barber Problem\n");
        Customer C1 = new Customer("C1", 0);
        Customer C2 = new Customer("C2", 1);
        Customer C3 = new Customer("C3", 2);
        Customer C4 = new Customer("C4", 3);
        Customer C5 = new Customer("C5", 4);

        try {
            C1.c.join();
            C1.Completion();
            C2.c.join();
            C2.Completion();
            C3.c.join();
            C3.Completion();
            C4.c.join();
            C4.Completion();
            C5.c.join();
            C5.Completion();
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
    }
}
