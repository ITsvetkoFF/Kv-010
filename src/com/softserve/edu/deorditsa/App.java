package com.softserve.edu.deorditsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * App is the main class of application
 *
 * @author Acidroed
 */


public class App {


    /**
     * Method readArguments read Emloyees data from InputStream.   *
     *
     * @return String array with Employees info.
     * @throws IOException
     */
    public static String[] readArguments() throws IOException {
        String[] employeeInfo = new String[4];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the name:");
        employeeInfo[0] = br.readLine();
        System.out.println("Please enter the age (18 <= age < 80):");
        employeeInfo[1] = br.readLine();
        System.out.println("Please enter the seniority (0 <= seniority <= (age - 18)):");
        employeeInfo[2] = br.readLine();
        System.out.println("Please enter the working hours:");
        employeeInfo[3] = br.readLine();
        System.out.println();
        return employeeInfo;
    }

    /**
     * Method secondTask runs second task of HW1. Reads six Employees info from InputStream, checks it, add to List, sorts, and displays result.
     *
     * @throws IOException
     */
    public static void secondTask() throws IOException {
        List<Employee> employeeList = new ArrayList<Employee>();
        for (int i = 0; i < 3; i++) {
            String[] employeeInfo = readArguments();
            try {
                Employee employee = new JuniorEmployee(employeeInfo[0], Integer.parseInt(employeeInfo[1]), Integer.parseInt(employeeInfo[2]), Integer.parseInt(employeeInfo[3]));
                employeeList.add(employee);
            } catch (MyException e) {
                System.out.println(e.getMessage());
                i--;
            }

        }
        for (int i = 0; i < 3; i++) {
            String[] employeeInfo = readArguments();
            try {
                Employee employee = new SeniorEmployee(employeeInfo[0], Integer.parseInt(employeeInfo[1]), Integer.parseInt(employeeInfo[2]), Integer.parseInt(employeeInfo[3]));
                employeeList.add(employee);
            } catch (MyException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }

        Collections.sort(employeeList);

        for (Employee employee : employeeList) {
            System.out.printf("Name: %s, Age: %d, Working seniority: %d, Working hours: %d, Salary: %.2f$%n", employee.getName(), employee.getAge(), employee.getSeniority(), employee.getHours(), employee.getPayment());

        }
    }


    /**
     * Method firstTask runs first task of HW1. Makes two Employees by Constructor and displays result.
     *
     * @throws IOException
     */
    public static void firstTask() {
        Employee jun = null;
        Employee sen = null;
        try {
            jun = new JuniorEmployee("Ivan", 22, 1, 2000);
            sen = new SeniorEmployee("Fedor", 32, 8, 10000);
            System.out.printf("Name: %s, Age: %d, Working seniority: %d, Working hours: %d, Salary: %.2f$%n", jun.getName(), jun.getAge(), jun.getSeniority(), jun.getHours(), jun.getPayment());
            System.out.printf("Name: %s, Age: %d, Working seniority: %d, Working hours: %d, Salary: %.2f$", sen.getName(), sen.getAge(), sen.getSeniority(), sen.getHours(), sen.getPayment());
        } catch (IllegalArgumentException e) {
            System.out.println("Please try again!");
        }
    }

    public static void main(String[] args) {

        //firstTask();

        try {
            secondTask();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
