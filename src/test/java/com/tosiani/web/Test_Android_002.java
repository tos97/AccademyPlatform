package com.tosiani.web;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.models.RicercaBershka;
import com.tosiani.step.StepsMobile;
import com.tosiani.utility.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import static com.tosiani.utility.GlobalParameters.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Android_002 {
    static private WebDriver driver = null;
    static private WebElement webElement = null;
    static private ExtentReports extentReports;
    static private ExtentTest extentTest;
    static private AndroidDriver<?> androidDriver;
    static private DesiredCapabilities desiredCapabilities = null;
    static private StepsMobile step = null;

    @BeforeAll
    static void beforeAll(){
        extentReports = new ExtentReports(REPORT_PATH + File.separator + "reportBershka" + EXT_HTML, true);
        extentReports.loadConfig(new File(REPORT_CONFIG_XML));

        desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "Bershka Fashion and trends online_v2.57.2_apkpure.com" + EXT_ANDROID);

        ManagmentDriver.startAndroidDriver(desiredCapabilities);
        androidDriver = ManagmentDriver.getAndroidDriver();
        step = new StepsMobile();
    }

    @BeforeEach
    void beforeEach(){}


    @Test
    @Order(4)
    @DisplayName("Test Android su zara")
    @Tag("Mobile")
    void Test_004_Android(TestInfo testInfo) throws InterruptedException{
        ArrayList<RicercaBershka> listaRicercati = new ArrayList<>();
        extentTest = extentReports.startTest(testInfo.getDisplayName());
        Thread.sleep(3000);
        extentTest.log(LogStatus.INFO,"Inizio con l'ancio dell'apk",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Accensione1")));
        step.start(androidDriver);
        extentTest.log(LogStatus.INFO,"Accettate tutte le condizioni",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("Accesso2")));

        androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.risultati", "android"))).click();
        Thread.sleep(5000);
        extentTest.log(LogStatus.INFO,"Risultati della ricerca",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("RicercaBershka")));

        step.ricercaBershka(androidDriver,"xpath.bershka.risul");
        listaRicercati.add(new RicercaBershka(androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.title", "android"))).getText(),
                new Float(androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.prize", "android"))).getText().substring(1))));
        extentTest.log(LogStatus.INFO,"Elemento 1 della ricerca",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("RicercaBershka2")));

        androidDriver.navigate().back();
        Thread.sleep(5000);
        step.ricercaBershka(androidDriver,"xpath.bershka.risultati");
        listaRicercati.add(new RicercaBershka(androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.title", "android"))).getText(),
                new Float(androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.prize", "android"))).getText().substring(1))));
        extentTest.log(LogStatus.INFO,"Elemento 2 della ricerca",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("RicercaBershka3")));

        float somma = 0;
        for (RicercaBershka elemento: listaRicercati){
            System.out.println(elemento.getTitolo());
            System.out.println(elemento.getPrezzo());
            somma += elemento.getPrezzo();
        }
        androidDriver.findElement(By.id("com.inditex.ecommerce.bershka:id/toolbar.icon")).click();
        Thread.sleep(5000);
        float prezzoFinale = new Float(androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.final.prize", "android"))).getText().substring(1));
        System.out.println(prezzoFinale);
        if (prezzoFinale==somma)
            extentTest.log(LogStatus.PASS,"Test passato con 2 elementi nel carrello e prezzo totale uguale a somma dei parziali",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("CarrelloBershka")));
        else{
            extentTest.log(LogStatus.FAIL,"Test fallito ci dovevano essere 2 elementi nel carrello e prezzo totale uguale a somma dei parziali",extentTest.addBase64ScreenShot(Utils.getScreenBase64Android("CarrelloBershka")));
            fail();
        }


        //assertEquals(prezzoFinale,somma);

        //androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.ImageView")).click();
        //Thread.sleep(5000);

        //Thread.sleep(5000);


        /*TouchAction action = new TouchAction<>(androidDriver);
        action.press(PointOption.point(500,1600));
        action.moveTo(PointOption.point(500, 800));
        action.release();
        action.perform();

        TouchAction action = new TouchAction((PerformsTouchActions) androidDriver);
        action.press(PointOption.point(537, 1609));
        action.moveTo(PointOption.point(532, 721));
        action.release();
        action.perform();*/

        /*if (androidDriver.findElement(By.id(Utils.valoreProp("id.bershka.search", "android"))).isDisplayed())
            extentTest.log(LogStatus.PASS,"Arrivato nella home","");
        else{
            extentTest.log(LogStatus.FAIL,"Fallito il test","");
        }

        Thread.sleep(15000);*/
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
