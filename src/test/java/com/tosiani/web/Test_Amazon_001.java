package com.tosiani.web;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.selenium.DefaultChromeOptions;
import com.tosiani.step.StepAmazon;
import com.tosiani.step.Steps;
import com.tosiani.utility.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import static com.tosiani.utility.GlobalParameters.*;
import static com.tosiani.utility.Utils.valoreProp;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Amazon_001 {

    static private Steps steps = null;
    static private StepAmazon step = null;
    static private WebDriver driver = null;
    static private WebElement webElement = null;
    static private String nomeProp = "amazon";
    static private ExtentReports extentReports;
    static private ExtentTest extentTest;

    static private boolean mobile = false;

    @BeforeAll
    static void beforeAll(){
        DefaultChromeOptions defaultChromeOptions = new DefaultChromeOptions(new ChromeOptions());
        if(mobile){
            defaultChromeOptions.addArguments("--window-size=375,812");
            defaultChromeOptions.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148");
            nomeProp = "mobile";
        }

        extentReports = new ExtentReports(REPORT_PATH + File.separator + "report" + EXT_HTML, true);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));
        steps = new Steps();
        step = new StepAmazon();
        ManagmentDriver.startDriver(defaultChromeOptions);
        driver = ManagmentDriver.getDriver();
    }

    @BeforeEach
    void beforeEach(){}

    @Test
    @Order(1)
    @DisplayName("Test Amazon")
    @Tag("Desktop")
    void Test_001_Amazon(TestInfo testInfo) throws InterruptedException{
        String qry = "Iphone";//"Cavo hdmi"
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        extentTest.log(LogStatus.INFO, "Test su Amazon","Test che si basa sul caricare un elemento ricercato attraverso vari filtri e senza assicurazione nel carrello per poi rimuoverlo");
        driver.get(valoreProp("A.url", nomeProp));
        extentTest.log(LogStatus.INFO, "Base64 img pgn iniziale Amazon: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon")));

        if (step.closeBanner(driver))
            extentTest.log(LogStatus.INFO, "Base64 img pgn iniziale Amazon banner chiuso: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon no Banner")));

        step.searchById(driver, qry);
        extentTest.log(LogStatus.INFO, "Base64 img ricerca base: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon ricerca")));

        if (qry == "Iphone"){
            step.sideBarMarca(driver, "Apple");
            step.sideBarPrezzo(driver,"500","1500");
        }
        else{
            step.sideBarPrezzo(driver,"5","150");
        }
        if(step.ordinaPer(driver,3))
            extentTest.log(LogStatus.INFO, "Base64 img ricerca con filtri: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon ricerca filtri")));
        else{
            extentTest.log(LogStatus.FAIL, "ERRORE: "+step.getERRORE()+"\nBase64 img ricerca con filtri fallita: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon ricerca filtri")));
            fail();
        }

        Thread.sleep(1500);
        if (step.selezioneArticolo(driver,3))
            extentTest.log(LogStatus.INFO, "Base64 img articolo selezionato: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon articolo")));
        else{
            extentTest.log(LogStatus.FAIL, "ERRORE: "+step.getERRORE()+"\nBase64 img fallimento nel selezionare l'oggetto: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon articolo")));
            fail();
        }

        if (step.selezioneTaglia(driver,2)) {
            step.quantity(driver);
            extentTest.log(LogStatus.INFO, "Base64 img con taglia e quantità aggiornate: ", extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon articolo mod")));
        } else{
            extentTest.log(LogStatus.FAIL, "ERRORE: "+step.getERRORE()+"\nBase64 img fallimento selezione taglia: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon articolo mod")));
        }

        step.carrello(driver);
        extentTest.log(LogStatus.INFO, "Base64 img carrello pieno: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon carrello pieno")));

        int risposta = step.removeAll(driver);
        if (risposta == 0)
            extentTest.log(LogStatus.PASS, "Base64 img carrello vuoto: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon carrello")));
        else{
            if (risposta == -1)
                extentTest.log(LogStatus.FAIL, "ERRORE: "+step.getERRORE()+"\nBase64 img ERRORE carrello non vuoto: ",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon carrello")));
            else{
                extentTest.log(LogStatus.FAIL, "Base64 img ERRORE carrello non vuoto\ncisono ancora: "+risposta+" prodotti",extentTest.addBase64ScreenShot(Utils.getScreenBase64("Amazon carrello")));
            }
            fail();
        }
    }


    @AfterEach
    void tearDown(){
        extentReports.endTest(extentTest);
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
        extentReports.flush();
    }
}
