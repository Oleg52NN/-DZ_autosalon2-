package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

import static com.company.Main.manufacturedAuto;
import static com.company.Main.soldAuto;

public class Buyer extends Thread {
    final int timeBuyer = 500;
    final int in_out = 1700;
    ReentrantLock locker = new ReentrantLock();
    boolean if_in = true;


    public String myGetName() {
        return name;
    }

    String name;

    public Buyer(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        locker.lock();
        try {
            if (if_in) {
                System.out.println(myGetName() + " зашёл в салон");
                if_in = false;
            }
            Instant t = Instant.now();
            if (Duration.between(t, Instant.now()).toMillis() < timeBuyer && if_in) {
                if (manufacturedAuto != 0) {
                    System.out.println(myGetName() + " уехал на новеньком авто");
                    manufacturedAuto--;
                    soldAuto++;
                    // numberPurchase++;
                    if_in = true;

                } else {
                    System.out.println("Машин в продаже нет");
                }
            }
            sleep(in_out);
            while (true) {
                if (Duration.between(t, Instant.now()).toMillis() > timeBuyer) {
                    if (manufacturedAuto == 0) {
                        System.out.println("Машин в продаже нет");
                        System.out.println(myGetName() + " не дождался и ушёл");
                    } else {
                        System.out.println(myGetName() + " уехал на новеньком авто");
                        manufacturedAuto--;
                        soldAuto++;
                    }
                    if_in = true;
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
}
