package com.tosiani.web;

import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.Steps;
import com.tosiani.models.RicercaEbay;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.List;

import static com.tosiani.utility.Utils.valoreProp;
import static org.junit.jupiter.api.Assertions.*;

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
        step.searchById(driver, "Iphone", nomeProp);
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
        step.searchById(driver, q, nomeProp);
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
        step.searchById(driver,"iphone",nomeProp);
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

    @Test
    @DisplayName("Colore 1")
    @Order(7)
    void Test_007(){
        driver.get(valoreProp("ebay.url", nomeProp));
        Color ButtonBackgroundColour = Color.fromString(driver.findElement(By.id(valoreProp("id.btn.search", nomeProp))).getCssValue("background-color"));
        assert ButtonBackgroundColour.asHex().equals("#3665f3");
    }

    @ParameterizedTest(name = "Oggetto: {0}")
    @DisplayName("Selezione")
    @CsvSource({"Auto,//*[@id=\"gh-cat\"]/option[4]","Hobby,//*[@id=\"gh-cat\"]/option[17]","TV,//*[@id=\"gh-cat\"]/option[28]"})
    @Order(8)
    void Test_008(String ricerca, String xpathRic){
        driver.get(valoreProp("ebay.url", nomeProp));
        driver.findElement(By.id(valoreProp("id.box", nomeProp))).click();
        for (WebElement element: driver.findElement(By.id(valoreProp("id.box", nomeProp))).findElements(By.tagName("option"))){
            if(element.getText().contains(ricerca))
                element.click();
        }
        assertTrue(driver.findElement(By.xpath(xpathRic)).isSelected());
    }


    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}

