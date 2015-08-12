package test.statsenko;

import com.softserve.edu.statsenko.MyException;

/**
 * Created by stako on 11.08.2015.
 */
public class StubEmployee {

    public StubEmployee(){
        String name = "Adam";
        int age = 22;
        int seniority = 2;
        int hours = 160;
        if (seniority > age){
            throw new MyException("Exception.");
        }
    }
}
