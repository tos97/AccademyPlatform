package com.tosiani;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import static com.tosiani.Utility.Utils.valoreProp;

public class Steps {

    private static WebElement webElement = null; // non necessaria infatti per il bottone facciamo inline

    public static void search(WebDriver driver, String q, String nomeProp){
        webElement = driver.findElement(By.name(valoreProp("name.input.search", nomeProp)));
        webElement.clear();
        webElement.sendKeys(q);
        driver.findElement(By.id(valoreProp("id.btn.search", nomeProp))).click();
    }

    public static void closeBannerEbay(WebDriver driver, String nomeProp){
        try {
            Thread.sleep(3000);
            if (driver.findElement(By.id(valoreProp("id.banner.gdb", nomeProp))).isDisplayed()) {
                driver.findElement(By.id(valoreProp("id.btn.gdb", nomeProp))).click();
                System.out.println("Banner Trovato e chiuso");
            }
        }catch (NoSuchElementException | InterruptedException e) {
            System.out.println("Elemento non Trovato");
        }
    }
}
