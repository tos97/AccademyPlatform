package com.tosiani.step;

import com.tosiani.models.RicercaEbay;
import com.tosiani.utility.Utils;
import org.openqa.selenium.*;

import java.util.ArrayList;

import static com.tosiani.utility.Utils.valoreProp;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsMobile {

    static private WebElement webElement = null;

    public void closeBannerGoogle(WebDriver driver){
        try {
            Thread.sleep(3000);
            if (driver.findElement(By.id(valoreProp("id.btn.banner.avanti", "mobile"))).isDisplayed()) {
                driver.findElement(By.id(valoreProp("id.btn.banner.avanti", "mobile"))).click();
                Thread.sleep(1000);
                driver.findElement(By.id(valoreProp("id.btn.banner.avanti", "mobile"))).click();
                Thread.sleep(1000);
                driver.findElement(By.id(valoreProp("id.btn.banner.avanti", "mobile"))).click();
                Thread.sleep(2500);
                driver.findElement(By.id(valoreProp("id.btn.cookie", "mobile"))).click();
                System.out.println("Banner Trovato e chiuso");
            }
        }catch (NoSuchElementException | InterruptedException e) {
            System.out.println("Banner non Trovato");
        }
    }

    public void searchByXpath(WebDriver driver, String q, String nomeProp){
        webElement = driver.findElement(By.xpath(valoreProp("xpath.input.ricerca", nomeProp)));
        webElement.clear();
        webElement.sendKeys(q);
        webElement.sendKeys(Keys.ENTER);
    }

    public void searchById(WebDriver driver, String q, String nomeProp){
        webElement = driver.findElement(By.id(valoreProp("id.input.ricerca", nomeProp)));
        webElement.clear();
        webElement.sendKeys(q);
        webElement.sendKeys(Keys.ENTER);
    }

    public boolean nextPgn(WebDriver driver, int pgn) throws InterruptedException{
        boolean ris = false;
        for (int i = 1;i < pgn;i++){
            Thread.sleep(4000);
            driver.findElement(By.className(valoreProp("class.btn.next", "mobile.ebay"))).click();
            Utils.getScreenshot();
            if (driver.getCurrentUrl().contains("pgn")){
                if (driver.getCurrentUrl().contains(String.valueOf(i+1)))
                    ris = true;
                else{
                    return false;
                }
            }
        }
        return ris;
    }

    public boolean previousPgn(WebDriver driver, int pgn) throws InterruptedException{
        boolean ris = false;
        for (int i = pgn;i > 1;i--){
            Thread.sleep(4000);
            driver.findElement(By.className(valoreProp("class.btn.previous", "mobile.ebay"))).click();
            if (driver.getCurrentUrl().contains("pgn")){
                if (driver.getCurrentUrl().contains(String.valueOf(i-1)))
                    ris = true;
                else{
                    return false;
                }
            }
        }
        return ris;
    }

    public void insertValoriEbay(WebDriver driver, String q, String nomeProp, String chiave){
        webElement = driver.findElement(By.id(valoreProp(chiave, nomeProp)));
        webElement.clear();
        webElement.sendKeys(q);
    }

    public ArrayList<RicercaEbay> cartSelection(WebDriver driver, String q, int i, boolean ricerca) throws InterruptedException{
        ArrayList<RicercaEbay> arrayRicerca = new ArrayList<>();

        if (ricerca)
            searchById(driver,q, "mobile.ebay");
        Thread.sleep(3000);
        String risultato = valoreProp("xpath.ric.parte1","mobile.ebay") + i + valoreProp("xpath.ric.parte2","mobile.ebay");
        driver.findElement(By.xpath(risultato)).click();
        Thread.sleep(1000);
        arrayRicerca.add(new RicercaEbay(
                driver.findElement(By.className(valoreProp("class.title", "mobile.ebay"))).getText(),
                driver.findElement(By.className(valoreProp("class.prezzo", "mobile.ebay"))).getText()
        ));
        String[] tmp = new String[2];
        tmp = arrayRicerca.get(0).getPrezzo().split(" ");
        arrayRicerca.get(0).setPrezzo(tmp[1].replace(",","."));

        driver.findElement(By.id(valoreProp("id.btn.add.cart","mobile.ebay"))).click();
        Thread.sleep(1000);

        return arrayRicerca;
    }

    public void login(WebDriver driver,String q) throws InterruptedException{
        driver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys(q);
        driver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys(q);
        driver.findElement(By.id(Utils.valoreProp("app.btn.login", "android"))).click();
        Thread.sleep(1000);
    }

    public void start(WebDriver driver) throws InterruptedException{
        driver.findElement(By.id(Utils.valoreProp("id.bershka.cookie","android"))).click();
        Thread.sleep(3000);
        driver.findElement(By.id(Utils.valoreProp("id.bershka.go","android"))).click();
        Thread.sleep(6000);
        driver.findElement(By.id(Utils.valoreProp("id.bershka.next","android"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Utils.valoreProp("id.bershka.geo.start","android"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id(Utils.valoreProp("id.bershka.geo.accept","android"))).click();
        Thread.sleep(6000);
    }

    public void ricercaBershka(WebDriver driver,String prop) throws InterruptedException{
        driver.findElement(By.xpath(Utils.valoreProp(prop, "android"))).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.inditex.ecommerce.bershka:id/product_detail.add_container")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.TextView")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.inditex.ecommerce.bershka:id/size.add")).click();
        Thread.sleep(6000);
    }
}
