package WebApp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestDeployApp {
	public static AndroidDriver<WebElement> driver;

	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {

		// Starting the Appium server code
		AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService((new AppiumServiceBuilder()
						.usingDriverExecutable(
								new File(
										"C:\\Program Files (x86)\\Appium\\node.exe"))
						.withAppiumJS(
								new File(
										"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
						.withLogFile(new File(
								"C:\\Users\\rkandimalla\\Desktop\\appiumlogs\\logs.txt"))));
		service.start();
		
		File app=new File("C:\\Users\\rkandimalla\\Desktop\\Apks\\BlablaCar.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("deviceName", "Android");
		cap.setCapability("app",app.getAbsolutePath());
		// if app is already installed in mobile
//		cap.setCapability("appPackage", "io.selendroid.testapp");
//		cap.setCapability("appActivity", ".HomeScreenActivity");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), cap);
		
		Thread.sleep(5000);
		driver.quit();
		service.stop();
		
	}
}
