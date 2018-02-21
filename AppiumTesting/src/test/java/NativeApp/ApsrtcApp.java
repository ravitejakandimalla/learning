package NativeApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ApsrtcApp {
	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		// Starting the Appium server code
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				(new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files (x86)\\Appium\\node.exe"))
						.withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
						.withLogFile(new File("C:\\Users\\rkandimalla\\Desktop\\appiumlogs\\logs.txt"))));
		service.start();

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Android");
		cap.setCapability("appPackage", "com.apsrtc.online");
		cap.setCapability("appActivity", "com.abhibus.app.apsrtc.DashBoardActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		System.out.println("APSRTC is opened");
		System.out.println(driver.findElements(By.className("android.widget.ImageView")).size());
		driver.findElement(By.id("com.apsrtc.online:id/bookTextView")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.view.View[@content-desc='Book Your Ticket as Guest']")).click();
		driver.findElement(By.id("fromPlaceName")).sendKeys("BENGA");
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"BENGALURU\"]")).click();
		driver.findElement(By.id("toPlaceName")).sendKeys("VIJAYAWA");
		driver.findElement(By.xpath("//android.webkit.WebView[@content-desc=\"APSRTC Official Website for Online Bus Ticket Booking - APSRTConline.in\"]/android.view.View[2]/android.view.View/android.widget.EditText[3]")).click();
		driver.findElement(By.id("txtJourneyDate")).click();
		DateFormat dateformat=new SimpleDateFormat("dd/MM/YYYY");
		Date date=new Date();
		String sysCurrentDate=dateformat.format(date);
		System.out.println(sysCurrentDate);
		driver.findElement(By.id("txtJorneyDate")).sendKeys(sysCurrentDate);
		
		driver.findElement(By.id("searchBtn")).click();
		
		Thread.sleep(5000);
		driver.quit();
		service.stop();
	
		
	}
}
