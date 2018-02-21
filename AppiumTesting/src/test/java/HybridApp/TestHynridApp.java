package HybridApp;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestHynridApp {
	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws Exception {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability("platformName", "Android");
			cap.setCapability("deviceName", "emulator-5554");
			cap.setCapability("platformVersion", "7.0");
			// cap.setCapability("app", "C:\\Users\\rkandimalla\\Downloads\\HTML5test
			// WebView_v1.0.2_apkpure.com.apk");
			cap.setCapability("appPackage", "com.html5test.webview");
			cap.setCapability("appActivity", "main.java.MainActivity");
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//android.view.View[@content-desc=\"compare\"]")).click();

			String text = "Embedding custom non-visible data";
		
			AndroidElement list=(AndroidElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\"location and orientation\"]"));
			MobileElement listGroup=list.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+"new UiSelector().textContains(\""+text+"\"));"));
			listGroup.click();
			
			System.out.println("Clicked");

		} catch (Exception e) {
			System.out.println(e);
		}
		Thread.sleep(4000);
		driver.quit();
	}
}
