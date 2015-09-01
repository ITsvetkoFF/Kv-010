package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

/**
 * Created by Roma on 23.10.2014.
 */
public interface IMapPage {

    void setPosition();
    void setView(double latitude, double longitude, int zoom);
    void setVisibleView(double latitude, double longitude, int zoom);
    void clickAtPagesCenter();
    void clickAtVisibleMapCenter(int offset);
    void clickAtProblemByCoordinateVisible(double latitude, double longitude);

    // for filters
    String getFilterTitle(int typeNumber);
    void clickZoomOut();
    void openFiltersBoard();
    void selectAllExceptOneFilter(int typeNumber) throws Exception;

    void selectAllExceptOneFilter(String typeName);

    void selectOnlyOneFilter(int typeNumber);
    void selectOnlyOneFilter(String typeName);
    void setAfterDate(String afterDate);
    void setBeforeDate(String beforeDate);
    void datePickersButtons();

    //not use
    void selectDate(WebElement datePicker, String year, String month, String day);

    //not use
    void selectOneDayPeriod(String fullDate);
}
