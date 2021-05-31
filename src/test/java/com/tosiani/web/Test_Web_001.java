package com.tosiani.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.tosiani.Utility.GlobalParameters.*;
import static com.tosiani.Utility.Utils.valoreProp;
import static org.junit.Assert.*;

public class Test_Web_001 {

    private static ChromeDriver cDriver;

    @BeforeAll
    static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        cDriver = new ChromeDriver();
    }

    @BeforeEach
    void beforeEach(){}

    @Test
    @DisplayName("Test Google")
    void Test_001_Google(){
        cDriver.get(valoreProp("G.url", "web"));
    }

    @AfterEach
    void tearDown(){
        cDriver.quit();
    }

    @AfterAll
    static void tearDownAll(){}

}
