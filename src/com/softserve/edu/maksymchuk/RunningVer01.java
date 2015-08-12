package com.softserve.edu.maksymchuk;

/**
 * @author Denys Maxymchuk
 */
public class RunningVer01 {
    public static void main(String[] args) {

        SeniorEmployee seniorEmployee = new SeniorEmployee("Vasily", 22, 4, 44);
        System.out.println(seniorEmployee);
        JuniorEmployee juniorEmployee = new JuniorEmployee("Jorge", 44, 11, 111);
        System.out.println(juniorEmployee);
    }
}
