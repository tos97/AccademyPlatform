package com.tosiani.step.cucumber;

import com.relevantcodes.extentreports.LogStatus;
import com.tosiani.drivers.ManagmentDriver;
import com.tosiani.models.IstanzaCucumber;
import com.tosiani.utility.Utils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

import static com.tosiani.utility.GlobalParameters.*;
import static org.junit.Assert.fail;

public class AndroidSteps {
    private AndroidDriver<?> androidDriver = IstanzaCucumber.getAndroid();
    private DesiredCapabilities desiredCapabilities = null;
    private String nomeLogin;

    @Given("open app")
    public void doSomethingBeforeStep(){
    }


    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    @When("^Login with username: (.*) password: (.*)$")
    public void login(String nome, String psw) throws InterruptedException {
        Thread.sleep(1000);
        androidDriver.findElement(By.id(Utils.valoreProp("app.id.username", "android"))).sendKeys(nome);
        Thread.sleep(500);
        androidDriver.findElement(By.id(Utils.valoreProp("app.id.pwd", "android"))).sendKeys(psw);
        Thread.sleep(1000);
        androidDriver.findElement(By.id(Utils.valoreProp("app.btn.login", "android"))).click();
        Thread.sleep(1000);
        setNomeLogin(nome);
    }

    @Then("let's check if you log in with your credentials")
    public void checkLogin(){
        try {
            if (androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).isDisplayed()) {
                androidDriver.findElement(By.id(Utils.valoreProp("id.btn.accettazione","android"))).click();
                Thread.sleep(1000);
                androidDriver.findElement(By.id(Utils.valoreProp("app.btn.reset","android"))).click();
                //fail();
            }
        } catch (NoSuchElementException | InterruptedException e){
            //fail();
            try{
                assertThat(true,is(androidDriver.findElement(By.id(Utils.valoreProp("id.welcome", "android"))).isDisplayed()));
            } catch (NoSuchElementException ex){
                fail();
            }
        }
    }

    @Then("check if you are logged in with the right account")
    public void checkUser() throws InterruptedException {
        Thread.sleep(2000);
        assertThat(true,is(androidDriver.findElement(By.id(Utils.valoreProp("id.welcome", "android"))).getText().contains(nomeLogin)));
    }

    /*@Given("connessione stabilita")
    public void connection() throws InterruptedException {
        doSomethingBeforeStep();
        login("admin","admin");
        clickLogin();
    }*/

}
