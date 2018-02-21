package NativeApp;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SelendroidAppTest {
	public static AndroidDriver<WebElement> driver;
	
//	static String NodePath = "C:\\Program Files\\nodejs\\node.exe";
//	static String AppiumJS_Path = "C:\\Users\\rkandimalla\\AppData\\Local\\Programs\\appium-desktop\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
//	static String LogFilePath = "C:\\Users\\rkandimalla\\Desktop\\Logs\\AppiumLogs.txt";
//	static AppiumDriverLocalService service;
//	static SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
	
	@Test
	public static void selendroidApp() throws InterruptedException, MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("app", "C:\\Users\\rkandimalla\\Downloads\\selendroid-test-app.apk");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), cap);
		cap.setCapability("appPackage", "io.selendroid.testapp");
		cap.setCapability("appActivity", ".HomeScreenActivity");
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Appium");
		driver.findElement(By.id("io.selendroid.testapp:id/input_adds_check_box")).click();
		driver.findElement(By.id("io.selendroid.testapp:id/visibleButtonTest")).click();
		Thread.sleep(4000);
		driver.quit();

	}
}
