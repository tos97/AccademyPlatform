package com.tosiani.web;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.step.StepsMobile;
import com.tosiani.utility.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;

import static com.tosiani.utility.GlobalParameters.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Android_001 {

    static private ExtentReports extentReports;
    static private ExtentTest extentTest;
    static private AndroidDriver<?> androidDriver;
    static private DesiredCapabilities desiredCapabilities = null;
    static private int contatore = 1;
    static private StepsMobile step = null;

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
        step = new StepsMobile();
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
                    extentTest.log(LogStatus.INFO, "controllo "+(i-1)+" per verificare parametri login", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Errore"+(i-1))));
                    System.out.println(androidDriver.findElement(By.id(Utils.valoreProp("id.error.message","android"))).getText());
                    androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).click();
                    Thread.sleep(500);
                    androidDriver.findElement(By.id(Utils.valoreProp("app.btn.reset","android"))).click();
                    Thread.sleep(1000);
                    continue;
                }
            } catch (NoSuchElementException e){
                extentTest.log(LogStatus.INFO, "Ingresso Login", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Enter")));
                break;
            }
        }

        if (i == 5){
            androidDriver.findElement(By.id(Utils.valoreProp("id.btn.add","android"))).click();
            Thread.sleep(1000);
            extentTest.log(LogStatus.INFO, "Attesa di nuovo utente", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Utente1")));
            androidDriver.findElement(By.id(Utils.valoreProp("id.edit.name","android"))).sendKeys("Franco");
            androidDriver.findElement(By.id(Utils.valoreProp("id.final.add","android"))).click();
            Thread.sleep(1000);
            extentTest.log(LogStatus.INFO, "Nuovo utente salvato", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Utente2")));
        }
        Thread.sleep(1000);
        androidDriver.findElement(By.id(Utils.valoreProp("id.btn.final.reset","android"))).click();
        Thread.sleep(700);
        androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).click();
        Thread.sleep(1000);
        extentTest.log(LogStatus.PASS, "Fine esercizio", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Vuoto")));
    }

    @ParameterizedTest(name = "{0}")
    @Order(2)
    @DisplayName("Test Android 2")
    @CsvSource({"user","admin"})
    @Tag("Mobile")
    void Test_002_Android(String utente,TestInfo testInfo) throws InterruptedException{
        extentTest = extentReports.startTest(testInfo.getDisplayName());

        androidDriver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys(utente);
        androidDriver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys(utente);
        androidDriver.findElement(By.id(Utils.valoreProp("app.btn.login", "android"))).click();
        Thread.sleep(1000);
        if (!androidDriver.findElement(By.id(Utils.valoreProp("id.welcome", "android"))).getText().contains(utente))
            extentTest.log(LogStatus.FAIL, "Warning nome utente non stampato correttamente", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("WelcomeUtente")));
        else{
            extentTest.log(LogStatus.PASS, "Nome utente stampato correttamente", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("WelcomeUtente")));
        }
        Thread.sleep(500);
        androidDriver.navigate().back();
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Test Android 2")
    @CsvSource({"Gennaro,Franco,Michele,Giovanni","Vito,Emanuele,Benedetto,Francesco"})
    @Tag("Mobile")
    void Test_003_Android(String nome1,String nome2,String nome3,String nome4,TestInfo testInfo) throws InterruptedException {
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        String utente = "user";
        ArrayList<String> listaNomi = new ArrayList<>();
        String [] nomi = new String[4];
        if (contatore < 2)
            nomi = new String[]{nome1,nome2,nome3,""};
        else {
            nomi = new String[]{nome1,nome2,nome3,nome4};
        }
        int j = 1;
        step.login(androidDriver,"user");

        for(WebElement i: androidDriver.findElements(By.className(Utils.valoreProp("class.nomi","android")))){
            if (j != 1 && j != 2)
                listaNomi.add(i.getText());
            j++;
        }

        for (int i = 0;i < nomi.length;i++) {
            j = 0;
            for (String nm : listaNomi) {
                if (!nomi[i].equals(nm) && nomi[i].length() != 0)
                    j++;
            }
            if (j == listaNomi.size()) {
                androidDriver.findElement(By.id(Utils.valoreProp("id.btn.add", "android"))).click();
                Thread.sleep(1000);
                androidDriver.findElement(By.id(Utils.valoreProp("id.edit.name", "android"))).sendKeys(nomi[i]);
                Thread.sleep(500);
                androidDriver.findElement(By.id(Utils.valoreProp("id.final.add", "android"))).click();
                Thread.sleep(1000);
                extentTest.log(LogStatus.INFO, "Nuovo utente salvato", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Utente2")));
                listaNomi.add(nomi[i]);
            } else {
                extentTest.log(LogStatus.INFO, "Attenzione L'utente: " + nomi[i] + " è già presente nella lista o il campo è vuoto, quindi non verrà salvato", "");
            }
        }

        androidDriver.findElement(By.id(Utils.valoreProp("id.btn.final.reset","android"))).click();
        Thread.sleep(700);
        androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).click();
        Thread.sleep(1000);
        extentTest.log(LogStatus.PASS, "Fine esercizio", extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Vuoto")));
        androidDriver.navigate().back();
    }


    @AfterEach
    void tearDown(){
        extentReports.endTest(extentTest);
        contatore++;
    }

    @AfterAll
    static void tearDownAll(){
        ManagmentDriver.stopDriver();
        extentReports.flush();
    }
}
