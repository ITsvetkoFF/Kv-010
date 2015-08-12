package com.softserve.edu;

import com.softserve.edu.Employee;
import com.softserve.edu.JuniorEmployee;
import com.softserve.edu.SeniorEmployee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Team {
    public Team() {
    }

    void createTeam() {
        JuniorEmployee jE = new JuniorEmployee("John Dou", 19, 1, 46);
        SeniorEmployee sE = new SeniorEmployee("Tom Smith", 30, 10, 80);
        System.out.println("Name: " + jE.getName() + ". Age: " + jE.getAge() + ". Seniority: " + jE.getSeniority() + ". Hours: " + jE.getHours() + ". Payment: " + jE.getPayment());
        System.out.println("Name: " + sE.getName() + ". Age: " + sE.getAge() + ". Seniority: " + sE.getSeniority() + ". Hours: " + sE.getHours() + ". Payment: " + sE.getPayment());
    }

    void sortTeam() {
        ArrayList emp_list = new ArrayList();
        emp_list.add(new SeniorEmployee("July McCollin", 27, 10, 80));
        emp_list.add(new SeniorEmployee("Amanda Rose", 30, 10, 95));
        emp_list.add(new SeniorEmployee("Sara Guberman", 39, 18, 30));
        emp_list.add(new JuniorEmployee("Sam Gosling", 19, 1, 46));
        emp_list.add(new JuniorEmployee("Tony Stone", 21, 1, 60));
        emp_list.add(new JuniorEmployee("Jim Carry", 23, 2, 84));
        boolean swapped = true;

        Employee a;
        while(swapped) {
            swapped = false;

            for(int i$ = 0; i$ < emp_list.size() - 1; ++i$) {
                if(((Employee)emp_list.get(i$)).compareTo((Employee)emp_list.get(i$ + 1)) > 0) {
                    a = (Employee)emp_list.get(i$);
                    emp_list.set(i$, emp_list.get(i$ + 1));
                    emp_list.set(i$ + 1, a);
                    swapped = true;
                }
            }
        }

        Iterator var5 = emp_list.iterator();

        while(var5.hasNext()) {
            a = (Employee)var5.next();
            System.out.print(a.getName() + ", ");
        }

    }

    void createArrayOfEmployees() {
        ArrayList list = new ArrayList();
        list.add(new SeniorEmployee("July McCollin", 27, 10, 80));
        list.add(new SeniorEmployee("Amanda Rose", 30, 10, 95));
        list.add(new SeniorEmployee("Sara Guberman", 39, 18, 30));
        list.add(new JuniorEmployee("Sam Gosling", 19, 1, 46));
        list.add(new JuniorEmployee("Tony Stone", 21, 1, 60));
        list.add(new JuniorEmployee("Jim Carry", 23, 2, 84));
        Collections.sort(list);
        System.out.println();
        Iterator i$ = list.iterator();

        while(i$.hasNext()) {
            Employee a = (Employee)i$.next();
            System.out.print(a.getName() + ", ");
        }

    }
}

