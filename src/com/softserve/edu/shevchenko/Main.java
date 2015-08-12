package com.softserve.edu.shevchenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 08.08.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("**********************TASK_1**********************");
        JuniorEmployee je = new JuniorEmployee("Mike", 34, 10, 148);
        SeniorEmployee se = new SeniorEmployee("Bob", 40, 24, 780);

        System.out.println("Name: " + je.getName() + ", age: " + je.getAge() +
                ", Seniority: " + je.getSeniority() + ", hours: " + je.getHours() + ", Payment: " + je.getPayment());
        System.out.println("Name: " + se.getName() + ", age: " + se.getAge() +
                ", Seniority: " + se.getSeniority() + ", hours: " + se.getHours() + ", Payment: " + se.getPayment());

        System.out.println("\n\n**********************TASK_2**********************");

        List<Employee> employeeList = new ArrayList<>();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        /* Creating employeers by typing from keyboard*/
        while (true) {
            System.out.println("Do you wanna create Senior Employee or Junior Employee? Type 'senior' or 'junior'");
            String who = console.readLine();
            System.out.println("Name (String): ");
            String name = console.readLine();
            System.out.println("Age (int): ");
            int age = Integer.parseInt(console.readLine());
            System.out.println("Seniority (int): ");
            int seniority = Integer.parseInt(console.readLine());
            System.out.println("Hours (int): ");
            int hours = Integer.parseInt(console.readLine());

            if (who.equals("junior")) {
                employeeList.add(new JuniorEmployee(name, age, seniority, hours));
            } else if (who.equals("senior")) {
                employeeList.add(new SeniorEmployee(name, age, seniority, hours));
            }

            System.out.println("Do you wanna create more employee? Type 'y' for create one more");
            String continueWhile = console.readLine();

            if (!continueWhile.equals("y")) {
                break;
            }
        }

        Collections.sort(employeeList);

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }


    }

}
