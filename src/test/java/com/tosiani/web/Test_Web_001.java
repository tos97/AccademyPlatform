package com.tosiani.web;

import com.tosiani.Drivers.ManagmentDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WindowType;

import static com.tosiani.Utility.Utils.valoreProp;

public class Test_Web_001 {



    @BeforeAll
    static void beforeAll(){
        ManagmentDriver.startDriver();
    }

    @BeforeEach
    void beforeEach(){}

    @Test
    @DisplayName("Test tasti navigazione Browser")
    void Test_001_Google(){
        ManagmentDriver.getDriver().get(valoreProp("G.url", "web"));
        ManagmentDriver.getDriver().get(valoreProp("ebay.url", "web"));

        System.out.println("Titolo:"+ManagmentDriver.getDriver().getTitle()); //stampa il titolo
        System.out.println("URL:"+ManagmentDriver.getDriver().getCurrentUrl()); //stampa l'url
        System.out.println();

        ManagmentDriver.getDriver().navigate().back();

        System.out.println("Titolo:"+ManagmentDriver.getDriver().getTitle());
        System.out.println("URL:"+ManagmentDriver.getDriver().getCurrentUrl());

        ManagmentDriver.getDriver().navigate().forward();
        ManagmentDriver.getDriver().navigate().refresh();

        ManagmentDriver.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    @Test
    @DisplayName("Test impostazioni finestra Browser")
    void Test_002_Google(){
        ManagmentDriver.getDriver().get(valoreProp("ebay.url", "web"));

        String handler = ManagmentDriver.getDriver().getWindowHandle();

        System.out.println("Handle Windows: "+handler); //info finestra
        System.out.println("Width: "+ManagmentDriver.getDriver().manage().window().getSize().getWidth()); //larghezza
        System.out.println("Height: "+ManagmentDriver.getDriver().manage().window().getSize().getHeight()); //altezza
        System.out.println("Pos X: "+ManagmentDriver.getDriver().manage().window().getPosition().getX()); //posizione X
        System.out.println("Pos Y: "+ManagmentDriver.getDriver().manage().window().getPosition().getY()); //posizione y

        //gestione posizione e dimensione
        ManagmentDriver.getDriver().manage().window().setSize(new Dimension(1024, 768));
        ManagmentDriver.getDriver().manage().window().setPosition(new Point(500, 0));

        ManagmentDriver.getDriver().manage().window().minimize();
        ManagmentDriver.getDriver().manage().window().maximize();
        ManagmentDriver.getDriver().manage().window().fullscreen();

        ManagmentDriver.getDriver().switchTo().newWindow(WindowType.TAB); //apre un nuovo tab
        ManagmentDriver.getDriver().get(valoreProp("G.url", "web"));
        ManagmentDriver.getDriver().close(); //chiude una scheda non la finestra
        ManagmentDriver.getDriver().switchTo().window(handler); //per cambiare scheda ci vuole il codice esatto (cambia ad ogni chiamata)

        ManagmentDriver.getDriver().switchTo().newWindow(WindowType.WINDOW);
        ManagmentDriver.getDriver().get(valoreProp("G.url", "web"));
        ManagmentDriver.getDriver().close();
    }

    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}
