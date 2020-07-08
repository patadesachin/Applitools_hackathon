package com.applitools.quickstarts.ModernTestsV2;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AppTestTask1 {

	public static void main(final String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver\\chromedriver.exe");
		// Create a new chrome web driver
		final WebDriver webDriver = new ChromeDriver();

		// Create a runner with concurrency of 1
		final VisualGridRunner runner = new VisualGridRunner(10);

		// Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
		final Eyes eyes = new Eyes(runner);

		setUp(eyes);

		try {
			// ⭐️ Note to see visual bugs, run the test using the above URL for the 1st run.
			// but then change the above URL to https://demo.applitools.com/index_v2.html
			// (for the 2nd run)
			ultraFastTest(webDriver, eyes);

		} finally {
			tearDown(webDriver, runner);
		}

	}

	public static void setUp(final Eyes eyes) {

		// Initialize eyes Configuration
		final Configuration config = new Configuration();

		// You can get your api key from the Applitools dashboard
		config.setApiKey("N98lg8lMVAnn60t6VS7aaGGb70qETqZHRaVq1sOUDVSk110");

		// create a new batch info instance and set it to the configuration
		config.setBatch(new BatchInfo("UFG--Hackathon"));
		// config.setTestName("Task-1");
		config.setTestName("Task-1");
		// Add browsers with different viewports
		config.addBrowser(1200, 700, BrowserType.CHROME);
		config.addBrowser(1200, 700, BrowserType.FIREFOX);
		config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
		config.addBrowser(768, 700, BrowserType.CHROME);
		config.addBrowser(768, 700, BrowserType.FIREFOX);
		config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);

		// Add mobile emulation devices in Portrait mode
		config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);

		// Set the configuration object to eyes
		eyes.setConfiguration(config);
	}

	public static void ultraFastTest(final WebDriver webDriver, final Eyes eyes) throws InterruptedException {

		try {

			// Navigate to the url we want to test
			webDriver.get("https://demo.applitools.com/gridHackathonV1.html");

		
			  // Call Open on eyes to initialize a test session 
			  eyes.open(webDriver,"Applitools", "Task-1", new RectangleSize(800, 600));
			 
			 // check the login page with fluent api, see more info here 
			 // https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html 
			//  eyes.check(Target.window().fully().withName("Login page")); 
			// webDriver.findElement(By.id("log-in")).click();
			 
		
			 // Check the app page eyes.check(); 
			 eyes.check("Cross-Device Elements Test", Target.window().fully().withName("Applitools UltraFastGrid | Cross Browser Testing Demo App"));
			 
			 
			 // Call Close on eyes to let the server know it should display the results
			 eyes.closeAsync();
			 
		} finally {
			eyes.abortAsync();
		}

	}

	private static void tearDown(final WebDriver webDriver, final VisualGridRunner runner) {
		// Close the browser
		webDriver.quit();

		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		final TestResultsSummary allTestResults = runner.getAllTestResults(false);
		System.out.println(allTestResults);
	}

}