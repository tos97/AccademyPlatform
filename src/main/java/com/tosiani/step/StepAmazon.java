package com.tosiani.step;

import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

import static com.tosiani.utility.Utils.valoreProp;

public class StepAmazon {

    private WebElement webElement = null;
    private String ERRORE = "";

    public String getERRORE() {
        return ERRORE;
    }

    public void setERRORE(String ERRORE) {
        this.ERRORE = ERRORE;
    }

    public boolean closeBanner(WebDriver driver){
        try {
            Thread.sleep(3000);
            if (driver.findElement(By.id(valoreProp("id.banner", "amazon"))).isDisplayed()) {
                driver.findElement(By.id(valoreProp("id.btn.banner", "amazon"))).click();
                System.out.println("Banner Trovato e chiuso");
                return true;
            }
        }catch (NoSuchElementException | InterruptedException e) {
            setERRORE(e.getMessage());
        }
        return false;
    }

    public void searchById(WebDriver driver, String q) throws InterruptedException {
        webElement = driver.findElement(By.id(valoreProp("id.input.text", "amazon")));
        webElement.clear();
        webElement.sendKeys(q);
        webElement.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    public void sideBarMarca(WebDriver driver, String marca) throws InterruptedException {
        driver.findElement(By.id(valoreProp("id.left.bar.marca", "amazon")+marca)).findElement(By.className(valoreProp("class.btn.selection", "amazon"))).click();
        Thread.sleep(3000);
    }

    public void sideBarPrezzo(WebDriver driver,String low,String high) throws InterruptedException {
        webElement = driver.findElement(By.id(valoreProp("id.left.bar.prezzo.low.range", "amazon")));
        webElement.clear();
        webElement.sendKeys(low);
        webElement = driver.findElement(By.id(valoreProp("id.left.bar.prezzo.high.range", "amazon")));
        webElement.clear();
        webElement.sendKeys(high);
        webElement.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    public boolean ordinaPer(WebDriver driver,int n) throws InterruptedException {
        try {
            driver.findElement(By.cssSelector("span[data-action = 'a-dropdown-button']")).click();
            Thread.sleep(1500);
            driver.findElement(By.cssSelector("li[aria-labelledby = 's-result-sort-select_" + n + "']")).click();
            Thread.sleep(1500);
            return true;
        } catch (Exception e) {
            setERRORE(e.getMessage());
            return false;
        }
    }

    public boolean selezioneArticolo(WebDriver driver,int elemento) throws InterruptedException {
        try {
            String risultati = "div[data-index = '" + elemento + "']";
            driver.findElement(By.cssSelector(risultati)).findElement(By.className("a-text-normal")).click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e){
            setERRORE(e.getMessage());
            return false;
        }
    }

    public boolean selezioneTaglia(WebDriver driver,int posizione) throws InterruptedException {
        try {
            List<WebElement> risDim = driver.findElement(By.cssSelector("ul[data-action = 'a-button-group']")).findElements(By.className(valoreProp("class.dim.ris", "amazon")));
            int sizeDim = risDim.size();
            if (posizione < 0 && posizione > sizeDim) {
                if (sizeDim >= 2) {
                    if (risDim.get(sizeDim - 1).isEnabled())
                        risDim.get(sizeDim - 2).click();
                    else {
                        risDim.get(sizeDim - 1).click();
                    }
                }
            } else {
                risDim.get(posizione - 1).click();
            }
            Thread.sleep(1000);
            return true;
        } catch (Exception e){
            setERRORE(e.getMessage());
            return false;
        }
    }

    public void quantity(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id(valoreProp("id.quantity", "amazon"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("option[value = '"+2+"']")).click();
        Thread.sleep(1000);
    }

    public void carrello(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id(valoreProp("id.btn.add.cart","amazon"))).click();
        Thread.sleep(2000);
        try {
            if (driver.findElement(By.xpath(valoreProp("xpath.no.btn", "amazon"))).isDisplayed())
                driver.findElement(By.xpath(valoreProp("xpath.no.btn", "amazon"))).click();
            Thread.sleep(3000);
            if(driver.findElement(ByChained.cssSelector("input[aria-labelledby = 'attach-sidesheet-view-cart-button-announce']")).isDisplayed())
                driver.findElement(ByChained.cssSelector("input[aria-labelledby = 'attach-sidesheet-view-cart-button-announce']")).click();
        } catch (NoSuchElementException e){
            Thread.sleep(2000);
            try{
                if(driver.findElement(ByChained.cssSelector("input[aria-labelledby = 'attach-sidesheet-view-cart-button-announce']")).isDisplayed())
                    driver.findElement(ByChained.cssSelector("input[aria-labelledby = 'attach-sidesheet-view-cart-button-announce']")).click();
            } catch (NoSuchElementException ex) {
                driver.findElement(By.id(valoreProp("id.btn.cart.no.banner", "amazon"))).click();
                setERRORE(e.getMessage());
            }
        }
        Thread.sleep(2000);
    }

    public int removeAll(WebDriver driver) throws InterruptedException {
        try {
            int totale = driver.findElements(By.cssSelector("input[value = 'Rimuovi']")).size();
            for (int i = totale; i > 0; i--) {
                driver.findElements(By.cssSelector("input[value = 'Rimuovi']")).get(i - 1).click();
            }
            Thread.sleep(2000);
            return driver.findElements(By.cssSelector("input[value = 'Rimuovi']")).size();
        } catch (Exception e){
            setERRORE(e.getMessage());
            return -1;
        }
    }
}
