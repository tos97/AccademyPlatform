package com.tosiani.step.cucumber;

import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.selenium.DefaultChromeOptions;
import com.tosiani.utility.Utils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.fail;

public class TrenitaliaSteps {
    private WebDriver driver = null;
    private WebElement webElement = null;
    private Properties prop = null;

    /*@Before
    public void doSomethingBeforeStep(){
        DefaultChromeOptions defaultChromeOptions = new DefaultChromeOptions(new ChromeOptions());
        ManagmentDriver.startDriver(defaultChromeOptions);
        driver = ManagmentDriver.getDriver();
        prop = Utils.loadProp("trenitalia");
    }*/


    @Given("open site")
    public void openSite() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(prop.getProperty("trenitalia.url"));
        Thread.sleep(2000);
    }

    @And("close banner")
    public void closeBanner(){
        try {
            if (driver.findElement(By.id(prop.getProperty("id.close.banner"))).isDisplayed())
                driver.findElement(By.id(prop.getProperty("id.close.banner"))).click();
            Thread.sleep(1000);
        } catch (NoSuchElementException | InterruptedException e){

        }
    }

    @Then("banner closing control")
    public void controlBanner(){
        try {
            if (driver.findElement(By.id(prop.getProperty("id.close.banner"))).isDisplayed())
                fail();
            Thread.sleep(1000);
        } catch (NoSuchElementException | InterruptedException e){

        }
    }

    public void clickFirst(List<WebElement> clickable){
        for (WebElement element: clickable) {
            element.click();
            return;
        }
    }

    @Given("^departure: (.*) and arrival: (.*)$")
    public void research(String partenza, String arrivo) throws InterruptedException {
        driver.findElement(By.id(prop.getProperty("id.insert.from"))).sendKeys(partenza);
        Thread.sleep(1000);
        clickFirst(driver.findElement(By.id(prop.getProperty("id.autocomplete.from")))
                .findElements(By.className(prop.getProperty("class.autocomplete"))));
        driver.findElement(By.id(prop.getProperty("id.insert.to"))).sendKeys(arrivo);
        Thread.sleep(1000);
        clickFirst(driver.findElement(By.id(prop.getProperty("id.autocomplete.to")))
                .findElements(By.className(prop.getProperty("class.autocomplete"))));
        /*if(driver.findElement(By.id(prop.getProperty("id.n.persone"))).getAttribute("value").equals("0")){

        }*/
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[title='Cerca']")).click();
        Thread.sleep(10000);
    }

    @Then("check if there are any results")
    public void controlResult(){
        try {
            assertThat(true, is(driver.findElement(By.id("carrello")).isDisplayed()));
        } catch (NoSuchElementException e){
            fail();
        }
    }

    public void clickAddCart() throws InterruptedException {
        try {
            for (int i = 0;i < 10;i++){
                int j = 0;
                for(WebElement element: driver.findElement(By.id("travelSolution"+i))
                        .findElements(By.cssSelector("td[data-toggle='collapse']"))){
                    j++;
                    if(j == 3) {
                        element.click();
                        Thread.sleep(2000);
                        driver.findElement(By.id("priceGrid"+i)).findElement(By.cssSelector("input[value='Continua']")).click();
                        Thread.sleep(2000);
                        if (driver.findElement(By.id("errorExc")).isDisplayed()){
                            break;
                        } else {
                            return;
                        }
                    }
                }
            }
        } catch (NoSuchElementException e){
            System.out.println("fine");
        }
    }

    @Then("check cart")
    public void controlResult2() throws InterruptedException {
        clickAddCart();
        Thread.sleep(2000);
        boolean result = false;
        //if (driver.findElement(By.id("totalTrip")))
        System.out.println("carrello: "+driver.findElement(By.id("totalTrip")).getText());
        System.out.println("prezzo: "+driver.findElement(By.id("totalPriceSolutionList")).getText());
        if(Integer.parseInt(driver.findElement(By.id("totalTrip")).getText()) > 0) {
            result = true;
        }
        assertThat(true,is(result));
        Thread.sleep(8000);
    }


    /*@After
    public void doSomethingAfter(){
        ManagmentDriver.stopDriver();
    }*/
}
