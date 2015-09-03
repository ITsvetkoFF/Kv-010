package com.saucelabs.pages;

/**
 * Created by stako on 02.09.2015.
 */
public interface IStatisticPage {
    String baseStatisticUrl = "http://localhost:8090/#/statistic";

    void likeFirstPopProblemAndBackToStatisticPage();
    void upSeverityInFirstSeverityProblemAndBackToStatisticPage();
    int getLikesNumberFirstPopProblem();
    int getSeverityNumberFirstProblem();
    int[] getFirstAndSecondCommentNumber();
}