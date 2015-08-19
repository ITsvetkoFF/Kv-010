package com.saucelabs;

import org.openqa.selenium.WebDriver;

/**
 * Created by Olya on 10/28/14.
 */
public interface IResourcesPage {
    void createResource(String sTitle, String sAlias, String sBody, String sPlaceToSave) throws Exception;
    void editResourceHeader(String sTitle, String sTextToAdd) throws Exception;
    void editResourceList(String sTitle, String sTextToAdd) throws Exception;
    void editResource(String sTitle, String sTextToAdd) throws  Exception;
    void deleteResourceFromHeader(String deleteTitle) throws Exception;
    void deleteResourceFromList(String deleteTitle) throws Exception;
    void deleteResource(String deleteTitle) throws  Exception;
}
