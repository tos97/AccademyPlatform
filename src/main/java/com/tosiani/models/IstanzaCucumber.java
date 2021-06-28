package com.tosiani.models;

import com.tosiani.drivers.ManagmentDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.tosiani.utility.GlobalParameters.*;

public class IstanzaCucumber {
    private static DesiredCapabilities desiredCapabilities = null;

    public static AndroidDriver<?> getAndroid() {
        desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "app" + EXT_ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");

        ManagmentDriver.startAndroidDriver(desiredCapabilities);
        return ManagmentDriver.getAndroidDriver();
    }
}
