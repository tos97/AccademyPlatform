package com.tosiani.web;

import com.tosiani.Drivers.ManagmentDriver;
import com.tosiani.Steps;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;

import static com.tosiani.Utility.Utils.valoreProp;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Ebay_001 {

    static private WebDriver driver = null;
    private static String nomeProp = "ebay";

    @BeforeAll
    static void beforeAll(){
        ManagmentDriver.startDriver();
        driver = ManagmentDriver.getDriver();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @DisplayName("Test ricerca nella pagina Ebay")
    @Order(1)
    void Test_001(){
        driver.get(valoreProp("ebay.url", nomeProp));
        Steps.search(driver, "Iphone", nomeProp);
    }

    @Test
    @DisplayName("Test banner Ebay")
    @Order(2)
    void Test_002(){
        driver.get(valoreProp("ebay.url", nomeProp));
        Steps.closeBannerEbay(driver, nomeProp);
    }

    @ParameterizedTest(name = "query: {0}")
    @DisplayName("Test ricercati")
    @CsvSource({"Iphone","Ipad"})
    @Order(3)
    void Test_003(String q){
        driver.get(valoreProp("ebay.url", nomeProp));
        Steps.closeBannerEbay(driver, nomeProp);
        Steps.search(driver, q, nomeProp);
        String risultato = driver.findElement(By.xpath(valoreProp("xpath.span.result", nomeProp))).getText();
        System.out.println("Risultato: "+risultato);
        if(risultato.length() < 1)
            fail("Risultato non presente");
    }


    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}

