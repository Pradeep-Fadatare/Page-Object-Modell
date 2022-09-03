package com.newproject.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class DriverInitialising {

    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static FileReader fr;
    public static ExtentReports extentReports;
    public static File file;
    public static ExtentTest extentTest;
    public static String screenshotsubfoldername;

    public DriverInitialising() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\src\\main\\java\\com\\newproject\\config\\config.properties");
        prop.load(fr);
    }
    @BeforeSuite
    public void initialiseExtentReport(){
        extentReports=new ExtentReports();
        file=new File("new.html");
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
        ExtentSparkReporter sparkReporter_failed=new ExtentSparkReporter("failed.html");
        sparkReporter_failed.filter().statusFilter().as(new Status[]{Status.FAIL}).apply();
        extentReports.attachReporter(sparkReporter, sparkReporter_failed);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @AfterSuite
    public void CloseRepoter() throws IOException {
        extentReports.flush();
        Desktop.getDesktop().browse(new File("newone.html").toURI());
        Desktop.getDesktop().browse(new File("failed.html").toURI());
    }
    @BeforeMethod
    public void setup(ITestContext context) {
        String BrowserName=prop.getProperty("browser");
        if (BrowserName.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(prop.getProperty("testurl"));
           extentTest.info("Navigate to URL");
        }else if(BrowserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(BrowserName.equals("firefox")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else {
            System.out.println("Not able to Instantiate class");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
        String device= capabilities.getBrowserName()+ "  " + capabilities.getBrowserVersion();
        extentTest=extentReports.createTest(context.getName());
        extentTest.assignAuthor("Pradeep");
        extentTest.assignDevice(device);
    }

    public static String CaptureScreenshot(String Filename) throws IOException {
        if(screenshotsubfoldername ==null){
            LocalDateTime ldt=LocalDateTime.now();
            DateTimeFormatter myformatobj=DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String screenshotsubfoldername= ldt.format(myformatobj);
        }

        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File src=new File("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\src\\test\\ScreenShots\\" + Filename+ screenshotsubfoldername+".jpg");
        FileUtils.copyFile(file,src);
        System.out.println("ScreenshotCaptured");
        return src.getAbsolutePath();
    }

    @AfterMethod
    public void teardown(Method m, ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            String pathname=CaptureScreenshot(result.getTestContext().getName());
            extentTest.addScreenCaptureFromPath(pathname);
            extentTest.fail(result.getThrowable());

        }else if(result.getStatus() == ITestResult.SUCCESS){
            extentTest.pass(m.getName() + "is passed");
        }
        //extentTest.assignCategory(m.getAnnotation(Test.class).groups());
        driver.quit();
    }
}
