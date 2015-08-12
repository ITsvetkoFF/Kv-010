package com.softserve.edu.vlasenko;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class MyClass {
    public static void main (String args [])
    {
        JuniorEmployee employee1 = new JuniorEmployee("Mariya", 21, 3, 1000);
        SeniorEmployee employee2 = new SeniorEmployee("Maxim", 26, 3, 3000);
        System.out.println(" Name " + employee1.getName() + " age " + employee1.getAge() +
                " seniority " + employee1.getSeniority()+ " hours " + employee1.getHours() + " payment " + employee1.getPayment());
        System.out.println(" Name " + employee2.getName() + " age " + employee2.getAge() +
                " seniority " + employee2.getSeniority()+ " hours " + employee2.getHours()+ " payment " + employee2.getPayment());

    }

}
