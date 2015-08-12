package test.statsenko;

/**
 * Created by stako on 11.08.2015.
 */
public class StubSeniorEmployeeNegative implements IJuniorEmployee {

    public StubSeniorEmployeeNegative(){
    }

    @Override
    public double getPayment() {
        return 0;
    }

    @Override
    public String toString(){
        return "";
    }

    public int getSeniority(){
        return 22;
    }

    public int getHours(){
        return 160;
    }
}