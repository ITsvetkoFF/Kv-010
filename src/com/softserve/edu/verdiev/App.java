package com.softserve.edu.verdiev;

/**
 * Created by yioteh on 10.08.15.
 */
public class App {
    public static void main(String[] args){

        JuniorEmployee duh = new JuniorEmployee("Young Mann", 14, 1, 30);
        SeniorEmployee ded = new SeniorEmployee("Old Boy", 38, 46, 42);

        System.out.println("Our guy name is " +duh.getName() +", he is " +duh.getAge()
                           +" years old. Seniority level " +duh.getSeniority()
                           +" because he works " +duh.getHours() +" hours per week.");

        System.out.println("Our bosses name is " +ded.getName() +", he is " +ded.getAge()
                +" years old. Seniority level " +ded.getSeniority()
                +" because he works " +ded.getHours() +" hours per week.");
    }

}
