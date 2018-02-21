package HybridApp;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Sleeper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StartAppium {
	
public static AppiumDriverLocalService service;
public static AndroidDriver<WebElement> driver;	
	public static void main(String[] args) throws InterruptedException, IOException {
		 service = AppiumDriverLocalService
		            .buildService(new AppiumServiceBuilder()
		                    .usingDriverExecutable(new File("C:\\Program Files (x86)\\Appium\\node.exe"))
		                    .withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js")));
		    service.start();

		Thread.sleep(5000);
		DesiredCapabilities cap=new DesiredCapabilities();
		System.out.println("Server was started");
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
		service.stop();
		System.out.println("Server was stopped");
	}

}
