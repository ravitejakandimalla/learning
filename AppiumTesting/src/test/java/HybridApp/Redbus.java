package HybridApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Redbus {

	public static AndroidDriver<WebElement> driver;

	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {

		File app=new File("C:\\Users\\rkandimalla\\Desktop\\Apks\\BlablaCar.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("app",app.getAbsolutePath());
		// if app is already installed in mobile
		cap.setCapability("appPackage", "com.comuto");
		cap.setCapability("appActivity", "com.comuto.proxy.ProxyActivity");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), cap);
		
		Thread.sleep(5000);
		driver.quit();
		
		
	}
}


