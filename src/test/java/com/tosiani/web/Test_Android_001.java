package com.tosiani.web;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.utility.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.tosiani.utility.GlobalParameters.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Android_001 {

    static private WebDriver driver = null;
    static private WebElement webElement = null;
    static private ExtentReports extentReports;
    static private ExtentTest extentTest;
    static private AndroidDriver<?> androidDriver;
    static private DesiredCapabilities desiredCapabilities = null;

    @BeforeAll
    static void beforeAll(){
        extentReports = new ExtentReports(REPORT_PATH + File.separator + "androidReport" + EXT_HTML, true);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));

        desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "app" + EXT_ANDROID);

        ManagmentDriver.startAndroidDriver(desiredCapabilities);
        androidDriver = ManagmentDriver.getAndroidDriver();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @Order(1)
    @DisplayName("Test Android")
    @Tag("Mobile")
    void Test_001_Android(TestInfo testInfo) throws InterruptedException{
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        int i = 1;
        while(true) {
            try{
                if (i == 1) {
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys("");
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys("");
                }
                if (i == 2) {
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys("");
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys("admin");
                }
                if (i == 3){
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys("admin");
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys("");
                }
                if (i == 4){
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys("admin");
                    androidDriver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys("admin");
                }
                i++;
                androidDriver.findElement(By.id(Utils.valoreProp("app.btn.login", "android"))).click();
                Thread.sleep(1000);
                if (androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).isDisplayed()) {
                    extentTest.log(LogStatus.INFO, "controllo "+(i-1)+" per verificare parametri login", Utils.getScreenBase64Android("Errore"+(i-1)));
                    System.out.println(androidDriver.findElement(By.id(Utils.valoreProp("id.error.message","android"))).getText());
                    androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).click();
                    Thread.sleep(500);
                    androidDriver.findElement(By.id(Utils.valoreProp("app.btn.reset","android"))).click();
                    Thread.sleep(1000);
                    continue;
                }
            } catch (NoSuchElementException e){
                extentTest.log(LogStatus.INFO, "Ingresso Login", Utils.getScreenBase64Android("Enter"));
                break;
            }
        }

        if (i == 5){
            androidDriver.findElement(By.id(Utils.valoreProp("id.btn.add","android"))).click();
            Thread.sleep(1000);
            extentTest.log(LogStatus.INFO, "Attesa di nuovo utente", Utils.getScreenBase64Android("Utente1"));
            androidDriver.findElement(By.id(Utils.valoreProp("id.edit.name","android"))).sendKeys("Franco");
            androidDriver.findElement(By.id(Utils.valoreProp("id.final.add","android"))).click();
            Thread.sleep(1000);
            extentTest.log(LogStatus.INFO, "Nuovo utente salvato", Utils.getScreenBase64Android("Utente2"));
        }
        Thread.sleep(1000);
        androidDriver.findElement(By.id(Utils.valoreProp("id.btn.final.reset","android"))).click();
        Thread.sleep(700);
        androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).click();
        Thread.sleep(1000);
        extentTest.log(LogStatus.PASS, "Fine esercizio", Utils.getScreenBase64Android("Vuoto"));
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
