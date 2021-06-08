package com.tosiani.utility;

import com.tosiani.drivers.ManagmentDriver;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.tosiani.utility.GlobalParameters.*;

public class Utils {

    public static Properties loadProp(String propNome){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(PROPERTIES_PATH + File.separator + propNome + EXT_PROP));
        } catch (IOException e){
            e.printStackTrace();
            getScreenshot();
        }
        return properties;
    }

    public static String valoreProp(String chiave, String nomeFile){
        Properties prop = loadProp(nomeFile);
        if (prop.getProperty(chiave).length() > 0)
            return prop.getProperty(chiave);
        return "";
    }

    public static void getScreenshot(){
        try {
            SimpleDateFormat dta = new SimpleDateFormat("yyyyMMddHHmmss");
            String sDate = dta.format(new Date());
            byte[] immagine = ((TakesScreenshot)ManagmentDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get(SCREENSHOT_PATH+sDate+".png"),immagine);
        } catch (IOException e) {
            e.printStackTrace();
            getScreenshot();
        }
    }
}
