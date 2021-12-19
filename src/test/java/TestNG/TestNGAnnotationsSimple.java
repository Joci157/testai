package TestNG;

import org.testng.annotations.*;

public class TestNGAnnotationsSimple {

    @BeforeTest
    public void setUp(){
        System.out.println("Pasileidzia pries visus testus esancius tame package");
    }

    @BeforeClass
    public void setUpClass(){
        System.out.println("Cia yra BeforeClass");

    }

    @BeforeMethod // JUnit -> beforeEach
    public void beforeMethod(){
        System.out.println("Eina pries testa");
    }

    @AfterTest
    public void tearDown(){
        System.out.println("Pasileidzia po visu paskego testu");

    }

    @AfterClass // Junit _>AfterALl
    public void afterClass(){
        System.out.println("Pasileidzia po visu testu");
    }

    @AfterMethod //JUnit _> afterEach
    public void afterMethod(){
        System.out.println("Eina po testo");
    }


    @Test
    public void test1(){
        System.out.println("TEST1");
    }
    @Test
    public void test2(){
        System.out.println("TEST2");
    }
    @Test
    public void test3(){
        System.out.println("TEST3");
    }
    @Test
    public void test4(){
        System.out.println("TEST4");
    }
}
