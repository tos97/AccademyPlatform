package com.tosiani.web;

import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.Steps;
import com.tosiani.models.RicercaEbay;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tosiani.utility.Utils.valoreProp;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Ebay_001 {

    static private WebDriver driver = null;
    private static String nomeProp = "ebay";
    private static Steps step = null;

    @BeforeAll
    static void beforeAll(){
        ManagmentDriver.startDriver();
        driver = ManagmentDriver.getDriver();
        step = new Steps();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @DisplayName("Test ricerca nella pagina Ebay")
    @Order(1)
    void Test_001(){
        driver.get(valoreProp("ebay.url", nomeProp));
        step.search(driver, "Iphone", nomeProp);
    }

    @Test
    @DisplayName("Test banner Ebay")
    @Order(2)
    void Test_002(){
        driver.get(valoreProp("ebay.url", nomeProp));
        step.closeBannerEbay(driver, nomeProp);
    }

    @ParameterizedTest(name = "query: {0}")
    @DisplayName("Test ricercati")
    @CsvSource({"Iphone","Ipad"})
    @Order(3)
    void Test_003(String q){
        driver.get(valoreProp("ebay.url", nomeProp));
        step.closeBannerEbay(driver, nomeProp);
        step.search(driver, q, nomeProp);
        String risultato = driver.findElement(By.xpath(valoreProp("xpath.span.result", nomeProp))).getText();
        System.out.println("Risultato: "+risultato);
        if(risultato.length() < 1)
            fail("Risultato non presente");
    }

    @Test
    @DisplayName("Lista Web")
    @Order(4)
    void Test_004(){
        driver.get(valoreProp("ebay.url", nomeProp));
        List<WebElement> listElements = driver.findElements(By.id(valoreProp("id.box",nomeProp)));
        System.out.println("Categorie");
        for (WebElement element: listElements){
            System.out.println(element.getText());
        }
    }

    @Test
    @DisplayName("Lista Web con modello array String")
    @Order(5)
    void Test_005(){
        driver.get(valoreProp("ebay.url", nomeProp));

        String[] arrayElements = step.listaElementi(driver, nomeProp);

        System.out.println("Categorie");
        //Arrays.toString(arrayElements);
        //System.out.println(arrayElements.toString());
        for (int i = 0;i < arrayElements.length;i++){
            System.out.println(arrayElements[i]);
        }
    }

    @Test
    @DisplayName("Lista Web con modello array String")
    @Order(6)
    void Test_006(){
        ArrayList<RicercaEbay> arrayRicerca = new ArrayList<>();

        driver.get(valoreProp("ebay.url", nomeProp));
        step.search(driver,"iphone",nomeProp);

        for (WebElement element: driver.findElement(By.id(valoreProp("id.div.result", nomeProp))).findElements(By.className(valoreProp("class.li", nomeProp)))){
            arrayRicerca.add(new RicercaEbay(step.getTitle(element,nomeProp),step.getSubtilte(element,nomeProp),step.getPrezzo(element,nomeProp),step.getImg(element,nomeProp)));
        }

        /*for (WebElement element: driver.findElement(By.id(valoreProp("id.div.result", nomeProp))).findElements(By.className(valoreProp("class.li", nomeProp)))){
            arrayRicerca.add(new RicercaEbay(element.findElement(By.className(valoreProp("class.title", nomeProp))).getText(),element.findElement(By.className(valoreProp("class.prize", nomeProp))).getText(),element.findElement(By.className(valoreProp("class.img", nomeProp))).getAttribute("src")));
        }*/

        /*for (RicercaEbay element: arrayRicerca){
            System.out.println();
            System.out.println(element.getNome());
            System.out.println(element.getPrezzo());
            System.out.println(element.getLink());
        }*/

        for (RicercaEbay element: arrayRicerca){
            System.out.println();
            element.stampa();
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

