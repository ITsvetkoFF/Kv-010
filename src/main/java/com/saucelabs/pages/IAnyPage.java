package com.saucelabs.pages;

import java.util.List;

public interface IAnyPage {
    String getFirstResourceTitleFromMenu();
    String getFirstResourceTitleFromOpenedResource();
    void logIn(String email, String password);
    void logOut();
    void register(String first_name, String last_name, String email, String password);
    String getLoggedInUserName();
    void addProblemToVisibleCenter(double latitude, double longitude,
                                   String problemName, String problemType,
                                   String problemDescription, String problemPropose,
                                   List<String> imageUrls, List<String> imageComments);
}
