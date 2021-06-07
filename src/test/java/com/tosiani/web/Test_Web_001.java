package com.tosiani.web;

import com.tosiani.Steps;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.models.Coordinate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;

import static com.tosiani.utility.Utils.valoreProp;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Web_001 {

    static private Steps steps = null;
    static private WebDriver driver = null;
    static private WebElement webElement = null;

    @BeforeAll
    static void beforeAll(){
        steps = new Steps();
        ManagmentDriver.startDriver();
        driver = ManagmentDriver.getDriver();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @DisplayName("Test tasti navigazione Browser")
    @Order(1)
    void Test_001_Google(){
        driver.get(valoreProp("G.url", "web"));
        driver.get(valoreProp("ebay.url", "web"));

        System.out.println("Titolo:"+driver.getTitle()); //stampa il titolo
        System.out.println("URL:"+driver.getCurrentUrl()); //stampa l'url
        System.out.println();

        driver.navigate().back();

        System.out.println("Titolo:"+driver.getTitle());
        System.out.println("URL:"+driver.getCurrentUrl());

        driver.navigate().forward();
        driver.navigate().refresh();

        //driver.switchTo().newWindow(WindowType.TAB);
    }

    @Test
    @DisplayName("Test impostazioni finestra Browser")
    @Order(2)
    void Test_002_Google(){
        driver.get(valoreProp("ebay.url", "web"));

        String handler = driver.getWindowHandle();

        System.out.println("Handle Windows: "+handler); //info finestra
        System.out.println("Width: "+driver.manage().window().getSize().getWidth()); //larghezza
        System.out.println("Height: "+driver.manage().window().getSize().getHeight()); //altezza
        System.out.println("Pos X: "+driver.manage().window().getPosition().getX()); //posizione X
        System.out.println("Pos Y: "+driver.manage().window().getPosition().getY()); //posizione y

        //gestione posizione e dimensione
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(500, 0));

        //driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        //driver.switchTo().newWindow(WindowType.TAB); //apre un nuovo tab
        driver.get(valoreProp("G.url", "web"));
        driver.close(); //chiude una scheda non la finestra
        driver.switchTo().window(handler); //per cambiare scheda ci vuole il codice esatto (cambia ad ogni chiamata)

        //driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(valoreProp("G.url", "web"));
        driver.close();
        driver.switchTo().window(handler);
    }

    /*@Test
    @DisplayName("Coordinate")
    @Order(3)
    void Test_009(){
        //driver.get(valoreProp("G.url", "web"));
        //ArrayList<Coordinate> coordinates = new ArrayList<>();
        //System.out.println();
        //driver.get(valoreProp("G.url", "web"));
        Map coordinates = new HashMap()
        {{
            put("latitude", 50.2334);
            put("longitude", 0.2334);
            put("accuracy", 1);
        }};
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driverC.get("https://www.google.it/maps");
    }*/

    @ParameterizedTest
    @DisplayName("accetta cookie")
    @CsvSource({"Iphone","accendini"})
    @Order(3)
    void Test_003(String q) throws InterruptedException{
        driver.get(valoreProp("G.url", "web"));

        steps.closeBannerGoogle(driver);

        steps.searchByXpath(driver, q, "web");
    }


    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}
