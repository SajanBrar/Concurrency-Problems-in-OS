// Sleeping Barber Problem

import java.lang.Thread;
import java.util.concurrent.*;

class Sem {
    static Semaphore barber = new Semaphore(1);
    static Semaphore chairs = new Semaphore(3);
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
        try {
            if (Sem.barber.availablePermits() == 0 && Sem.chairs.availablePermits() > 0) {
                Sem.chairs.acquire(1);
                System.out.println(name + " In waiting chair.");
                Thread.sleep(100);
                Sem.chairs.release(1);
                Sem.barber.acquire();
                System.out.println(name + " In the barber chair.");
                Sem.barber.release();
            } else if (Sem.barber.availablePermits() == 1 && Sem.chairs.availablePermits() == 0) {
                System.out.println(name + " No chair available. Going back.");
            } else if (Sem.barber.availablePermits() == 1) {
                Sem.barber.acquire();
                System.out.println(name + " In the barber chair.");
                Sem.barber.release();
            }
        } catch (InterruptedException ex) {
            System.out.println("Exception Occured");
        }
    }

    void Completion() {
        System.out.println(name + ": Process Complete");
    }
}

class SleepBarber {
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
