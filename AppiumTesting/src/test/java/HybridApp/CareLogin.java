package HybridApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CareLogin {
	
	public static AndroidDriver<WebElement> driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
DesiredCapabilities cap = new DesiredCapabilities();
		
		//File app=new File("C:\\Users\\rkandimalla\\Downloads\\com.care.android.careview.5.7.1.apk");
		cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.0");
		//cap.setCapability("app",app.getAbsolutePath());
		// if app is already installed in mobile
		cap.setCapability("appPackage", "com.care.android.careview");
		cap.setCapability("appActivity", "com.care.android.careview.ui.startup.VisitorHomeActivity");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), cap);
		
		driver.findElement(By.id("com.care.android.careview:id/sign_up_email")).click();
		driver.findElement(By.id("com.care.android.careview:id/seeker_enrollment_layout")).click();
		
		
		
		Thread.sleep(5000);
		driver.quit();
	}

}
