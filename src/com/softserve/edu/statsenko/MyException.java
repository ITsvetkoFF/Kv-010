package com.softserve.edu.statsenko;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by stako on 11.08.2015.
 */
public class MyException extends RuntimeException {

    public MyException(String str){

        System.out.println(str);
    }
}
