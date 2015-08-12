package com.softserve.edu.statsenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//     1. Всі класи реалізовувати у пакеті com.softserve.edu.
//        Написати інтерфейс (Worker), з методами String getName(), int getAge(). int getSeniority() та double getPayment().
//
//        Написати абстрактний клас (Employee), який наслідує створений інтерфейс (Worker).
//        В класі створити поля для збереження імені (name), віку (age), трудового стажу (seniority) та відпрацьованих годин (hours).
//        Поля заповнювати за допомогою конструктора, вік та стаж перевіряти на коректність. Інших конструкторів та set-ів не створювати.
//        Реалізувати public методи getName(), getAge() i getSeniority() та protected метод getHours().
//        Метод getPayment() залишити абстрактним.
//
//        Написати два класи JuniorEmployee та SeniorEmployee які наслідують Employee.
//        В класах реалізувати конструктор з викликом конструктора батьківського класу.
//        В кожному з класів реалізувати метод getPayment() по своєму алгоритму (на власний розсуд).
//        При розрахунку в методі getPayment() врахувати стаж та відпрацьовані години (звертатися через методи getAge() та getHours()).
//
//        У методі main (деякого іншого класу) сворити два об'єкти класів JuniorEmployee та SeniorEmployee.
//        Вивести вмістиме даних класу (використати get-ри) на екран.
//
//     2. В умовах завдання 1 реалізувати можливість сортування класів JuniorEmployee та SeniorEmployee
//        по імені за допомогою наслідування інтерфейсу Comparable.
//        Обгрунтувати вибір класу (класів), для наслідування Comparable.
//
//        У методі main (деякого іншого класу) створити клекцію типу ArrayList.
//        Додати до колекції по три об'єкти класів JuniorEmployee та SeniorEmployee, значення полів яких прочитати з клавіатури.
//        Посортувати колекцію використавши метод Collections.sort().
//        Вмістиме відсортованої колекції вивести на екран.

public class Main {
    private static List<Employee> employeeList;
    private static String name;
    private static BufferedReader br;

    public static void main(String[] args) {
        employeeList = new ArrayList<>();

//        JuniorEmployee juniorEmployee0 = new JuniorEmployee("Adam", 24, 1, 160);
//        JuniorEmployee juniorEmployee1 = new JuniorEmployee("Tom", 24, 20, 159);
//        JuniorEmployee juniorEmployee2 = new JuniorEmployee("Kitty", 24, 1, 158);
//        SeniorEmployee seniorEmployee0 = new SeniorEmployee("Sem", 29, 6, 157);
//        SeniorEmployee seniorEmployee1 = new SeniorEmployee("Arny", 29, 4, 156);
//        SeniorEmployee seniorEmployee2 = new SeniorEmployee("Roger", 29, 23, 155);
//        employeeList.add(juniorEmployee0);
//        employeeList.add(juniorEmployee1);
//        employeeList.add(juniorEmployee2);
//        employeeList.add(seniorEmployee0);
//        employeeList.add(seniorEmployee1);
//        employeeList.add(seniorEmployee2);

        System.out.println("How many employee do you want to add?");
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                System.out.println("If you want to add junior employee enter '1' else '0'.");
                int employeeIndex = Integer.parseInt(br.readLine());
                switch (employeeIndex) {
                    case 1:
                        addJuniorEmployee();
                        break;
                    case 0:
                        addSeniorEmployee();
                        break;
                    default:
                        System.out.println("Invalid input.");
                        count++;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Collections.sort(employeeList);

        for (Employee e: employeeList){
            System.out.println(e);
        }
    }

/*
 * Add junior employee to list.
 */
    private static void addJuniorEmployee() throws IOException{
                System.out.println("Enter the name please: ");
                name = br.readLine();
                System.out.println("Enter age please: ");
                int age = Integer.parseInt(br.readLine());
                System.out.println("Enter seniority please: ");
                int seniority = Integer.parseInt(br.readLine());
                System.out.println("Enter hours please: ");
                int hours = Integer.parseInt(br.readLine());
                employeeList.add(new JuniorEmployee(name, age, seniority, hours));
    }

    /*
     * Add senior employee to list.
     */
    private static void addSeniorEmployee() throws IOException{
            System.out.println("Enter the name please: ");
            name = br.readLine();
            System.out.println("Enter age please: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter seniority please: ");
            int seniority = Integer.parseInt(br.readLine());
            System.out.println("Enter hours please: ");
            int hours = Integer.parseInt(br.readLine());
            employeeList.add(new SeniorEmployee(name, age, seniority, hours));
    }
}

