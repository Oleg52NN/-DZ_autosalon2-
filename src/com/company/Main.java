package com.company;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;

public class Main {
    static int manufacturedAuto = 0;
    static int quantitySales = 10;
    static int soldAuto = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread manufacturer = new Manufacturer();
        manufacturer.start();
        final int timeBetweenBuyer = 700;
        int numberBuyer = 1;
        while (soldAuto < quantitySales){

            Thread buyer = new Thread(new Buyer("Покупатель " + numberBuyer++));
            buyer.start();
            sleep(timeBetweenBuyer);
        }


    }
}
