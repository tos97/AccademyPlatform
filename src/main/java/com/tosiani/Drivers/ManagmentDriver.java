package com.tosiani.Drivers;

import com.tosiani.Utility.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.tosiani.Utility.GlobalParameters.*;

public class ManagmentDriver {
    private static ChromeDriver cDriver;

    public static void startDriver(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_WIN);
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
