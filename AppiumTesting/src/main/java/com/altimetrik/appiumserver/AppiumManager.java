package com.altimetrik.appiumserver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;

import java.io.File;
import java.net.URL;

/**
 * Appium Manager - this class contains method to start and stops appium server
 * To execute the tests from eclipse, you need to set PATH as
 * /usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin in run configuration
 */
public class AppiumManager {

    
    private AvailablePorts ap = new AvailablePorts();
    public AppiumDriverLocalService appiumDriverLocalService;
    public AppiumServiceBuilder builder = new AppiumServiceBuilder();

    public AppiumManager(){
    }

    /**
     * start appium with auto generated ports : appium port, chrome port,
     * bootstrap port and device UDID
     */

    public AppiumServiceBuilder appiumServerForAndroid(String deviceID,String appium_js_path)
        throws Exception {
        System.out.println(
            "**************************************************************************\n");
        System.out.println("Starting Appium Server to handle Android Device::" + deviceID + "\n");
        System.out.println(
            "**************************************************************************\n");
        File classPathRoot = new File(System.getProperty("user.dir"));
        File appiumlogs=new File(classPathRoot.getAbsolutePath()+"/target/appiumlogs");
        if(!appiumlogs.exists()){
        	appiumlogs.mkdir();
        }
        int port = ap.getPort();
        int chromePort = ap.getPort();
        int bootstrapPort = ap.getPort();
        int selendroidPort = ap.getPort();
        AppiumServiceBuilder builder =
            new AppiumServiceBuilder().withAppiumJS(new File(appium_js_path))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info").withLogFile(new File(
                System.getProperty("user.dir") + "/target/appiumlogs/" + deviceID
                    .replaceAll("\\W", "_") + ".txt"))
                .withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, Integer.toString(chromePort))
                .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,
                    Integer.toString(bootstrapPort))
                .withIPAddress("127.0.0.1")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)
                .withArgument(AndroidServerFlag.SELENDROID_PORT, Integer.toString(selendroidPort))
                .usingPort(port);
        /* and so on */
        ;
        appiumDriverLocalService = builder.build();
        appiumDriverLocalService.start();
        return builder;

    }

    /**
     * start appium with auto generated ports : appium port, chrome port,
     * bootstrap port and device UDID
     */
	ServerArgument webKitProxy = new ServerArgument() {
		public String getArgument() {
			return "--webkit-debug-proxy-port";
		}
	};

    public AppiumServiceBuilder appiumServerForIOS(String deviceID, String methodName,
        String webKitPort,String devicename,String appium_js_path) throws Exception {
    	AppiumServiceBuilder builder=null;
    	if(deviceID==null || deviceID.isEmpty()){
    		deviceID=devicename;
    	}
        System.out
            .println("**********************************************************************\n");
        System.out.println("Starting Appium Server to handle IOS::" + deviceID + "\n");
        System.out
            .println("**********************************************************************\n");
        File classPathRoot = new File(System.getProperty("user.dir"));
        File appiumlogs=new File(classPathRoot.getAbsolutePath()+"/target/appiumlogs");
        if(!appiumlogs.exists()){
        	appiumlogs.mkdir();
        }
        int port = ap.getPort();
        if(webKitPort!=null){
         builder =
            new AppiumServiceBuilder().withAppiumJS(new File(appium_js_path))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info").withLogFile(new File(
                System.getProperty("user.dir") + "/target/appiumlogs/" + deviceID.replaceAll("\\W", "_") + "__" + methodName + ".txt"))
                .withArgument(webKitProxy, webKitPort)
                .withIPAddress("127.0.0.1")
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                .withArgument(GeneralServerFlag.TEMP_DIRECTORY,
                        new File("/tmp/" + "tmp_"+ port).getAbsolutePath())
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE).usingPort(port);
        }else{
        	builder =
                    new AppiumServiceBuilder().withAppiumJS(new File(appium_js_path))
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "info").withLogFile(new File(
                        System.getProperty("user.dir") + "/target/appiumlogs/" + deviceID.replaceAll("\\W", "_") + "__" + methodName + ".txt"))
                        .withIPAddress("127.0.0.1")
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                        .withArgument(GeneralServerFlag.TEMP_DIRECTORY,
                                new File("/tmp/" + "tmp_"+ port).getAbsolutePath())
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE).usingPort(port);
        }
        appiumDriverLocalService = builder.build();
        appiumDriverLocalService.start();
        return builder;

    }

    public URL getAppiumUrl() {
        return appiumDriverLocalService.getUrl();
    }

    public void destroyAppiumNode() {
    	if(appiumDriverLocalService!=null){
        appiumDriverLocalService.stop();
        if (appiumDriverLocalService.isRunning()) {
            System.out.println("AppiumServer didn't shut... Trying to quit again....");
            appiumDriverLocalService.stop();
        }
        System.out
        .println("**********************************************************************\n");
    System.out.println("Appium Stopped Successfully \n");
    System.out
        .println("**********************************************************************\n");
    	}
    }    
}