package com.singtel.todomvc.web.utils;

public class OsCheck extends WebDriverInitializer {

    public static String detectedOSDriver;

    /*
     * This is used to detect the OS and browser selected and provide the Driver Binary file location
     * Returns String
     * Arguments - browsername - String
     */
    public static String getOperatingSystemDriver(String browser) {
        String OS = "";
        if (System.getProperty("OS") == null) {
            OS = environment.getProperty("OS");
        } else {
            OS = System.getProperty("OS");
        }
        OS = OS.toLowerCase();
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            System.out.println("--------------The system is MacOS--------------------");
            switch (browser) {
                case "Chrome":
                    System.out.println("---------The browser is Chrome------------------");
                    detectedOSDriver = "/src/test/resources/drivers/chromedriver_mac";
                    break;
                case "Firefox":
                    System.out.println("---------The browser is Firefox-----------------");
                    detectedOSDriver = "/src/test/resources/drivers/geckodriver_mac";
                    break;
                default:
                    System.out.println("---------The default browser is Chrome-----------");
                    detectedOSDriver = "/src/test/resources/drivers/chromedriver_mac";
                    break;
            }
        } else if (OS.indexOf("win") >= 0) {
            System.out.println("--------------The system is Windows--------------------");
            switch (browser) {
                case "Chrome":
                    System.out.println("---------The browser is Chrome------------------");
                    detectedOSDriver = "/src/test/resources/drivers/chromedriver_win.exe";
                    break;
                case "Firefox":
                    System.out.println("---------The browser is Firefox-----------------");
                    detectedOSDriver = "/src/test/resources/drivers/geckodriver_win.exe";
                    break;
                default:
                    System.out.println("---------The default browser is Chrome-----------");
                    detectedOSDriver = "/src/test/resources/drivers/chromedriver_win.exe";
                    break;
            }
        } else if (OS.indexOf("nux") >= 0) {
            System.out.println("--------------The system is Linux---------------------");
            switch (browser) {
                case "Chrome":
                    System.out.println("---------The browser is Chrome------------------");
                    detectedOSDriver = "/src/test/resources/drivers/chromedriver";
                    break;
                case "Firefox":
                    System.out.println("---------The browser is Firefox-----------------");
                    detectedOSDriver = "/src/test/resources/drivers/geckodriver";
                    break;
                default:
                    System.out.println("---------The default browser is Chrome-----------");
                    detectedOSDriver = "/src/test/resources/drivers/chromedriver";
                    break;
            }
        } else {
            System.out.println("----------------------OS unindetified-------------------");
        }
        return detectedOSDriver;
    }
}