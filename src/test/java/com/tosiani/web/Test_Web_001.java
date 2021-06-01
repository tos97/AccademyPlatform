package com.tosiani.web;

import com.tosiani.ManagmentDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.tosiani.Driver.GlobalParameters.*;
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
    }

    @Test
    @DisplayName("Info Windows")
    void Test_002_Google(){
        ManagmentDriver.getDriver().get(valoreProp("ebay.url", "web"));

        System.out.println("Handle Windows: "+ManagmentDriver.getDriver().getWindowHandle()); //info finestra
        System.out.println("Width: "+ManagmentDriver.getDriver().manage().window().getSize().getWidth()); //larghezza
        System.out.println("Height: "+ManagmentDriver.getDriver().manage().window().getSize().getHeight()); //altezza
        System.out.println("Pos X: "+ManagmentDriver.getDriver().manage().window().getPosition().getX()); //posizione X
        System.out.println("Pos Y: "+ManagmentDriver.getDriver().manage().window().getPosition().getY()); //posizione y
    }

    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}
