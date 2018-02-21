package NativeApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class MultiTouch {

	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File app = new File("C:\\Users\\rkandimalla\\Downloads\\multitouchpro.tests.apk");
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Android");
		cap.setCapability("app", app.getAbsolutePath());
		cap.setCapability("appPackage","multitouchpro.tests");
		cap.setCapability("appActivity","Multitouch");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		
		TouchAction action1=new TouchAction(driver).tap(799,745).waitAction(2000);
		TouchAction action2=new TouchAction(driver).tap(268, 1163).waitAction(2000);
		TouchAction action3=new TouchAction(driver).tap(925,2015);
		
		new MultiTouchAction(driver).add(action1).add(action2).add(action3).perform();
		System.out.println("Multitouch action are performed");
		
		Thread.sleep(4000);
		driver.quit();
	}
}
