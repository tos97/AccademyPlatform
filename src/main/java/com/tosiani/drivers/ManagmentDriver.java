package com.tosiani.drivers;

import com.tosiani.selenium.DefaultChromeOptions;
import com.tosiani.utility.Utils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

import static com.tosiani.utility.GlobalParameters.*;

public class ManagmentDriver {
    private static ChromeDriver cDriver;
    private static AndroidDriver<?> androidDriver;

    public static void startAndroidDriver(DesiredCapabilities desiredCapabilities){
        try{
            androidDriver = new AndroidDriver<MobileElement>(new URL(SERVER_APPIUM), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void startDriver(DefaultChromeOptions defaultChromeOptions){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_WIN);
        System.setProperty("org.freemarker.loggerLibrary","none");
        cDriver = new ChromeDriver(defaultChromeOptions);
        System.err.close();
        System.setErr(System.err);
        Utils.loadProp("log4j");
        //BasicConfigurator.configure();
    }

    public static AndroidDriver<?> getAndroidDriver(){
        return androidDriver;
    }

    public static WebDriver getDriver(){
        return cDriver;
    }

    public static void stopDriver(){
        if (cDriver != null){
            cDriver.quit();
        }
        if (androidDriver != null){
            androidDriver.quit();
        }
    }

    public static Wait<AndroidDriver> getWaitAndroid(){
        return new FluentWait<AndroidDriver>(getAndroidDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(40))
                .ignoring(NoSuchElementException.class);
    }
}
