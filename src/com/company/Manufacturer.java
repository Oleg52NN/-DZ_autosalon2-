package com.company;

import static com.company.Main.*;

public class Manufacturer extends Thread {
    final int timeManufaktured = 1500;


    @Override
    public void run() {
        while (soldAuto < quantitySales) {
            System.out.println("Производитель доставил в салон автомобиль ");
            manufacturedAuto++;
            try {
                sleep(timeManufaktured);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
