package com.softserve.edu;

import com.softserve.edu.GetPaymentTest;
import com.softserve.edu.Team;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Team team = new Team();
        team.createTeam();
        team.sortTeam();
        team.createArrayOfEmployees();
        GetPaymentTest getPaymentTest = new GetPaymentTest();
        getPaymentTest.Test();
    }
}

