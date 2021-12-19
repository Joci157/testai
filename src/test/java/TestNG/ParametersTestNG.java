package TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParametersTestNG {

    @DataProvider(name = "mokiniai")
    public Object[][] dataProvider(){

        return new Object[][]{
                {"Tomas", 28},
                {"Lina", 20},
                {"Karolis", 25}
        };
    }
    @DataProvider(name = "mokiniai2")
    public Object[][] dataProvider2(){

        return new Object[][]{
                {"Jonas", 38},
                {"Janina", 40},
                {"Petronele", 28}
        };
    }


    @Test(dataProvider = "mokiniai2")
    public void testWithMokiniai(String name, int age ){
        System.out.println(name + " " + age);
    }

}
