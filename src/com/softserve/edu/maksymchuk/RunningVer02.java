package com.softserve.edu.maksymchuk;


import java.util.Scanner;

/**
 * @author Denys Maxymchuk
 */
public class RunningVer02 {
    public static void main(String[] args) {
//        List<Employee> employees = new ArrayList<>();
        try (Scanner sc = new Scanner(System.in)) {
            Employee employee = employeeRead(sc);
        }


    }

    private static Employee employeeRead(Scanner sc) {
        boolean isCreated = false;
        Employee employee = null;
        while (!isCreated) {
            System.out.println("to create senior input 1, to create junior input 2");
            System.out.print(">");
            if (sc.hasNext()) {
                switch (sc.next()) {
                    case "1": {
                        isCreated = true;
                        break;
                    }
                    case "2": {
                        isCreated = true;
                        break;
                    }
                    default: {
                        System.out.println("wrong input, try again");
                        break;
                    }

                }
            }
        }
        return employee;
    }
}
