package com.tosiani.web;

import com.tosiani.Drivers.ManagmentDriver;
import com.tosiani.Steps;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.tosiani.Utility.Utils.valoreProp;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_ilMeteo_001 {

    WebElement webElement = null;
    static private WebDriver driver = null;
    private static String nomeProp = "ilMeteo";

    @BeforeAll
    static void beforeAll(){
        ManagmentDriver.startDriver();
        driver = ManagmentDriver.getDriver();
    }

    @BeforeEach
    void beforeEach(){}


    @ParameterizedTest(name = "query: {0}")
    @DisplayName("Test ilMeteo ricerca")
    @CsvSource({"Pescara","Ferrara"})
    @Order(1)
    void Test_001_Ricerca(String q){
        driver.get(valoreProp("ilMeteo.url", nomeProp));
        Steps.closeBannerEbay(driver, nomeProp);
        Steps.search(driver, q, nomeProp);
        String risultato = driver.findElement(By.id(valoreProp("id.citta.result", nomeProp))).getText();
        if(!risultato.contains(q.toUpperCase()))
            fail("Città non trovata\n");
        else{
            System.out.println("Città "+q.toUpperCase()+"trovata\n");
        }
    }

    @Test
    @DisplayName("Test ilMeteo menù bar Ciclo for")
    @Order(2)
    void Test_002_Controllo(){
        String nome;
        driver.get(valoreProp("ilMeteo.url", nomeProp));
        try {
            for (int i = 1; i <= 14; i++) {
                webElement = driver.findElement(By.id(("tab" + i)));
                nome = webElement.getText();
                webElement.click();
                Thread.sleep(500);
                if (nome.equals("Home"))
                    continue;
                else{
                    if (!driver.findElement(By.xpath(valoreProp("xpath.title.result", nomeProp))).getText().toLowerCase().contains(nome.toLowerCase()))
                        System.out.println(nome+" Non Trovato");
                    else{
                        System.out.println(nome+" Trovato, Bravo Programmatore");
                    }
                    assertTrue(driver.findElement(By.xpath(valoreProp("xpath.title.result", nomeProp))).getText().toLowerCase().contains(nome.toLowerCase()));
                }
            }
        }catch (InterruptedException e) {
            System.out.println("Banner non Trovato");
        }
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Test ilMeteo menù bar CSV")
    @CsvSource({"tab1","tab2","tab3","tab4","tab5","tab6","tab7","tab8","tab9","tab10","tab11","tab12","tab13","tab14"})
    @Order(3)
    void Test_003_Controllo(String tab){
        String nome;
        driver.get(valoreProp("ilMeteo.url", nomeProp));
        try {
            webElement = driver.findElement(By.id((tab)));
            nome = webElement.getText();
            webElement.click();
            Thread.sleep(500);
            if (!nome.equals("Home"))
                assertTrue(driver.findElement(By.xpath(valoreProp("xpath.title.result", nomeProp))).getText().toLowerCase().contains(nome.toLowerCase()));
        }catch (InterruptedException e) {
            System.out.println("Banner non Trovato");
        }
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Test ilMeteo menù bar Selected")
    @CsvSource({"tab1","tab2","tab3","tab4","tab5","tab6","tab7","tab8","tab9","tab10","tab11","tab12","tab13","tab14"})
    @Order(3)
    void Test_004_Selected(String tab){
        driver.get(valoreProp("ilMeteo.url", nomeProp));
        try {
            webElement = driver.findElement(By.id((tab)));
            webElement.click();
            Thread.sleep(500);
            assertTrue(driver.findElement(By.xpath(valoreProp("xpath.title.result", nomeProp))).isEnabled());
        }catch (InterruptedException e) {
            System.out.println("Banner non Trovato");
        }
    }


    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}
