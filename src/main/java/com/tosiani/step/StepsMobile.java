package com.tosiani.step;

import com.tosiani.utility.Utils;
import org.openqa.selenium.*;

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

    public void searchByXpath(WebDriver driver, String q){
        webElement = driver.findElement(By.xpath(valoreProp("xpath.input.ricerca", "mobile")));
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
                    ris = false;
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
                    ris = false;
                }
            }
        }
        return ris;
    }
}
