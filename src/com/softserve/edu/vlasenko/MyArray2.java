package com.softserve.edu.vlasenko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class MyArray2 {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        in = new Scanner(System.in);
        ArrayList array1 = new ArrayList();


        for (int i = 0; i < 3; i++)
        {
            System.out.print("Enter name: ");
            String name = in.next();
            System.out.print("Enter age: ");
            int age = in.nextInt();
            System.out.print("Enter seniority: ");
            int seniority = in.nextInt();
            System.out.print("Enter worked hours: ");
            int hours = in.nextInt();
            array1.add(new JuniorEmployee(name, age, seniority, hours));
        }
        for (int i = 0; i < 3; i++)
        {
            System.out.print("Enter name: ");
            String name = in.next();
            System.out.print("Enter age: ");
            int age = in.nextInt();
            System.out.print("Enter seniority: ");
            int seniority = in.nextInt();
            System.out.print("Enter worked hours: ");
            int hours = in.nextInt();
            array1.add(new SeniorEmployee(name, age, seniority, hours));
        }
        Collections.sort(array1);
        System.out.println("After sorting:" + array1);

    }
}
