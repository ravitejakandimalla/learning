package WebApp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class DropdownsWebapp {
	public static AndroidDriver<WebElement> driver;

	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "android");

		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.get("http://wikipedia.org");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.id("searchLanguage"));
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText("Latina");

		List<WebElement> values = driver.findElements(By.tagName("option"));
		System.out.println(values.size());
		for (WebElement value : values) {
			System.out.println(value.getAttribute("lang"));
		}

		Thread.sleep(3000);

		driver.quit();
	}
}
