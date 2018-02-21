package NativeApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DragSort {
	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		// Starting the Appium server code
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				(new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files (x86)\\Appium\\node.exe"))
						.withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
						.withLogFile(new File("C:\\Users\\rkandimalla\\Desktop\\appiumlogs\\logs.txt"))));
		service.start();

		File app = new File("C:\\Users\\rkandimalla\\Downloads\\Drag Sort Demos_v0.5.0_apkpure.com.apk");
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Android");
		cap.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		driver.manage().timeouts().implicitlyWait(10L,TimeUnit.SECONDS);
		//driver.findElements(By.id("com.mobeta.android.demodslv:id/activity_title")).get(0).click();
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Cursor')]")).click();
		WebDriverWait wait= new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mobeta.android.demodslv:id/text")));
		System.out.println(driver.getPageSource());
		MobileElement drag=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Brad')]/preceding-sibling::android.widget.ImageView"));
		MobileElement drop=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Wayne')]/preceding-sibling::android.widget.ImageView"));
		
		TouchAction action=new TouchAction(driver);
		action.longPress(drag).moveTo(drop).release().perform();
		System.out.println("elements are draged");
		Thread.sleep(5000);
		driver.quit();
		service.stop();

	}
}
