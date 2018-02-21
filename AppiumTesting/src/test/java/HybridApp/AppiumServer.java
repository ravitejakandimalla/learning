package HybridApp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.altimetrik.appiumserver.AppiumManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
	static AppiumDriver<WebElement> driver;
	static String NodePath = "C:\\Program Files\\nodejs\\node.exe";
	static String AppiumJS_Path = "C:\\Users\\rkandimalla\\AppData\\Local\\Programs\\appium-desktop\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	static String LogFilePath = "C:\\Users\\rkandimalla\\Desktop\\Logs\\AppiumLogs.txt";
	static AppiumDriverLocalService service;
	static SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
	static AppiumManager appiumManager=new AppiumManager();

	@BeforeTest
	public void startServer() throws Exception {
//		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//				.usingDriverExecutable(new File(NodePath)).withAppiumJS(new File(AppiumJS_Path))
//				.withIPAddress("127.0.0.1").usingPort(4728).withLogFile(new File(LogFilePath)));
//		
//		System.out.println("Starting the Appium Server...."+"\n"+df.format(new Date()));
//		
//		service.start();
		appiumManager.appiumServerForAndroid("07cb1460d08aee66", "E:\\appium-1.5\\node_modules\\appium\\build\\lib\\main.js");
		Thread.sleep(5000);
	}
	@org.testng.annotations.Test(priority=1)
	public void testServer() {
//		System.err.println("Is Server running: "+service.isRunning());

	}
	@Test(priority=2)
	public static void selendroidApp() throws InterruptedException {


		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "07cb1460d08aee66");
		cap.setCapability("platformVersion", "5.1");
		cap.setCapability("app", "C:\\Users\\rkandimalla\\Downloads\\selendroid-test-app.apk");
		cap.setCapability("appPackage", "io.selendroid.testapp");
		cap.setCapability("appActivity", ".HomeScreenActivity");
		driver=new AndroidDriver<>(appiumManager.getAppiumUrl(), cap);
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Appium");
		driver.findElement(By.id("io.selendroid.testapp:id/input_adds_check_box")).click();
		driver.findElement(By.id("io.selendroid.testapp:id/visibleButtonTest")).click();
		Thread.sleep(4000);
		driver.quit();

	}
	@AfterTest
	public void stopServer() {
		/*if(service.isRunning()==true) {
		service.stop();
		System.out.println("Appium Server was stopped");
		}*/
//		driver.quit();
		appiumManager.destroyAppiumNode();
	}
		
}
