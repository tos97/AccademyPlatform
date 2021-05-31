package com.tosiani.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.tosiani.Utility.GlobalParameters.CHROME_DRIVER_PATH;
import static org.junit.Assert.*;

public class Test_Web_001 {

    @BeforeAll
    static void beforeAll(){}

    @BeforeEach
    void beforeEach(){}

    @Test
    void Test_001(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeDriver cDriver = new ChromeDriver();
        cDriver.get("http:\\\\www.google.it");
    }

    @AfterEach
    void tearDown(){}

    @AfterAll
    static void tearDownAll(){}

}
