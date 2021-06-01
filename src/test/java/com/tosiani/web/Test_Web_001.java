package com.tosiani.web;

import com.tosiani.Drivers.ManagmentDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static com.tosiani.Utility.Utils.valoreProp;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Web_001 {

    static private WebDriver driver = null;

    @BeforeAll
    static void beforeAll(){
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

        driver.switchTo().newWindow(WindowType.TAB);
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

        driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        driver.switchTo().newWindow(WindowType.TAB); //apre un nuovo tab
        driver.get(valoreProp("G.url", "web"));
        driver.close(); //chiude una scheda non la finestra
        driver.switchTo().window(handler); //per cambiare scheda ci vuole il codice esatto (cambia ad ogni chiamata)

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(valoreProp("G.url", "web"));
        driver.close();
        driver.switchTo().window(handler);
    }


    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}
