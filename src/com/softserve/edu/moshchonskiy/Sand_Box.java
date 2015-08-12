package com.softserve.edu.moshchonskiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Evgen on 8/9/2015.
 */
public class Sand_Box {

    public static void main(String[] args) {

    //    JuniorEmployee junior = new JuniorEmployee("Vasiliy Pugovkin", 26, 3, 170);

     //   SeniorEmployee senior = new SeniorEmployee("Arkadiy Zadorojniy", 32, 5, 170);

      //  System.out.println(junior.getName() + " is a junior employee. " +);

    //    System.out.println(senior.getName() + " is a senior employee. " +
    //            "Hi is " + senior.getAge() +
     //           ". He works " + senior.getSeniority() + " years. " +
     //           " In this month, his payment is " + senior.getPayment() + ".");


        List myList = new ArrayList();

        try {

            for (int i = 0; i < 1; i++) {

                Scanner in = new Scanner(System.in);

                System.out.println("Name?");
                String tname = in.nextLine();
                System.out.println("Age?");
                int tage = in.nextInt();
                System.out.println("Seniority?");
                int tsenior = in.nextInt();
                System.out.println("Hours?");
                int thours = in.nextInt();

                myList.add(new JuniorEmployee(tname, tage, tsenior, thours));

            }

            for (int i = 0; i < 1; i++) {

                Scanner in = new Scanner(System.in);

                System.out.println("Name?");
                String tname = in.nextLine();
                System.out.println("Age?");
                int tage = in.nextInt();
                System.out.println("Seniority?");
                int tsenior = in.nextInt();
                System.out.println("Hours?");
                int thours = in.nextInt();

                myList.add(new SeniorEmployee(tname, tage, tsenior, thours));

            }


        } catch (MyException e) {

            System.out.println(e);
        }


        Collections.sort(myList);

        for (Object e : myList) {

            System.out.println(e);
        }

    }


}