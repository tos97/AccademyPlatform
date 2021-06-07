package com.tosiani.web;

import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.step.Steps;
import com.tosiani.models.RicercaEbay;
import com.tosiani.selenium.DefaultChromeOptions;
import com.tosiani.step.StepsMobile;
import com.tosiani.utility.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.List;

import static com.tosiani.utility.Utils.valoreProp;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Ebay_001 {

    static private WebDriver driver = null;
    private static String nomeProp = "";
    private static Steps step = null;
    private static StepsMobile stepsMobile = null;

    static private boolean mobile = true;

    @BeforeAll
    static void beforeAll(){
        DefaultChromeOptions defaultChromeOptions = new DefaultChromeOptions(new ChromeOptions());
        if(mobile){
            defaultChromeOptions.addArguments("--window-size=375,812");
            defaultChromeOptions.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148");
            nomeProp = "mobile";
        }
        else{
            nomeProp = "ebay";
        }
        ManagmentDriver.startDriver(defaultChromeOptions);
        driver = ManagmentDriver.getDriver();
        step = new Steps();
        stepsMobile = new StepsMobile();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @DisplayName("Test ricerca nella pagina Ebay")
    @Order(1)
    @Tag("Desktop")
    void Test_001(){
        driver.get(valoreProp("ebay.url", nomeProp));
        step.searchById(driver, "Iphone", nomeProp);
    }

    @Test
    @DisplayName("Test banner Ebay")
    @Order(2)
    @Tag("Desktop")
    void Test_002(){
        driver.get(valoreProp("ebay.url", nomeProp));
        step.closeBannerEbay(driver, nomeProp);
    }

    @ParameterizedTest(name = "query: {0}")
    @DisplayName("Test ricercati")
    @CsvSource({"Iphone","Ipad"})
    @Order(3)
    @Tag("Desktop")
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
    @Tag("Desktop")
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
    @Tag("Desktop")
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
    @Tag("Desktop")
    void Test_006(){
        ArrayList<RicercaEbay> arrayRicerca = new ArrayList<>();
        driver.get(valoreProp("ebay.url", nomeProp));
        step.searchById(driver,"iphone",nomeProp);
        for (WebElement element: driver.findElement(By.id(valoreProp("id.div.result", nomeProp))).findElements(By.className(valoreProp("class.li", nomeProp)))){
            arrayRicerca.add(new RicercaEbay(step.getTitle(element,nomeProp),step.getSubtilte(element,nomeProp),step.getPrezzo(element,nomeProp),step.getImg(element,nomeProp)));
        }
        for (RicercaEbay element: arrayRicerca){
            System.out.println();
            element.stampa();
        }
    }

    @Test
    @DisplayName("Colore 1")
    @Order(7)
    @Tag("Desktop")
    void Test_007(){
        driver.get(valoreProp("ebay.url", nomeProp));
        Color ButtonBackgroundColour = Color.fromString(driver.findElement(By.id(valoreProp("id.btn.search", nomeProp))).getCssValue("background-color"));
        assert ButtonBackgroundColour.asHex().equals("#3665f3");
    }

    @ParameterizedTest(name = "Oggetto: {0}")
    @DisplayName("Selezione")
    @CsvSource({"Auto,//*[@id=\"gh-cat\"]/option[4]","Hobby,//*[@id=\"gh-cat\"]/option[17]","TV,//*[@id=\"gh-cat\"]/option[28]"})
    @Order(8)
    @Tag("Desktop")
    void Test_008(String ricerca, String xpathRic){
        driver.get(valoreProp("ebay.url", nomeProp));
        driver.findElement(By.id(valoreProp("id.box", nomeProp))).click();
        for (WebElement element: driver.findElement(By.id(valoreProp("id.box", nomeProp))).findElements(By.tagName("option"))){
            if(element.getText().contains(ricerca))
                element.click();
        }
        assertTrue(driver.findElement(By.xpath(xpathRic)).isSelected());
    }

    @Test
    @DisplayName("Ricerca Ebay mobile")
    @Order(9)
    @Tag("Mobile")
    void Test_009(){
        driver.get(valoreProp("ebay.url", nomeProp));
        step.closeBannerEbay(driver,"ebay");
        step.searchByXpath(driver,"iphone", "mobile.ebay");
        String risultato = driver.findElement(By.xpath(valoreProp("xpath.n.risultati", "mobile.ebay"))).getText();
        System.out.println("Risultato: "+risultato);
    }

    @Test
    @DisplayName("Lista Web con modello ArrayString")
    @Order(10)
    @Tag("Mobile")
    void Test_010(){
        ArrayList<RicercaEbay> arrayRicerca = new ArrayList<>();
        driver.get(valoreProp("ebay.url", nomeProp));
        step.searchByXpath(driver,"iphone", "mobile.ebay");
        for (WebElement element: driver.findElement(By.id(valoreProp("id.div.result", "ebay"))).findElements(By.className(valoreProp("class.li", "ebay")))){
            arrayRicerca.add(new RicercaEbay(step.getTitle(element,"ebay"),step.getSubtilte(element,"mobile.ebay"),step.getPrezzo(element,"mobile.ebay"),step.getImg(element,"ebay")));
        }
        for (RicercaEbay element: arrayRicerca){
            System.out.println();
            element.stampa();
        }
    }

    @ParameterizedTest(name = "n: {0}")
    @DisplayName("next e screenshot")
    @Order(10)
    @CsvSource({"Iphone,4","ipad,3"})
    @Tag("Mobile")
    void Test_011(String qry,String pgn) throws InterruptedException{
        driver.get(valoreProp("ebay.url", nomeProp));
        step.searchByXpath(driver,qry, "mobile.ebay");
        //System.out.println("URL:"+driver.getCurrentUrl());
        Utils.getScreenshot();
        assertTrue(stepsMobile.nextPgn(driver,Integer.parseInt(pgn)));
        assertTrue(stepsMobile.previousPgn(driver,Integer.parseInt(pgn)));
        /*for (int i = 1;i < Integer.parseInt(pgn);i++){
            Thread.sleep(4000);
            driver.findElement(By.className(valoreProp("class.btn.next", "mobile.ebay"))).click();
            Utils.getScreenshot();
            if (driver.getCurrentUrl().contains("pgn")){
                assertTrue(driver.getCurrentUrl().contains(String.valueOf(i+1)));
                if (driver.getCurrentUrl().contains(String.valueOf(i+1)))
                    System.out.println("pgn giusta " + (i+1));
                else{
                    fail("ERRORE pagina");
                }
            }
        }*/

        /*for (int i = Integer.parseInt(pgn);i > 1;i--){
            Thread.sleep(4000);
            driver.findElement(By.className(valoreProp("class.btn.previous", "mobile.ebay"))).click();
            if (driver.getCurrentUrl().contains("pgn")){
                assertTrue(driver.getCurrentUrl().contains(String.valueOf(i-1)));
                if (driver.getCurrentUrl().contains(String.valueOf(i-1)))
                    System.out.println("pgn giusta " + (i-1));
                else{
                    fail("ERRORE pagina");
                }
            }
        }*/
    }


    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
    }

}

