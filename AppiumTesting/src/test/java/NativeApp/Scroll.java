package NativeApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Scroll {

	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		// File app = new
		// File("C:\\Users\\rkandimalla\\Downloads\\multitouchpro.tests.apk");
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Android");
		// cap.setCapability("app", app.getAbsolutePath());
		cap.setCapability("appPackage", "com.android.contacts");
		cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		// scroll function
		String text = "Tek";
		// driver.findElementByAndroidUIAutomator("new UiScrollable(new
		// UiSelector().resourceId(\"android:id/list\")).scrollIntoView(new
		// UiSelector.textContains(\""+text+"\"))").click();
		/*MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/tab_recycler_view\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.TextView\"), \"Games We Are Playing\")"));

		// Perform the action on the element
		element.click();*/
		driver.findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));")).click();
		Thread.sleep(5000);
		driver.quit();
	}
}
