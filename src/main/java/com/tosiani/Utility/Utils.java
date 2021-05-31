package com.tosiani.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.tosiani.Utility.GlobalParameters.*;

public class Utils {

    public static Properties loadProp(String propNome){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(PROPERTIES_PATH + File.separator + propNome + EXIT_PROPERTIES));
        } catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static String valoreProp(String chiave, String nomeFile){
        Properties prop = loadProp(nomeFile);
        if (prop.getProperty(chiave).length() > 0)
            return prop.getProperty(chiave);
        return "";
    }
}
