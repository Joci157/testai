package _5pamoka;

import org.junit.jupiter.api.*;
import org.testng.annotations.BeforeClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationsJUnitSimple {

    //@BeforeAll
    //@BeforeEach
    //@Test
    //@AfterAll
    //@AfterEach
    //@Ignore

    //@ParameterizedTest
    //@RunWith
    //@Parameters

    @BeforeAll
    public static void setupEnvironment(){
        System.out.println("Susetupinam envirnment'a");
    }
    @AfterAll
    public static void tearDown(){
        System.out.println("Testai baigesi, isvalom aplinka");
    }

    @BeforeEach
    public void beforeTest(){
        System.out.println("Padariau veiksma pries teksta");
    }

    @AfterEach
    public void afterTest(){
        System.out.println("Padariau veiksma po testo");
    }


    @Test
    public void test1(){
        int c= 15 + 2;
        assertEquals(17, c, "KAzkas nemoka matkes");
    }


    @Test
    public void multiplication(){
        int c = 12*4;
        int expectedResult = 48;
        assertEquals(expectedResult, c, "2testas");
    }



}
