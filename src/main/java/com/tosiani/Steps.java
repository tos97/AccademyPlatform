package com.tosiani;

import com.tosiani.drivers.ManagmentDriver;
import org.openqa.selenium.*;

import java.util.List;

import static com.tosiani.utility.Utils.valoreProp;

public class Steps {

    private WebElement webElement = null; // non necessaria infatti per il bottone facciamo inline
    private WebDriver driver = ManagmentDriver.getDriver();

    public void search(WebDriver driver, String q, String nomeProp){
        webElement = driver.findElement(By.id(valoreProp("id.input.search", nomeProp)));
        webElement.clear();
        webElement.sendKeys(q);
        driver.findElement(By.id(valoreProp("id.btn.search", nomeProp))).click();
    }

    public void closeBannerEbay(WebDriver driver, String nomeProp){
        try {
            //webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(By.id(valoreProp("id.banner.gdb", nomeProp))));
            Thread.sleep(3000);
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (driver.findElement(By.id(valoreProp("id.banner.gdb", nomeProp))).isDisplayed()) {
                driver.findElement(By.id(valoreProp("id.btn.gdb", nomeProp))).click();
                System.out.println("Banner Trovato e chiuso");
            }
        }catch (NoSuchElementException | InterruptedException e) {
            System.out.println("Banner non Trovato");
            //InterruptedException eccezione sleep
        }
    }

    public String[] listaElementi(WebDriver driver, String nomeProp){
        List<WebElement> listElements = driver.findElements(By.id(valoreProp("id.box",nomeProp)));
        String [] temp = new String[listElements.size()];

        for (int i = 0;i < listElements.size();i++){
            temp[i] = listElements.get(i).getText();
        }

        return temp;
    }

    public String getTitle(WebElement element, String nomeProp){
        return element.findElement(By.className(valoreProp("class.title", nomeProp))).getText();
    }

    public String getPrezzo(WebElement element, String nomeProp){
        return element.findElement(By.className(valoreProp("class.prize", nomeProp))).getText();
    }

    public String getSubtilte(WebElement element, String nomeProp){
        return element.findElement(By.className(valoreProp("class.subtitle", nomeProp))).getText();
    }

    public String getImg(WebElement element, String nomeProp){
        return element.findElement(By.className(valoreProp("class.img", nomeProp))).getAttribute("src");
    }
}
