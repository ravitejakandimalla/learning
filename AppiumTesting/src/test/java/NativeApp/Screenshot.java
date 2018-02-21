package NativeApp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.altimetrik.appiumserver.AppiumManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Screenshot {

	public static AndroidDriver<MobileElement> driver;
	private static String destDir;
	private static DateFormat dateFormat;
	
	public static void takeScreenShot() throws IOException {
		//directory
		destDir="screenshots";
		File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//Set date
		dateFormat=new SimpleDateFormat("dd-MMM-yy__hh_mm_ssaa");
		//create folder
		new File(destDir).mkdir();
		
		String destFile=dateFormat.format(new Date())+".png";
		
		FileUtils.copyFile(scrFile,new File(destDir+"/"+destFile));
		
	}

	public static void main(String[] args) throws Exception {

		
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "2394b357");
		cap.setCapability("platformVersion", "8.0.0");
		cap.setCapability("app", "C:\\Users\\rkandimalla\\Downloads\\selendroid-test-app.apk");
		cap.setCapability("appPackage", "io.selendroid.testapp");
		cap.setCapability("appActivity", ".HomeScreenActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		takeScreenShot();
		Thread.sleep(5000);
		driver.quit();
		
	}
}
