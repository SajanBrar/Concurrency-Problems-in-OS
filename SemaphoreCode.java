// Semaphore

import java.lang.Thread;

class Sp {
    public static int SemaP = 1;
}

class Process implements Runnable {
    String name;
    Thread p;
    int counter;

    Process(String threadname) {
        name = threadname;
        p = new Thread(this, name);
        System.out.println("Process: " + p);
        p.start();
    }

    void enter() {
        while (Sp.SemaP <= 0) {
            // System.out.println("Waiting");
            // Do Nothing
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
            counter++;
        }
        Sp.SemaP--;
    }

    void exit() {
        Sp.SemaP++;
    }

    public void run() {
        enter();
        // System.out.println("Process " + name + " changed Semaphore to: " + S.SemaP);
        System.out.println(name + ": Critical Section1");
        System.out.println(name + ": Critical Section2");
        exit();
        // System.out.println("Process " + name + " changed Semaphore to: " + S.SemaP);
        System.out.println(name + ": Remainder Section");
    }

    void Completion() {
        System.out.println(name + ": Process Complete");
    }
}

class ProcessWithNoCS implements Runnable {
    String name;
    Thread p;
    int counter;

    ProcessWithNoCS(String threadname) {
        name = threadname;
        p = new Thread(this, name);
        System.out.println("Process: " + p);
        p.start();
    }

    public void run() {
        System.out.println(name + ": This process has no CS");
    }

    void Completion() {
        System.out.println(name + ": Process Complete");
    }
}

class SemaphoreCode {
    public static void main(String args[]) {
        System.out.println("Sajanjit Singh Brar\n20124087\nSemaphore Implementation\n");
        System.out.println("7 Processes with CS are running in parallel");
        Process P1 = new Process("P1");
        Process P2 = new Process("P2");
        Process P3 = new Process("P3");
        Process P4 = new Process("P4");
        Process P5 = new Process("P5");
        Process P6 = new Process("P6");
        Process P7 = new Process("P7");
        ProcessWithNoCS P8 = new ProcessWithNoCS("P8");
        ProcessWithNoCS P9 = new ProcessWithNoCS("P9");
        ProcessWithNoCS P10 = new ProcessWithNoCS("P10");

        try {
            P1.p.join();
            P1.Completion();
            P2.p.join();
            P2.Completion();
            P3.p.join();
            P3.Completion();
            P4.p.join();
            P4.Completion();
            P5.p.join();
            P5.Completion();
            P6.p.join();
            P6.Completion();
            P7.p.join();
            P7.Completion();
            P8.p.join();
            P8.Completion();
            P9.p.join();
            P9.Completion();
            P10.p.join();
            P10.Completion();
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
    }
}
