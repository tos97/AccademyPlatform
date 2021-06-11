package com.tosiani.web;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tosiani.step.Steps;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.selenium.DefaultChromeOptions;
import com.tosiani.step.StepsMobile;
import com.tosiani.utility.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static com.tosiani.utility.GlobalParameters.*;
import static com.tosiani.utility.Utils.valoreProp;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Web_001 {

    static private Steps steps = null;
    static private StepsMobile stepsMobile = null;
    static private WebDriver driver = null;
    static private WebElement webElement = null;
    static private String nomeProp = "";
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
        else{
            nomeProp = "web";
        }

        extentReports = new ExtentReports(REPORT_PATH + File.separator + "report" + EXT_HTML, false);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));
        steps = new Steps();
        stepsMobile = new StepsMobile();
        ManagmentDriver.startDriver(defaultChromeOptions);
        driver = ManagmentDriver.getDriver();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @DisplayName("Test tasti navigazione Browser Desktop")
    @Order(1)
    @Tag("Desktop")
    void Test_001_Google(TestInfo testInfo) throws InterruptedException{
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        driver.get(valoreProp("G.url", nomeProp));
        driver.get(valoreProp("ebay.url", nomeProp));

        /*System.out.println("Titolo:"+driver.getTitle()); //stampa il titolo
        System.out.println("URL:"+driver.getCurrentUrl()); //stampa l'url
        System.out.println();
        String sc = Utils.getScreen();
        driver.navigate().back();

        System.out.println("Titolo:"+driver.getTitle());
        System.out.println("URL:"+driver.getCurrentUrl());
        Utils.getScreenshot();
        driver.navigate().forward();
        driver.navigate().refresh();

        //driver.switchTo().newWindow(WindowType.TAB);

        Thread.sleep(4000);*/
        extentTest.log(LogStatus.PASS, "Base64 img: "+extentTest.addBase64ScreenShot(Utils.getScreenBase64()));
    }

    @Test
    @DisplayName("Test impostazioni finestra Browser Desktop")
    @Order(2)
    @Tag("Desktop")
    void Test_002_Google(){
        driver.get(valoreProp("ebay.url", nomeProp));

        String handler = driver.getWindowHandle();

        System.out.println("Handle Windows: "+handler); //info finestra
        System.out.println("Width: "+driver.manage().window().getSize().getWidth()); //larghezza
        System.out.println("Height: "+driver.manage().window().getSize().getHeight()); //altezza
        System.out.println("Pos X: "+driver.manage().window().getPosition().getX()); //posizione X
        System.out.println("Pos Y: "+driver.manage().window().getPosition().getY()); //posizione y

        //gestione posizione e dimensione
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(500, 0));

        //driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        //driver.switchTo().newWindow(WindowType.TAB); //apre un nuovo tab
        driver.get(valoreProp("G.url", nomeProp));
        driver.close(); //chiude una scheda non la finestra
        driver.switchTo().window(handler); //per cambiare scheda ci vuole il codice esatto (cambia ad ogni chiamata)

        //driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(valoreProp("G.url", nomeProp));
        driver.close();
        driver.switchTo().window(handler);
    }

    /*@Test
    @DisplayName("Coordinate")
    @Order(3)
    void Test_009(){
        //driver.get(valoreProp("G.url", "web"));
        //ArrayList<Coordinate> coordinates = new ArrayList<>();
        //System.out.println();
        //driver.get(valoreProp("G.url", "web"));
        Map coordinates = new HashMap()
        {{
            put("latitude", 50.2334);
            put("longitude", 0.2334);
            put("accuracy", 1);
        }};
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driverC.get("https://www.google.it/maps");
    }*/

    @ParameterizedTest
    @DisplayName("accetta cookie google Desktop")
    @CsvSource({"Iphone","accendini"})
    @Order(3)
    @Tag("Desktop")
    void Test_003(String q){
        driver.get(valoreProp("G.url", nomeProp));
        steps.closeBannerGoogle(driver);
        steps.searchByXpath(driver, q, nomeProp);
        Utils.getScreenshot();
    }

    @ParameterizedTest
    @DisplayName("accetta cookie google Mobile")
    @CsvSource({"Iphone","accendini"})
    @Order(4)
    @Tag("Mobile")
    void Test_004(String q){
        driver.get(valoreProp("G.url", nomeProp));
        stepsMobile.closeBannerGoogle(driver);
        stepsMobile.searchByXpath(driver, q, "mobile");
    }

    @Test
    @DisplayName("Apre yt e lancia una canzone mobile")
    @Order(4)
    @Tag("Mobile")
    void Test_005(){
        driver.get(valoreProp("YT.url", nomeProp));
        driver.findElement(By.xpath(valoreProp("yt.primo.risultato", nomeProp))).click();
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
