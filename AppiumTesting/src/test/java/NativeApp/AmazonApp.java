package NativeApp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class AmazonApp {

	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws InterruptedException, IOException {

		//File app = new File("C:\\Users\\rkandimalla\\Downloads\\amazonIndia.apk");
		DesiredCapabilities cap = new DesiredCapabilities();

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "2394b357");
		capabilities.setCapability("platformVersion", "8.0.0");
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.findElement(By.id("in.amazon.mShop.android.shopping:id/sso_continue")).click();
		driver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text")).click();
		driver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text")).sendKeys("tvs");
		driver.pressKeyCode(AndroidKeyCode.ENTER);
		
		List<MobileElement> names = driver.findElements(By.id("in.amazon.mShop.android.shopping:id/item_title"));

		try {
			while (true) {

				for (MobileElement name : names) {

					if (name.getText().startsWith("TVS BSC-101 STA")) {

						name.click();
						break;
					}

				}

				driver.swipe(500, 1900, 500, 200, 5000);
			}
		} catch (Throwable t) {
				
		}
	
		Screenshot.takeScreenShot();
		Thread.sleep(4000);
		driver.quit();
	}

}
