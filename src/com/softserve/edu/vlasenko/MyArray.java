package com.softserve.edu.vlasenko;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class MyArray {
    public static void main (String args [])
    {

        ArrayList array1 = new ArrayList();

        array1.add(new JuniorEmployee("Susana", 24, 1, 2000 ));
        array1.add(new JuniorEmployee("Susana", 24, 1, 2000));
        array1.add(new JuniorEmployee("Irina", 24, 5, 3000));
        array1.add(new SeniorEmployee("Kate", 32, 1, 1000));
        array1.add(new SeniorEmployee("Tosya", 52, 10, 20000));
        array1.add(new SeniorEmployee("Leva", 51, 17, 10000));
        System.out.println (array1);
        Collections.sort(array1);
        System.out.println("After sorting:" + array1);


    }
}
