package com.tosiani.Utility;

import java.io.File;

public class GlobalParameters {

    //PATH
    //public static final String BASE_PATH                    = System.getProperty("user.dir");
    public static final String BASE_PATH = "C:\\Users\\rober\\Documents\\GitHub\\AccademyPlatform";
    public static final String SRC_PATH                     = BASE_PATH + File.separator + "src";
    public static final String MAIN_PATH                    = BASE_PATH + File.separator + SRC_PATH + File.separator + "main";
    public static final String TEST_PATH                    = BASE_PATH + SRC_PATH + "\\test\\java";
    public static final String LOGS_PATH                    = BASE_PATH + File.separator + "logs";
    public static final String REPORT_PATH                  = BASE_PATH + File.separator + "report";
    public static final String SCREENSHOT_PATH              = BASE_PATH + File.separator + "screenshot";
    public static final String CHROME_DRIVER_PATH           = BASE_PATH + File.separator + "driver" + File.separator + "chrome" + File.separator + "Windows" + File.separator + "chromedriver.exe";
    public static final String RESOURCES_PATH               = MAIN_PATH + File.separator + "resources";
    public static final String PROPERTIES_PATH              = RESOURCES_PATH + File.separator + "properties";
    public static final String PACKAGE                      = "com.tosiani";

    //EXT_SYSTEM
    public static final String EXIT_JPG                     = ".jpg";
    public static final String EXIT_PNG                     = ".png";
    public static final String EXIT_HTML                    = ".html";
    public static final String EXIT_EXE                     = ".exe";
    public static final String EXIT_APK                     = ".apk";
    public static final String EXIT_JSON                    = ".json";
    public static final String EXIT_LOGS                    = ".logs";
    public static final String EXIT_PROPERTIES              = ".properties";

    //INTERNET
    public static final String HTTP                         = "http";
    public static final String HTTPS                        = "https";

    public static final String MY_SO                        = System.getProperty("os.name");
    public static final String WINDOWS_SO                   = "Windows";
}
