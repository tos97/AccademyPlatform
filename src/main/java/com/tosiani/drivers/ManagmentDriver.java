package com.tosiani.drivers;

import com.tosiani.selenium.DefaultChromeOptions;
import com.tosiani.utility.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.tosiani.utility.GlobalParameters.*;

public class ManagmentDriver {
    private static ChromeDriver cDriver;

    public static void startDriver(DefaultChromeOptions defaultChromeOptions){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_WIN);
        System.setProperty("org.freemarker.loggerLibrary","none");
        cDriver = new ChromeDriver(defaultChromeOptions);
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
