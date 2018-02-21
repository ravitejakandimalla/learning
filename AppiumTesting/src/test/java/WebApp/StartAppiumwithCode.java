package WebApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class StartAppiumwithCode {
	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {
		String Appium_Node_Path="C:\\Program Files\\nodejs\\node.exe";
		String Appium_JS_Path="C:\\Users\\rkandimalla\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
		AppiumDriverLocalService appiumService;

		appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
			                usingAnyFreePort().usingDriverExecutable(new File(Appium_Node_Path)).
			                withAppiumJS(new File(Appium_JS_Path)));
		appiumService.start();
		
						
		
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("app", "C:\\Users\\rkandimalla\\Downloads\\selendroid-test-app.apk");
		cap.setCapability("appPackage", "io.selendroid.testapp");
		cap.setCapability("appActivity", ".HomeScreenActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	
		//System.out.println(driver.findElements(By.className("android.widget.Button")).size());
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Appium Selendroid..");
		driver.hideKeyboard();
		System.out.println("Buttons displayed in screen: "+driver.findElements(By.className("android.widget.Button")).size());
		java.util.List<MobileElement> btn=driver.findElements(By.className("android.widget.Button"));
		for(MobileElement button:btn) {
			if(button.getAttribute("text").contains("Display text view")) {
				button.click();
			}
		}
		System.out.println(driver.findElement(By.xpath("(//android.widget.LinearLayout[@content-desc=\"visibleTestAreaCD\"])[1]/android.widget.TextView")).getText());	
		driver.closeApp();
		appiumService.stop();
	}
}
