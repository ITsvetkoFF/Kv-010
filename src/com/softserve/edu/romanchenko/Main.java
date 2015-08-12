package com.softserve.edu.romanchenko;

import com.softserve.edu.romanchenko.Team;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Team team = new Team();
        team.createTeam();
        team.sortTeam();
        team.createArrayOfEmployees();
    }
}
