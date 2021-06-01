package com.tosiani;

import com.tosiani.Utility.Utils;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.text.normalizer.Utility;

import static com.tosiani.Driver.GlobalParameters.CHROME_DRIVER;

public class ManagmentDriver {
    private static ChromeDriver cDriver;

    public static void startDriver(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        System.setProperty("org.freemarker.loggerLibrary","none");
        cDriver = new ChromeDriver();
        System.err.close();
        System.setErr(System.err);
        Utils.loadProp("log4j");
        //BasicConfigurator.configure();
    }

    public static WebDriver getDriver(){
        return cDriver;
    }

    public static void stopDriver(){
        cDriver.quit();
    }
}
