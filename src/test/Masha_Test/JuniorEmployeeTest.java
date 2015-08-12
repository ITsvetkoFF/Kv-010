package test.Masha_Test;

import com.softserve.edu.Worker;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class JuniorEmployeeTest {
    Worker test;
    @BeforeTest
    public void init ()
    {
        test = new StubJuniorEmployee("Nael", 23,1,1222);
    }

