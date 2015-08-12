package com.softserve.edu.verdiev;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yioteh on 11.08.15.
 */
public class Appl2 {
    public static void main(String[] args) throws IOException{

        //char c;

        ArrayList<Employee> peoples = new ArrayList<>();
        JuniorEmployee intern1 = new JuniorEmployee("John Doe", 1, 18, 20);
        JuniorEmployee intern2 = new JuniorEmployee("Kevin Smith", 2, 19, 25);
        JuniorEmployee intern3 = new JuniorEmployee("Larry Pit", 3, 17, 30);
        SeniorEmployee lead1 = new SeniorEmployee("Jim Dean", 12, 38, 20);
        SeniorEmployee lead2 = new SeniorEmployee("Carl Mayor", 15, 25, 20);
        SeniorEmployee lead3 = new SeniorEmployee("Leave Alone", 19, 33, 20);
        peoples.add(intern1);
        peoples.add(intern2);
        peoples.add(intern3);
        peoples.add(lead1);
        peoples.add(lead2);
        peoples.add(lead3);

        /*Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter employee name");
        String fName = keyboard.nextLine();
        System.out.println("You've entered " + fName);


        int fId = keyboard.nextInt();

        for (int i=1; i<4; i++) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter name, age, seniority, hours. Value separator - comma. Finish - !");

            do {
                c = (char) br.read();

            }
        }*/
        //Collections.sort(peoples);


    }
}
