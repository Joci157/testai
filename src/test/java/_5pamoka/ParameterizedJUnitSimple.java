package _5pamoka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedJUnitSimple {


    public String name;
    public int age;

    public ParameterizedJUnitSimple(String name, int age){
        this.name = name;
        this.age = age;

    }

    @Test
    public void testmethod() {
        System.out.println(name + " " + age);
    }
        @Parameterized.Parameters
                public static Collection<Object[]> parameters(){
            Object[][] data = new Object[3][2];
            data[0][0] = "Laurynas";
            data[0][1] = 30;
            data[1][0] = "Edgaras";
            data[1][1] = 20;
            data[2][0] = "Ieva";
            data[2][1] = 25;

return Arrays.asList(data);
    }
}
